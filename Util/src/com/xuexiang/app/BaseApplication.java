package com.xuexiang.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.xuexiang.app.activityswitcher.ActivitySwitcher;
import com.xuexiang.util.app.AppUtils;
import com.xuexiang.util.log.LogHelper;

/**
 * 捕获应用异常Application 在这里完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置,
 * 预加载处理
 * 
 * @author jingle1267@163.com
 */

public class BaseApplication extends Application {
	private static BaseApplication mInstance;
	private static Context mContext;

	// 全局的 handler 对象
	private static Handler mAppHandler;
	// 全局对象
	private static ActivityLifecycleHelper mActivityLifecycleHelper;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		mAppHandler = new Handler();
		mInstance = new BaseApplication();
		mActivityLifecycleHelper = new ActivityLifecycleHelper();
		registerActivityLifecycleCallbacks(mActivityLifecycleHelper);
		ActivitySwitcher.getInstance().init(this);
	}

	public static BaseApplication getInstance() {
		return mInstance;
	}

	// 入口
	public static Context getContext() {
		return mContext;
	}

	public static Handler getAppHandler() {
		return mAppHandler;
	}
	
	/**
	 * 退出程序
	 */
	public static void exitApp(Context context) {
		AppUtils.stopAllRunningService(context);
		mActivityLifecycleHelper.finishAllActivity();
		LogHelper.close();
		System.exit(0);
	}

}
