package com.example.mycustomview.custom;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.custom.TimeLineView;

public class TimeLineActivity extends BaseHeadActivity {
	TimeLineView mView1;
	TimeLineView mView2;
	TimeLineView mView3;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_timeline;
	}

	@Override
	protected void init() {
		mView1 = (TimeLineView) findViewById(R.id.timeLineView1);
		mView2 = (TimeLineView) findViewById(R.id.timeLineView2);
		mView3 = (TimeLineView) findViewById(R.id.timeLineView3);
		String[] txts = new String[3];
		txts[0] = "111111111";
		txts[1] = "2111122";
		txts[2] = "3111133";
		mView1.builder().pointStrings(txts, 1).startedCircleColor(Color.BLUE).underwayCircleColor(Color.BLUE).preCircleColor(Color.GRAY).startedLineColor(Color.BLUE).preLineColor(Color.GRAY)
				.startedStringColor(Color.BLUE).underwayStringColor(Color.BLUE).preStringColor(Color.GRAY).load(); // 开始绘制
		List<String> data = new ArrayList<String>();
		data.add("等候支付");
		data.add("等候商家接单");
		data.add("等候配送");
		data.add("等候送达");
		mView2.builder().pointStrings(data, 2).load();
		data = new ArrayList<String>();
		data.add("第一步");
		data.add("第二步");
		data.add("第三步");
		data.add("第四步");
		data.add("第五步");
		data.add("第六步");
		data.add("第七步");
		mView3.builder().pointStrings(data, 1).load();
		mView3.setOnStepChangedListener(new TimeLineView.OnStepChangedListener() {
			@Override
			public void onchanged(TimeLineView view, int step, String stepStr) {
				Toast.makeText(TimeLineActivity.this, "step=" + step + ",str=" + stepStr, Toast.LENGTH_SHORT).show();
			}
		});
		findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mView3.nextStep();
			}
		});
		findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mView3.setStep(1);
			}
		});
	}
}
