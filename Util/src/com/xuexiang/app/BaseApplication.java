package com.xuexiang.app;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.OkHttpStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.net.okhttp.OkHttpUtils;
import com.xuexiang.util.net.okhttp.https.HttpsUtils;
import com.xuexiang.util.net.okhttp.log.LoggerInterceptor;
import com.xuexiang.util.net.okhttp.persistentcookiejar.ClearableCookieJar;
import com.xuexiang.util.net.okhttp.persistentcookiejar.PersistentCookieJar;
import com.xuexiang.util.net.okhttp.persistentcookiejar.cache.SetCookieCache;
import com.xuexiang.util.net.okhttp.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xuexiang.util.view.FlexibleToast;

/**
 * 捕获应用异常Application 在这里完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置,
 * 预加载处理
 * 
 * @author jingle1267@163.com
 */

public class BaseApplication extends Application {
	public static String app_url = "http://192.168.43.186:8080/";
	public Stack<Activity> activityStack = null;
	private SettingSharePreferenceUtil mSettingManager;
	private static BaseApplication mInstance;
	private static Context mContext;
	/** Volley请求队列 */
	private static RequestQueue mRequestQueue;

	// 全局的 handler 对象
	private static Handler mAppHandler = new Handler();
	// 全局的 Toast 对象
	private static FlexibleToast mFlexibleToast;
	private static FlexibleToast.Builder mBuilder;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		// Volley+Okhttp
		mRequestQueue = Volley.newRequestQueue(getApplicationContext(),
				new OkHttpStack(new OkHttpClient()));
		// Okhttp
		ClearableCookieJar cookieJar = new PersistentCookieJar(
				new SetCookieCache(), new SharedPrefsCookiePersistor(
						getApplicationContext()));
		HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,
				null, null);
		okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
				.connectTimeout(10000L, TimeUnit.MILLISECONDS)
				.readTimeout(10000L, TimeUnit.MILLISECONDS)
				.addInterceptor(new LoggerInterceptor("OkHttpClient"))
				.cookieJar(cookieJar)
				.hostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				})
				.sslSocketFactory(sslParams.sSLSocketFactory,
						sslParams.trustManager).build();
		OkHttpUtils.initClient(okHttpClient);

		// activity管理
		activityStack = new Stack<Activity>();
		// 异常处理
		BaseCrashHandler.getInstance().init(this);
		// CrashHandler.getInstance().init(this);

		mInstance = new BaseApplication();
		mFlexibleToast = new FlexibleToast(this);
		mSettingManager = SettingSharePreferenceUtil.getInstance(this);
		if (!TextUtils.isEmpty(mSettingManager.getAppUrl())) {
			app_url = mSettingManager.getAppUrl();
		}
		// 程序异常关闭1s之后重新启动
		// new RebootThreadExceptionHandler(getBaseContext());
		LogUtils.e("欢迎使用xx的Util！");
	}

	@Override
	public void onTerminate() {

		super.onTerminate();

	}

	// 在内存低时,发送广播可以释放一些内存
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// /退出整个应用
	public void exitApp() {
		for (Activity activity : activityStack) {
			if (activity != null) {
				activity.finish();
			}
		}
	}

	public static BaseApplication getInstance() {
		return mInstance;
	}

	// 入口
	public static RequestQueue getVolleyRequestQueue() {
		return mRequestQueue;
	}

	// 入口
	public static Context getContext() {
		return mContext;
	}

	public static Handler getAppHandler() {
		return mAppHandler;
	}

	public static void toastShowByBuilder(FlexibleToast.Builder builder) {
		mBuilder = builder;
		if (Looper.myLooper() != Looper.getMainLooper()) {
			getAppHandler().post(new Runnable() {
				@Override
				public void run() {
					mFlexibleToast.toastShow(mBuilder);
				}
			});
		} else {
			mFlexibleToast.toastShow(mBuilder);
		}
	}

}
