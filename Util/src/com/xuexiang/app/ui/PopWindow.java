package com.xuexiang.app.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

/**
 * 基类PopupWindow
 * @author xx
 * @Date 2017-1-18 上午11:10:05
 */
public class PopWindow extends PopupWindow {

	/**
	 * @param contentView 布局控件
	 */
	public PopWindow(View contentView) {
		this(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
	/**
	 * @param context
	 * @param layoutId 布局资源id
	 */
	public PopWindow(Context context, int layoutId) {
		this(context, layoutId, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
	/**
	 * @param contentView
	 * @param width 宽
	 * @param height 高
	 */
	public PopWindow(View contentView, int width, int height) {
		super(contentView, width, height, false);
		init();
	}
	
	/**
	 * @param context
	 * @param layoutId 布局资源id
	 * @param width 宽
	 * @param height 高
	 */
	public PopWindow(Context context, int layoutId, int width, int height) {
		super();
		initContentView(context, layoutId, width, height);
		init();
	}
	
	private void initContentView(Context context, int layoutId, int width, int height) {
		View contentView = View.inflate(context, layoutId, null);	
		setContentView(contentView);
		setWidth(width);
	    setHeight(height);
	    
	}

	/**
	 * 默认可聚焦、可外部点击消失、无背景
	 */
	private void init() {
		setFocusable(true);
		setOutsideTouchable(true);
		// 必须设置，否则获得焦点后页面上其他地方点击无响应
		setBackgroundDrawable(new BitmapDrawable());
	}
	
	/**
	 * 点击显示或者隐藏弹窗
	 * @param v 点击显示弹窗的控件
	 */
	public void onClick(View v) {
		if (isShowing()) {
			dismiss();
		} else {
			showAsDropDown(v);
		}
	}
	
	/**
	 * 点击显示或者隐藏弹窗
	 * @param v 点击显示弹窗的控件
	 * @param xoff x轴偏移量
	 * @param yoff y轴偏移量
	 */
	public void onClick(View v, int xoff, int yoff) {
		if (isShowing()) {
			dismiss();
		} else {
			showAsDropDown(v, xoff, yoff);
		}
	}
	
	public View findViewById(int resId) {
		return getContentView().findViewById(resId);
	}

	/**
	 * 隐藏PopWindow
	 * @param popWindow
	 */
	public static void dismissPopWindow(PopWindow popWindow) {
		if (popWindow != null) {
			popWindow.dismiss();
		}
	}
}
