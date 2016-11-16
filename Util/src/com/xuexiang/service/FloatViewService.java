package com.xuexiang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 悬浮框服务
 * @author xx
 *
 */
public class FloatViewService extends Service {
	private FloatViewManager mFloatViewManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		createFloatView();
  	
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void createFloatView() {
		mFloatViewManager = FloatViewManager.getInstance(getApplicationContext());
		mFloatViewManager.initFloatView();
		mFloatViewManager.showFloatView();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mFloatViewManager.dismissFloatView();
	}
	
}
