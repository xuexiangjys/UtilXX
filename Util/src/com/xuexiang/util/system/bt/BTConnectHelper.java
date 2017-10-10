package com.xuexiang.util.system.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.common.StringUtil;
import com.xuexiang.util.log.LogHelper;
import com.xuexiang.util.system.bt.BluetoothHelper.DeviceFilter;
import com.xuexiang.util.system.bt.BluetoothHelper.OnBluetoothDeviceListener;
import com.xuexiang.util.view.DialogUtil;


/**
 * 蓝牙设备连接助手
 * 
 * @author xx
 * @Date 2017-2-28 下午2:12:58
 */
public abstract class BTConnectHelper implements OnBluetoothDeviceListener {
	protected Context mContext;
	/**
	 * 蓝牙匹配辅助类（单例）
	 */
	protected BluetoothHelper mBluetoothHelper;
	/**
	 * 保存扫描到的蓝牙设备
	 */
	private List<BluetoothDevice> mBondedList = new ArrayList<BluetoothDevice>();
	/**
	 * 扫描蓝牙设备的loading框
	 */
	private Dialog mLoadingDialog;
	/**
	 * 是否已有匹配的蓝牙设备
	 */
	protected boolean mIsBonded = false;
	/**
	 * 二代平板蓝牙扫描结束后会发两次ACTION_DISCOVERY_FINISHED广播，该参数就是为了过滤该广播
	 */
	private boolean isDeviceBindDialogShow = false;
	/**
	 * 配置的需要需要连接的蓝牙的地址
	 */
	protected String mBtAddress;
	
	/**
	 * 连接蓝牙采集盒监听
	 */
	protected OnConnectDeviceListener mOnConnectDeviceListener;

	// ================================================设备初始化================================================//
	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 */
	public BTConnectHelper(Context context) {
		mContext = context;
		mBluetoothHelper = BluetoothHelper.getInstance(context);
		mBluetoothHelper.close();
		mBluetoothHelper.setOnBluetoothDeviceListener(this);
	}

	/**
	 * 开启蓝牙
	 * 
	 * @return
	 */
	public void OpenBluetooth() {
		mBluetoothHelper.openBluetooth(false);
	}

	// ===============================蓝牙检验=====================================//
	/**
	 * 检查设备是否绑定 如果没绑定就扫描设备
	 */
	public void checkPairedDevice() {
		if (mBluetoothHelper.isBtAddressVaild(mBtAddress)) {
			checkPairedDevice(mBtAddress);
		} else {
			checkPairedDevice(mBluetoothHelper.getDeviceFilter());
		}
	}

	/**
	 * 检查设备是否绑定[地址输入正确的判断] 如果没绑定就扫描设备
	 */
	private void checkPairedDevice(String btAddress) {
		if (mBluetoothHelper.isBtAddressVaild(btAddress)) {
			mIsBonded = mBluetoothHelper.isBlueToothBond(btAddress) && mBluetoothHelper.isCorrectDevice(btAddress);
			onDeviceCheckFinished();
		} else {
			Toast("请设置正确的蓝牙地址！");
		}
	}

