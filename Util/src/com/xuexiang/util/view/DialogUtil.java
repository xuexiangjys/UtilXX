package com.xuexiang.util.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

/**
 * 对话框显示的工具类
 *
 */
public class DialogUtil {
	
	public static ProgressDialog getProgressDia(Activity context,String message){
		ProgressDialog progressdialog = ProgressDialog.show(context, "Loading!",message);
		return progressdialog;
	}
	
	
	public static void showDialog(Context context, String title, String msg,
			String leftbtn, String rightbtn,
			OnClickListener LeftOnClickListener,
			OnClickListener RightOnClickListener, boolean cancelable) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setCancelable(cancelable);
		builder.setTitle(title).setMessage(msg)
				.setNegativeButton(leftbtn, LeftOnClickListener)
				.setPositiveButton(rightbtn, RightOnClickListener).create()
				.show();
	}
    
    /**
     * 改变数据的提示
     * @param ctx
     * @param yesListener
     */
    public static void showChangePrompt(Context ctx,String message,OnClickListener yesListener,OnClickListener noListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_dialog_info);
    	builder.setTitle(
				"提示");
		builder.setMessage(message);
		builder.setPositiveButton("是", yesListener);
		builder.setNegativeButton("否", noListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
    }
    
    /**
	 * 进度条对话框
	 * 
	 * @param title
	 * @param msg
	 */
	public static ProgressDialog progressDialog(Context context,final int maxCount,String title, String msg) {
		final ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setCancelable(false);// 设置点击空白处也不能关闭该对话框
		
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax(maxCount);
		dialog.setIndeterminate(false);// 设置显示明确的进度
		dialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 这里添加点击后的逻辑
					}
				});
		dialog.show();
	  return dialog;
	}

    /**
     * 设置网络的提示
     * @param ctx
     * @param message
     */
    public static void showSettingsNetwork(Context ctx,String message){
    	final Context context = ctx;
    	AlertDialog.Builder builder = new Builder(ctx);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("提示");
		builder.setMessage(message);
		builder.setPositiveButton("设置", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}
		});
		
		builder.setNegativeButton("取消", null);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
    
    /**
     * 确认对话框
     * @param ctx
     * @param message
     * @param yesListener
     */
    public static AlertDialog getConfirmDialog(Context ctx,String message,OnClickListener yesListener){
		return getConfirmDialog(ctx, null, message, yesListener);
    }
    
    /**
     * 确认对话框
     * @param ctx
     * @param message
     * @param yesListener
     */
    public static AlertDialog getConfirmDialog(Context ctx, String title, String message, OnClickListener yesListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_dialog_info);
    	if (TextUtils.isEmpty(title)) {
    		builder.setTitle("提示");
    	} else {
    		builder.setTitle(title);
    	}   	
		builder.setMessage(message);
		builder.setPositiveButton("确定", yesListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);

		return dialog;
    }
    
    /**
     * 自定义显示内容的提示框
     * @param ctx
     * @param yesListener
     */
    public static void showCustomPrompt(Context ctx,String message,String yesMsg,String noMsg,OnClickListener yesListener,OnClickListener noListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_delete);
    	builder.setTitle(
				"删除");
		builder.setMessage(message);
		builder.setPositiveButton(yesMsg, yesListener);
		builder.setNegativeButton(noMsg, noListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
    }
    
    /**
     * 填写设置的自定义对话框
     * @param ctx
     * @param title 标题
     * @param et edittext控件
     * @param edtcontent 填写内容
     * @param yesListener
     */
    public static void showFilloutDialog(Context ctx,String title,EditText et,String edtcontent,OnClickListener yesListener){
		et.setText(edtcontent);
    	AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(et)
				.setNegativeButton("取消", null);
		builder.setPositiveButton("确定",yesListener).create().show();
    }

}
