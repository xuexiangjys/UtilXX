package com.xuexiang.util.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.resource.RUtils;


/**
 * 
 * 软件更新
 * @author xx
 *
 */
public class UpdateManager {
	private Context mContext;
	// 提示语
	private String mUpdateMsg;
	// 返回的安装包url
	private String mApkUrl;
	private Dialog mNoticeDialog;
	private Dialog mDownloadDialog;
	private String mApkName;
	private String mSaveFileName;
	/* 进度条与通知ui刷新的handler和msg常量 */
	private boolean ishide = false;
	private ProgressBar mProgress;
	private TextView mTextView;
	private Notification mDownloadNotification;
	private NotificationManager mDownloadNM;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private int downNotiID = 21;
	private int progress = 0;
	private boolean interceptFlag = false;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				if (ishide) {
					updateProgress(progress);
				} else {
					mProgress.setProgress(progress);
				}
				break;
			case DOWN_OVER:
				if (ishide) {
					mDownloadNM.cancel(downNotiID);
				} else {
					mDownloadDialog.cancel();
				}
				installApk();
				break;
			default:
				break;
			}
		};
	};

	/**
	 * @param context
	 * @param apkName 应用名
	 * @param updatemsg 更新内容  以；隔开内容
	 * @param dlurl 下载地址
	 */
	public UpdateManager(Context context, String apkName, String updatemsg, String dlurl) {
		mContext = context;
		mApkName = apkName;
		mSaveFileName = LocalFileUtil.APK_PATH + mApkName;
		mUpdateMsg = getUpdateMsg(updatemsg);
		mApkUrl = dlurl;
	}

	public void showNoticeDialog() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(RUtils.getString(mContext, "software_update"));
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(RUtils.getLayout(mContext, "dialog_software_update"), null);
		mTextView = (TextView) v.findViewById(RUtils.getId(mContext, "update_msg"));
		mTextView.setText(mUpdateMsg);
		builder.setView(v);
		builder.setPositiveButton(RUtils.getString(mContext, "download"), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mNoticeDialog.dismiss();
				showDownloadDialog();
			}
		});
		builder.setNegativeButton(RUtils.getString(mContext, "system_exit"), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityManager.getInstance().AppExit();
			}
		}).setCancelable(false);
		mNoticeDialog = builder.create();
		mNoticeDialog.show();
	}

	private String getUpdateMsg(String msg) {
		if (!msg.equals("") && msg.length() > 0) {
			msg = msg.replace(";", "\n");
		}
		return msg;
	}

	public void showDownloadDialog() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(RUtils.getString(mContext, "software_update"));
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(RUtils.getLayout(mContext, "dialog_software_update"), null);
		mProgress = (ProgressBar) v.findViewById(RUtils.getId(mContext, "progress"));
		mTextView = (TextView) v.findViewById(RUtils.getId(mContext, "update_msg"));
		mTextView.setText(mUpdateMsg);
		mProgress.setVisibility(View.VISIBLE);
		builder.setView(v).setCancelable(false);
		builder.setPositiveButton(RUtils.getString(mContext, "hide"), new OnClickListener() {
		@Override
	    public void onClick(DialogInterface dialog, int which) {
			mDownloadDialog.dismiss();
			initNotif();
			ishide = true;
		}
		});
		builder.setNegativeButton(RUtils.getString(mContext, "system_cancel"), new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			mDownloadDialog.dismiss();
			interceptFlag = true;
		}
		});
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		downloadApk();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(mApkUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				File file = new File(LocalFileUtil.APK_PATH);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = mSaveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[8192];
				Timer mTimer = new Timer();
				mTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						mHandler.sendEmptyMessage(DOWN_UPDATE);
					}
				}, 0, 100);

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_UPDATE);
						mHandler.sendEmptyMessage(DOWN_OVER);
						mTimer.cancel();
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.
				mTimer.cancel();
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	@SuppressWarnings("deprecation")
	public void initNotif() {
		mDownloadNM = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mDownloadNotification = new Notification(RUtils.getDrawable(mContext, "ic_launcher"), mApkName
				+ mContext.getResources().getString(RUtils.getString(mContext, "download")),
				System.currentTimeMillis());
		mDownloadNotification.contentView = new RemoteViews(
				mContext.getPackageName(), RUtils.getLayout(mContext, "custom_notification"));
		// 显示下载的包名
		mDownloadNotification.contentView.setTextViewText(RUtils.getId(mContext, "down_tv"), "正在下载：" + mApkName);
		// 显示下载的进度
		mDownloadNotification.contentView.setTextViewText(RUtils.getId(mContext, "down_rate"), "下载进度：" + "0%");
		mDownloadNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		mDownloadNM.notify(downNotiID, mDownloadNotification);
	}

	public void updateProgress(int progress) {
		mDownloadNotification.contentView.setTextViewText(RUtils.getId(mContext, "down_rate"),
				"下载进度："+ progress + "%");
		mDownloadNM.notify(downNotiID, mDownloadNotification);

	}

	/**
	 * 下载apk
	 * 
	 * @param url
	 */

	private void downloadApk() {
		ThreadPoolManager.getInstance().addTask(mdownApkRunnable);
	}

	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(mSaveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);

	}
}