	/**
	 * 检查设备是否绑定[地址输入不正确的判断] 如果没绑定就扫描设备
	 */
	private void checkPairedDevice(DeviceFilter deviceFilter) {
		if (deviceFilter != null) {
			Set<BluetoothDevice> pairedDevices = mBluetoothHelper.getBluetoothAdapter().getBondedDevices();
			for (BluetoothDevice device : pairedDevices) {
				if (deviceFilter.isCorrect(device.getName())) {
					mIsBonded = true;
					mBondedList.add(device);
				}
			}
			if (mBondedList.size() > 1) {
				showBtDevice(mBondedList, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						mBtAddress = mBondedList.get(which).getAddress();
						onDeviceCheckFinished();
					}
				});
			} else {
				if (mBondedList.size() == 1) {
					mBtAddress = mBondedList.get(0).getAddress();
				}
				onDeviceCheckFinished();
			}
		}
	}

	private void onDeviceCheckFinished() {
		if (mIsBonded) {
			onDeviceBonded(getBluetoothDevice());
		} else {
			onDeviceUnBonded();
		}
	}

	/**
	 * 设备未绑定
	 */
	protected void onDeviceUnBonded() {
		mBluetoothHelper.searchDevices(this);
	}

	// ================================================抽象方法================================================//
	/**
	 * 蓝牙设备已绑定【采集设备绑定后，点击采集连接蓝牙设备，打印设备绑定后，直接连接蓝牙设备】
	 * 
	 * @param device
	 */
	protected abstract void onDeviceBonded(BluetoothDevice device);

	/**
	 * 连接设备
	 */
	public abstract void connectDevice();

	// ===============================蓝牙匹配=====================================//
	/**
	 * 匹配蓝牙设备
	 */
	public void paireBtDevice(String address) {
		if (!isDeviceBindDialogShow) {
			isDeviceBindDialogShow = true;
			if (mBluetoothHelper.isBtAddressVaild(address)) {
				startPaireBtDevice(mBluetoothHelper.getBluetoothDevice(address));
			} else {
				Toast("请设置正确的蓝牙地址！");
			}
		}
	}

	/**
	 * 开始匹配蓝牙设备
	 * 
	 * @param device
	 */
	public void startPaireBtDevice(BluetoothDevice device) {
		// 取消搜索
		mBluetoothHelper.stopSearch();
		try {
			if (!mBluetoothHelper.createBind(device)) {
				if (!mIsBonded) {
					mBluetoothHelper.openBluetooth(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ================================================状态获取================================================//
	/**
	 * 是否已经开启蓝牙
	 * 
	 * @return
	 */
	public boolean isOpenBluetooth() {
		return mBluetoothHelper.isOpenBluetooth();
	}

	// ================================================蓝牙设备监听================================================//
	@Override
	public void onStartDiscovery() {
		mLoadingDialog = DialogUtil.changeLoadingDialogStatus(mContext, mLoadingDialog, "正在扫描蓝牙设备...");
	}

	@Override
	public void onNewDeviceFound(BluetoothDevice device) {
		if (device != null) {
			if (!StringUtil.isEmpty(device.getName())) {
				// 找到后，添加设备
				if (!mBondedList.contains(device)) {
					mBondedList.add(device);
				}
			}
		}
	}

	@Override
	public void onSearchCompleted(List<BluetoothDevice> bondedList, List<BluetoothDevice> newList) {
		closeDialog();
		if (mBondedList.size() > 0) {
			if (!mBluetoothHelper.isBtAddressVaild(mBtAddress)) {
				showBtDevice(mBondedList, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						mBtAddress = mBondedList.get(which).getAddress();
						paireBtDevice(mBtAddress);
					}
				});
			} else {
				paireBtDevice(mBtAddress);
			}
		} else {
			Toast("当前扫描不到蓝牙设备！");
		}
	}

	/**
	 * 显示蓝牙设备列表
	 * 
	 * @param btDeviceList
	 */
	private void showBtDevice(final List<BluetoothDevice> btDeviceList, DialogInterface.OnClickListener listener) {
		String[] deviceList = new String[btDeviceList.size()];
		for (int i = 0; i < deviceList.length; i++) {
			deviceList[i] = new StringBuilder(btDeviceList.get(i).getName()).append("\n").append(btDeviceList.get(i).getAddress()).toString();
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT).setIcon(android.R.drawable.ic_dialog_info).setTitle("采集设置").setSingleChoiceItems(deviceList, 0, listener).setCancelable(false);
		AlertDialog dialog = builder.create();
		// 需要的窗口句柄方式，没有这句会报错的
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}

	@Override
	public void onBondStateChanged(BluetoothDevice device, Intent intent) {
		if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
			LogHelper.trace("====BOND_BONDED====已绑定蓝牙设备");
			Toast("蓝牙设备匹配成功！");
			mIsBonded = true;
			onDeviceBonded(device);
		} else if (device.getBondState() == BluetoothDevice.BOND_BONDING) {
			LogHelper.trace("====BOND_BONDING====正在绑定蓝牙设备");
		} else {
			int reason = intent.getIntExtra("android.bluetooth.device.extra.REASON", BluetoothDevice.ERROR);
			if (reason != 0 && reason != 9) {
				LogHelper.trace("====蓝牙设备绑定异常====请到系统蓝牙设置界面检查");
			}
		}
	}

	@Override
	public void onBluetoothOpened() {
		ActivityUtil.runOnUIThread(new Runnable() {
			@Override
			public void run() {
				checkPairedDevice();
			}
		});

	}

	@Override
	public void onBluetoothReOpened() {
		ActivityUtil.runOnUIThread(new Runnable() {
			@Override
			public void run() {
				closeDialog();
				isDeviceBindDialogShow = false;
				paireBtDevice(mBtAddress);
			}
		});
	}

	// ================================================对象销毁、公共方法================================================//
	/**
	 * 显示连接蓝牙采集盒出错的提示框
	 */
	public void showConnectErrorMsg() {
		DialogUtil.getConfirmDialog(mContext, "   1.请检查蓝牙采集盒是否开机或正在被使用。\n\n   2.请检查蓝牙地址是否设置正确。", null).show();
	}

	public void Toast(String msg) {
		ActivityUtil.toastOnUIThread(msg);
	}

	/**
	 * 对象销毁
	 */
	public void close() {
		mBluetoothHelper.close();
		mBondedList = null;
		mLoadingDialog = null;
	}

	/**
	 * 关闭dialog
	 */
	private void closeDialog() {
		if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
			mLoadingDialog = null;
		}
	}

	// ================================================set/get================================================//
	/**
	 * 设置蓝牙设备过滤器
	 * 
	 * @param deviceFilter
	 *            蓝牙设备过滤器
	 */
	public void setDeviceFilter(DeviceFilter deviceFilter) {
		mBluetoothHelper.setDeviceFilter(deviceFilter);
	}

	/**
	 * 设置蓝牙地址
	 * 
	 * @param btAddress
	 */
	public void setBtAddress(String btAddress) {
		mBtAddress = btAddress;
	}

	/**
	 * 获取蓝牙地址
	 * 
	 * @return btAddress
	 */
	public String getBtAddress() {
		return mBtAddress;
	}

	/**
	 * 获取蓝牙匹配助手
	 * 
	 * @return
	 */
	public BluetoothHelper getBluetoothHelper() {
		return mBluetoothHelper;
	}

	/**
	 * 获取蓝牙设备
	 */
	public BluetoothDevice getBluetoothDevice() {
		return mBluetoothHelper.getBluetoothDevice(mBtAddress);
	}
	
	/**
	 * 设置连接蓝牙设备的监听
	 * @param onConnectDeviceListener 连接蓝牙设备的监听
	 */
	public void setOnConnectDeviceListener(OnConnectDeviceListener onConnectDeviceListener) {
		mOnConnectDeviceListener = onConnectDeviceListener;
	}

	/**
	 * 连接蓝牙设备的监听
	 * 
	 * @author xx
	 * 
	 */
	public interface OnConnectDeviceListener {
		/**
		 * 连接结束
		 * 
		 * @param isConnected
		 *            是否连接
		 */
		void onConnectFinished(boolean isConnected);
	}
}
