package com.xuexiang.view.dialog.confirmdialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xuexiang.util.resource.RUtils;

public class ConfirmPopWindow extends PopupWindow{
	private Context mContext;
	private TextView titleTv,contentTv;
	private Button okBtn,cancelBtn;
	private OnDialogClickListener mDialogClickListener;

	public ConfirmPopWindow(Context context) {
		super(context);
		this.mContext = context;
		initView("", "");
	}
	
	public ConfirmPopWindow(Context context, String title, String content, OnDialogClickListener clickListener) {
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

		titleTv = (TextView) view.findViewById(RUtils.getId(mContext, "title_name"));
		contentTv = (TextView)  view.findViewById(RUtils.getId(mContext, "text_view"));
		init(title, content);
		okBtn =  (Button) view.findViewById(RUtils.getId(mContext, "btn_ok"));
		cancelBtn =  (Button) view.findViewById(RUtils.getId(mContext, "btn_cancel"));
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
		this.setBackgroundDrawable(new ColorDrawable(0)); 
		DisplayMetrics d = mContext.getResources().getDisplayMetrics();
		this.setWidth((int) (d.widthPixels * 0.5));  
		this.setHeight(LayoutParams.WRAP_CONTENT);  
		this.setFocusable(true);  
		this.setOutsideTouchable(true);  
		this.update();  
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
	
	public void showAtBottom(View view){
		showAsDropDown(view, Math.abs((view.getWidth() - getWidth())/2), 20);
	}
	
	public void setOnDialogClickListener(OnDialogClickListener clickListener){
		mDialogClickListener = clickListener;
	}
}
