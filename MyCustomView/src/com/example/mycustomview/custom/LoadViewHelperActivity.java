package com.example.mycustomview.custom;

import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.loadviewhelper.LoadViewHelper;
import com.xuexiang.view.loadviewhelper.helper.OnLoadViewListener;


public class LoadViewHelperActivity extends BaseHeadActivity {

    private LoadViewHelper mHelper;
    @Override
	protected int getLayoutId() {
		return R.layout.activity_loadviewhelper;
	}

	@Override
	protected void init() {
		View contentLayout = findViewById(R.id.content_layout);
        mHelper = new LoadViewHelper(contentLayout);
        mHelper.setListener(new OnLoadViewListener() {
            @Override
            public void onRetryClick() {
                Toast.makeText(getApplicationContext(), "点击了重试", Toast.LENGTH_SHORT).show();
            }
        });
        mHelper.showLoading();
	}

    public void showError(View view) {
        mHelper.showError();
    }

    public void showEmpty(View view) {
        mHelper.showEmpty();
    }

    public void showLoading(View view) {
        mHelper.showLoading("加载中...");
    }

    public void showSuccess(View view) {
        mHelper.showContent();
    }
	
}
