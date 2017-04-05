package com.example.mycustomview.custom;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.stepview.FlowViewVertical;

public class StepViewVerticalActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_stepview_vertical;
	}

	@Override
	protected void init() {
		FlowViewVertical vFlow = (FlowViewVertical) findViewById(R.id.vflow);
		vFlow.setProgress(9, 10, getResources().getStringArray(R.array.vflow_titles), getResources().getStringArray(R.array.vflow_times));
	}
}
