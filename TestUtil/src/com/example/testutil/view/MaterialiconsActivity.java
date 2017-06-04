package com.example.testutil.view;

import com.example.testutil.adapter.IconsAdapter;
import com.xuexiang.app.activity.BaseListActivity;

public class MaterialiconsActivity extends BaseListActivity{

	@Override
	protected void initData() {
		getListView().setAdapter(new IconsAdapter(this));
	}

}
