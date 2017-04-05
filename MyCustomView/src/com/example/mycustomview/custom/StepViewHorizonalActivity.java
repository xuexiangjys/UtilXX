package com.example.mycustomview.custom;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.view.View;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.stepview.FlowViewHorizontal;

public class StepViewHorizonalActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_stepview_horizonal;
	}

	@Override
	protected void init() {
		FlowViewHorizontal hFlow3 = (FlowViewHorizontal) findViewById(R.id.hflowview3);
		FlowViewHorizontal hFlow4 = (FlowViewHorizontal) findViewById(R.id.hflowview4);
		FlowViewHorizontal hFlow5 = (FlowViewHorizontal) findViewById(R.id.hflowview5);
		FlowViewHorizontal hFlow6 = (FlowViewHorizontal) findViewById(R.id.hflowview6);

		Map<String, String> map = new HashMap<>();
		map.put("异常", "#FF0000");

		hFlow3.setProgress(3, 4, null, null);

		hFlow4.setProgress(5, 5, getResources().getStringArray(R.array.hflow), null);
		hFlow4.setKeyColor(map);

		hFlow5.setProgress(4, 5, getResources().getStringArray(R.array.htime5), null);

		hFlow6.setProgress(5, 5, getResources().getStringArray(R.array.hflow6), getResources().getStringArray(R.array.htime6));
		Map<String, String> map1 = new HashMap<>();
		map1.put("接单", "#009999");
		map1.put("取件", "#A65100");
		map1.put("配送", "#620CAC");
		map1.put("完成", "#00733E");
		hFlow6.setKeyColor(map1);

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(StepViewHorizonalActivity.this, StepViewVerticalActivity.class));
			}
		});
	}
}
