package com.xuexiang.util.app;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import com.xuexiang.util.common.ToastUtil;

import android.app.Activity;
import android.content.Context;

/**
 * 创建时间：2016-2-3 上午9:43:00
 * 
 * @author xuexiang 文件名称：ActivityManager.java
 **/
public class ActivityManager {
	// Activity栈
	private static Stack<Activity> activityStack;
	// 单例模式
	private static ActivityManager instance;

	private ActivityManager() {
	}

	/**
	 * 单一实例
	 */
	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0; i < activityStack.size(); i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit() {
		try {
			finishAllActivity();
			/*
			 * ActivityManager activityMgr = (ActivityManager) context
			 * .getSystemService(Context.ACTIVITY_SERVICE);
			 * activityMgr.killBackgroundProcesses(context.getPackageName());
			 */
			System.exit(0);
		} catch (Exception e) {
		}
	}

	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;

	public void exitBy2Click(Context context) {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			ToastUtil.getInstance(context).showToast("再按一次退出程序");
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			AppExit();
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
