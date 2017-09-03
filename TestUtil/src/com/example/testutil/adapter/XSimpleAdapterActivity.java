package com.example.testutil.adapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import com.example.testutil.R;
import com.example.testutil.adapter.bean.ItemBean;
import com.xuexiang.app.activity.BaseHeadActivity;

/** 
 * @author xx
 * @Date 2017-8-21 下午2:07:49 
 */
public class XSimpleAdapterActivity extends BaseHeadActivity {
	private ListView mListView;
	private List<ItemBean> mDatas = new ArrayList<ItemBean>();
	private SimpleAdapter mAdapter;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_adapterlist;
	}

	@Override
	protected void init() {
		initDatas();
		mListView = (ListView) findViewById(R.id.id_lv_main);
		mAdapter = new SimpleAdapter(this, mDatas);
		mListView.setAdapter(mAdapter);
	}
	
	private void initDatas() {
		ItemBean bean = null;
		bean = new ItemBean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086", "20130240122");
		mDatas.add(bean);
	}


}
