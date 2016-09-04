package com.xuexiang.util.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;

/** 
 * SharedPreferences管理工具基类
 * @author  XX 
 */

public class BaseSharedPreferencesUtil {
	public SharedPreferences.Editor mEditor;
	public SharedPreferences mSharedPreferences;
	public Context mContext;
	
	/**
	 * 获取自定义的SharedPreferences
	 * @param context
	 * @param spName 自定义SharedPreferences名
	 */
	public BaseSharedPreferencesUtil(Context context, String spName) {
		mContext = context;
		mSharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
	    mEditor = mSharedPreferences.edit();
	}
	
	/**
	 * 获取系统默认的SharedPreferences
	 * @param context
	 */
	public BaseSharedPreferencesUtil(Context context) {
		mContext = context;
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	    mEditor = mSharedPreferences.edit();
	}
	
	
	/**
	 * 自定义sharedpreferences设置boolean值
	 * @param key
	 * @param value
	 */
	public void putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置float值
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		mEditor.putFloat(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置long值
	 * @param key
	 * @param value
	 */
	public void putLong(String key, long value) {
		mEditor.putLong(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置String值
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		mEditor.putString(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置int值
	 * @param key
	 * @param value
	 */
	public void putInt(String key, int value) {
		mEditor.putInt(key, value);
	    mEditor.commit();  
	}
	

	public void remove(String key) {
		mEditor.remove(key);
	    mEditor.commit();  
	}	
	
	/**
	 * 清空销毁
	 */
	public void clear() {
		mEditor.clear();
    	mEditor.commit();
	}

/*****************************************************************************************************************************************************************/
	
	/**
	 * 根据key获取boolean值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPreferences.getBoolean(key, defValue);
	}
	
	/**
	 * 根据key获取long值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, long defValue) {
		return mSharedPreferences.getLong(key, defValue);
	}
	
	/**
	 * 根据key获取float值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloat(String key, float defValue) {
		return mSharedPreferences.getFloat(key, defValue);
	}
	
	/**
	 * 根据key获取String值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getString(String key, String defValue) {
		return mSharedPreferences.getString(key, defValue);
	}
	
	/**
	 * 根据key获取int值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}
	
	
	/**
	 * 根据资源id获取String值
	 * @param resourceid 资源id
	 * @return
	 */
	public String getResourceString(int resourceId) {
		return mContext.getResources().getString(resourceId);
	}
	
	/**
	 * 获取ListPreference中输入的具体的值
	 * @param listpref
	 * @param matchvalue
	 * @return
	 */
	public String getListEntryFromValue(ListPreference listpref, String matchvalue) {
		CharSequence[] entries = listpref.getEntries();
		CharSequence[] entryValues = listpref.getEntryValues();
		for (int i = 0; i < entryValues.length; i++) {
			if (entryValues[i].equals((String) matchvalue))
				return (String) entries[i];
		}
		return "";
	}
	
}
