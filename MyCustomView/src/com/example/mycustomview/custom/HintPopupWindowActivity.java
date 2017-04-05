package com.example.mycustomview.custom;

import java.util.ArrayList;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.view.custom.HintPopupWindow;

public class HintPopupWindowActivity extends BaseHeadActivity {

	private HintPopupWindow hintPopupWindow;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_hintpopupwindow;
	}

	@Override
	protected void init() {
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 弹出选项弹窗
				hintPopupWindow.showPopupWindow(v);
			}
		});
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 弹出选项弹窗
				hintPopupWindow.showPopupWindow(v);
			}
		});

		// 下面的操作是初始化弹出数据
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("选项item1");
		strList.add("选项item2");
		strList.add("选项item3");

		ArrayList<View.OnClickListener> clickList = new ArrayList<View.OnClickListener>();
		View.OnClickListener clickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(HintPopupWindowActivity.this, "点击事件触发", Toast.LENGTH_SHORT).show();
			}
		};
		clickList.add(clickListener);
		clickList.add(clickListener);
		clickList.add(clickListener);
		clickList.add(clickListener);

		// 具体初始化逻辑看下面的图
		hintPopupWindow = new HintPopupWindow(this, strList, clickList);
	}
}
