package com.xuexiang.util.imageloader.loader;

import android.graphics.Bitmap;

/** 
 * 网络图片加载监听
 * @author xx
 * @Date 2017-8-21 下午2:24:42 
 */
public interface OnLoadListener {
	void onLoadFailed(Throwable e);

    void onLoadReady(Bitmap bitmap);

    void onLoadCanceled();
}
