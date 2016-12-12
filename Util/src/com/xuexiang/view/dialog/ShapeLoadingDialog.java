package com.xuexiang.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;

import com.xuexiang.util.resource.RUtils;

/**
 * Created by zzz40500 on 15/6/15.
 */
public class ShapeLoadingDialog extends AlertDialog {
    private LoadingView mLoadingView;
    private String mLoadingText;
    
    public ShapeLoadingDialog(Context context) {
    	super(context, RUtils.getStyle(context, "custom_dialog"));
    }
    
    public ShapeLoadingDialog(Context context, String loadingText) {
    	super(context, RUtils.getStyle(context, "custom_dialog"));
    	mLoadingText = loadingText;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_shapeloading"));
        mLoadingView = (LoadingView) findViewById(RUtils.getId(getContext(), "loadView"));
        setLoadingText(mLoadingText);
        setCanceledOnTouchOutside(false);
    }

    public void setBackground(int color){
        GradientDrawable gradientDrawable= (GradientDrawable) getCurrentFocus().getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(String loadingText) {
    	if (mLoadingView != null && !TextUtils.isEmpty(loadingText)) {
    		mLoadingView.setLoadingText(loadingText);
    	}
    }

}
