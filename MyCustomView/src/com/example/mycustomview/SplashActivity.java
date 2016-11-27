package com.example.mycustomview;

import com.xuexiang.app.BaseSplashActivity;
import com.xuexiang.util.app.ActivityUtil;

public class SplashActivity extends BaseSplashActivity {

	@Override
	public void onCreateActivity() {
	    if (mSettingManager.getIsfirst()) {
	    	mSettingManager.setIsfirst(false);
	    	ActivityUtil.startActivity(SplashActivity.this, GuideActivity.class);
			finish();
	    	
	    }  else {
	    	initSplashView(R.drawable.splash);
			startSplashAnim();
	    }
	}

	@Override
	public void welcomeFunction() {
        ActivityUtil.startActivity(this, MainActivity.class);
        finish();
	}

}
