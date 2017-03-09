package com.xuexiang.app.activity;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * 提供简单的ListActivity，测试程序功能使用
 * 
 * @author xx
 * @Date 2017-2-22 下午4:09:04
 */
public abstract class ListSimpleActivity extends BaseListActivity {
	protected List<String> mSimpleData;

	@Override
	protected void initData() {
		String title = getIntent().getStringExtra(ActivityItem.TITLE);
		if (title != null) {
			setTitle(title);
		}
		mSimpleData = initSimpleData();
		getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mSimpleData));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
