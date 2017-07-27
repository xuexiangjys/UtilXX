package com.nl.rxjava;

import java.util.ArrayList;
import java.util.List;

import com.nl.rxjava.combine.RxJavaCombineActivity;
import com.nl.rxjava.create.RxJavaCreateActivity;

import android.content.Intent;

/** 
 * @author xx
 * @Date 2017-7-21 上午11:05:36 
 */
public class MainActivity extends ListSampleActivity{

	@Override
	protected void init() {
		
	}

	@Override
	protected List<ActivityItem> initSampleActivityData() {
		List<ActivityItem> list = new ArrayList<ActivityItem>();
		list.add(new ActivityItem("RxJava Create", RxJavaCreateActivity.class));
		list.add(new ActivityItem("RxJava Combine", RxJavaCombineActivity.class));
		return list;
	}

	@Override
	protected void startActivityForSample(Intent intent) {
		startActivity(intent);
	}

}
