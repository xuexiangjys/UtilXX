package com.xuexiang.util.net;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.common.StringUtils;
import com.xuexiang.util.log.LogHelper;

/**
 * 该类用来管理Wifi连接，做一些断开、连接的操作
 */
public class WifiHelper {
	// 定义WifiManager对象
	private WifiManager mWifiManager;
	// 定义WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfiguration;
	// 定义一个WifiLock
	private WifiLock mWifiLock;
	private Context mContext;
	/**
	 * 路由名称
	 */
	private String mWifiSsid;
	/**
	 * 路由密码
	 */
	private String mWifiPassword;
	private int TIME_OUT = 25;

	private OnConnectWifiStateListener mOnConnectWifiStateListener;

	public void setOnConnectWifiStateListener(OnConnectWifiStateListener listener) {
		mOnConnectWifiStateListener = listener;
	}

	public interface OnConnectWifiStateListener {
		/**
		 * 连接成功时调用
		 */
		void connectWifiSuccess();

		/**
		 * 连接wife失败时调用
		 */
		void connectWifiFailed();
	}

	// 构造器
	public WifiHelper(Context context, String wifiSsid, String wifiPassword) {
		mContext = context;
		mWifiSsid = wifiSsid;
		mWifiPassword = wifiPassword;
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();

	}
	
	/**
	 * 连接WiFi
	 */
	public void connectWifi() {
		ThreadPoolManager.getInstance().addTask(new connectWifiRunnable());
	}

	/**
	 * 连接指定WIFI网络的线程
	 * @author xx
	 */
	private class connectWifiRunnable implements Runnable {
		@Override
		public void run() {
			
			if (!checkState()) {
				openWifi();// 打开wifi
				checkWifiEnable();
				
				try {
					Thread.sleep(2000); //延时等待wifi自动连接
				} catch (InterruptedException e) {
					e.printStackTrace();
					onCheckWifiStateFinished(false);
					return;
				}
			}
			// 检测现有的连接是否是配置中的路由
			if (checkSSIDState(mWifiSsid)) {// 如果配置中的网络已连接，直接上传数据
				checkConnectWifi();
			} else {
				startScan();
				WifiConfiguration wcg = IsExsits(mWifiSsid);
				if (wcg != null) {// 如果该网络已经保存在配置中,直接连到该网络
					connectConfigurationWifi(wcg);
				} else {
					int encryptionType = 1;// 加密类型
					if (!StringUtils.isEmpty(mWifiPassword)) {
						encryptionType = 3;
					}
					addNetwork(CreateWifiInfo(mWifiSsid, mWifiPassword, encryptionType));
				}
				checkConnectWifi();
			}
		}
	}
	
	/**
	 * 检验wifi开关是否成功开启
	 */
	private void checkWifiEnable() {
		int timeCount = 0;
		while (!checkState()) {// 每隔1秒钟检查一次wifi是否启用成功
			if (timeCount >= TIME_OUT) {// 如果打开时间超过25秒，退出这次打开
				onCheckWifiStateFinished(false);
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				onCheckWifiStateFinished(false);
				return;
			}
			timeCount++;
		}
	}
	
	/**
	 * 检验wifi是否连接成功
	 */
	private void checkConnectWifi() {
		int timeCount = 0;
		while (!isConnectSuccess()) {// 每隔1秒钟检查一次是否连接上指定的网络
			if (timeCount >= TIME_OUT) {// 如果打开时间超过25秒，退出这次连接
				onCheckWifiStateFinished(false);
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				onCheckWifiStateFinished(false);
				return;
			}
			timeCount++;
		}
		LogHelper.trace("连接路由花费时间：" + timeCount + "秒");
		onCheckWifiStateFinished(true);
	}
	
	public void onCheckWifiStateFinished(boolean isConnectSuccess) {
		if (mOnConnectWifiStateListener != null) {
			if (isConnectSuccess) {
				mOnConnectWifiStateListener.connectWifiSuccess();
			} else {
				mOnConnectWifiStateListener.connectWifiFailed();
			}
		}
	}
	
	public void close() {
		if (mWifiList != null && mWifiList.size() > 0) {
			mWifiList.clear();
		}
		if (mWifiConfiguration != null && mWifiConfiguration.size() > 0) {
			mWifiConfiguration.clear();
		}
		
	}
	
