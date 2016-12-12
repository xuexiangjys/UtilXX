package com.xuexiang.util.net;

import android.app.Dialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.view.DialogUtil;

/**
 * 热点开关辅助类
 * @author xx
 *
 */
public class WifiAPHelper {
	
	private Context mContext;

	private Handler mWifiHandler;
	private WifiManager mWifiManager;

	private CloseWifiRunnable mCloseWifiRunnable;
	private StartWifiApRunnable mStartWifiApRunnable;
	private CloseWifiApRunnable mCloseWifiApRunnable;
	
	private Dialog mLoadingDialog = null;
	private ToastUtil mToastUtil;
	private String mWifiAPSsid, mWifiAPPassword;
	private OnWifiAPStatusChangedListener mListener;
	
	public void setOnWifiAPStatusChangedListener(OnWifiAPStatusChangedListener listener) {
		mListener = listener;
	}

	public WifiAPHelper(Context context, String wifiAPSsid, String wifiAPPassword) {
		mContext = context.getApplicationContext();
		mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		mWifiHandler = new Handler();
		mToastUtil = ToastUtil.getInstance(mContext);
		setWifiAPConfig(wifiAPSsid, wifiAPPassword);
	}
	
	public void setWifiAPConfig(String wifiAPSsid, String wifiAPPassword) {
		mWifiAPSsid = wifiAPSsid;
		mWifiAPPassword = wifiAPPassword;
	}

	/**
	 * 开启WLAN热点
	 * @param handler
	 * @param message
	 */
	public void startWifiAp(Handler handler, String message) {
		mLoadingDialog = DialogUtil.createLoadingDialog(mContext, message);
		mLoadingDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mLoadingDialog.show();
		
		if (mWifiManager.isWifiEnabled()) {
			closeWifiTh();
		} else {
			startWifiApTh();
		}

	}

	/**
	 * 关闭Wifi的线程
	 * @param handler
	 */
	public void closeWifiTh() {
		mCloseWifiRunnable = new CloseWifiRunnable();
		ThreadPoolManager.getInstance().addTask(mCloseWifiRunnable);
	}

	/**
	 * 开启热点线程
	 * @param handler
	 */
	public void startWifiApTh() {
		mStartWifiApRunnable = new StartWifiApRunnable();
		ThreadPoolManager.getInstance().addTask(mStartWifiApRunnable);
	}

	/**
	 * 关闭热点线程
	 * @param handler
	 */
	public void stopWifiApTh() {
		mCloseWifiApRunnable = new CloseWifiApRunnable();
		ThreadPoolManager.getInstance().addTask(mCloseWifiApRunnable);
	}


	/**
	 * 关闭wifi
	 * 
	 */
	private class CloseWifiRunnable implements Runnable {
		@Override
		public void run() {
			int state = mWifiManager.getWifiState();
			if (state == WifiManager.WIFI_STATE_ENABLED) {
				mWifiManager.setWifiEnabled(false);
				mWifiHandler.postDelayed(mCloseWifiRunnable, 100);
			} else if (state == WifiManager.WIFI_STATE_DISABLING) {
				mWifiHandler.postDelayed(mCloseWifiRunnable, 100);
			} else if (state == WifiManager.WIFI_STATE_DISABLED) {
				mWifiHandler.post(new Runnable() {
					@Override
					public void run() {
						startWifiApTh();
						mToastUtil.showToast("已关闭wifi", Toast.LENGTH_SHORT);
					}
				});
			}
		}
	}

	private class StartWifiApRunnable implements Runnable {
		@Override
		public void run() {
			int state = WifiAPUtil.getWifiApState(mContext);
			if(state == WifiAPUtil.WIFI_AP_STATE_FAILED){
				mWifiHandler.post(new Runnable() {
					@Override
					public void run() {
						if(mLoadingDialog != null) {
							mLoadingDialog.dismiss();
							mLoadingDialog = null;
						}
						mToastUtil.showToast("打开热点失败，请到系统设置里检查热点状态！", Toast.LENGTH_SHORT);
					}
				});
			} else if (state == WifiAPUtil.WIFI_AP_STATE_DISABLED) {
				WifiAPUtil.startWifiAp(mContext, mWifiAPSsid, mWifiAPPassword);
				mWifiHandler.postDelayed(mStartWifiApRunnable, 100);
			} else if (state == WifiAPUtil.WIFI_AP_STATE_ENABLING) {// ||
				mWifiHandler.postDelayed(mStartWifiApRunnable, 100);
			} else if (state == WifiAPUtil.WIFI_AP_STATE_ENABLED) {
				mWifiHandler.post(new Runnable() {
					@Override
					public void run() {
						if(mLoadingDialog != null){
							mLoadingDialog.dismiss();
							mLoadingDialog = null;
						}
						mToastUtil.showToast("已开启WLAN热点", Toast.LENGTH_SHORT);
					}
				});
				if (mListener != null) {
					mListener.onWifiAPStatusChanged(true);
				}
			}
		}
	}

	private class CloseWifiApRunnable implements Runnable {
		@Override
		public void run() {
			int state = WifiAPUtil.getWifiApState(mContext);
			if (state == WifiAPUtil.WIFI_AP_STATE_ENABLED) {
				WifiAPUtil.stopWifiAp(mContext, mWifiAPSsid, mWifiAPPassword);
				mWifiHandler.postDelayed(mCloseWifiApRunnable, 100);
			} else if (state == WifiAPUtil.WIFI_AP_STATE_DISABLING || state == WifiAPUtil.WIFI_AP_STATE_FAILED) {
				mWifiHandler.postDelayed(mCloseWifiApRunnable, 100);
			} else if (state == WifiAPUtil.WIFI_AP_STATE_DISABLED) {
				if (mListener != null) {
					mListener.onWifiAPStatusChanged(false);
				}
			}
		}
	}

	/**
	 * 热点状态改变的事件监听
	 * @author xx
	 *
	 */
	public interface OnWifiAPStatusChangedListener {
		void onWifiAPStatusChanged(boolean isEnable);
	}
}
