package com.xuexiang.util.system;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.common.StringUtil;

/**
 * 蓝牙匹配辅助类（单例）
 * 
 * @author xx
 * @Date 2016-12-21 下午10:27:02
 */
public class BluetoothHelper {

	private Context mContext;
	private static volatile BluetoothHelper sBluetoothHelper;
	/**
	 * 蓝牙广播接收器
	 */
	private volatile BlueToothReceiver mReceiver;
	/**
	 * 是否需要注销广播
	 */
	private boolean mNeed2unRegister = false;

	/**
	 * 系统蓝牙适配器
	 */
	private BluetoothAdapter mBluetoothAdapter;
	/**
	 * 已绑定蓝牙设备集合
	 */
	private List<BluetoothDevice> mBondedList;
	/**
	 * 新发现的蓝牙设备集合（未绑定）
	 */
	private List<BluetoothDevice> mNewList;

	/**
	 * 蓝牙设备监听器
	 */
	private OnBluetoothDeviceListener mListener;
	/**
	 * 设备过滤器
	 */
	private DeviceFilter mDeviceFilter;

	// ================================================初始化================================================//

	public static BluetoothHelper getInstance(Context context) {
		if (sBluetoothHelper == null) {
			synchronized (BluetoothHelper.class) {
				if (sBluetoothHelper == null)
					sBluetoothHelper = new BluetoothHelper(context);
			}
		}
		return sBluetoothHelper;
	}

