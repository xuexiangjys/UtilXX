package com.xuexiang.util.imageloader.lru;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * @author xx
 * @Date 2017-5-25 上午11:30:55
 */
public class LruImageLoader {

	/**
	 * 加载网络图片
	 * @param url
	 * @param imageView
	 */
	public static void loadImage(String url, ImageView imageView){
		Bitmap bitmap = LruCacheUtils.getInstance().getBitmapFromMemCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			displayImageTarget(imageView, url, getTarget(imageView, url));
		}
	}

	/**
	 * 加载图片 Target
	 * 
	 * @param imageView
	 * @param target
	 * @param url
	 */
	public static void displayImageTarget(final ImageView imageView, final String url, BitmapImageViewTarget target) {
		Glide.get(imageView.getContext()).with(imageView.getContext()).load(url).asBitmap()// 强制转换Bitmap
				.diskCacheStrategy(DiskCacheStrategy.NONE).into(target);
	}

	/**
	 * 获取BitmapImageViewTarget
	 */
	private static BitmapImageViewTarget getTarget(ImageView imageView, final String url) {
		return new BitmapImageViewTarget(imageView) {
			@Override
			protected void setResource(Bitmap resource) {
				super.setResource(resource);
				// 缓存Bitmap，以便于在没有用到时，自动回收
				LruCacheUtils.getInstance().addBitmapToMemoryCache(url, resource);
			}
		};
	}
}
