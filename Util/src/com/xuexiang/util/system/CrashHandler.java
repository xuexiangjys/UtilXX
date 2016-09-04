package com.xuexiang.util.system;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

import com.xuexiang.util.data.DateUtils;
import com.xuexiang.util.file.LocalFileUtil;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	// 系统默认的UncaughtException处理类
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	// CrashHandler实例
	private static CrashHandler INSTANCE;
	// 程序的Context对象
	private Context mContext;

	//保证只有一个CrashHandler实例 
	private CrashHandler() {

	}

	//获取CrashHandler实例 ,单例模式
	public static CrashHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该重写的方法来处理
	 */
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果自定义的没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 *            异常信息
	 * @return true 如果处理了该异常信息;否则返回false.
	 */
	public boolean handleException(Throwable ex) {
		if (ex == null || mContext == null)
			return false;
		final String crashReport = getCrashReport(mContext, ex);
		new Thread() {
			public void run() {
				Looper.prepare();
				File file = save2File(crashReport);
				sendAppCrashReport(mContext, crashReport, file);
				Looper.loop();
			}

		}.start();
		return true;
	}

	@SuppressLint("SimpleDateFormat")
	private File save2File(String crashReport) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				String CRASH_LOG_PATH = LocalFileUtil.LOG_DIR
						+ "crash_log/";
				File file = new File(CRASH_LOG_PATH + "crash_log_"
						+ DateUtils.formatDate(new Date(), DateUtils.yyyyMMddHHmmss) + ".txt");
				if (file.getParentFile().exists() == false) {
					file.getParentFile().mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(crashReport.toString().getBytes());
				fos.close();
				return file;
			} catch (Exception e) {
				//sd卡存储，记得加上权限，不然这里会抛出异常
				Log.i("Show","save2File error:" + e.getMessage());
			}
		}
		return null;
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
							String[] tos = { "way.ping.li@gmail.com" };
							intent.putExtra(Intent.EXTRA_EMAIL, tos);

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

}