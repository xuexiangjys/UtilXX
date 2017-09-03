package com.example.testutil;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.adapter.MultiAdapterActivity;
import com.example.testutil.adapter.SimpleAdapterActivity;
import com.example.testutil.adapter.XSimpleAdapterActivity;
import com.xuexiang.app.activity.ActivityItem;
import com.xuexiang.app.activity.ListSampleActivity;
import com.xuexiang.util.app.ActivityUtil;

public class AdapterListviewActivity extends ListSampleActivity {
	@Override
	protected List<ActivityItem> initSampleActivityData() {
		List<ActivityItem> list = new ArrayList<ActivityItem>();
		list.add(new ActivityItem("Simple Adapter Text", SimpleAdapterActivity.class));
		list.add(new ActivityItem("X Simple Adapter Text", XSimpleAdapterActivity.class));
		list.add(new ActivityItem("MultiItemStyleText", MultiAdapterActivity.class));
		return list;
	}

	@Override
	protected void startActivityForSample(Intent intent) {
		startActivity(intent);
	}

	@Override
	protected void init() {
		ActivityUtil.initTitleBarDynamic(this, "AdapterListviewActivity", new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
