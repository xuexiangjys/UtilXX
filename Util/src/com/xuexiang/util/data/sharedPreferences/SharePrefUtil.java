package com.xuexiang.util.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.xuexiang.util.security.Base64Util;

public class SharePrefUtil {

	private final static String SP_NAME = "SharePrefUtil";
	private static SharedPreferences sp;

	private static void checkSp(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
	}
	
	public static void clear(Context context) {
		checkSp(context);
		sp.edit().clear().commit();
	}
	
	/**
	 * 保存布尔值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		checkSp(context);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 保存字符串
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(Context context, String key, String value) {
		checkSp(context);
		sp.edit().putString(key, value).commit();

	}

	

	/**
	 * 保存long型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveLong(Context context, String key, long value) {
		checkSp(context);
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * 保存int型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context, String key, int value) {
		checkSp(context);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 保存float型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(Context context, String key, float value) {
		checkSp(context);
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * 获取字符值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		checkSp(context);
		return sp.getString(key, defValue);
	}

	/**
	 * 获取int值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		checkSp(context);
		return sp.getInt(key, defValue);
	}

	/**
	 * 获取long值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		checkSp(context);
		return sp.getLong(key, defValue);
	}

	/**
	 * 获取float值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		checkSp(context);
		return sp.getFloat(key, defValue);
	}

	/**
	 * 获取布尔值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		checkSp(context);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 将对象进行base64编码后保存到SharePref中
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void saveObj(Context context, String key, Object object) {
		checkSp(context);
		// 将对象的转为base64码
		String objBase64 = Base64Util.encode(object.toString(), "UTF-8");
		sp.edit().putString(key, objBase64).commit();
	}

	/**
	 * 将SharePref中经过base64编码的对象读取出来
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getObj(Context context, String key) {
		checkSp(context);
		String objBase64 = sp.getString(key, null);
		if (TextUtils.isEmpty(objBase64))
			return null;
		// 对Base64格式的字符串进行解码
		String obj = Base64Util.decode(objBase64, "UTF-8");
		return obj;
	}

}
