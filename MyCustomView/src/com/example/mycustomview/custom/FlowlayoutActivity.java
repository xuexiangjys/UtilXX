package com.example.mycustomview.custom;

import android.widget.Button;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.custom.CheckableButton;
import com.xuexiang.view.custom.FlowLayout;

/**
 * 创建时间：2017-4-3 下午4:37:43 项目名称：MyCustomView
 * 
 * @author xuexiang 文件名称：FlowlayoutActivity.java
 **/
public class FlowlayoutActivity extends BaseHeadActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_flowlayout;
	}

	@Override
	protected void init() {
		addChildTo(((FlowLayout) findViewById(R.id.flow_layout)));
	}

	private void addChildTo(FlowLayout flowLayout) {
		for (int i = 'A'; i < 'Z'; i++) {
			Button btn = new CheckableButton(this);
			btn.setHeight(DisplayUtils.dip2px(this, 32));
			btn.setTextSize(16);
			btn.setTextColor(getResources().getColorStateList(R.drawable.checkable_text_color));
			btn.setBackgroundResource(R.drawable.checkable_background);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < i - 'A' + 4; j++) {
				sb.append((char) i);
			}
			btn.setText(sb.toString());
			flowLayout.addView(btn);
		}
	}

}
