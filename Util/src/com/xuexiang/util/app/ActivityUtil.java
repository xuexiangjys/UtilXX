package com.xuexiang.util.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xuexiang.app.BaseApplication;
import com.xuexiang.util.common.ToastUtil;
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
	public static TitleBar initTitleBar(final Activity activity, String title) {
		TitleBar mTitleBar = initTitleBar(activity, title, new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				activity.finish();
			}
		});
		return mTitleBar;
	}

	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static TitleBar initTitleBar(final Activity activity, String title, OnClickListener listener) {
		TitleBar titleBar = (TitleBar) activity.findViewById(RUtils.getId(activity, "title_bar"));
		titleBar.setImmersive(false);

		titleBar.setBackgroundColor(Color.parseColor("#64b4ff"));

		titleBar.setLeftImageResource(RUtils.getDrawable(activity, "back_white"));
		titleBar.setLeftText("返回");
		titleBar.setLeftTextColor(Color.WHITE);
		titleBar.setLeftClickListener(listener);
		titleBar.setTitle(title);
		titleBar.setTitleColor(Color.WHITE);
		titleBar.setSubTitleColor(Color.WHITE);
		titleBar.setDividerColor(Color.GRAY);
		titleBar.setActionTextColor(Color.WHITE);
		return titleBar;
	}

	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static TitleBar initTitleBarDynamic(final Activity activity, String title) {
		TitleBar mTitleBar = initTitleBarDynamic(activity, title, new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				activity.finish();
			}
		});
		return mTitleBar;
	}

	/**
	 * 动态生成TitleBar
	 */
	public static TitleBar initTitleBarDynamic(Context context, String title, OnClickListener listener) {
		TitleBar titleBar = new TitleBar(context);
		RelativeLayout.LayoutParams titleBarParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		titleBarParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleBar.setLayoutParams(titleBarParams);

		titleBar.setImmersive(false);

		titleBar.setBackgroundColor(Color.parseColor("#64b4ff"));

		titleBar.setLeftImageResource(RUtils.getDrawable(context, "back_white"));
		titleBar.setLeftText("返回");
		titleBar.setLeftTextColor(Color.WHITE);
		titleBar.setLeftClickListener(listener);
		titleBar.setTitle(title);
		titleBar.setTitleColor(Color.WHITE);
		titleBar.setSubTitleColor(Color.WHITE);
		titleBar.setDividerColor(Color.GRAY);
		titleBar.setActionTextColor(Color.WHITE);

		return titleBar;
	}

	/**
	 * 动态生成TitleBar
	 * 
	 * @param activity
	 * @return
	 */
	public static void initTitleBarDynamic(Activity activity) {
		ViewGroup rootView = getRootView(activity);
		TitleBar titleBar = initTitleBarDynamic(activity, activity.getClass().getSimpleName());
		rootView.addView(titleBar, 0);
	}
	
	/**
	 * 获取setContentView的父布局
	 * 
	 * @param activity
	 * @return
	 */
	public static ViewGroup getRootView(Activity activity) {
		return (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
	}
	
	/**
	 * 获取setContentView的父布局
	 * 
	 * @param activity
	 * @return
	 */
	public static FrameLayout getContentView(Activity activity) {
		ViewGroup view = (ViewGroup) activity.getWindow().getDecorView();
		FrameLayout content = (FrameLayout) view.findViewById(android.R.id.content);
		return content;
	}

	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static void initTitleBar(final Activity activity, String title, OnClickListener leftClickListener, TitleBar.ImageAction imageAction) {
		TitleBar mTitleBar = initTitleBar(activity, title, leftClickListener);
		mTitleBar.addAction(imageAction);
	}

	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public static void initTitleBarWithRightMenu(final Activity activity, String title, TitleBar.ImageAction imageAction) {
		TitleBar mTitleBar = initTitleBar(activity, title);
		mTitleBar.addAction(imageAction);
	}

	/**
	 * 延迟去往新的Activity
	 * 
	 * @param context
	 * @param cls
	 * @param delay
	 */
	public static void delayToActivity(final Context context, final Class<? extends Activity> cls, long delay) {
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
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);
	}

	/**
	 * 跳转到另一个Activity，携带数据
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		context.startActivity(intent);
	}

	/**
	 * 功能描述：带数据的Activity之间的跳转
	 * 
	 * @param activity
	 * @param cls
	 * @param hashMap
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls, HashMap<String, Object> hashMap) {
		Intent intent = new Intent(context, cls);
		Iterator<?> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String) {
				intent.putExtra(key, (String) value);
			} else if (value instanceof Boolean) {
				intent.putExtra(key, (Boolean) value);
			} else if (value instanceof Integer) {
				intent.putExtra(key, (Integer) value);
			} else if (value instanceof Float) {
				intent.putExtra(key, (Float) value);
			} else if (value instanceof Double) {
				intent.putExtra(key, (Double) value);
			} else if (value instanceof Serializable) {
				Bundle bundle = new Bundle();
				bundle.putSerializable(key, (Serializable) value);
				intent.putExtras(bundle);
			}
		}
		context.startActivity(intent);
	}

	/*********************************************** 有动作的启动activity *****************************************************************/
	/**
	 * go to activity,use animation
	 * 
	 * @param context
	 * @param cls
	 * @param enterAnim
	 * @param exitAnim
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls, int enterAnim, int exitAnim, Bundle bundle) {
		Activity activity = (Activity) context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(enterAnim, exitAnim);
	}

	/**
	 * to new activity,use animation from right to left
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromLeft2Right(Context context, Class<? extends Activity> cls) {
		startActivityFromLeft2Right(context, cls, null);
	}

	/**
	 * to new activity,use animation from right to left carry data
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromLeft2Right(Context context, Class<? extends Activity> cls, Bundle bundle) {
		Activity activity = (Activity) context;
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
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromRight2Left(Context context, Class<? extends Activity> cls) {
		startActivityFromRight2Left(context, cls, null);
	}

	/**
	 * to new activity,use animation from left to right carry data
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromRight2Left(Context context, Class<? extends Activity> cls, Bundle bundle) {
		Activity activity = (Activity) context;
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
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromBottom2Top(Context context, Class<? extends Activity> cls) {
		startActivityFromBottom2Top(context, cls, null);
	}

	/**
	 * to new activity,use animation from bottom to top carry data
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromBottom2Top(Context context, Class<? extends Activity> cls, Bundle bundle) {
		Activity activity = (Activity) context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_bottom"), RUtils.getAnim(context, "out_to_top"));
	}

	/**
	 * to new activity,use animation form top to bottom
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivityFromTop2Bottom(Context context, Class<? extends Activity> cls) {
		startActivityFromTop2Bottom(context, cls, null);
	}

	/**
	 * to new activity,use animation from bottom to top carry data
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void startActivityFromTop2Bottom(Context context, Class<? extends Activity> cls, Bundle bundle) {
		Activity activity = (Activity) context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(RUtils.getAnim(context, "in_from_top"), RUtils.getAnim(context, "out_to_bottom"));
	}

	/****************************************************** 启动动作归类 ********************************************************************************************/
	public enum StartAnim {
		Left2Right, Right2Left, Bottom2Top, Top2Bottom,
	}

	/**
	 * 跳转到另一个Activity，携带数据
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls, Bundle bundle, StartAnim startAnim) {
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
	 * 
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class<? extends Activity> cls, StartAnim startAnim) {
		startActivity(context, cls, null, startAnim);
	}

	// =================================================全局的UI========================================================================//
	/**
	 * 在主线程中运行
	 * 
	 * @param r
	 */
	public static void runOnUIThread(Runnable r) {
		BaseApplication.getAppHandler().post(r);
	}

	/**
	 * 在主线程中Toast
	 * 
	 * @param msg
	 */
	public static void toastOnUIThread(final String msg) {
		BaseApplication.getAppHandler().post(new Runnable() {
			@Override
			public void run() {
				ToastUtil.getInstance(BaseApplication.getContext()).showToast(msg);
			}
		});
	}

	/**
	 * 获取resources对象
	 * 
	 * @return
	 */
	public static Resources getResources() {
		return BaseApplication.getContext().getResources();
	}

	/**
	 * 获取字符串
	 * 
	 * @param resId
	 * @return
	 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**
	 * 获取资源图片
	 * 
	 * @param resId
	 * @return
	 */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/**
	 * 获取dimes值
	 * 
	 * @param resId
	 * @return
	 */
	public static float getDimens(int resId) {
		return getResources().getDimension(resId);
	}

	/**
	 * 获取字符串的数组
	 * 
	 * @param resId
	 * @return
	 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	// =================================================BaseActivity========================================================================//
	public void Toast(CharSequence hint) {
		Toast.makeText(BaseApplication.getContext(), hint, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 检验EditText内容是否为空
	 * 
	 * @param et
	 *            EditText控件
	 * @param msg
	 *            为空时的提示文字
	 * @return
	 */
	public boolean IsEditTextEmpty(EditText et, String msg) {
		boolean result = false;
		if (TextUtils.isEmpty(et.getText().toString())) {
			if (SettingSharePreferenceUtil.getInstance(BaseApplication.getContext()).isAllowVibrate()) {
				new EditTextShakeHelper(BaseApplication.getContext()).shake(et);
			}
			Toast(msg);
			result = true;
		}
		return result;
	}

}
