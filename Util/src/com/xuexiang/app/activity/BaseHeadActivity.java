package com.xuexiang.app.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.TitleBar;

/**  
 * 创建时间：2017-3-1 上午12:26:22
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：BaseHeadActivity.java 
 **/
public abstract class BaseHeadActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		initContentView();
		
		init();
	}

	private void initContentView() {
		initTab();
		initLayout();
	}

	private void initTab() {
		mTitleBar = ActivityUtil.initTitleBarDynamic(mContext, TAG, new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		addContentView(mTitleBar, mTitleBar.getLayoutParams());
	}
	
	public void initTab(String title) {
		mTitleBar = ActivityUtil.initTitleBarDynamic(mContext, title, new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		addContentView(mTitleBar, mTitleBar.getLayoutParams());
	}

	/**
	 * 加载布局
	 */
	private void initLayout() {
		View v = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin = DisplayUtils.dip2px(mContext, TitleBar.DEFAULT_TITLE_BAR_HEIGHT);
		addContentView(v, params);
	}

	protected abstract void init();

	/**
	 * @return 布局
	 */
	protected abstract int getLayoutId();
	
	
}
