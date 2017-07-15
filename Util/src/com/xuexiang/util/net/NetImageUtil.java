package com.xuexiang.util.net;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.widget.ImageView;

import com.thirdparty.volley.toolbox.ImageLoader;
import com.thirdparty.volley.toolbox.ImageLoader.ImageListener;
import com.thirdparty.volley.toolbox.NetworkImageView;
import com.xuexiang.app.BaseApplication;
import com.xuexiang.app.SampleApplication;
import com.xuexiang.util.resource.RUtils;

/**  
 * 创建时间：2016-5-17 上午9:42:25  
 * 项目名称：VolleyDemo  
 * @author xuexiang
 * 文件名称：LoadNetImageUtil.java  
 **/
public class NetImageUtil {
	
	
	/************************************************Volley框架*********************************************************************************/
	
    public static void getImage(String url,ImageView imageView){
		
		ImageLoader imageLoader = new ImageLoader(SampleApplication.getVolleyRequestQueue(),BitmapCache.instance());
		// 图片监听 （默认图片，错误图片） 和 imageView
		ImageListener imageListener = ImageLoader.getImageListener(imageView, RUtils.getDrawable(BaseApplication.getContext(), "noimg"), RUtils.getDrawable(BaseApplication.getContext(), "noimg"));		
		//加载图片
		imageLoader.get(url, imageListener);
		
	}
    
    
    public static void getNetImage(String url, NetworkImageView imageView) {
        ImageLoader imageLoader = new ImageLoader(SampleApplication.getVolleyRequestQueue(),BitmapCache.instance());		
        imageView.setDefaultImageResId(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        imageView.setErrorImageResId(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        imageView.setImageUrl(url, imageLoader);
	}
    
   /************************************************Final框架*********************************************************************************/
	
    public static void getImage(Context context, String url, ImageView imageView){
    	FinalBitmap bitmap = FinalBitmap.create(context);
    	bitmap.configLoadingImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
    	bitmap.configLoadfailImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        bitmap.display(imageView, url);	
	}
    
    public static void getFinalImage(String url, ImageView imageView){
    	FinalBitmap bitmap = FinalBitmap.create(BaseApplication.getContext());
    	bitmap.configLoadingImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
    	bitmap.configLoadfailImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        bitmap.display(imageView, url);	
    }
	
	
}
