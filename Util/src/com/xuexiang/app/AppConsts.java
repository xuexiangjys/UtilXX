package com.xuexiang.app;

import android.support.v4.view.ViewPager;

import com.xuexiang.view.flycobanner.transform.DepthTransformer;
import com.xuexiang.view.flycobanner.transform.FadeSlideTransformer;
import com.xuexiang.view.flycobanner.transform.FlowTransformer;
import com.xuexiang.view.flycobanner.transform.RotateDownTransformer;
import com.xuexiang.view.flycobanner.transform.RotateUpTransformer;
import com.xuexiang.view.flycobanner.transform.ZoomOutSlideTransformer;


/**
 * 常量类
 *
 */
public class AppConsts {
	
	public static Class<? extends ViewPager.PageTransformer> transformers[] = new Class[]{
        DepthTransformer.class,
        FadeSlideTransformer.class,
        FlowTransformer.class,
        RotateDownTransformer.class,
        RotateUpTransformer.class,
        ZoomOutSlideTransformer.class,
    };
	
	
}
