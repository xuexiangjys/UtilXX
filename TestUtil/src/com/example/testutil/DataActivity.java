package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.data.ACacheActivity;
import com.example.testutil.data.AhibernateActivity;
import com.example.testutil.data.OrmliteActivity;
import com.xuexiang.app.activity.BaseActivity;

public class DataActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_data);
		initActionBar("数据操作主界面");
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_ahibernate:
			mToastUtil.showToast("点击了AhibernateActivity");
        	intent.setClass(this, AhibernateActivity.class);
            startActivity(intent);
			break;
			
		case R.id.btn_ormlite:
			mToastUtil.showToast("点击了OrmliteActivity");
        	intent.setClass(this, OrmliteActivity.class);
            startActivity(intent);
			break;
			
		case R.id.btn_acache:
			mToastUtil.showToast("点击了ACacheActivity");
        	intent.setClass(this, ACacheActivity.class);
            startActivity(intent);
			break;

		default:
			break;
		}
	}
}
