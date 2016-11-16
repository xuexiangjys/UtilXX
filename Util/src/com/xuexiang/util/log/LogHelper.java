package com.xuexiang.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
/**
 * 日志工具类
 * 用来保存debug、error、Crash等Log
 */
public class LogHelper {

	private static Context mContext;
	private static boolean mIsDebugMode = false;// 标识DebugMode的状态，获取堆栈信息会影响性能，发布应用时记得关闭DebugMode,在需要时打开。

	private static final String CLASS_METHOD_LINE_FORMAT = "%s.%s()  Line:%d  (%s)\r\n\n";//格式化日志信息
	private static FileOutputStream mLogWriter = null;
	private static String ERROR_LOG_PATH;
	private static String mLineBreak = "\r\n";//换行符
	public static String mDebugLogPath;

	/**
	 * 用于格式化日期,作为日志文件名的一部分 SimpleDateFormat : mSimpleDateFormat
	 */
	private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	// 格式化日期，用于打印每行日志的时间
	private static SimpleDateFormat mLineTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat mErrorLogDateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss.SSS");

	/**
	 * 打开Log Helper
	 * @param deleteOld 是否删除旧的日志
	 */
	public static void open(Context ctx, boolean deleteOld) {

		if (mIsDebugMode == false) {
			mContext = ctx;
			mDebugLogPath = LocalFileUtil.LOG_DIR + "debug_log/";
			
			if(deleteOld){
				File debugLogDir = new File(mDebugLogPath);
				FileUtils.DeleteFile(debugLogDir);
			}			
			
			String debugLogName = mDebugLogPath + "debug_log_"+mSimpleDateFormat.format(new Date()) + ".txt";
			
			File debugLogFile = new File(debugLogName);
			if(debugLogFile.getParentFile().exists()==false)
				debugLogFile.getParentFile().mkdirs();
			
			try {
				mLogWriter = new FileOutputStream(debugLogFile, true);
			} catch (IOException e) {
				ToastUtil.getInstance(mContext).showToast("打开日志助手失败！");
				e.printStackTrace();
				return;
			}
			mIsDebugMode = true;
			saveDeviceInfo();
			trace("Open Log Helper!");
			ToastUtil.getInstance(mContext).showToast("日志助手已打开！");
		}

	}

	/**
	 * 关闭Log Helper
	 */
	public static void close() {
		if (mIsDebugMode) {
			trace("Close Log Helper!");
			mIsDebugMode = false;
			
			try {
				if (mLogWriter != null)
					mLogWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				saveExceptionStackInfo(e);
			}
			
			
			mContext = null;
		}

	}

	/**
	 * 追踪 某个操作所调用的具体类和方法
	 * 
	 * @throws
	 */
	public static void trace() {
		if (mIsDebugMode) {
			StackTraceElement traceElement = Thread.currentThread()
					.getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息
			String logtext = mLineTime.format(new Date()) + mLineBreak;
			logtext += "stack: "+ String.format(CLASS_METHOD_LINE_FORMAT, traceElement
					.getClassName(), traceElement.getMethodName(), traceElement
					.getLineNumber(), traceElement.getFileName());
			logtext += mLineBreak+mLineBreak+mLineBreak;
			logtext += "-------------------------------------------------------------"+mLineBreak;

			write(logtext);
		}
	}

	public static void trace(String info) {
		if (mIsDebugMode) {
			StackTraceElement traceElement = Thread.currentThread()
					.getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息
			String logtext = mLineTime.format(new Date()) + mLineBreak;
			
			logtext += "stack: "+String.format(CLASS_METHOD_LINE_FORMAT, traceElement
					.getClassName(), traceElement.getMethodName(), traceElement
					.getLineNumber(), traceElement.getFileName()) + mLineBreak;
			
			logtext+="【trace info: " + info + "】" + mLineBreak+ mLineBreak+ mLineBreak+ mLineBreak;
			logtext += "-------------------------------------------------------------"+mLineBreak;

			write(logtext);
		}
	}

	/**
	 * 保存一个异常的堆栈信息
	 * @param ex
	 */
	public static void saveExceptionStackInfo(Throwable e){
		PrintStream printStream = null;
		ERROR_LOG_PATH =  LocalFileUtil.LOG_DIR  + "error_log/";
		File file = new File(ERROR_LOG_PATH + "error_log_" + mErrorLogDateFormat.format(new Date()) + ".txt");
		if(file.getParentFile().exists()==false)
			file.getParentFile().mkdirs();
		try {
			printStream = new PrintStream(file);
			e.printStackTrace(printStream);
			
			printStream.flush();
			printStream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	

	/**
	 * saveDeviceInfo:{保存设备参数信息}
	 */
	public static void saveDeviceInfo() {

		try {
			mLogWriter.write(getDevicesInfo(mContext).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static String getDevicesInfo(Context paramContext){
		Map<String, String> logInfo = new HashMap<String, String>();
		try {
			// 获得包管理器
			PackageManager mPackageManager = paramContext.getPackageManager();
			// 得到该应用的信息，即主Activity
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(
					paramContext.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (mPackageInfo != null) {
				String versionName = mPackageInfo.versionName == null ? "null"
						: mPackageInfo.versionName;
				String versionCode = mPackageInfo.versionCode + "";
				logInfo.put("versionName", versionName);
				logInfo.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		// 反射机制
		Field[] mFields = Build.class.getDeclaredFields();
		// 迭代Build的字段key-value 此处的信息主要是为了在服务器端手机各种版本手机报错的原因
		for (Field field : mFields) {
			try {
				field.setAccessible(true);
				logInfo.put(field.getName(), field.get("").toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		StringBuffer stringBuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : logInfo.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			stringBuffer.append(key + "=" + value + "\r\n");
		}
		stringBuffer.append(mLineBreak);
		stringBuffer.append(mLineBreak);
		stringBuffer.append("-------------------------------------------------------------"+mLineBreak);
		
		return stringBuffer.toString();
	}
	
	public static void write(String logText){
		
		try {
			mLogWriter.write(logText.getBytes());
			mLogWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
