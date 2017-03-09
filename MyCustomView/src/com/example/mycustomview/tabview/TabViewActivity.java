package com.example.mycustomview.tabview;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.example.mycustomview.tabview.sample.QuickStartActivity;
import com.example.mycustomview.tabview.sample.TabViewInJavaActivity;
import com.example.mycustomview.tabview.sample.TabViewInXMLActivity;
import com.xuexiang.app.activity.ActivityItem;
import com.xuexiang.app.activity.ListSampleActivity;

/**  
 * 创建时间：2017-2-23 上午12:23:34
 * 项目名称：MyCustomView  
 * @author xuexiang
 * 文件名称：TabViewActivity.java 
 **/
public class TabViewActivity extends ListSampleActivity {

	@Override
	protected List<ActivityItem> initSampleActivityData() {
		List<ActivityItem> list = new ArrayList<ActivityItem>();
		list.add(new ActivityItem("quick start", QuickStartActivity.class));
		list.add(new ActivityItem("custom in xml", TabViewInXMLActivity.class));
		list.add(new ActivityItem("custom in java", TabViewInJavaActivity.class));
		list.add(new ActivityItem("use in fragment", TabViewInFragmentActivity.class));
		return list;
	}

	@Override
	protected void startActivityForSample(Intent intent) {
		startActivity(intent);
	}

	@Override
	protected void init() {
		
	}

}
