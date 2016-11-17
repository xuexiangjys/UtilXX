package com.xuexiang.util.common;

public class ClickUtils {

	private static long lastClickTime;

	/**
     * 是否是快速点击
     * @return true:是，false:不是
     */
    public static boolean isFastDoubleClick() {  
        long time = System.currentTimeMillis();  
        long timeD = time - lastClickTime;  
        if ( 0 < timeD && timeD < 1000) {     
            return true;     
        }     
        lastClickTime = time;     
        return false;     
    }  
    
    /**
     * 是否是快速点击
     * @return true:是，false:不是
     */
    public static boolean isFastDoubleClick(float interval) {  
        long time = System.currentTimeMillis();  
        long timeD = time - lastClickTime;  
        if ( 0 < timeD && timeD < (interval * 1000)) {     
            return true;     
        }     
        lastClickTime = time;     
        return false;     
    }  
}
