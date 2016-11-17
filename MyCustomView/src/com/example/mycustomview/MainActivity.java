package com.example.mycustomview;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.custom.CircleMenuActivity;
import com.example.mycustomview.custom.StateButtonActivity;
import com.xuexiang.app.BaseActivity;

public class MainActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_main);
    	initTitleBar("自定义控件主页", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				exitBy2Click();
			}
		});
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.circlemenu:
			mToastUtil.showToast("点击了CircleMenuActivity");
			startActivity(CircleMenuActivity.class);
			break;
		case R.id.statebutton:
			mToastUtil.showToast("点击了StateButtonActivity");
			startActivity(StateButtonActivity.class);
			break;

		default:
			break;
		}
	}
	
	/**  * 菜单、返回键响应  */
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if(keyCode == KeyEvent.KEYCODE_BACK) {    
			exitBy2Click(); 
		}  
		return false; 
	} 

}
