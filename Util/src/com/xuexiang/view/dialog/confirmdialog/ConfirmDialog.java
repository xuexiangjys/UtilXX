package com.xuexiang.view.dialog.confirmdialog;

import com.xuexiang.util.resource.RUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDialog extends Dialog {
	private Context mContext;
	private TextView titleTv,contentTv;
	private Button okBtn,cancelBtn;
	private OnDialogClickListener mDialogClickListener;

	public ConfirmDialog(Context context) {
		super(context, RUtils.getStyle(context, "CustomDialog"));
		mContext = context;
		initView("", "");
	}
	
	public ConfirmDialog(Context context, String title, String content, OnDialogClickListener clickListener) {
		super(context);
		mContext = context;
		mDialogClickListener = clickListener;
		initView(title, content);
	}

	private void initView(String title, String content) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(RUtils.getLayout(mContext, "confirm_dialog"), null);
		setContentView(view);
		initWindow();

		titleTv = (TextView) findViewById(RUtils.getId(mContext, "title_name"));
		contentTv = (TextView) findViewById(RUtils.getId(mContext, "text_view"));
		init(title, content);
		
		okBtn = (Button) findViewById(RUtils.getId(mContext, "btn_ok"));
		cancelBtn = (Button) findViewById(RUtils.getId(mContext, "btn_cancel"));
		okBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				if(mDialogClickListener != null){
					mDialogClickListener.onSubmit();
				}
			}
		});
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				if(mDialogClickListener != null){
					mDialogClickListener.onCancel();
				}
			}
		});
	}

	private void initWindow() {
		Window dialogWindow = getWindow();
		dialogWindow.setBackgroundDrawable(new ColorDrawable(0));
		dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = mContext.getResources().getDisplayMetrics();
		lp.width = (int) (d.widthPixels * 0.5); 
		lp.gravity = Gravity.CENTER;		
		dialogWindow.setAttributes(lp);
	}
	
	public void init(String title, String content) {
		initTitle(title);
		initContent(content);
	}
	
	public void initTitle(String title) {
		if (title != null && !TextUtils.isEmpty(title)) {
			titleTv.setText(title);
		}
	}
	
	public void initContent(String content) {
		if (content != null && !TextUtils.isEmpty(content)) {
			contentTv.setText(content);
		}
	}
	
	
	public void setOnDialogClickListener(OnDialogClickListener clickListener){
		mDialogClickListener = clickListener;
	}
}
