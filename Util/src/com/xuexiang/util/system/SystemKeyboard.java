package com.xuexiang.util.system;

import java.lang.reflect.Method;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.view.KeyEvent;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.common.ShellUtils;

/**
 * 系统快捷键
 * 
 * @author xx
 * 
 */
public class SystemKeyboard {

	/**
	 * 回到主界面
	 * 
	 * @param context
	 */
	public static void toHome(Context context) {
		Intent i = new Intent();
		i.setAction(Intent.ACTION_MAIN);
		i.addCategory(Intent.CATEGORY_HOME);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);

	}

	/**
	 * 返回按钮
	 */
	public static void toBack() {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				sendKeyevent(KeyEvent.KEYCODE_BACK);
			}
		});
	}
	
	/**
	 * 发送按钮事件
	 * @param eventCode
	 */
	public static void sendKeyevent(int eventCode) {
		ShellUtils.execCommand("input keyevent " + eventCode, true, false);
	}

	/**
	 * 菜单按钮
	 */
	public static void toMenu() {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				sendKeyevent(KeyEvent.KEYCODE_MENU);
			}
		});
	}
	
	/**
	 * 最近运行应用列表
	 */
	public static void toRecent() {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				sendKeyevent(KeyEvent.KEYCODE_APP_SWITCH);
			}
		});
	}

	/**
	 * 音量调节
	 * 
	 * @param context
	 * @param isAdjustLower
	 *            是否是调低音量
	 */
	public static void volumeAdjustment(Context context, boolean isAdjustLower) {
		AudioManager mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		if (isAdjustLower) { // 降低音量，调出系统音量控制
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER,
					AudioManager.FX_FOCUS_NAVIGATION_UP);
		} else { // 增加音量，调出系统音量控制
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE,
					AudioManager.FX_FOCUS_NAVIGATION_UP);
		}
	}

	/**
	 * 展开通知栏
	 * @param context
	 */
	public static void expandNotify(Context context) {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		try {
			Object service = context.getSystemService("statusbar");
			Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
			Method method = null;
			if (service != null) {
				if (currentApiVersion <= 16) {
					method = statusbarManager.getMethod("expand");
					method.setAccessible(true);
					method.invoke(service);
				} else {
					method = statusbarManager.getMethod("expandNotificationsPanel");
				}
				method.setAccessible(true);
				method.invoke(service);
			}
		} catch (Exception e) {
		}
	}

	
}
