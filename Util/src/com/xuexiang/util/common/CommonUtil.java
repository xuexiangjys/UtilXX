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
package com.xuexiang.util.common;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

/**
 * 通用工具类
 *
 * @author jingle1267@163.com
 */
public class CommonUtil {

    /**
     * 是否有SDCard
     *
     * @return 是否有SDCard
     */
    public static boolean hasSDCard() {

        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {

        return Runtime.getRuntime().maxMemory() / 1024;
    }
    
    /**
	 * 根据应用名字去应用市场查询该应用 
	 * @param context
	 * @param appName market://search?q=pub:听听中心
	 */
	public static void searchTingting(Context context,String appName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://search?q=pub:"+ appName +""));
		context.startActivity(intent);
	}
	/**
	 * 根据应用的包名，去应用市场搜索该应用
	 * com.google.android.voicesearch google语音
	 * com.snda.tts.service 听听中心
	 * @param context
	 * @param appPckName
	 */
	public static void searchAppByPkgName(Context context, String appPckName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + appPckName));
		context.startActivity(intent);
	}
	
	public static void call(Context context, String phoneNumber) {
		Intent myIntentDial = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));  
		context.startActivity(myIntentDial); 
	}
	
	
	public static void shareImage(final Context context, File file) {
		if (file != null) {
			Intent intent=new Intent(Intent.ACTION_SEND);  
			intent.setType("image/*");
			Uri u = Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, u);
			context.startActivity(Intent.createChooser(intent, "分享"));
		}
	}
	/**
	 *打开设置网页界面
	 */
	public static void openSettingNet(Context context) {
        Intent intent = null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if(android.os.Build.VERSION.SDK_INT>10){
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }
	
	public static void openBroswer(Context context, String url) {
		Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));  
        context.startActivity(it); 
	}
	
	public static void openImage(Context context, String path) {
		if (path != null && path.length() > 0 && context != null) {
			Intent intent = new Intent(Intent.ACTION_VIEW);     
			intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
			context.startActivity(intent);
		}
	}
	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "提示", "确定", null);
	}

	public static void showInfoDialog(Context context, String message,
			String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}

	public static Bitmap getImageFromAssetsFile(Context ct, String fileName) {
		Bitmap image = null;
		AssetManager am = ct.getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;

	}

	/**
	 * 更新刷新时间
	 * @param created
	 * @return
	 */
	public static String getUploadtime(long created) {
		StringBuffer when = new StringBuffer();
		int difference_seconds;
		int difference_minutes;
		int difference_hours;
		int difference_days;
		int difference_months;
		long curTime = System.currentTimeMillis();
		difference_months = (int) (((curTime / 2592000) % 12) - ((created / 2592000) % 12));
		if (difference_months > 0) {
			when.append(difference_months + "月");
		}

		difference_days = (int) (((curTime / 86400) % 30) - ((created / 86400) % 30));
		if (difference_days > 0) {
			when.append(difference_days + "天");
		}

		difference_hours = (int) (((curTime / 3600) % 24) - ((created / 3600) % 24));
		if (difference_hours > 0) {
			when.append(difference_hours + "小时");
		}

		difference_minutes = (int) (((curTime / 60) % 60) - ((created / 60) % 60));
		if (difference_minutes > 0) {
			when.append(difference_minutes + "分钟");
		}

		difference_seconds = (int) ((curTime % 60) - (created % 60));
		if (difference_seconds > 0) {
			when.append(difference_seconds + "秒");
		}

		return when.append("前").toString();
	}

}
