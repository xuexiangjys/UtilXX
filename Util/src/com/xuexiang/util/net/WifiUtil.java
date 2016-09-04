package com.xuexiang.util.net;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class WifiUtil {
	
	public WifiUtil(){
		int versionID=getAndroidSDKVersion();
		if(versionID<=10){
			WIFI_AP_STATE_DISABLING = 0;
			WIFI_AP_STATE_DISABLED = 1;
			WIFI_AP_STATE_ENABLING = 2;
			WIFI_AP_STATE_ENABLED = 3;
			WIFI_AP_STATE_FAILED = 4;
		}else{
			WIFI_AP_STATE_DISABLING = 10;
			WIFI_AP_STATE_DISABLED = 11;
			WIFI_AP_STATE_ENABLING = 12;
			WIFI_AP_STATE_ENABLED = 13;
			WIFI_AP_STATE_FAILED = 14;
		}
	}

	public static int WIFI_AP_STATE_DISABLING = 0;
	public static int WIFI_AP_STATE_DISABLED = 1;
	public static int WIFI_AP_STATE_ENABLING = 2;
	public static int WIFI_AP_STATE_ENABLED = 3;
	public static int WIFI_AP_STATE_FAILED = 4;
	

	public void startWifiAp(Context context, String wifiAP_ssid, String wifiAP_password) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
		Method method1 = null;
		try {
			method1 = wifiManager.getClass().getMethod("setWifiApEnabled",
					WifiConfiguration.class, boolean.class);
			WifiConfiguration netConfig=getConfig(wifiAP_ssid, wifiAP_password);

			method1.invoke(wifiManager, netConfig, true);
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}

	}
	
	public void stopWifiAp(Context context, String wifiAP_ssid, String wifiAP_password) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
		Method method1 = null;
		try {
			method1 = wifiManager.getClass().getMethod("setWifiApEnabled",
					WifiConfiguration.class, boolean.class);
			WifiConfiguration netConfig = getConfig(wifiAP_ssid, wifiAP_password);

			method1.invoke(wifiManager, netConfig, false);
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}
	}
	
	public WifiConfiguration getConfig(String wifiAP_ssid, String wifiAP_password){
		WifiConfiguration netConfig = new WifiConfiguration();
		int securityType = getValue("WPA2_PSK");//通过反射，获取被隐藏的安全类型WPA2_PSK
		//wifi热点名字
		netConfig.SSID = wifiAP_ssid;
		netConfig.allowedAuthAlgorithms
				.set(WifiConfiguration.AuthAlgorithm.OPEN);
		netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		netConfig.allowedKeyManagement
				.set(securityType);//WifiConfiguration.KeyMgmt.WPA_PSK
		netConfig.allowedPairwiseCiphers
				.set(WifiConfiguration.PairwiseCipher.CCMP);
		netConfig.allowedPairwiseCiphers
				.set(WifiConfiguration.PairwiseCipher.TKIP);
		netConfig.allowedGroupCiphers
				.set(WifiConfiguration.GroupCipher.CCMP);
		netConfig.allowedGroupCiphers
				.set(WifiConfiguration.GroupCipher.TKIP);
		//密码
		netConfig.preSharedKey = wifiAP_password;
		
		return netConfig;
	}

	public int getWifiApState(WifiManager wifiManager) {
		try {
			Method method = wifiManager.getClass().getMethod("getWifiApState");
			int i = (Integer) method.invoke(wifiManager);
			return i;
		} catch (Exception e) {
			Log.e("Cannot get WiFi AP state", e+"");
			return WIFI_AP_STATE_FAILED;
		}
	}
	
	public int getAndroidSDKVersion() { 
		   int version=0; 
		   try { 
		     version = Integer.valueOf(android.os.Build.VERSION.SDK); 
		   } catch (NumberFormatException e) { 

		   } 
		   return version; 
		}
	
	public int getValue(String attributeName){
		int securityType = 0;
		try {
			securityType = (Integer)(KeyMgmt.class.getField("WPA2_PSK").get(null));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return securityType;
	}
	
	/**
     * 判断某个网络有没有连接
     * @param ssid
     * @return
     */
    public static boolean checkSSIDState(Context context,String ssid){
    	WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    	WifiConfiguration info=null;
		try {
			info = (WifiConfiguration) wifiManager.getClass().getMethod("getWifiApConfiguration").invoke(wifiManager);
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}
		
    	if(info!=null){
    		if(info.SSID.equals("\"" + ssid + "\"")||info.SSID.equals(ssid)){
    	  		  return true;
    	  	}else{
    	  		Log.d("","wifi ap ssid:"+info.SSID);
    	  	}
    	}
    	
    	return false;
    }
	
	/**
	 * 
	 * 描述：打开wifi.
	 * @param context
	 * @param enabled
	 * @return
	 */
	public static void setWifiEnabled(Context context,boolean enabled){
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if(enabled){
			wifiManager.setWifiEnabled(true);
		}else{
			wifiManager.setWifiEnabled(false);
		}
	}
	
	/**
	 * 
	 * 描述：是否有网络连接.
	 * @param context
	 * @return
	 */
	public static boolean isConnectivity(Context context) {
		
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((connectivityManager.getActiveNetworkInfo() != null && connectivityManager
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || telephonyManager
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}
	
	/**
	 * 判断当前网络是否是wifi网络.
	 *
	 * @param context the context
	 * @return boolean
	 */
	public static boolean isWifiConnectivity(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 描述：得到所有的WiFi列表.
	 * @param context
	 * @return
	 */
	public static List<ScanResult> getScanResults(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		List<ScanResult> list = null;
		//开始扫描WiFi
		boolean f = wifiManager.startScan();
		if(!f){
			getScanResults(context);
		}else{
			// 得到扫描结果
			list = wifiManager.getScanResults();
		}
		
		return list;
	}

	/**
	 * 
	 * 描述：根据SSID过滤扫描结果.
	 * @param context
	 * @param bssid
	 * @return
	 */
	public static ScanResult getScanResultsByBSSID(Context context,String bssid) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		ScanResult scanResult = null;
		//开始扫描WiFi
		boolean f = wifiManager.startScan();
		if(!f){
			getScanResultsByBSSID(context,bssid);
		}
		// 得到扫描结果
		List<ScanResult> list = wifiManager.getScanResults();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 得到扫描结果
				scanResult = list.get(i);
				if (scanResult.BSSID.equals(bssid)) {
					break;
				}
			}
		}
		return scanResult;
	}

	/**
	 * 
	 * 描述：获取连接的WIFI信息.
	 * @param context
	 * @return
	 */
	public static WifiInfo getConnectionInfo(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		return wifiInfo;
	}
	
	
}
