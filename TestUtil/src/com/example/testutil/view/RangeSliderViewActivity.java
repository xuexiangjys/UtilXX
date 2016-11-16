package com.example.testutil.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.rangesliderview.RangeSliderView;

/** 
 * @author xx
 * @Date 2016-9-27 下午10:20:24 
 * @Copyright (c) 2016, xuexiangjys@163.com All Rights Reserved. 
 */
public class RangeSliderViewActivity extends BaseActivity {
	private RangeSliderView smallSlider;
	private RangeSliderView largeSlider;
	private RangeSliderView rsv_another;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_rangesliderview);
		initTitleBar(TAG);
		
		initView();
	}
	private void initView() {
		smallSlider = (RangeSliderView) findViewById(R.id.rsv_small);
		largeSlider = (RangeSliderView) findViewById(R.id.rsv_large);
		rsv_another = (RangeSliderView) findViewById(R.id.rsv_another);
	    RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
			@Override
			public void onSlide(int index) {
				Toast.makeText(getApplicationContext(), "Hi index: " + index, Toast.LENGTH_SHORT).show();
			}
		};
		smallSlider.setOnSlideListener(listener);
		largeSlider.setOnSlideListener(listener);
		Button rangeSlider = (Button) findViewById(R.id.Range_Slider);
		rangeSlider.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				rsv_another.setInitialIndex(3);
				rsv_another.setCanMove(false);
			}
		});
	}

}
