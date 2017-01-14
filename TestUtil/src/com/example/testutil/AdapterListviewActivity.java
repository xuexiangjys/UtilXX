package com.example.testutil;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.example.testutil.adapter.MultiAdapterActivity;
import com.example.testutil.adapter.SimpleAdapterActivity;
import com.xuexiang.app.ActivityItem;
import com.xuexiang.app.ListSampleActivity;

public class AdapterListviewActivity extends ListSampleActivity {
	@Override
	protected List<ActivityItem> initSampleActivityData() {
		List<ActivityItem> list = new ArrayList<ActivityItem>();
		list.add(new ActivityItem("Simple Adapter Text", SimpleAdapterActivity.class));
		list.add(new ActivityItem("MultiItemStyleText", MultiAdapterActivity.class));
		return list;
	}

	@Override
	protected void startActivityForSample(Intent intent) {
		startActivity(intent);
	}
}
