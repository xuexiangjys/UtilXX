package com.example.testutil.view;

import java.util.ArrayList;

import android.widget.ListView;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.timeline.TimeItem;
import com.xuexiang.view.timeline.TimeLineAdapter;

/**  
 * 创建时间：2016-7-8 上午8:51:41  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：TimeLineActivity.java  
 **/
public class TimeLineActivity extends BaseHeadActivity {
	private ListView uplevel_list;
	private ArrayList<TimeItem> list;
	private TimeLineAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_timeline;
	}

	@Override
	protected void init() {
		uplevel_list = (ListView) findViewById(R.id.uplevel_list);
		initData();
	}

	private void initData() {
		list = new ArrayList<TimeItem>();
		for (int i = 1; i <= 100; i++) {
			TimeItem item = new TimeItem("2015/8/17", "今天赚了" + i + "000块");			
			list.add(item);
		}
		adapter = new TimeLineAdapter(this, list);
		uplevel_list.setAdapter(adapter);
	}

	

}
