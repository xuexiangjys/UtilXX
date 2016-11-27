package com.xuexiang.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.flycobanner.anim.select.ZoomInEnter;
import com.xuexiang.view.flycobanner.samples.SimpleGuideBanner;
import com.xuexiang.view.flycobanner.util.ViewFindUtils;

/**
 * 启动引导页
 * 
 * @author xx
 * 
 */
public abstract class BaseGuideActivity extends Activity {
	private Context mContext;
	private View mDecorView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(RUtils.getLayout(mContext, "activity_guide"));
		mDecorView = getWindow().getDecorView();
		onCreateActivity();
	}
	
	/**
	 * activity启动后的初始化
	 */
	public abstract void onCreateActivity();
	
	/**
	 * 初始化引导页动画
	 * @param guidesResIdList  引导图片
	 * @param cls  点击后跳转的Activity类
	 */
	public void initGuideView(ArrayList<Integer> guidesResIdList, final Class<?> cls) {
		initGuideView(guidesResIdList, AppConsts.transformers[0], cls);
	}
	
	/**
	 * 初始化引导页动画
	 * @param guidesResIdList  引导图片
	 * @param transformerClass  引导图片切换的效果
	 * @param cls  点击后跳转的Activity类
	 */
	public void initGuideView(ArrayList<Integer> guidesResIdList, Class<? extends ViewPager.PageTransformer> transformerClass, final Class<?> cls) {
		SimpleGuideBanner sgb = ViewFindUtils.find(mDecorView, RUtils.getId(mContext, "sgb"));

		sgb.setIndicatorWidth(6).setIndicatorHeight(6).setIndicatorGap(12)
				.setIndicatorCornerRadius(3.5f)
				.setSelectAnimClass(ZoomInEnter.class)
				.setTransformerClass(transformerClass).barPadding(0, 10, 0, 10)
				.setSource(guidesResIdList).startScroll();

		sgb.setOnJumpClickL(new SimpleGuideBanner.OnJumpClickL() {
			@Override
			public void onJumpClick() {
				ActivityUtil.startActivity(mContext, cls);
				finish();
			}
		});
	}
	
	/**
	 * 初始化引导页动画
	 * @param guidesResIdList  引导图片
	 * @param transformerClass  引导图片切换的效果
	 * @param cls  点击按钮的点击效果
	 */
	public void initGuideView(ArrayList<Integer> guidesResIdList, Class<? extends ViewPager.PageTransformer> transformerClass, SimpleGuideBanner.OnJumpClickL onJumpClickListener) {
		SimpleGuideBanner sgb = ViewFindUtils.find(mDecorView, RUtils.getId(mContext, "sgb"));

		sgb.setIndicatorWidth(6).setIndicatorHeight(6).setIndicatorGap(12)
				.setIndicatorCornerRadius(3.5f)
				.setSelectAnimClass(ZoomInEnter.class)
				.setTransformerClass(transformerClass).barPadding(0, 10, 0, 10)
				.setSource(guidesResIdList).startScroll();

		sgb.setOnJumpClickL(onJumpClickListener);
	}
}
