package com.example.testutil.service;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.service.FloatViewService;

public class FloatServiceActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_floatservice);
		initTitleBar(TAG);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.start_id:
			startService(new Intent(mContext, FloatViewService.class));
			break;
        case R.id.remove_id:        	
			stopService(new Intent(mContext, FloatViewService.class));
			break;

		default:
			break;
		}
		
	}
	
	

}
