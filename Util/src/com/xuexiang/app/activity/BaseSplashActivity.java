package com.xuexiang.app.activity;

import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author xx 启动页
 */
public abstract class BaseSplashActivity extends Activity {
	protected LinearLayout mWelcomeLayout;
	public SettingSharePreferenceUtil mSettingManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSettingManager = SettingSharePreferenceUtil.getInstance(this);
		Window window = getWindow();// 获取当前的窗体对象
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏了状态栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏了标题栏

		onCreateActivity();

	}
	
	public void initSplashView(int splashImgResid) {
		mWelcomeLayout = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mWelcomeLayout.setLayoutParams(params);
		mWelcomeLayout.setOrientation(LinearLayout.VERTICAL);
		mWelcomeLayout.setBackgroundResource(splashImgResid);
		setContentView(mWelcomeLayout);
	}
	
	/**
	 * activity启动后的初始化
	 */
	public abstract void onCreateActivity();
	
	/**
	 * 启动动画结束后的动作
	 */
	public abstract void welcomeFunction();
	
	/**
	 * 启动动画
	 */
	public void startSplashAnim() {
		AlphaAnimation animail = new AlphaAnimation(0.1f, 1.0f);
		animail.setDuration(3000);
		mWelcomeLayout.startAnimation(animail);
		animail.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				welcomeFunction();
			}
		});
	}
	
}
