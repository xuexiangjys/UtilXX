package com.xuexiang.app.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.InputMethodUtils;

/** 
 * 基类Dialog
 * 触摸Dialog屏幕以外的区域，dialog消失同时隐藏键盘
 * @author xx
 * @Date 2017-1-18 下午2:14:55 
 */
public class BaseDialog extends Dialog {
	private View mContentView;
	
	public BaseDialog(Context context, int layoutId) {
		this(context, RUtils.getStyle(context, "BaseDialog"), layoutId);
	}
	
	public BaseDialog(Context context, View contentView) {
		this(context, RUtils.getStyle(context, "BaseDialog"), contentView);
	}
	
	public BaseDialog(Context context) {
		super(context, RUtils.getStyle(context, "BaseDialog"));
	}
	
	public BaseDialog(Context context, int theme, int layoutId) {
		super(context, theme);
		init(context, layoutId);
		
	}
	
	public BaseDialog(Context context, int theme, View contentView) {
		super(context, theme);
		init(contentView);
	}

	public void init(Context context, int layoutId) {
		View view = View.inflate(context, layoutId, null);	
		init(view);
	}
	
	private void init(View view) {
		setContentView(view);
		mContentView = view;
		setCanceledOnTouchOutside(true);
	}
	
	public View findViewById(int resId) {
		return mContentView.findViewById(resId);
	}

	@Override  
    public boolean onTouchEvent(MotionEvent ev) {  
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();  
            if (InputMethodUtils.isShouldHideInput(v, ev)) {
            	InputMethodUtils.hideKeyboard(v);
            }  
        }  
        return super.onTouchEvent(ev);  
    } 
}
