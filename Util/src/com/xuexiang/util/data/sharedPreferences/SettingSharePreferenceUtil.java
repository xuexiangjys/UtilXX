package com.xuexiang.util.data.sharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

/** 设置项管理
  * @ClassName: SharePreferenceUtil
  * @Description: TODO
  * @author smile
  * @date 2014-6-10 下午4:20:14
  */
@SuppressLint("CommitPrefEdits")
public class SettingSharePreferenceUtil extends BaseSharedPreferencesUtil{
	private static SettingSharePreferenceUtil sInstance;
	
	public static final String SETTING = "setting";
	
	private String SHARED_KEY_IS_FIRST = "isFirst";
	private String SHARED_KEY_CITY_LOCATION = "city";
	private String SHARED_KEY_APP_URL = "appurl";
	private String SHARED_KEY_NOTIFY = "shared_key_notify";
	private String SHARED_KEY_VOICE = "shared_key_sound";
	private String SHARED_KEY_VIBRATE = "shared_key_vibrate";
	
	private String SHARED_KEY_DEBUGMODE = "shared_key_debugMode";
	
	private String SHARED_KEY_PASSWORD = "password";

	public SettingSharePreferenceUtil(Context context) {
		super(context, SETTING);
	}
	
	public static SettingSharePreferenceUtil getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new SettingSharePreferenceUtil(c.getApplicationContext());
		}
		return sInstance;
	}
	
	public void setIsfirst(boolean isfirst) {  
        putBoolean(SHARED_KEY_IS_FIRST, isfirst);           
    }  
	
	public void setCity(String city) {  
        putString(SHARED_KEY_CITY_LOCATION, city);            
    }  
	
	public void setAppUrl(String appurl) {  
        putString(SHARED_KEY_APP_URL, appurl);           
    }  
	
	public boolean getIsfirst() {  
	   return getBoolean(SHARED_KEY_IS_FIRST,true);  
    }  
	
	public String getCity() {  
		 return getString(SHARED_KEY_CITY_LOCATION,"");  
    }  
	
	public String getAppUrl() {  
		return getString(SHARED_KEY_APP_URL,"");  
    }  
	
	public void setPassword(String password) {  
        putString(SHARED_KEY_PASSWORD, password);  
          
    }  
	
    public String getPassword() {  
        return getString(SHARED_KEY_PASSWORD,"");  
    }  
	
    public boolean isNeedVerify() {
    	if (TextUtils.isEmpty(getPassword())) {
    	    return false;  
    	} else {
    		return true;
    	}       
    }  

	public void setPushNotifyEnable(boolean isChecked) {
		putBoolean(SHARED_KEY_NOTIFY, isChecked);		
	}

	public void setAllowVoiceEnable(boolean isChecked) {
		putBoolean(SHARED_KEY_VOICE, isChecked);		
	}
	
	public void setAllowVibrateEnable(boolean isChecked) {
		putBoolean(SHARED_KEY_VIBRATE, isChecked);		
	}
	
	public void setDebugMode(boolean isDebugMode) {
		putBoolean(SHARED_KEY_DEBUGMODE, isDebugMode);		
	}
	
	/**
	 * 是否允许推送通知
	 */
	public boolean isAllowPushNotify() {
		return getBoolean(SHARED_KEY_NOTIFY, true);
	}
	/**
	 * 是否允许声音
	 */
	public boolean isAllowVoice() {
		return getBoolean(SHARED_KEY_VOICE, true);
	}
	/**
	 * 是否允许震动
	 */
	public boolean isAllowVibrate() {
		return getBoolean(SHARED_KEY_VIBRATE, true);
	}

	/**
	 * 是否调试模式
	 */
	public boolean isDebugMode() {
		return getBoolean(SHARED_KEY_DEBUGMODE, false);
	}

}
