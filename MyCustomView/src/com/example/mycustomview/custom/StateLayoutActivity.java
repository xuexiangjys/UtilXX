package com.example.mycustomview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.R;
import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.view.statelayout.FadeScaleViewAnimProvider;
import com.xuexiang.view.statelayout.FadeViewAnimProvider;
import com.xuexiang.view.statelayout.StateLayout;


public class StateLayoutActivity extends AppCompatActivity {

    StateLayout stateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statelayout);
        ActivityUtil.initTitleBar(this, "StateLayoutActivity");
        
        stateLayout = (StateLayout) findViewById(R.id.stateLayout);
        stateLayout.setViewSwitchAnimProvider(new FadeViewAnimProvider());
        stateLayout.setEmptyAction(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.toastOnUIThread("点击空界面按钮！");
			}
		});
        
        stateLayout.setErrorAction(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ActivityUtil.toastOnUIThread("点击错误界面按钮！");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_statelayout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.error:
                stateLayout.showErrorView();
                break;
            case R.id.empty:
                stateLayout.showEmptyView();
                break;
            case R.id.progress:
                stateLayout.showProgressView();
                break;
            case R.id.content:
                stateLayout.showContentView();
                break;

            case R.id.fade:
                stateLayout.setViewSwitchAnimProvider(new FadeViewAnimProvider());
                break;
            case R.id.fade_scale:
                stateLayout.setViewSwitchAnimProvider(new FadeScaleViewAnimProvider());
                break;
        }
        return true;
    }
}
