package com.example.testutil.view;

import android.util.Log;
import android.widget.FrameLayout;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.GestureContentView;
import com.xuexiang.view.Gesture.GestureDrawline.GestureCallBack;

/**  
 * 创建时间：2016-5-25 下午11:42:27  
 * 项目名称：GesturePassword  
 * @author xuexiang
 * 文件名称：ChangePasswordActivity.java  
 **/
public class ChangePasswordActivity extends BaseActivity {
	private FrameLayout mContainer;
	private GestureContentView mContentView;
	private boolean isFirstInput = true;
	private String mFirstPassword;
	private int viewWidth;

	@Override
	public void onCreateActivity() {
        setContentView(R.layout.activity_changepassword);
		
		initView();
	}	

	private void initView() {
		
		viewWidth = DisplayUtils.getScreenW(this) * 9 / 10;
		mContainer = (FrameLayout) findViewById(R.id.container);
		
		refreshGestureContentView();
	}
	
	private void refreshGestureContentView() {
		mContentView = new GestureContentView(this, viewWidth, mSettingManager.isNeedVerify() , mSettingManager.getPassword(),
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
						Toast("验证成功,请设置新密码！");			
						mSettingManager.setPassword("");
						mContentView.clearDrawlineState(0L);
						refreshGestureContentView();
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
