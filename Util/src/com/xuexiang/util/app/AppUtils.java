/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.security.auth.x500.X500Principal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.xuexiang.util.common.ShellUtils;
import com.xuexiang.util.data.CleanUtils;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.security.EncryptUtils;

/**
 * APP工具类 APP相关信息工具类。获取版本信息
 * 
 * @author jingle1267@163.com
 */
public final class AppUtils {

	private static final boolean DEBUG = true;
	private static final String TAG = "AppUtils";

	/**
	 * Don't let anyone instantiate this class.
	 */
	private AppUtils() {
		throw new Error("Do not need instantiate!");
	}

	/**
	 * 得到软件版本号
	 * 
	 * @param context
	 *            上下文
	 * @return 当前版本Code
	 */
	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			String packageName = context.getPackageName();
			verCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 得到软件显示版本信息
	 * 
	 * @param context
	 *            上下文
	 * @return 当前版本信息
	 */
	public static String getVerName(Context context) {
		String verName = "";
		try {
			String packageName = context.getPackageName();
			verName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return verName;
	}

	/**
	 * 得到应用的名称
	 * 
	 * @param context
	 *            上下文
	 * @return 当前版本信息
	 */
	public static String getAppName(Context context, int pID) {
		String processName = "";
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
		Iterator<RunningAppProcessInfo> iterator = processInfoList.iterator();
		PackageManager pm = context.getPackageManager();
		while (iterator.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (iterator.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
					processName = info.processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}

	/**
	 * 安装apk
	 * 
	 * @param context
	 *            上下文
	 * @param file
	 *            APK文件
	 */
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 安装apk
	 * 
	 * @param context
	 *            上下文
	 * @param file
	 *            APK文件uri
	 */
	public static void installApk(Context context, Uri file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(file, "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 卸载apk
	 * 
	 * @param context
	 *            上下文
	 * @param packageName
	 *            包名
	 */
	public static void uninstallApk(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}

	/**
	 * 检测服务是否运行
	 * 
	 * @param context
	 *            上下文
	 * @param className
	 *            类名
	 * @return 是否运行的状态
	 */
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
		for (RunningServiceInfo si : servicesList) {
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}

	/**
	 * 停止运行服务
	 * 
	 * @param context
	 *            上下文
	 * @param className
	 *            类名
	 * @return 是否执行成功
	 */
	public static boolean stopRunningService(Context context, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(context, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = context.stopService(intent_service);
		}
		return ret;
	}

	/**
	 * 停止指定应用的所以运行的服务
	 * 
	 * @param context
	 */
	public static void stopAllRunningService(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
		for (RunningServiceInfo si : servicesList) {
			if (context.getPackageName().equals(si.service.getPackageName())) {
				stopRunningService(context, si.service.getClassName());
			}
		}
	}

	/**
	 * 打开应用
	 * 
	 * @param context
	 * @param packageName
	 */
	public static void openApp(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			resolveIntent.setPackage(pi.packageName);
			List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, 0);
			ResolveInfo ri = apps.iterator().next();
			if (ri != null) {
				String packagename = ri.activityInfo.packageName;
				String className = ri.activityInfo.name;
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);// Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
				ComponentName cn = new ComponentName(packagename, className);
				intent.setComponent(cn);
				context.startActivity(intent);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到CPU核心数
	 * 
	 * @return CPU核心数
	 */
	public static int getNumCores() {
		try {
			File dir = new File("/sys/devices/system/cpu/");
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (Pattern.matches("cpu[0-9]", pathname.getName())) {
						return true;
					}
					return false;
				}
			});
			return files.length;
		} catch (Exception e) {
			return 1;
		}
	}

	/**
	 * whether this process is named with processName
	 * 
	 * @param context
	 *            上下文
	 * @param processName
	 *            进程名
	 * @return <ul>
	 *         return whether this process is named with processName
	 *         <li>if context is null, return false</li>
	 *         <li>if {@link ActivityManager#getRunningAppProcesses()} is null,
	 *         return false</li>
	 *         <li>if one process of
	 *         {@link ActivityManager#getRunningAppProcesses()} is equal to
	 *         processName, return true, otherwise return false</li>
	 *         </ul>
	 */
	public static boolean isNamedProcess(Context context, String processName) {
		if (context == null || TextUtils.isEmpty(processName)) {
			return false;
		}

		int pid = android.os.Process.myPid();
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
		if (processInfoList == null) {
			return true;
		}

		for (RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
			if (processInfo.pid == pid && processName.equalsIgnoreCase(processInfo.processName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * whether application is in background
	 * <ul>
	 * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
	 * </ul>
	 * 
	 * @param context
	 *            上下文
	 * @return if application is in background return true, otherwise return
	 *         false
	 */
	public static boolean isApplicationInBackground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(1);
		if (taskList != null && !taskList.isEmpty()) {
			ComponentName topActivity = taskList.get(0).topActivity;
			if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * whether application is Running
	 * <ul>
	 * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
	 * </ul>
	 * 
	 * @param context
	 *            上下文
	 * @return if application is running return true, otherwise return false
	 */
	public static boolean isApplicationRunning(Context context) {
		boolean isAppRunning = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(Integer.MAX_VALUE);
		if (taskList != null && !taskList.isEmpty()) {
			for (RunningTaskInfo runningTaskInfo : taskList) {
				ComponentName topActivity = runningTaskInfo.topActivity;
				ComponentName baseActivity = runningTaskInfo.baseActivity;
				if (topActivity != null && baseActivity != null) {
					if (topActivity.getPackageName().equals(context.getPackageName()) && baseActivity.getPackageName().equals(context.getPackageName())) {
						isAppRunning = true;
						break;
					}
				}
			}
		}
		return isAppRunning;
	}

	/**
	 * 获取应用签名
	 * 
	 * @param context
	 *            上下文
	 * @param pkgName
	 *            包名
	 */
	public static String getSign(Context context, String pkgName) {
		try {
			PackageInfo pis = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
			return hexdigest(pis.signatures[0].toByteArray());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将签名字符串转换成需要的32位签名
	 * 
	 * @param paramArrayOfByte
	 *            签名byte数组
	 * @return 32位签名字符串
	 */
	private static String hexdigest(byte[] paramArrayOfByte) {
		final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramArrayOfByte);
			byte[] arrayOfByte = localMessageDigest.digest();
			char[] arrayOfChar = new char[32];
			for (int i = 0, j = 0;; i++, j++) {
				if (i >= 16) {
					return new String(arrayOfChar);
				}
				int k = arrayOfByte[i];
				arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
				arrayOfChar[++j] = hexDigits[(k & 0xF)];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 清理后台进程与服务
	 * 
	 * @param context
	 *            应用上下文对象context
	 * @return 被清理的数量
	 */
	public static int gc(Context context) {
		long i = getDeviceUsableMemory(context);
		int count = 0; // 清理掉的进程数
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		// 获取正在运行的service列表
		List<RunningServiceInfo> serviceList = am.getRunningServices(100);
		if (serviceList != null)
			for (RunningServiceInfo service : serviceList) {
				if (service.pid == android.os.Process.myPid())
					continue;
				try {
					android.os.Process.killProcess(service.pid);
					count++;
				} catch (Exception e) {
					e.getStackTrace();
				}
			}

		// 获取正在运行的进程列表
		List<RunningAppProcessInfo> processList = am.getRunningAppProcesses();
		if (processList != null)
			for (RunningAppProcessInfo process : processList) {
				// 一般数值大于RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了
				// 一般数值大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的进程都是非可见进程，也就是在后台运行着
				if (process.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
					// pkgList 得到该进程下运行的包名
					String[] pkgList = process.pkgList;
					for (String pkgName : pkgList) {
						if (DEBUG) {
							LogUtils.d(TAG, "======正在杀死包名：" + pkgName);
						}
						try {
							am.killBackgroundProcesses(pkgName);
							count++;
						} catch (Exception e) { // 防止意外发生
							e.getStackTrace();
						}
					}
				}
			}
		if (DEBUG) {
			LogUtils.d(TAG, "清理了" + (getDeviceUsableMemory(context) - i) + "M内存");
		}
		return count;
	}

	/**
	 * 获取设备的可用内存大小
	 * 
	 * @param context
	 *            应用上下文对象context
	 * @return 当前内存大小
	 */
	public static int getDeviceUsableMemory(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		// 返回当前系统的可用内存
		return (int) (mi.availMem / (1024 * 1024));
	}

	/**
	 * 获取系统中所有的应用
	 * 
	 * @param context
	 *            上下文
	 * @return 应用信息List
	 */
	public static List<PackageInfo> getAllApps(Context context) {

		List<PackageInfo> apps = new ArrayList<PackageInfo>();
		PackageManager pManager = context.getPackageManager();
		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
		for (int i = 0; i < paklist.size(); i++) {
			PackageInfo pak = paklist.get(i);
			if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				// customs applications
				apps.add(pak);
			}
		}
		return apps;
	}

	/**
	 * 获取手机系统SDK版本
	 * 
	 * @return 如API 17 则返回 17
	 */
	public static int getSDKVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 是否Dalvik模式
	 * 
	 * @return 结果
	 */
	public static boolean isDalvik() {
		return "Dalvik".equals(getCurrentRuntimeValue());
	}

	/**
	 * 是否ART模式
	 * 
	 * @return 结果
	 */
	public static boolean isART() {
		String currentRuntime = getCurrentRuntimeValue();
		return "ART".equals(currentRuntime) || "ART debug build".equals(currentRuntime);
	}

	/**
	 * 获取手机当前的Runtime
	 * 
	 * @return 正常情况下可能取值Dalvik, ART, ART debug build;
	 */
	public static String getCurrentRuntimeValue() {
		try {
			Class<?> systemProperties = Class.forName("android.os.SystemProperties");
			try {
				Method get = systemProperties.getMethod("get", String.class, String.class);
				if (get == null) {
					return "WTF?!";
				}
				try {
					final String value = (String) get.invoke(systemProperties, "persist.sys.dalvik.vm.lib",
					/* Assuming default is */"Dalvik");
					if ("libdvm.so".equals(value)) {
						return "Dalvik";
					} else if ("libart.so".equals(value)) {
						return "ART";
					} else if ("libartd.so".equals(value)) {
						return "ART debug build";
					}

					return value;
				} catch (IllegalAccessException e) {
					return "IllegalAccessException";
				} catch (IllegalArgumentException e) {
					return "IllegalArgumentException";
				} catch (InvocationTargetException e) {
					return "InvocationTargetException";
				}
			} catch (NoSuchMethodException e) {
				return "SystemProperties.get(String key, String def) method is not found";
			}
		} catch (ClassNotFoundException e) {
			return "SystemProperties class is not found";
		}
	}

	private final static X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");

	/**
	 * 检测当前应用是否是Debug版本
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isDebuggable(Context ctx) {
		boolean debuggable = false;
		try {
			PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
			Signature signatures[] = pinfo.signatures;
			for (int i = 0; i < signatures.length; i++) {
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
				X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
				debuggable = cert.getSubjectX500Principal().equals(DEBUG_DN);
				if (debuggable)
					break;
			}

		} catch (NameNotFoundException e) {
		} catch (CertificateException e) {
		}
		return debuggable;
	}

	/**
	 * 获取设备唯一标识
	 * 
	 * @param context
	 * @return
	 */
	public static String getUUID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, tmPhone, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		if (com.xuexiang.BuildConfig.DEBUG)
			Log.d(TAG, "uuid=" + uniqueId);

		return uniqueId;
	}

	/**
	 * 是否是主线程
	 * 
	 * @return
	 */
	public static boolean isMainProcess(Context context) {
		ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = context.getPackageName();
		int myPid = android.os.Process.myPid();
		for (RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}

	private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

	/**
	 * 计算状态栏高度高度 getStatusBarHeight
	 * 
	 * @return
	 */
	public static int getStatusBarHeight() {
		return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
	}

	private static int getInternalDimensionSize(Resources res, String key) {
		int result = 0;
		int resourceId = res.getIdentifier(key, "dimen", "android");
		if (resourceId > 0) {
			result = res.getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 获取版本号
	 */
	public static int getAppVersion(Context context) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionCode;
	}

	/**
	 * 判断App是否安装
	 * 
	 * @param packageName
	 *            包名
	 * @return {@code true}: 已安装<br>
	 *         {@code false}: 未安装
	 */
	public static boolean isInstallApp(Context context, String packageName) {
		return !isSpace(packageName) && IntentUtils.getLaunchAppIntent(context, packageName) != null;
	}

	/**
	 * 安装App(支持7.0)
	 * 
	 * @param filePath
	 *            文件路径
	 * @param authority
	 *            7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性 <br>
	 *            参看https://developer.android.com/reference/android/support/v4/
	 *            content/FileProvider.html
	 */
	public static void installApp(Context context, String filePath, String authority) {
		installApp(context, FileUtils.getFileByPath(filePath), authority);
	}

	/**
	 * 安装App（支持7.0）
	 * 
	 * @param file
	 *            文件
	 * @param authority
	 *            7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性 <br>
	 *            参看https://developer.android.com/reference/android/support/v4/
	 *            content/FileProvider.html
	 */
	public static void installApp(Context context, File file, String authority) {
		if (!FileUtils.isFileExists(file))
			return;
		context.startActivity(IntentUtils.getInstallAppIntent(context, file, authority));
	}

	/**
	 * 安装App（支持6.0）
	 * 
	 * @param activity
	 *            activity
	 * @param filePath
	 *            文件路径
	 * @param authority
	 *            7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性 <br>
	 *            参看https://developer.android.com/reference/android/support/v4/
	 *            content/FileProvider.html
	 * @param requestCode
	 *            请求值
	 */
	public static void installApp(Activity activity, String filePath, String authority, int requestCode) {
		installApp(activity, FileUtils.getFileByPath(filePath), authority, requestCode);
	}

	/**
	 * 安装App(支持6.0)
	 * 
	 * @param activity
	 *            activity
	 * @param file
	 *            文件
	 * @param authority
	 *            7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性 <br>
	 *            参看https://developer.android.com/reference/android/support/v4/
	 *            content/FileProvider.html
	 * @param requestCode
	 *            请求值
	 */
	public static void installApp(Activity activity, File file, String authority, int requestCode) {
		if (!FileUtils.isFileExists(file))
			return;
		activity.startActivityForResult(IntentUtils.getInstallAppIntent(activity, file, authority), requestCode);
	}

	/**
	 * 静默安装App
	 * <p>
	 * 非root需添加权限
	 * {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}
	 * </p>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return {@code true}: 安装成功<br>
	 *         {@code false}: 安装失败
	 */
	public static boolean installAppSilent(Context context, String filePath) {
		File file = FileUtils.getFileByPath(filePath);
		if (!FileUtils.isFileExists(file))
			return false;
		String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
		ShellUtils.CommandResult commandResult = ShellUtils.execCommand(command, !isSystemApp(context), true);
		return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
	}

	/**
	 * 卸载App
	 * 
	 * @param packageName
	 *            包名
	 */
	public static void uninstallApp(Context context, String packageName) {
		if (isSpace(packageName))
			return;
		context.startActivity(IntentUtils.getUninstallAppIntent(packageName));
	}

	/**
	 * 卸载App
	 * 
	 * @param activity
	 *            activity
	 * @param packageName
	 *            包名
	 * @param requestCode
	 *            请求值
	 */
	public static void uninstallApp(Activity activity, String packageName, int requestCode) {
		if (isSpace(packageName))
			return;
		activity.startActivityForResult(IntentUtils.getUninstallAppIntent(packageName), requestCode);
	}

	/**
	 * 静默卸载App
	 * <p>
	 * 非root需添加权限
	 * {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}
	 * </p>
	 * 
	 * @param packageName
	 *            包名
	 * @param isKeepData
	 *            是否保留数据
	 * @return {@code true}: 卸载成功<br>
	 *         {@code false}: 卸载失败
	 */
	public static boolean uninstallAppSilent(Context context, String packageName, boolean isKeepData) {
		if (isSpace(packageName))
			return false;
		String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
		ShellUtils.CommandResult commandResult = ShellUtils.execCommand(command, !isSystemApp(context), true);
		return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
	}

	/**
	 * 判断App是否有root权限
	 * 
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isAppRoot() {
		ShellUtils.CommandResult result = ShellUtils.execCommand("echo root", true);
		if (result.result == 0) {
			return true;
		}
		if (result.errorMsg != null) {
			LogUtils.d("isAppRoot", result.errorMsg);
		}
		return false;
	}

	/**
	 * 打开App
	 * 
	 * @param packageName
	 *            包名
	 */
	public static void launchApp(Context context, String packageName) {
		if (isSpace(packageName))
			return;
		context.startActivity(IntentUtils.getLaunchAppIntent(context, packageName));
	}

	/**
	 * 打开App
	 * 
	 * @param activity
	 *            activity
	 * @param packageName
	 *            包名
	 * @param requestCode
	 *            请求值
	 */
	public static void launchApp(Activity activity, String packageName, int requestCode) {
		if (isSpace(packageName))
			return;
		activity.startActivityForResult(IntentUtils.getLaunchAppIntent(activity, packageName), requestCode);
	}

	/**
	 * 获取App包名
	 * 
	 * @return App包名
	 */
	public static String getAppPackageName(Context context) {
		return context.getPackageName();
	}

	/**
	 * 获取App具体设置
	 */
	public static void getAppDetailsSettings(Context context) {
		getAppDetailsSettings(context, context.getPackageName());
	}

	/**
	 * 获取App具体设置
	 * 
	 * @param packageName
	 *            包名
	 */
	public static void getAppDetailsSettings(Context context, String packageName) {
		if (isSpace(packageName))
			return;
		context.startActivity(IntentUtils.getAppDetailsSettingsIntent(packageName));
	}

	/**
	 * 获取App名称
	 * 
	 * @return App名称
	 */
	public static String getAppName(Context context) {
		return getAppName(context, context.getPackageName());
	}

	/**
	 * 获取App名称
	 * 
	 * @param packageName
	 *            包名
	 * @return App名称
	 */
	public static String getAppName(Context context, String packageName) {
		if (isSpace(packageName))
			return null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取App图标
	 * 
	 * @return App图标
	 */
	public static Drawable getAppIcon(Context context) {
		return getAppIcon(context, context.getPackageName());
	}

	/**
	 * 获取App图标
	 * 
	 * @param packageName
	 *            包名
	 * @return App图标
	 */
	public static Drawable getAppIcon(Context context, String packageName) {
		if (isSpace(packageName))
			return null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return pi == null ? null : pi.applicationInfo.loadIcon(pm);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取App路径
	 * 
	 * @return App路径
	 */
	public static String getAppPath(Context context) {
		return getAppPath(context, context.getPackageName());
	}

	/**
	 * 获取App路径
	 * 
	 * @param packageName
	 *            包名
	 * @return App路径
	 */
	public static String getAppPath(Context context, String packageName) {
		if (isSpace(context.getPackageName()))
			return null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return pi == null ? null : pi.applicationInfo.sourceDir;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取App版本号
	 * 
	 * @return App版本号
	 */
	public static String getAppVersionName(Context context) {
		return getAppVersionName(context, context.getPackageName());
	}

	/**
	 * 获取App版本号
	 * 
	 * @param packageName
	 *            包名
	 * @return App版本号
	 */
	public static String getAppVersionName(Context context, String packageName) {
		if (isSpace(packageName))
			return null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return pi == null ? null : pi.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取App版本码
	 * 
	 * @return App版本码
	 */
	public static int getAppVersionCode(Context context) {
		return getAppVersionCode(context, context.getPackageName());
	}

	/**
	 * 获取App版本码
	 * 
	 * @param packageName
	 *            包名
	 * @return App版本码
	 */
	public static int getAppVersionCode(Context context, String packageName) {
		if (isSpace(packageName))
			return -1;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return pi == null ? -1 : pi.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 判断App是否是系统应用
	 * 
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isSystemApp(Context context) {
		return isSystemApp(context, context.getPackageName());
	}

	/**
	 * 判断App是否是系统应用
	 * 
	 * @param packageName
	 *            包名
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isSystemApp(Context context, String packageName) {
		if (isSpace(packageName))
			return false;
		try {
			PackageManager pm = context.getPackageManager();
			ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
			return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断App是否是Debug版本
	 * 
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isAppDebug(Context context) {
		return isAppDebug(context, context.getPackageName());
	}

	/**
	 * 判断App是否是Debug版本
	 * 
	 * @param packageName
	 *            包名
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isAppDebug(Context context, String packageName) {
		if (isSpace(packageName))
			return false;
		try {
			PackageManager pm = context.getPackageManager();
			ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
			return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取App签名
	 * 
	 * @return App签名
	 */
	public static Signature[] getAppSignature(Context context) {
		return getAppSignature(context, context.getPackageName());
	}

	/**
	 * 获取App签名
	 * 
	 * @param packageName
	 *            包名
	 * @return App签名
	 */
	public static Signature[] getAppSignature(Context context, String packageName) {
		if (isSpace(packageName))
			return null;
		try {
			PackageManager pm = context.getPackageManager();
			@SuppressLint("PackageManagerGetSignatures")
			PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			return pi == null ? null : pi.signatures;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取应用签名的的SHA1值
	 * <p>
	 * 可据此判断高德，百度地图key是否正确
	 * </p>
	 * 
	 * @return 应用签名的SHA1字符串,
	 *         比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
	 */
	public static String getAppSignatureSHA1(Context context) {
		return getAppSignatureSHA1(context, context.getPackageName());
	}

	/**
	 * 获取应用签名的的SHA1值
	 * <p>
	 * 可据此判断高德，百度地图key是否正确
	 * </p>
	 * 
	 * @param packageName
	 *            包名
	 * @return 应用签名的SHA1字符串,
	 *         比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
	 */
	public static String getAppSignatureSHA1(Context context, String packageName) {
		Signature[] signature = getAppSignature(context, packageName);
		if (signature == null)
			return null;
		return EncryptUtils.encryptSHA1ToString(signature[0].toByteArray()).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
	}

	/**
	 * 判断App是否处于前台
	 * 
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isAppForeground(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> info = manager.getRunningAppProcesses();
		if (info == null || info.size() == 0)
			return false;
		for (ActivityManager.RunningAppProcessInfo aInfo : info) {
			if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return aInfo.processName.equals(context.getPackageName());
			}
		}
		return false;
	}

	/**
	 * 判断App是否处于前台
	 * <p>
	 * 当不是查看当前App，且SDK大于21时， 需添加权限
	 * {@code <uses-permission android:name="android.permission.PACKAGE_USAGE_STATS"/>}
	 * </p>
	 * 
	 * @param packageName
	 *            包名
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isAppForeground(Context context, String packageName) {
		return !isSpace(packageName) && packageName.equals(ProcessUtils.getForegroundProcessName(context));
	}

	/**
	 * 封装App信息的Bean类
	 */
	public static class AppInfo {

		private String name;
		private Drawable icon;
		private String packageName;
		private String packagePath;
		private String versionName;
		private int versionCode;
		private boolean isSystem;

		public Drawable getIcon() {
			return icon;
		}

		public void setIcon(Drawable icon) {
			this.icon = icon;
		}

		public boolean isSystem() {
			return isSystem;
		}

		public void setSystem(boolean isSystem) {
			this.isSystem = isSystem;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public String getPackagePath() {
			return packagePath;
		}

		public void setPackagePath(String packagePath) {
			this.packagePath = packagePath;
		}

		public int getVersionCode() {
			return versionCode;
		}

		public void setVersionCode(int versionCode) {
			this.versionCode = versionCode;
		}

		public String getVersionName() {
			return versionName;
		}

		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}

		/**
		 * @param name
		 *            名称
		 * @param icon
		 *            图标
		 * @param packageName
		 *            包名
		 * @param packagePath
		 *            包路径
		 * @param versionName
		 *            版本号
		 * @param versionCode
		 *            版本码
		 * @param isSystem
		 *            是否系统应用
		 */
		public AppInfo(String packageName, String name, Drawable icon, String packagePath, String versionName, int versionCode, boolean isSystem) {
			this.setName(name);
			this.setIcon(icon);
			this.setPackageName(packageName);
			this.setPackagePath(packagePath);
			this.setVersionName(versionName);
			this.setVersionCode(versionCode);
			this.setSystem(isSystem);
		}

		@Override
		public String toString() {
			return "pkg name: " + getPackageName() + "\napp name: " + getName() + "\napp path: " + getPackagePath() + "\napp v name: " + getVersionName() + "\napp v code: " + getVersionCode()
					+ "\nis system: " + isSystem();
		}
	}

	/**
	 * 获取App信息
	 * <p>
	 * AppInfo（名称，图标，包名，版本号，版本Code，是否系统应用）
	 * </p>
	 * 
	 * @return 当前应用的AppInfo
	 */
	public static AppInfo getAppInfo(Context context) {
		return getAppInfo(context, context.getPackageName());
	}

	/**
	 * 获取App信息
	 * <p>
	 * AppInfo（名称，图标，包名，版本号，版本Code，是否系统应用）
	 * </p>
	 * 
	 * @param packageName
	 *            包名
	 * @return 当前应用的AppInfo
	 */
	public static AppInfo getAppInfo(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			return getBean(pm, pi);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到AppInfo的Bean
	 * 
	 * @param pm
	 *            包的管理
	 * @param pi
	 *            包的信息
	 * @return AppInfo类
	 */
	private static AppInfo getBean(PackageManager pm, PackageInfo pi) {
		if (pm == null || pi == null)
			return null;
		ApplicationInfo ai = pi.applicationInfo;
		String packageName = pi.packageName;
		String name = ai.loadLabel(pm).toString();
		Drawable icon = ai.loadIcon(pm);
		String packagePath = ai.sourceDir;
		String versionName = pi.versionName;
		int versionCode = pi.versionCode;
		boolean isSystem = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != 0;
		return new AppInfo(packageName, name, icon, packagePath, versionName, versionCode, isSystem);
	}

	/**
	 * 获取所有已安装App信息
	 * <p>
	 * {@link #getBean(PackageManager, PackageInfo)}
	 * （名称，图标，包名，包路径，版本号，版本Code，是否系统应用）
	 * </p>
	 * <p>
	 * 依赖上面的getBean方法
	 * </p>
	 * 
	 * @return 所有已安装的AppInfo列表
	 */
	public static List<AppInfo> getAppsInfo(Context context) {
		List<AppInfo> list = new ArrayList<>();
		PackageManager pm = context.getPackageManager();
		// 获取系统中安装的所有软件信息
		List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
		for (PackageInfo pi : installedPackages) {
			AppInfo ai = getBean(pm, pi);
			if (ai == null)
				continue;
			list.add(ai);
		}
		return list;
	}

	/**
	 * 清除App所有数据
	 * 
	 * @param dirPaths
	 *            目录路径
	 * @return {@code true}: 成功<br>
	 *         {@code false}: 失败
	 */
	public static boolean cleanAppData(Context context, String... dirPaths) {
		File[] dirs = new File[dirPaths.length];
		int i = 0;
		for (String dirPath : dirPaths) {
			dirs[i++] = new File(dirPath);
		}
		return cleanAppData(context, dirs);
	}

	/**
	 * 清除App所有数据
	 * 
	 * @param dirs
	 *            目录
	 * @return {@code true}: 成功<br>
	 *         {@code false}: 失败
	 */
	public static boolean cleanAppData(Context context, File... dirs) {
		boolean isSuccess = CleanUtils.cleanInternalCache(context);
		isSuccess &= CleanUtils.cleanInternalDbs(context);
		isSuccess &= CleanUtils.cleanInternalSP(context);
		isSuccess &= CleanUtils.cleanInternalFiles(context);
		isSuccess &= CleanUtils.cleanExternalCache(context);
		for (File dir : dirs) {
			isSuccess &= CleanUtils.cleanCustomCache(dir);
		}
		return isSuccess;
	}

	private static boolean isSpace(String s) {
		if (s == null)
			return true;
		for (int i = 0, len = s.length(); i < len; ++i) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
