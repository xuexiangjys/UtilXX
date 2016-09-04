package com.xuexiang.view.dialog.monindicator;

import com.xuexiang.util.view.DisplayUtils;

import android.content.Context;

public class ParamsCreator {
	private int screenWidth;//ÆÁÄ»¿í¶È
    private int densityDpi;//ÏñËØÃÜ¶È
    public ParamsCreator(Context context){
    	screenWidth = DisplayUtils.getScreenW(context);
    	densityDpi = DisplayUtils.getScreenDpi(context);
    }
    
	/**
	 * »ñµÃÄ¬ÈÏÔ²µÄ°ë¾¶
	 */
	public int getDefaultCircleRadius(){
    	if(screenWidth >= 1400){//1440
    		return 50;
    	}
    	if(screenWidth >= 1000){//1080
    		if(densityDpi >=480)
        		return 48;
        	if(densityDpi >= 320)
        		return 48;
        	return 48;
    	}
    	if(screenWidth >= 700){//720
        	if(densityDpi >= 320)
        		return 34;
        	if(densityDpi >= 240)
        		return 34;
        	if(densityDpi >= 160)
        		return 34;
        	return 34;
    	}
    	if(screenWidth >= 500){//540
        	if(densityDpi >= 320)
        		return 30;
        	if(densityDpi >= 240)
        		return 30;
        	if(densityDpi >= 160)
        		return 30;
        	return 30;
    	}
    	return 30;
	}
	/**
	 * »ñµÃÄ¬ÈÏÔ²µÄ¼ä¾à
	 */
	public int getDefaultCircleSpacing(){
    	if(screenWidth >= 1400){//1440
    		return 12;
    	}
    	if(screenWidth >= 1000){//1080
    		if(densityDpi >=480)
        		return 12;
        	if(densityDpi >= 320)
        		return 12;
        	return 12;
    	}
    	if(screenWidth >= 700){//720
        	if(densityDpi >= 320)
        		return 8;
        	if(densityDpi >= 240)
        		return 8;
        	if(densityDpi >= 160)
        		return 8;
        	return 8;
    	}
    	if(screenWidth >= 500){//540
        	if(densityDpi >= 320)
        		return 5;
        	if(densityDpi >= 240)
        		return 5;
        	if(densityDpi >= 160)
        		return 5;
        	return 5;
    	}
    	return 5;
	}
}
