package com.xuexiang.util.app;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.xuexiang.app.BaseApplication;
import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.system.EditTextShakeHelper;
import com.xuexiang.view.TitleBar;
/**
 * @Description: Activity的工具类
 */ 
public class ActivityUtil {
	
	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static void initTitleBar(final Activity activity, String title){
		initTitleBar(activity, title, new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				activity.finish();
			}
		});
	}
	
	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static void initTitleBar(final Activity activity, String title, OnClickListener listener) {
		TitleBar mTitleBar = (TitleBar) activity.findViewById(RUtils.getId(activity, "title_bar"));
		mTitleBar.setImmersive(false);

		mTitleBar.setBackgroundColor(Color.parseColor("#64b4ff"));

		mTitleBar.setLeftImageResource(RUtils.getDrawable(activity, "back_white"));
        mTitleBar.setLeftText("返回");
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.setLeftClickListener(listener);        
        mTitleBar.setTitle(title);
        mTitleBar.setTitleColor(Color.WHITE);
        mTitleBar.setSubTitleColor(Color.WHITE);
        mTitleBar.setDividerColor(Color.GRAY);
        mTitleBar.setActionTextColor(Color.WHITE);
	}
	
	/**
	 * 延迟去往新的Activity
	 * @param context
	 * @param cls
	 * @param delay
	 */
	public static void delayToActivity(final Context context,final Class<?> cls,long delay) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				context.startActivity(new Intent(context, cls));
			}
		}, delay);
	}
	/**
	 * 跳转到另一个Activity，不携带数据，不设置flag
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);
	}
	
	/**
	 * 跳转到另一个Activity，携带数据
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		context.startActivity(intent);
	}

/***********************************************有动作的启动activity*****************************************************************/
	/**
	 * go to activity,use animation
	 * @param context
	 * @param cls
	 * @param enterAnim
	 * @param exitAnim
	 */
	public static void startActivity(Context context, Class<?> cls, int enterAnim, int exitAnim, Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(enterAnim, exitAnim);
	}
	/**
	 * to new activity,use animation from right to left
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromLeft2Right(Context context, Class<?> cls) {
		startActivityFromLeft2Right(context, cls, null);
	}
	/**
	 * to new activity,use animation from right to left carry data
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromLeft2Right(Context context, Class<?> cls, Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
		   intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_right"), RUtils.getAnim(context, "out_to_right"));
	}
	/**
	 * to new activity,use animation from left to right
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromRight2Left(Context context, Class<?> cls) {
		startActivityFromRight2Left(context, cls, null);
	}
	/**
	 * to new activity,use animation from left to right carry data
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromRight2Left(Context context,Class<?> cls, Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_left"), RUtils.getAnim(context, "out_to_left"));
	}
	
	/**
	 * to new activity,use animation from bottom to top
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromBottom2Top(Context context,Class<?> cls) {
		startActivityFromBottom2Top(context, cls, null);
	}
	
	/**
	 * to new activity,use animation from bottom to top carry data
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromBottom2Top(Context context,Class<?> cls,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_bottom"),RUtils.getAnim(context, "out_to_top"));
	}
	
	/**
	 * to new activity,use animation form top to bottom
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromTop2Bottom(Context context, Class<?> cls) {
		startActivityFromTop2Bottom(context, cls, null);
	}
	
	/**
	 * to new activity,use animation from bottom to top carry data
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromTop2Bottom(Context context,Class<?> cls,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_top"), RUtils.getAnim(context, "out_to_bottom"));
	}
	
/******************************************************启动动作归类********************************************************************************************/	
	public enum StartAnim {
		Left2Right,
		Right2Left,
		Bottom2Top,
		Top2Bottom,	
	}
	
	/**
	 * 跳转到另一个Activity，携带数据
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<?> cls, Bundle bundle, StartAnim startAnim) {
		if (startAnim != null) {
			switch (startAnim) {
			case Left2Right:
				startActivityFromLeft2Right(context, cls, bundle);
				break;
			case Right2Left:
				startActivityFromRight2Left(context, cls, bundle);
				break;
			case Bottom2Top:
				startActivityFromBottom2Top(context, cls, bundle);
				break;
			case Top2Bottom:
				startActivityFromTop2Bottom(context, cls, bundle);
				break;
	
			default:
				startActivity(context, cls, bundle);
				break;
			}
		}
	}
	
	/**
	 * 跳转到另一个Activity，携带数据
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<?> cls, StartAnim startAnim) {
		startActivity(context, cls, null, startAnim);
	}
	
	//=================================================全局的UI========================================================================//
	/**
	 * 在主线程中运行
	 * @param r
	 */
	public static void runOnUIThread(Runnable r){
		BaseApplication.getAppHandler().post(r);
	}
	
	/**
	 * 获取resources对象
	 * @return
	 */
	public static Resources getResources(){
		return BaseApplication.getContext().getResources();
	}
	
	/**
	 * 获取字符串
	 * @param resId
	 * @return
	 */
	public static String getString(int resId){
		return getResources().getString(resId);
	}

	/**
	 * 获取资源图片
	 * @param resId
	 * @return
     */
	public static Drawable getDrawable(int resId){
		return getResources().getDrawable(resId);
	}
	
	/**
	 * 获取dimes值
	 * @param resId
	 * @return
	 */
	public static float getDimens(int resId){
		return getResources().getDimension(resId);
	}
	
	/**
	 * 获取字符串的数组
	 * @param resId
	 * @return
	 */
	public static String[] getStringArray(int resId){
		return getResources().getStringArray(resId);
	}
	
	//=================================================BaseActivity========================================================================//
	public void Toast(CharSequence hint){
	    Toast.makeText(BaseApplication.getContext(), hint , Toast.LENGTH_SHORT).show();
	}	
	
	/**
	 * 检验EditText内容是否为空
	 * @param et  EditText控件
	 * @param msg 为空时的提示文字
	 * @return
	 */
	public boolean IsEditTextEmpty(EditText et, String msg){
		boolean result = false;
		if(TextUtils.isEmpty(et.getText().toString())){
			if(SettingSharePreferenceUtil.getInstance(BaseApplication.getContext()).isAllowVibrate()){
			  new EditTextShakeHelper(BaseApplication.getContext()).shake(et);
			}
			Toast(msg);
			result = true;
		}
		return result;
	}
	
}
