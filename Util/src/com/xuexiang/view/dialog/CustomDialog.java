package com.xuexiang.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;


/**
 * 自定义透明的dialog
 */
public class CustomDialog extends AlertDialog{
    private String mLoadingText;
    private TextView mLoadingTextView;
    private boolean mIsNeedCanceled;
    public CustomDialog(Context context) {
        super(context, RUtils.getStyle(context, "CustomDialog"));
    }

    public CustomDialog(Context context, String loadingText) {
        super(context, RUtils.getStyle(context, "CustomDialog"));
        mLoadingText = loadingText;
    }
    
    public CustomDialog(Context context, int theme) {
		super(context, theme);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_custom"));
        
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
            	if (mIsNeedCanceled) {
            		if (isShowing()) {
            			dismiss();
            		}
            	}
                break;
        }
        return true;
    }

    private void initView(){
       
        mLoadingTextView = (TextView)findViewById(RUtils.getId(getContext(), "loadingText"));
        if (TextUtils.isEmpty(mLoadingText)) {
        	mLoadingTextView.setText("正在加载中...");
	     } else {
	    	 mLoadingTextView.setText(mLoadingText);
	     }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.9f;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(false);

    }
    
    public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mLoadingTextView != null) {
			mLoadingTextView.setText(mLoadingText);
		}
	}
    
    public void setCanceledByBackEvent(boolean isNeedCanceled) {
    	mIsNeedCanceled = isNeedCanceled;
	}
}