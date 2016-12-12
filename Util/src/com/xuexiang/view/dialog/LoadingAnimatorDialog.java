package com.xuexiang.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;

import com.xuexiang.util.resource.RUtils;

/**
 * Created by xx on 15/6/15.
 */
public class LoadingAnimatorDialog  extends AlertDialog {

    private LoadingAnimatorView mLoadingView;
    private String mLoadingText;
    
    public LoadingAnimatorDialog(Context context) {
    	super(context, RUtils.getStyle(context, "custom_dialog"));
    }
    
    public LoadingAnimatorDialog(Context context, String loadingText) {
    	super(context, RUtils.getStyle(context, "custom_dialog"));
    	mLoadingText = loadingText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "my_canvas_dialog"));
        mLoadingView = (LoadingAnimatorView) findViewById(RUtils.getId(getContext(), "loadView"));
        setLoadingText(mLoadingText);
        setCanceledOnTouchOutside(false);
    }

    public void setBackground(int color){
        GradientDrawable gradientDrawable= (GradientDrawable) getCurrentFocus().getBackground();
        gradientDrawable.setColor(color);
    }
    
    public void setLoadingText(String loadingText) {
    	if (mLoadingView != null && !TextUtils.isEmpty(loadingText)) {
    		mLoadingView.setLoadText(loadingText);
    	}
    }

    @Override
    public void dismiss() {
    	mLoadingView.close();
        super.dismiss();
    }

    
    

       
}
