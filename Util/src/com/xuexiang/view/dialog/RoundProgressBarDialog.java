package com.xuexiang.view.dialog;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.RoundProgressBar;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

/**  
 * 创建时间：2016-6-4 下午5:57:21  
 * 项目名称：spotsloadingdialog  
 * @author xuexiang
 * 文件名称：RoundProgressBarDialog.java  
 **/
public class RoundProgressBarDialog extends AlertDialog {
	
	private TextView mTitle;
	private String mLoadingText;
	private RoundProgressBar mRoundProgressBar;
	private int progress = 0;
	
	public RoundProgressBarDialog(Context context) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
		
	}
	public RoundProgressBarDialog(Context context,String title) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
	    mLoadingText = title;
	}
	
	public RoundProgressBarDialog(Context context, int theme) {
		super(context, theme);
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_roundprogressbar"));
        
        initView();
    }
	
	private void initView() {
		 mTitle = (TextView) findViewById(RUtils.getId(getContext(), "title"));
		 mRoundProgressBar = (RoundProgressBar) findViewById(RUtils.getId(getContext(), "roundProgressBar"));
	     if (TextUtils.isEmpty(mLoadingText)) {
	    	 mTitle.setText("正在加载中...");
	     } else {
	    	 mTitle.setText(mLoadingText);
	     }
	     mRoundProgressBar.setProgress(progress);
	     setCanceledOnTouchOutside(false);
	}

	public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mTitle != null) {
			  mTitle.setText(mLoadingText);
		}
	}

	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
		if (mRoundProgressBar != null) {
			mRoundProgressBar.setProgress(progress);
		}
	}
	
    private boolean mIsNeedCanceled;
	
	public void setCanceledByBackEvent(boolean isNeedCanceled) {
    	mIsNeedCanceled = isNeedCanceled;
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
	
}
