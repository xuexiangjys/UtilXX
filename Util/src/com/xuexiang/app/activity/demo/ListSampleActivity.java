package com.xuexiang.app.activity.demo;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xuexiang.app.activity.ActivityItem;

/**
 * 提供简单的ListActivity实现例子的展现
 * 
 * @author xx
 * 
 */
public abstract class ListSampleActivity extends ListActivity {

	protected List<ActivityItem> mSampleData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String title = getIntent().getStringExtra(ActivityItem.TITLE);
		if (title != null) {
			setTitle(title);
		}
		mSampleData = initSampleActivityData();
		getListView().setAdapter(new ArrayAdapter<ActivityItem>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mSampleData));

		init();
	}

	/**
	 * 初始化
	 */
	protected abstract void init();

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(getPackageName(), mSampleData.get(position).page);
		intent.putExtra(ActivityItem.TITLE, mSampleData.get(position).title);
		startActivityForSample(intent);
	}

	/**
	 * 初始化例子
	 * 
	 * @return
	 */
	protected abstract List<ActivityItem> initSampleActivityData();

	/**
	 * 点击条目后启动activity
	 * 
	 * @param intent
	 */
	protected abstract void startActivityForSample(Intent intent);
}
