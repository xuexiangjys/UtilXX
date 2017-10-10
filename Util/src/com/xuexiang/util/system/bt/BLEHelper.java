package com.xuexiang.util.system.bt;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.CountDownTimer;

import com.xuexiang.util.app.ThreadPoolManager;

/** 
 * 低功耗蓝牙连接助手
 * @author xx
 * @Date 2017-10-10 上午10:49:39 
 */
@SuppressLint("NewApi") 
public class BLEHelper{
	private static volatile BLEHelper sBluetoothHelper;
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
	 * 蓝牙设备扫描监听器
	 */
	private OnSearchDeviceListener mListener;
	/**
	 * 设备过滤器
	 */
	private DeviceFilter mDeviceFilter;
	
	private BLESearchCountDownTimer mTimeOutTimer;
	
	/**
	 * 扫描超时时间（默认30秒）
	 */
	private long mTimeout = 30;
	
	// ================================================初始化================================================//

	public static BLEHelper getInstance() {
		if (sBluetoothHelper == null) {
			synchronized (BLEHelper.class) {
				if (sBluetoothHelper == null)
					sBluetoothHelper = new BLEHelper();
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
	private BLEHelper() {
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}
		
	/**
	 * BLE扫描回调
	 */
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
		@Override
		public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
			if (isCorrectDevice(device)) {
				if (device.getBondState() == BluetoothDevice.BOND_NONE) {
					if (mNewList != null) {
						mNewList.add(device);
					}
				} else if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
					if (mBondedList != null) {
						mBondedList.add(device);
					}
				}
				if (mListener != null) {
					if (mListener.onNewDeviceFound(device)) {
						stopSearch();
						mListener.onSearchCompleted(mBondedList, mNewList);
					}
				}
			}
		}
	};
	
	/**
	 * 设置扫描超时时间
	 * @param timeout
	 */
	public void setTimeout(long timeout) {
		mTimeout = timeout;
	}
	
	/**
	 * 低功耗蓝牙扫描倒计时
	 * @author XUE
	 *
	 */
	public class BLESearchCountDownTimer extends CountDownTimer {

		public BLESearchCountDownTimer(long timeout) {
			super(timeout * 1000, 1000);
		}
		@Override
		public void onTick(long millisUntilFinished) {}
		
		@Override
		public void onFinish() {
			stopSearch();
			if (mListener != null) {
				mListener.onSearchCompleted(mBondedList, mNewList);
			}
		}
	}
	
	 /**
     * 开始扫描倒计时
     */
    public void startScanListener() {
        if (mTimeOutTimer == null) {
        	mTimeOutTimer = new BLESearchCountDownTimer(mTimeout);
        }
        mTimeOutTimer.start();
    }

    /**
     * 取消倒计时
     */
    public void cancelScanListener() {
        if (mTimeOutTimer != null) {
        	mTimeOutTimer.cancel();
        	mTimeOutTimer = null;
        }
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
	
	// ================================================蓝牙开启================================================//
	/**
	 * 开启蓝牙
	 * 
	 * @param isReOpen
	 *            是否是重新打开蓝牙
	 */
	public void openBluetooth() {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				boolean openResult = getOpenBluetoothResult();
				if (openResult) {
					if (mListener != null) {
						mListener.onBluetoothOpened();
					}
				}
			}
		});
	}

	/**
	 * 获取开启蓝牙的结果【耗时操作】
	 */
	private boolean getOpenBluetoothResult() {
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
		mBluetoothAdapter.startLeScan(mLeScanCallback);
		startScanListener();
	}

	/**
	 * 停止扫描蓝牙
	 */
	public void stopSearch() {
		if (mBluetoothAdapter == null) {
			return;
		}
		mBluetoothAdapter.stopLeScan(mLeScanCallback);
		cancelScanListener();
	}

	/**
	 * 扫描蓝牙设备
	 * 
	 * @param listener
	 *            蓝牙设备监听器
	 */
	public void searchDevices(OnSearchDeviceListener listener) {
		mListener = listener;
		if (mBluetoothAdapter == null) {
			return;
		}
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
	
	// ================================================对象销毁================================================//
	/**
	 * Closes the connection and releases any system resources associated with
	 * the stream.
	 */
	public void close() {
		stopSearch();

		mNewList = null;
		mBondedList = null;
		mListener = null;
		mDeviceFilter = null;
	}

	// ================================================set/get================================================//
	/**
	 * 是否已经开启蓝牙
	 * 
	 * @return
	 */
	public boolean isOpenBluetooth() {
		return mBluetoothAdapter.isEnabled();
	}
	
	/**
	 * 设备蓝牙设备扫描监听
	 * 
	 * @param listener
	 */
	public void setOnSearchDeviceListener(OnSearchDeviceListener listener) {
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
		 * @param device
		 * @return true：已发现需要的设备，无需继续扫描
		 */
		boolean onNewDeviceFound(BluetoothDevice device);

		/**
		 * 扫描设备结束时回调
		 * 
		 */
		void onSearchCompleted(List<BluetoothDevice> bondedList, List<BluetoothDevice> newList);
		
		/**
		 * 蓝牙打开监听
		 */
		void onBluetoothOpened();

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


