package com.xuexiang.util.app;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.xuexiang.app.StartAppReceiver;

public class BroadcastHelper {
	
	/**
	 * 发起启动应用的广播
	 * @param context
	 */
	public static void sendStartAppBroadCast(Context context) {
		Intent intent = new Intent(context, StartAppReceiver.class);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.sendBroadcast(intent);
	}
	
	private static void sendBroadCast(Context context, Class<?> cls, String action, String key, String value1, int value2, Serializable value3) {
		Intent intent = new Intent();
		if (cls != null) {
			intent.setClass(context, cls);
		} 
		if (action != null) {
			intent.setAction(action);
		}
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        if (value1 != null) {
        	intent.putExtra(key, value1);
        } else if (value3 != null) {
        	Bundle bundle = new Bundle();
        	bundle.putSerializable(key, value3);
    		intent.putExtras(bundle);
        } else {
        	intent.putExtra(key, value2);
        }       
        context.sendBroadcast(intent);
	}
	
	public static void sendBroadCast(Context context, Class<?> cls, String key, String value) {
		sendBroadCast(context, cls, null, key, value, 0, null);
	}
	
	public static void sendBroadCast(Context context, Class<?> cls, String key, int value) {
		sendBroadCast(context, cls, null, key, null, value, null);
	}
	
	/**
	 * 发广播，传递String型数据
	 * @param context
	 * @param action
	 * @param key
	 * @param value
	 */
	public static void sendBroadCast(Context context, String action, String key, String value) {
		sendBroadCast(context, null, action, key, value, 0, null);
	}
	
	/**
	 * 发广播，传递int型数据
	 * @param context
	 * @param action
	 * @param key
	 * @param value
	 */
	public static void sendBroadCast(Context context, String action, String key, int value) {
		sendBroadCast(context, null, action, key, null, value, null);
	}
	
	/**
	 * 发广播，传递一个对象
	 * @param context
	 * @param action
	 * @param key
	 * @param value
	 */
	public static void sendBroadCast(Context context, String action, String key, Serializable value) {
		sendBroadCast(context, null, action, key, null, 0, value);
	}
	
	/**
	 * 发广播，传递多个对象
	 * @param context
	 * @param cls
	 * @param action
	 * @param map
	 */
	public static void sendBroadCast(Context context, Class<?> cls, String action, Map<String, Serializable> map) {
		Intent intent = new Intent();
		if (cls != null) {
			intent.setClass(context, cls);
		} 
		if (action != null) {
			intent.setAction(action);
		}
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Bundle bundle = new Bundle();
        for (String key : map.keySet()) {
        	bundle.putSerializable(key, map.get(key));
        }
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
	}
	
	/**
	 * 广播注册
	 * @param context
	 * @param actionList
	 * @param receiver
	 */
	public static void registerReceiver(Context context, List<String> actionList, BroadcastReceiver receiver) {
		IntentFilter iFilter = new IntentFilter();
        for (String action : actionList) {
        	iFilter.addAction(action);
		}
        context.registerReceiver(receiver, iFilter);
	}
	
}