	//============================================wifi连接、断开===============================================================//
	/**
	 * 创建网络连接配置
	 * @param SSID 路由名称
	 * @param Password 路由密码
	 * @param Type 加密类型
	 * @return
	 */
	public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type) {
		WifiConfiguration config = new WifiConfiguration();
		config.allowedAuthAlgorithms.clear();
		config.allowedGroupCiphers.clear();
		config.allowedKeyManagement.clear();
		config.allowedPairwiseCiphers.clear();
		config.allowedProtocols.clear();
		config.SSID = "\"" + SSID + "\""; //

		if (Type == 1) {  // WIFICIPHER_NOPASS
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		} else if (Type == 2) { // WIFICIPHER_WEP
			config.hiddenSSID = true;
			config.wepKeys[0] = "\"" + Password + "\"";
			config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		} else if (Type == 3) { // WIFICIPHER_WPA
			config.preSharedKey = "\"" + Password + "\"";
			config.hiddenSSID = true;
			config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			config.status = WifiConfiguration.Status.ENABLED;
		}
		return config;
	}
	
	/**
	 * 指定配置好的网络进行连接
	 * 
	 * @param index
	 */
	public void connectConfiguration(int index) {
		// 索引大于配置好的网络索引返回
		if (index > mWifiConfiguration.size()) {
			return;
		}
		// 连接配置好的指定ID的网络
		mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId, true);
	}

	/**
	 * 添加一个网络并连接
	 * @param wcg
	 * @return
	 */
	public boolean addNetwork(WifiConfiguration wcg) {
		int wcgID = mWifiManager.addNetwork(wcg);
		boolean result = mWifiManager.enableNetwork(wcgID, true);
		return result;
	}

	/**
	 * 连接配置好的Wifi
	 */
	public boolean connectConfigurationWifi(WifiConfiguration wcg) {
		return mWifiManager.enableNetwork(wcg.networkId, true);
	}

	/**
	 * 断开指定ID的网络
	 * @param netId
	 */
	public void disconnectWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}

	
	//============================================wifi开关、状态获取===============================================================//
	/**
	 * 打开WIFI
	 */
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
		}
	}

	/**
	 * 关闭WIFI
	 */
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
		}
	}

	/**
	 * 检查当前WIFI状态
	 */
	public boolean checkState() {
		return mWifiManager.isWifiEnabled();
	}

	/**
	 * 锁定WifiLock
	 */
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	/**
	 * 解锁WifiLock
	 */
	public void releaseWifiLock() {
		// 判断时候锁定
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	/**
	 * 创建一个WifiLock
	 */
	public void creatWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("Test");
	}

	/**
	 * 得到配置好的网络
	 */
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfiguration;
	}

	
	//============================================wifi路由状态的判断===============================================================//
	/**
	 * 判断某个网络是否已保存在配置中
	 * 
	 * @param ssid
	 * @return
	 */
	public WifiConfiguration IsExsits(String SSID) {
		for (WifiConfiguration existingConfig : mWifiConfiguration) {
			if (existingConfig.SSID.equals("\"" + SSID + "\"") || existingConfig.SSID.equals(SSID)) {
				return existingConfig;
			}
		}
		return null;
	}

	/**
	 * 判断某个网络有没有连接
	 * 
	 * @param ssid
	 * @return
	 */
	public boolean checkSSIDState(String ssid) {
		mWifiInfo = mWifiManager.getConnectionInfo();
		if (mWifiInfo != null && getSSID() != null) {
			if (getSSID().equals("\"" + ssid + "\"") || getSSID().equals(ssid)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断wifi是否连接成功
	 * 
	 * @param ssid
	 * @return
	 */
	public boolean isConnectSuccess() {
		return checkState() && checkSSIDState(mWifiSsid) && NetUtil.IsHaveInternet(mContext);
	}
	
	//============================================wifi路由扫描===============================================================//
	/**
	 * 开始扫描网络
	 */
	public void startScan() {
		mWifiManager.startScan();
		// 得到扫描结果
		mWifiList = mWifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfiguration = mWifiManager.getConfiguredNetworks();
	}

	/**
	 * 得到网络列表
	 */
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	/**
	 * 查看扫描结果
	 */
	public StringBuilder lookUpScan() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mWifiList.size(); i++) {
			stringBuilder.append("Index_" + new Integer(i + 1).toString() + ":");
			// 将ScanResult信息转换成一个字符串包
			// 其中把包括：BSSID、SSID、capabilities、frequency、level
			stringBuilder.append((mWifiList.get(i).SSID).toString());
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}
	
	/**
	 * 获取扫描的结果
	 * 
	 * @return
	 */
	public List<String> getScanResultList() {
		List<String> list = new ArrayList<String>();
		if (mWifiList != null && mWifiList.size() > 0) {
			for (int i = 0; i < mWifiList.size(); i++) {
				list.add(i + "号--  " + mWifiList.get(i).SSID);
			}
		}
		return list;
	}

	/**
	 * 获取已保存配置的网络
	 * 
	 * @return
	 */
	public List<String> getConfigWifiList() {
		List<String> list = new ArrayList<String>();
		if (mWifiConfiguration != null && mWifiConfiguration.size() > 0) {
			for (int i = 0; i < mWifiConfiguration.size(); i++) {
				list.add(i + "号--  " + mWifiConfiguration.get(i).SSID);
			}
		}
		return list;
	}
	
	/**
	 * 判断能不能搜索到指定的网络
	 * 
	 * @param SSID
	 * @return
	 */
	public boolean checkScanResult(String SSID) {
		if (mWifiList != null && mWifiList.size() > 0) {
			for (int i = 0; i < mWifiList.size(); i++) {
				if (mWifiList.get(i).SSID.equals(SSID) || mWifiList.get(i).SSID.equals("\"" + SSID + "\"")) {
					return true;
				}
			}
		}
		return false;
	}
	
	//============================================wifi信息详情===============================================================//
	/**
	 * 得到MAC地址
	 */
	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	/**
	 * 得到接入点的BSSID
	 */
	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	/**
	 * 得到IP地址
	 */
	public int getIPAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	/**
	 * 得到连接的ID
	 */
	public int getNetworkId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	/**
	 * 得到WifiInfo的所有信息包
	 */
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	/**
	 * 得到接入点的SSID
	 */
	public String getSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getSSID();
	}
}
