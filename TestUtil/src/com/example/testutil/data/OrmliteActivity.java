package com.example.testutil.data;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;

public class OrmliteActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_ormlite);
		initTitleBar(TAG);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.auto:
	        startActivity(new Intent(this, AutoDataBaseActivity.class));
			break;
		case R.id.defalut:
			startActivity(new Intent(this, DefaultDataBaseActivity.class));
			break;
		case R.id.custom:
			startActivity(new Intent(this, CustomDataBaseActivity.class));
			break;
		default:
			break;
		}
		
	}
	

}
