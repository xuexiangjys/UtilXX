package com.xuexiang.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xuexiang.util.resource.RUtils;

/**  
 * 创建时间：2017-3-1 下午11:24:25
 * 项目名称：Util  
 * @author xuexiang
 * 文件名称：BaseListActivity.java 
 **/
public abstract class BaseListActivity extends BaseActivity implements OnItemClickListener{

	private ListView mListView;
	
	@Override
	public void onCreateActivity() {
		setContentView(RUtils.getLayout(mContext, "activity_listview"));
		initTitleBar(TAG);
		mListView = (ListView) findViewById(RUtils.getId(mContext, "listView"));
		mListView.setOnItemClickListener(this);
		initData();
	}
	
	protected ListView getListView() {
		return mListView;
	}
	
	protected abstract void initData();

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}
