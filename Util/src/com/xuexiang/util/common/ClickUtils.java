package com.xuexiang.util.common;

import android.view.View;

public class ClickUtils {

	/**
	 * 最近一次点击的时间
	 */
	private static long sLastClickTime;
	/**
	 * 最近一次点击的控件ID
	 */
	private static int sLastClickViewId;

	/**
	 * 是否是快速点击
	 * 
	 * @param v
	 *            点击的控件
	 * @return true:是，false:不是
	 */
	public static boolean isFastDoubleClick(View v) {
		return isFastDoubleClick(v, 1F);
	}

	/**
	 * 是否是快速点击
	 * 
	 * @param v
	 *            点击的控件
	 * @param interval
	 *            时间间期（秒）
	 * @return
	 */
	public static boolean isFastDoubleClick(View v, float interval) {
		long time = System.currentTimeMillis();
		int viewId = v.getId();
		long timeD = time - sLastClickTime;
		if (0 < timeD && timeD < (interval * 1000) && viewId == sLastClickViewId) {
			return true;
		} else {
			sLastClickTime = time;
			sLastClickViewId = viewId;
			return false;
		}
	}
}
