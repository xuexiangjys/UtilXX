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
package com.xuexiang.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

import com.xuexiang.util.data.DateUtils;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.log.LogHelper;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.view.DialogUtil;

/**
 * 在Application中统一捕获异常，保存到文件中下次再打开时上传
 *
 * @author jingle1267@163.com
 */
public class BaseCrashHandler implements UncaughtExceptionHandler {

    /**
     * 是否开启日志输出,在Debug状态下开启, 在Release状态下关闭以提示程序性能
     */
    public static final boolean DEBUG = true;

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler实例
     */
    private static BaseCrashHandler INSTANCE;

    private Context mContext;
    
    /**
     * 自处理崩溃时间
     */
    private int mHandlerTime = 60;  //默认一分钟

    /**
     * 保证只有一个CrashHandler实例
     */
    private BaseCrashHandler() {
    }

	public void setHandlerTime(int handlerTime) {
		mHandlerTime = handlerTime;
	}

	/**
     * 获取CrashHandler实例 ,单例模式
     */
    public static BaseCrashHandler getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new BaseCrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     *
     * @param context 上下文
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
    	LogUtils.e("BaseCrashHandler正在处理");
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else { // 如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            try {
                Thread.sleep(mHandlerTime * 1000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }

    }
    
    

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null || mContext == null)
			return false;
		final String crashReport = getCrashReport(mContext, ex);
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				File file = saveCrashLogToFile(ex);
				sendAppCrashReport(mContext, crashReport, file);
				Looper.loop();
			}
		}.start();
		return true;
	}
	
	/**
	 * 获取APP崩溃异常报告
	 * 
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pinfo = getPackageInfo(context);
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version: " + pinfo.versionName + "("
				+ pinfo.versionCode + ")\n");
		exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
				+ "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception: " + ex.getMessage() + "\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}
	
	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	private PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			 e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}
    
    /**
	 * 保存程序崩溃时的日志
	 * 
	 * @param paramThrowable
	 */
	private File saveCrashLogToFile(Throwable paramThrowable) {
		String CRASH_LOG_PATH = LocalFileUtil.LOG_DIR
				+ "crash_log/";
		File file = new File(CRASH_LOG_PATH + "crash_log_"
				+ DateUtils.formatDate(new Date(), DateUtils.yyyyMMddHHmmss) + ".txt");
		if (file.getParentFile().exists() == false) {
			file.getParentFile().mkdirs();
		}
		
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
			ps.write(LogHelper.getDevicesInfo(mContext).getBytes());
			paramThrowable.printStackTrace(ps);
			ps.flush();
			return file;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			LogHelper.saveExceptionStackInfo(e1);
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.saveExceptionStackInfo(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return null;
	}
	
	/**
	 * 显示崩溃详细信息
	 * @param crashFile
	 */
	private void showCrashDetail(Context context, File crashFile) {
		AlertDialog dialog = DialogUtil.getConfirmDialog(context, "崩溃详情", FileUtils.readFile(crashFile.getPath(), "UTF-8").toString(), new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 退出
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}});
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}
	
	private void sendAppCrashReport(final Context context,
			final String crashReport, final File file) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setTitle("应用程序错误")
		.setMessage("很抱歉，应用程序出现错误，即将退出。\n请选择邮件的方式将此错误报告提交给我，我会尽快修复这个问题，谢谢！")
		.setPositiveButton("提交报告",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						try {

							//这以下的内容，只做参考，因为没有服务器
							Intent intent = new Intent(Intent.ACTION_SEND);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							String[] tos = { "xuexiangandroid@163.com"};
							String[] ccs = { "xuexiangjys2012@gmail.com"};
							intent.putExtra(Intent.EXTRA_EMAIL, tos); //收件人
							intent.putExtra(Intent.EXTRA_CC, ccs);  //抄送者
							intent.putExtra(Intent.EXTRA_BCC, ccs);  //密送者

							intent.putExtra(Intent.EXTRA_SUBJECT,
									"Android客户端 - 错误报告");
							if (file != null) {
								intent.putExtra(Intent.EXTRA_STREAM,
										Uri.fromFile(file));
								intent.putExtra(Intent.EXTRA_TEXT,
										"请将此错误报告发送给我，以便我尽快修复此问题，谢谢合作！\n");
							} else {
								intent.putExtra(Intent.EXTRA_TEXT,
										"请将此错误报告发送给我，以便我尽快修复此问题，谢谢合作！\n"
												+ crashReport);
							}
							intent.setType("text/plain");
							intent.setType("message/rfc882");
							Intent.createChooser(intent, "Choose Email Client");
							context.startActivity(intent);
							
						} catch (Exception e) {
							Log.i("Show","error:" + e.getMessage());
						} finally {
							dialog.dismiss();
							// 退出
							android.os.Process.killProcess(android.os.Process.myPid());
							System.exit(1);
						}
					}
				})
		.setNeutralButton("查看详情", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showCrashDetail(context, file);
			}
		})
		.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						android.os.Process.killProcess(android.os.Process.myPid());
						System.exit(1);
					}
				});
		
		AlertDialog dialog = builder.create();
		//需要的窗口句柄方式，没有这句会报错的
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}


    /**
     * 获取手机信息
     *
     * @return
     */
    public String getPhoneInfo() {
        String phoneInfo = "Product: " + android.os.Build.PRODUCT;
        phoneInfo += ", CPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += ", TAGS: " + android.os.Build.TAGS;
        phoneInfo += ", VERSION_CODES.BASE: "
                + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ", MODEL: " + android.os.Build.MODEL;
        phoneInfo += ", SDK: " + android.os.Build.VERSION.SDK_INT;
        phoneInfo += ", VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ", DEVICE: " + android.os.Build.DEVICE;
        phoneInfo += ", DISPLAY: " + android.os.Build.DISPLAY;
        phoneInfo += ", BRAND: " + android.os.Build.BRAND;
        phoneInfo += ", BOARD: " + android.os.Build.BOARD;
        phoneInfo += ", FINGERPRINT: " + android.os.Build.FINGERPRINT;
        phoneInfo += ", ID: " + android.os.Build.ID;
        phoneInfo += ", MANUFACTURER: " + android.os.Build.MANUFACTURER;
        phoneInfo += ", USER: " + android.os.Build.USER;
        return phoneInfo;
    }
}