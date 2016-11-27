package com.xuexiang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 悬浮框服务
 * 
 * @author xx
 * 
 */
public class FloatViewService extends Service {
	private BaseFloatViewManager mFloatViewManager;
	public final static String FLOATVIEW_TYPE = "floatview_type";
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int floatViewType = intent.getIntExtra(FLOATVIEW_TYPE, 0);
		createFloatView(floatViewType);
		return START_REDELIVER_INTENT;
	}

	private void createFloatView(int floatViewType) {
		if (floatViewType == 0) {
			mFloatViewManager = FloatViewManager.getInstance(getApplicationContext());
			mFloatViewManager.showFloatView();
		} else if (floatViewType == 1) {
			mFloatViewManager = ButtonSwitchManager.getInstance(getApplicationContext());
			mFloatViewManager.showFloatView();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mFloatViewManager.dismissFloatView();
	}

}
