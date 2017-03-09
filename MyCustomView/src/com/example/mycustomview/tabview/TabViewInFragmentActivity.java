package com.example.mycustomview.tabview;

import com.example.mycustomview.R;
import com.example.mycustomview.tabview.sample.FragmentSample;
import com.xuexiang.app.activity.BaseActivity;

public class TabViewInFragmentActivity extends BaseActivity {
	FragmentSample fragmentSample;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_tabview_fragment_container);
		initTitleBar(TAG);
		fragmentSample = new FragmentSample();
		getSupportFragmentManager().beginTransaction().add(R.id.frame, fragmentSample).show(fragmentSample).commit();

	}
}
