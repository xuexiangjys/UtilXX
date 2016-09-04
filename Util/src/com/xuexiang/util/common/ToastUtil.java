package com.xuexiang.util.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.util.resource.MResource;
/**
 * 显示全局的Toast，防止Toast的重复弹出
 *
 */
public class ToastUtil {
	private Toast mToast = null;
	private Context mCtx;
	private static ToastUtil mToastUtil = null;
	
	public ToastUtil(Context ctx){
		mCtx = ctx;
	}
	
	public static ToastUtil getInstance(Context ctx){
		if(mToastUtil == null)
			mToastUtil = new ToastUtil(ctx);
		return mToastUtil;
	}

	public void showToast(String text,int duration) {  
        if(mToast == null) { 
            mToast = makeText(mCtx, text, duration); 
        } else {
        	TextView tv = (TextView)mToast.getView().findViewById(MResource.getIdByName(mCtx, "id", "TextViewInfo"));
        	tv.setText(text);   
        }  
        mToast.show();
    }  
	
	public void showToast(String text) {  
        if(mToast == null) { 
            mToast = makeText(mCtx, text, Toast.LENGTH_SHORT); 
        } else {
        	TextView tv = (TextView)mToast.getView().findViewById(MResource.getIdByName(mCtx, "id", "TextViewInfo"));
        	tv.setText(text);   
        }  
        mToast.show();
    }  
      
    public void cancelToast() {  
        if (mToast != null) {  
            mToast.cancel();  
        }  
    }
    
    public Toast makeText(Context context, String msg,int duration){
    	View toastRoot = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(MResource.getIdByName(context, "layout", "my_toast"), null);
    	Toast toast=new Toast(context);
    	toast.setView(toastRoot);
    	TextView tv=(TextView)toastRoot.findViewById(MResource.getIdByName(context, "id", "TextViewInfo"));
    	tv.getBackground().setAlpha(100);
    	tv.setText(msg);
    	toast.setDuration(duration);
    	return toast;
    }
}
