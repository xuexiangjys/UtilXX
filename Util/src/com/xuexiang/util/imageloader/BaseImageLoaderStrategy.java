package com.xuexiang.util.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.xuexiang.util.imageloader.glideprogress.ProgressLoadListener;
import com.xuexiang.util.imageloader.listener.ImageSaveListener;
import com.xuexiang.util.imageloader.listener.SourceReadyListener;


/**
 * Created by soulrelay on 2016/10/11.
 * Class Note:
 * abstract class/interface defined to load image
 * (Strategy Pattern used here)
 */
public interface BaseImageLoaderStrategy {
    //无占位图
    void loadImage(String url, ImageView imageView);

    //这里的context指定为ApplicationContext
    void loadImageWithAppCxt(String url, ImageView imageView);

    void loadImage(String url, int placeholder, ImageView imageView);

    void loadImage(Context context, String url, int placeholder, ImageView imageView);

    void loadCircleImage(String url, int placeholder, ImageView imageView);

    void loadCircleBorderImage(String url, int placeholder, ImageView imageView,float borderWidth, int borderColor);

    void loadCircleBorderImage(String url, int placeholder, ImageView imageView,float borderWidth, int borderColor, int heightPx, int widthPx);

    void loadGifImage(String url, int placeholder, ImageView imageView);

    void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener);

    void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, SourceReadyListener listener);

    void loadGifWithPrepareCall(String url, ImageView imageView, SourceReadyListener listener);

    //清除硬盘缓存
    void clearImageDiskCache(final Context context);

    //清除内存缓存
    void clearImageMemoryCache(Context context);

    //根据不同的内存状态，来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    //获取缓存大小
    String getCacheSize(Context context);

    void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener);

}
