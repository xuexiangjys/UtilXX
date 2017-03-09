package com.example.mycustomview;
import com.xuexiang.app.activity.BaseGuideActivity;
import com.xuexiang.view.AppConsts;


public class GuideActivity extends BaseGuideActivity {

	@Override
	public void onCreateActivity() {
		initGuideView(AppConsts.getUsertGuides(), MainActivity.class);
	}

}
