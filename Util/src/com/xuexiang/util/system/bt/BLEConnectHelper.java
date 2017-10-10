package com.xuexiang.util.system.bt;

import java.util.List;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.system.bt.BLEHelper.DeviceFilter;
import com.xuexiang.util.system.bt.BLEHelper.OnSearchDeviceListener;
import com.xuexiang.util.view.DialogUtil;

/** 
 * @author xx
 * @Date 2017-10-10 上午11:33:28 
 */
public class BLEConnectHelper implements OnSearchDeviceListener{
	private Context mContext;
	/**
	 * 低功耗蓝牙辅助类（单例）
	 */
	private BLEHelper mBLEHelper;
	/**
	 * 配置的需要需要连接的蓝牙的地址
	 */
	private String mBtMacAddress;
	
	/**
	 * 扫描蓝牙设备的loading框
	 */
	private Dialog mLoadingDialog;
	
	private OnSearchFinishListener mOnSearchFinishListener;

	/**
	 * 是否找到设备
	 */
	private boolean mIsFoundDevice;
	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 */
	public BLEConnectHelper(Context context, String btMacAddress) {
		mContext = context;
		mBtMacAddress = btMacAddress;
		mBLEHelper = BLEHelper.getInstance();
		mBLEHelper.close();
		mBLEHelper.setOnSearchDeviceListener(this);
	}
	
	public BLEConnectHelper setOnSearchFinishListener(OnSearchFinishListener onSearchFinishListener) {
		mOnSearchFinishListener = onSearchFinishListener;
		return this;
	}
	
	public BLEConnectHelper setDeviceFilter(DeviceFilter deviceFilter) {
		mBLEHelper.setDeviceFilter(deviceFilter);
		return this;
	}
	
	public void startScan() {
		if (!mBLEHelper.isOpenBluetooth()) {
			mBLEHelper.openBluetooth();
		} else{
			mBLEHelper.searchDevices(BLEConnectHelper.this);
		}
	}
	
	@Override
	public void onStartDiscovery() {
		mIsFoundDevice = false;
		mLoadingDialog = DialogUtil.changeLoadingDialogStatus(mContext, mLoadingDialog, "正在扫描蓝牙设备...");
	}

	@Override
	public boolean onNewDeviceFound(BluetoothDevice device) {
		boolean isFoundDevice = device != null && device.getAddress().equals(mBtMacAddress);
		if (isFoundDevice) {
			mIsFoundDevice = true;
		}
		return isFoundDevice;
	}
	
	@Override
	public void onSearchCompleted(List<BluetoothDevice> bondedList, List<BluetoothDevice> newList) {
		closeDialog();
		if (mOnSearchFinishListener != null) {
			mOnSearchFinishListener.onSearchFinished(mIsFoundDevice);
		}
	}

	@Override
	public void onBluetoothOpened() {
		ActivityUtil.runOnUIThread(new Runnable() {
			@Override
			public void run() {
				mBLEHelper.searchDevices(BLEConnectHelper.this);
			}
		});
	}
	
	/**
	 * 对象销毁
	 */
	public void close() {
		mBLEHelper.close();
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

	/**
	 * 扫描结果监听
	 * @author XUE
	 *
	 */
	public interface OnSearchFinishListener {
		void onSearchFinished(boolean result);
	}
	
}
