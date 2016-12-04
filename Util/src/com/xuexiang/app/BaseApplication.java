package com.xuexiang.app;

import com.xuexiang.app.activityswitcher.ActivitySwitcher;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

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

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		mAppHandler = new Handler();
		mInstance = new BaseApplication();
		ActivitySwitcher.getInstance().init(this);
	}

	@Override
	public void onTerminate() {

		super.onTerminate();

	}

	// 在内存低时,发送广播可以释放一些内存
	@Override
	public void onLowMemory() {
		super.onLowMemory();
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


}
