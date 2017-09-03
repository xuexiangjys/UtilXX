package com.xuexiang.util.imageloader.loader;

import android.graphics.Bitmap;


/**
 * 加载回调
 * @author XUE
 *
 */
public abstract class OnLoadCallback implements OnLoadListener{

	@Override
	public void onLoadFailed(Throwable e) {
		
	}

	public abstract void onLoadReady(Bitmap bitmap);
	
	@Override
	public void onLoadCanceled() {
		
	}
}
