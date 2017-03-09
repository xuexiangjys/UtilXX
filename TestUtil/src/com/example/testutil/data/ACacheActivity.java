package com.example.testutil.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.example.testutil.data.acache.SaveBitmapActivity;
import com.example.testutil.data.acache.SaveDrawableActivity;
import com.example.testutil.data.acache.SaveJsonArrayActivity;
import com.example.testutil.data.acache.SaveJsonObjectActivity;
import com.example.testutil.data.acache.SaveMediaActivity;
import com.example.testutil.data.acache.SaveObjectActivity;
import com.example.testutil.data.acache.SaveStringActivity;
import com.xuexiang.app.activity.ActivityItem;
import com.xuexiang.app.activity.ListSampleActivity;

/**  
 * 创建时间：2017-3-9 下午11:10:10
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：ACacheActivity.java 
 **/
public class ACacheActivity extends ListSampleActivity {

	@Override
	protected void init() {

	}

	@Override
	protected List<ActivityItem> initSampleActivityData() {
		List<ActivityItem> list = new ArrayList<ActivityItem>();
		list.add(new ActivityItem("String Cache", SaveStringActivity.class));
		list.add(new ActivityItem("JsonObject Cache", SaveJsonObjectActivity.class));
		list.add(new ActivityItem("JsonArray Cache", SaveJsonArrayActivity.class));
		list.add(new ActivityItem("Bitmap Cache", SaveBitmapActivity.class));
		list.add(new ActivityItem("Media Cache", SaveMediaActivity.class));
		list.add(new ActivityItem("Drawable Cache", SaveDrawableActivity.class));
		list.add(new ActivityItem("Object Cache", SaveObjectActivity.class));
		return list;
	}

	@Override
	protected void startActivityForSample(Intent intent) {
		startActivity(intent);
	}

}
