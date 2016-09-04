package com.example.testutil.view;

import android.content.Intent;
import android.view.View;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;

/**  
 * 创建时间：2016-5-29 下午11:36:48  
 * 项目名称：TestUtil  
 * @author xuexiang
 * 文件名称：GesturePasswordMainActivity.java  
 **/
public class GesturePasswordMainActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_gesturepasswordmain);
	}

	public void changePassword(View view) {
		startActivity(new Intent(GesturePasswordMainActivity.this, ChangePasswordActivity.class));
	}
}
