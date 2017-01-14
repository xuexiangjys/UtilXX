package com.xuexiang.util.view;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.data.DateUtils;
import com.xuexiang.view.dialog.CircularProgressDialog;
import com.xuexiang.view.dialog.CustomDialog;
import com.xuexiang.view.dialog.LoadingAnimatorDialog;
import com.xuexiang.view.dialog.MonIndicatorDialog;
import com.xuexiang.view.dialog.RoundProgressBarDialog;
import com.xuexiang.view.dialog.ShapeLoadingDialog;
import com.xuexiang.view.dialog.SpotsDialog;

/**
 * 对话框显示的工具类
 * 
 */
public class DialogUtil {

	public static ProgressDialog getProgressDia(Activity context, String message) {
		ProgressDialog progressdialog = ProgressDialog.show(context, "Loading!", message);
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
	 * 
	 * @param ctx
	 * @param yesListener
	 */
	public static void showChangePrompt(Context ctx, String message,
			OnClickListener yesListener, OnClickListener noListener) {
		AlertDialog.Builder builder = new Builder(ctx);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("提示");
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
	public static ProgressDialog progressDialog(Context context,
			final int maxCount, String title, String msg) {
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
	 * 
	 * @param ctx
	 * @param message
	 */
	public static void showSettingsNetwork(Context ctx, String message) {
		final Context context = ctx;
		AlertDialog.Builder builder = new Builder(ctx);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("提示");
		builder.setMessage(message);
		builder.setPositiveButton("设置", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(
						android.provider.Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}
		});

		builder.setNegativeButton("取消", null);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 确认对话框
	 * 
	 * @param ctx
	 * @param message
	 * @param yesListener
	 */
	public static AlertDialog getConfirmDialog(Context ctx, String message,
			OnClickListener yesListener) {
		return getConfirmDialog(ctx, null, message, yesListener);
	}

	/**
	 * 确认对话框
	 * 
	 * @param ctx
	 * @param message
	 * @param yesListener
	 */
	public static AlertDialog getConfirmDialog(Context ctx, String title,
			String message, OnClickListener yesListener) {
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
	 * 
	 * @param ctx
	 * @param yesListener
	 */
	public static void showCustomPrompt(Context ctx, String message,
			String yesMsg, String noMsg, OnClickListener yesListener,
			OnClickListener noListener) {
		AlertDialog.Builder builder = new Builder(ctx);
		builder.setIcon(android.R.drawable.ic_delete);
		builder.setTitle("删除");
		builder.setMessage(message);
		builder.setPositiveButton(yesMsg, yesListener);
		builder.setNegativeButton(noMsg, noListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	/**
	 * 填写设置的自定义对话框
	 * 
	 * @param ctx
	 * @param title
	 *            标题
	 * @param et
	 *            edittext控件
	 * @param edtcontent
	 *            填写内容
	 * @param yesListener
	 */
	public static void showFilloutDialog(Context ctx, String title,
			EditText et, String edtcontent, OnClickListener yesListener) {
		et.setText(edtcontent);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title).setIcon(android.R.drawable.ic_dialog_info)
				.setView(et).setNegativeButton("取消", null);
		builder.setPositiveButton("确定", yesListener).create().show();
	}
	
	//=======================================自定义loading================================================================================//
	/**
	 * loading框的样式
	 * @author xx
	 *
	 */
	public enum LoadingStyle {
		Circular, Spots , ShapeLoading, Transparent, RoundProgressBar, MonIndicator, LoadingAnimator
	}
	
    /**
     * 得到自定义的LoadingDialog 
     * @param context
     * @param msg loading信息
     * @param style 【Circular, Spots , ShapeLoading, Transparent, RoundProgressBar, MonIndicator, LoadingAnimator】
     * @return
     */
    public static AlertDialog createLoadingDialog(Context context, String msg, LoadingStyle loadingStyle) {
    	AlertDialog dialog;
    	switch (loadingStyle) {
		case Circular:
			dialog = new CircularProgressDialog(context, msg);
			break;
		case Spots:
			dialog = new SpotsDialog(context, msg);
			break;
		case ShapeLoading:
			dialog = new ShapeLoadingDialog(context, msg);
			break;
		case Transparent:
			dialog = new CustomDialog(context, msg);
			break;
		case RoundProgressBar:
			dialog = new RoundProgressBarDialog(context, msg);
			break;
		case MonIndicator:
			dialog = new MonIndicatorDialog(context, msg);
			break;
		case LoadingAnimator:
			dialog = new LoadingAnimatorDialog(context, msg);
			break;
		default:
			dialog = new CircularProgressDialog(context, msg);
			break;
		}
    	return dialog;
    }  
    
	/** 
     * 得到自定义的progressDialog 
     * @param context 
     * @param msg 
     * @return 
     */  
    public static Dialog createLoadingDialog(Context context, String msg) {
    	return new CircularProgressDialog(context, msg);
    }  

	/**
	 * 改变LoadingDialog的状态
	 * 
	 * @param msg
	 */
	public static Dialog changeLoadingDialogStatus(Context context, Dialog dialog, String msg) {
		if (dialog == null || !dialog.isShowing()) {
			dialog = createLoadingDialog(context, msg);
			// dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			dialog.show();
		} else {
			changeDialogTipMsg(dialog, msg);
		}
		return dialog;
	}

	/**
	 * 刷新Dialog的提示文字
	 * 
	 * @param dialog
	 * @param msg
	 */
	public static void changeDialogTipMsg(Dialog dialog, String msg) {
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		setViewTextMsg(view, msg);
	}

	private static void setViewTextMsg(View view, String msg) {
		if (view instanceof ViewGroup) {
			ViewGroup parent = (ViewGroup) view;
			int count = parent.getChildCount();
			for (int i = 0; i < count; i++) {
				setViewTextMsg(parent.getChildAt(i), msg);
			}
		} else if (view instanceof TextView) {
			TextView textview = (TextView) view;
			textview.setText(msg);
		}
	}
	
	/**
	 * 显示生日选择输入框
	 * @param context
	 * @param etPatBirth
	 */
	public static DatePickerDialog createBirthDatePickerDialog(final Context context, final EditText etPatBirth) {
	    final Date durrentDate = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		String birthday = etPatBirth.getText().toString();
		calendar.setTime(DateUtils.parseStrDate(birthday));
		DatePickerDialog dlg = new DatePickerDialog(context, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker arg0, int l1, int i2, int j2) {
				StringBuilder stringbuilder = (new StringBuilder(String.valueOf(l1))).append("-");
				stringbuilder.append((i2 + 1) < 10 ? "0" + (i2 + 1): (i2 + 1)).append("-").append(j2 < 10 ? "0" + j2 : j2);
				Date selectDate = DateUtils.parseStrDate(stringbuilder.toString());
				int result = DateUtils.compareDate(selectDate, durrentDate);
				if (result == 1) {
					ToastUtil.getInstance(context).showToast("出生日期不能是未来的时间!",Toast.LENGTH_SHORT);
					return;
				} else {
					etPatBirth.setText(stringbuilder.toString());
				}
			}
		}, calendar.get(1), calendar.get(2), calendar.get(5));
		return dlg;
	}
	
	
	/**
	 * 显示时间选择输入框
	 * @param context
	 * @param etPatBirth
	 */
	public static DatePickerDialog createDatePickerDialog(final Context context, final EditText editText) {
		Calendar calendar = Calendar.getInstance();
		String birthday = editText.getText().toString();
		calendar.setTime(DateUtils.parseStrDate(birthday));
		DatePickerDialog dlg = new DatePickerDialog(context, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker arg0, int l1, int i2, int j2) {
				StringBuilder stringbuilder = (new StringBuilder(String.valueOf(l1))).append("-");
				editText.setText(stringbuilder.append((i2 + 1) < 10 ? "0" + (i2 + 1) : (i2 + 1)).append("-").append(j2 < 10 ? "0" + j2 : j2).toString());
			}
		}, calendar.get(1), calendar.get(2), calendar.get(5));
		return dlg;
	}

}