	/**
	 * private constructor for singleton
	 * 
	 * @param context
	 *            context
	 */
	private BluetoothHelper(Context context) {
		mContext = context.getApplicationContext();
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	/**
	 * 蓝牙广播接收器【接收绑定状态变化、发现新设备、扫描结束的广播】
	 * 
	 * @author xx
	 * 
	 */
	private class BlueToothReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (isCorrectDevice(device)) {
					if (mListener != null) {
						mListener.onNewDeviceFound(device);
					}
					if (device.getBondState() == BluetoothDevice.BOND_NONE) {
						mNewList.add(device);
					} else if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
						mBondedList.add(device);
					}
				}

			} else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (mListener != null) {
					mListener.onBondStateChanged(device, intent);
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				if (mListener != null) {
					mListener.onSearchCompleted(mBondedList, mNewList);
				}
			}
		}
	}

	// ================================================蓝牙开启================================================//
	/**
	 * 注册蓝牙广播接收器
	 */
	public void registerBTReceiver(Context context) {
		if (mReceiver == null) {
			mReceiver = new BlueToothReceiver();
		}
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		context.getApplicationContext().registerReceiver(mReceiver, intentFilter);
		mNeed2unRegister = true;
	}

	/**
	 * 开启蓝牙
	 * 
	 * @param isReOpen
	 *            是否是重新打开蓝牙
	 */
	public void openBluetooth(final boolean isReOpen) {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			boolean openResult;

			@Override
			public void run() {
				if (isReOpen) {
					openResult = reOpenBluetooth();
				} else {
					openResult = openBluetooth();
				}
				if (openResult) {
					if (mListener != null) {
						if (isReOpen) {
							mListener.onBluetoothReOpened();
						} else {
							mListener.onBluetoothOpened();
						}
					}
				}
			}
		});
	}

	/**
	 * 开启蓝牙【耗时操作】
	 */
	private boolean openBluetooth() {
		boolean result = false;
		if (mBluetoothAdapter != null) {
			if (mBluetoothAdapter.isEnabled()) {
				return true;
			} else {
				result = enableBluetooth();
			}
		}
		return result;
	}

	/**
	 * 重新开启蓝牙【耗时操作】
	 */
	private boolean reOpenBluetooth() {
		if (mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.disable(); // 关闭蓝牙
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return enableBluetooth();
	}

	/**
	 * 打开蓝牙开关【耗时操作】
	 * 
	 * @return
	 */
	private boolean enableBluetooth() {
		boolean result = false;
		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.enable(); // 重新开启蓝牙
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (mBluetoothAdapter.isEnabled()) {
				result = true;
			}
		}
		return result;
	}

	// ================================================蓝牙扫描================================================//
	/**
	 * 开始扫描蓝牙
	 */
	public void startSearch() {
		stopSearch();
		mBluetoothAdapter.startDiscovery();
	}

	/**
	 * 停止扫描蓝牙
	 */
	public void stopSearch() {
		if (mBluetoothAdapter == null) {
			return;
		}
		if (mBluetoothAdapter.isDiscovering()) {
			mBluetoothAdapter.cancelDiscovery();
		}
	}

	/**
	 * 扫描蓝牙设备
	 * 
	 * @param listener
	 *            蓝牙设备监听器
	 */
	public void searchDevices(OnBluetoothDeviceListener listener) {
		mListener = listener;
		if (mBluetoothAdapter == null) {
			return;
		}
		registerBTReceiver(mContext);

		if (mBondedList == null) {
			mBondedList = new ArrayList<BluetoothDevice>();
		}
		if (mNewList == null) {
			mNewList = new ArrayList<BluetoothDevice>();
		}
		mBondedList.clear();
		mNewList.clear();

		startSearch();
		if (mListener != null) {
			mListener.onStartDiscovery();
		}
	}

	// ================================================蓝牙匹配================================================//
	/**
	 * 弹出蓝牙连接的方法
	 * 
	 * @param btDevice
	 * @return
	 * @throws Exception
	 */
	public boolean createBind(BluetoothDevice btDevice) throws Exception {
		Method createBondMethod = btDevice.getClass().getMethod("createBond");
		Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
		return returnValue.booleanValue();
	}

	/**
	 * 蓝牙匹配的方法
	 * 
	 * @param btDevice
	 * @return
	 * @throws Exception
	 */
	@SuppressLint("NewApi")
	public boolean createBind(BluetoothDevice btDevice, String pingCode) {
		btDevice.setPin(pingCode.getBytes());
		return btDevice.createBond();
	}

	/**
	 * 蓝牙匹配的方法
	 * 
	 * @param btDevice
	 * @return
	 * @throws Exception
	 */
	@SuppressLint("NewApi")
	public boolean pairBtDevice(String address) {
		boolean result = false;
		stopSearch();
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}
		if (BluetoothAdapter.checkBluetoothAddress(address)) { // 检查蓝牙地址是否有效
			BluetoothDevice device = getBluetoothDevice(address);
			if (isCorrectDevice(device)) {
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
					result = device.createBond();
				} else {
					result = true;
				}
			}
		}
		return result;
	}

	// ================================================状态获取================================================//
	/**
	 * 是否已经开启蓝牙
	 * 
	 * @return
	 */
	public boolean isOpenBluetooth() {
		return mBluetoothAdapter.isEnabled();
	}

	/**
	 * 检验蓝牙地址的有效性
	 * 
	 * @param address
	 *            蓝牙地址
	 * @return true:有效，false:无效
	 */
	public boolean isBtAddressVaild(String address) {
		return !StringUtil.isEmpty(address) && BluetoothAdapter.checkBluetoothAddress(address);
	}

	/**
	 * 蓝牙设备是否正确
	 * 
	 * @param address
	 * @return
	 */
	public boolean isCorrectDevice(String address) {
		return isCorrectDevice(getBluetoothDevice(address));
	}

	/**
	 * 是否是指定的蓝牙设备
	 * 
	 * @param device
	 * @return
	 */
	public boolean isCorrectDevice(BluetoothDevice device) {
		boolean isCorrectDevice = true;
		if (mDeviceFilter != null) {
			isCorrectDevice = mDeviceFilter.isCorrect(device.getName());
		}
		return isCorrectDevice;
	}

	/**
	 * 蓝牙是否已绑定
	 * 
	 * @param address
	 * @return
	 */
	public boolean isBlueToothBond(String address) {
		BluetoothDevice device = getBluetoothDevice(address);
		if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
			return true;
		} else {
			return false;
		}
	}

	// ================================================对象销毁================================================//
	/**
	 * Closes the connection and releases any system resources associated with
	 * the stream.
	 */
	public void close() {
		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.cancelDiscovery();
		}

		// unregister
		if (mNeed2unRegister) {
			mContext.unregisterReceiver(mReceiver);
			mNeed2unRegister = false;
		}

		mNewList = null;
		mBondedList = null;
		mListener = null;
		mReceiver = null;
		mDeviceFilter = null;
	}

	// ================================================set/get================================================//

	/**
	 * 设备蓝牙设备监听
	 * 
	 * @param listener
	 */
	public void setOnBluetoothDeviceListener(OnBluetoothDeviceListener listener) {
		mListener = listener;
	}

	/**
	 * 设置蓝牙设备过滤器
	 * 
	 * @param deviceFilter
	 */
	public void setDeviceFilter(DeviceFilter deviceFilter) {
		mDeviceFilter = deviceFilter;
	}

	public DeviceFilter getDeviceFilter() {
		return mDeviceFilter;
	}

	public BluetoothAdapter getBluetoothAdapter() {
		return mBluetoothAdapter;
	}

	/**
	 * 根据地址获取蓝牙设备
	 * 
	 * @param address
	 * @return
	 */
	public BluetoothDevice getBluetoothDevice(String address) {
		if (mBluetoothAdapter != null) {
			return mBluetoothAdapter.getRemoteDevice(address);
		} else {
			return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
		}
	}

	/**
	 * --------------------------------------------接口回调------------------------
	 * --------------------------------
	 **/
	/**
	 * 蓝牙设备监听器
	 * 
	 * @author xx
	 * 
	 */
	public interface OnBluetoothDeviceListener extends OnSearchDeviceListener {
		/**
		 * 蓝牙绑定状态发生改变
		 */
		void onBondStateChanged(BluetoothDevice device, Intent intent);

		/**
		 * 蓝牙打开监听
		 */
		void onBluetoothOpened();

		/**
		 * 蓝牙重新打开监听
		 */
		void onBluetoothReOpened();
	}

	/**
	 * 设备扫描的监听
	 * 
	 * @author xx
	 * 
	 */
	public interface OnSearchDeviceListener {

		/**
		 * 开始扫描设备
		 */
		void onStartDiscovery();

		/**
		 * 发现一个新设备时回调
		 * 
		 */
		void onNewDeviceFound(BluetoothDevice device);

		/**
		 * 扫描设备结束时回调
		 * 
		 */
		void onSearchCompleted(List<BluetoothDevice> bondedList, List<BluetoothDevice> newList);

	}

	/**
	 * 设备过滤器（过滤蓝牙设备名称）
	 * 
	 * @author xx
	 * 
	 */
	public interface DeviceFilter {
		/**
		 * 是否是指定的蓝牙设备
		 * 
		 * @param deviceName
		 *            蓝牙设备的名称
		 * @return
		 */
		boolean isCorrect(String deviceName);

	}
}
