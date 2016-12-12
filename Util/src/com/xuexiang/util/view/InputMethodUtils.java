package com.xuexiang.util.view;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by 大灯泡 on 2016/1/14.
 * 显示键盘d工具类
 */
public class InputMethodUtils {
    /** 显示软键盘 */
    public static void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                                                          .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /** 显示软键盘 */
    public static void showInputMethod(Context context) {
        InputMethodManager imm = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /** 多少时间后显示软键盘 */
    public static void showInputMethod(final View view, long delayMillis) {
        // 显示输入法
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                InputMethodUtils.showInputMethod(view);
            }
        }, delayMillis);
    }
    
    /**
	 * 隐藏键盘
	 * @param v
	 */
	public static void hideKeyboard(View v) {
		InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
	    if (imm.isActive()) {     
	        imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , InputMethodManager.HIDE_NOT_ALWAYS );   	          
	    } 
    }
	
	/**
	 * 显示键盘
	 */
	public static void showKeyboard(Context context){
		InputMethodManager m=(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
	}
	
	public static boolean isShouldHideInput(View v, MotionEvent event) {  
        if (v != null && (v instanceof EditText)) {  
            int[] leftTop = { 0, 0 };  
            //获取输入框当前的location位置  
            v.getLocationInWindow(leftTop);  
            int left = leftTop[0];  
            int top = leftTop[1];  
            int bottom = top + v.getHeight();  
            int right = left + v.getWidth();  
            if (event.getX() > left && event.getX() < right  
                    && event.getY() > top && event.getY() < bottom) {  
                // 点击的是输入框区域，保留点击EditText的事件  
                return false;  
            } else {  
                return true;  
            }  
        }  
        return false;  
    }
}
