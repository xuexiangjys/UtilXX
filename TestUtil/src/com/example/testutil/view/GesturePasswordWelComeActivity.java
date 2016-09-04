package com.example.testutil.view;

import android.content.Intent;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.GestureContentView;
import com.xuexiang.view.Gesture.GestureDrawline.GestureCallBack;

public class GesturePasswordWelComeActivity extends BaseActivity {

	private FrameLayout mContainer;
	private GestureContentView mContentView;
	private boolean isFirstInput = true;
	private String mFirstPassword;

	@Override
	public void onCreateActivity() {
	    setContentView(R.layout.activity_welcome);		
		initView();		
	}	

	private void initView() {
		
		int viewWidth = DisplayUtils.getScreenW(this) * 9 / 10;
		mContainer = (FrameLayout) findViewById(R.id.container);
		mContentView = new GestureContentView(this, viewWidth, mSettingManager.isNeedVerify(), mSettingManager.getPassword(),
				new GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {
						Log.e("password:", inputCode);
						if (isFirstInput) {
							mFirstPassword = inputCode;
							mContentView.clearDrawlineState(0L);
							isFirstInput = false;
							Toast("请再输入一次确认！");
						} else {
							if (inputCode.equals(mFirstPassword)) {
								mSettingManager.setPassword(mFirstPassword);
								Toast("设置手势密码成功");
								mContentView.clearDrawlineState(0L);
								startActivity(new Intent(GesturePasswordWelComeActivity.this, GesturePasswordMainActivity.class));
								finish();
							} else {
								mContentView.clearDrawlineState(500L);
								Toast("验证失败，请重新设置手势密码！");
								isFirstInput = true;
							}
						}
						
					}

					@Override
					public void checkSuccess() {
						Toast("验证成功！");
						startActivity(new Intent(GesturePasswordWelComeActivity.this, GesturePasswordMainActivity.class));
						finish();
					}

					@Override
					public void checkFail() {
						Toast("验证失败！");
						mContentView.clearDrawlineState(500L);
					}
				});
		mContentView.setParentView(mContainer);
	}
	

	

}
