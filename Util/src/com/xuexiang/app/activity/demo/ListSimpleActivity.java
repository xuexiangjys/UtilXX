package com.xuexiang.app.activity.demo;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xuexiang.app.activity.ActivityItem;

/**
 * 提供简单的ListActivity，测试程序功能使用
 * 
 * @author xx
 * @Date 2017-2-22 下午4:09:04
 */
public abstract class ListSimpleActivity extends ListActivity {
	protected List<String> mSimpleData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String title = getIntent().getStringExtra(ActivityItem.TITLE);
		if (title != null) {
			setTitle(title);
		}
		mSimpleData = initSimpleData();
		getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mSimpleData));
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		onItemClick(position);
	}

	/**
	 * 初始化例子
	 * 
	 * @return
	 */
	protected abstract List<String> initSimpleData();

	/**
	 * 条目点击
	 * 
	 * @param intent
	 */
	protected abstract void onItemClick(int position);

}
