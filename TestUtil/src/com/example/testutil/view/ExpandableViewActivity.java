package com.example.testutil.view;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testutil.R;
import com.example.testutil.view.expandableview.ExpandableGridViewActivity;
import com.example.testutil.view.expandableview.ExpandableListViewActivity;
import com.example.testutil.view.expandableview.ListListActivity;
import com.example.testutil.view.expandableview.ScrollGridActivity;
import com.xuexiang.app.activity.BaseActivity;

/**  
 * 创建时间：2016-6-22 下午11:50:29  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：ExpandableViewActivity.java  
 **/
public class ExpandableViewActivity extends BaseActivity {

	private Button listlistview, listgridview, expandableListView,expandableGridView;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_expandableview);
		initTitleBar("二级列表");
		
		initview();
		
		initListener();
	}
	
	private void initview() {
		listlistview = (Button) findViewById(R.id.listlist);
		listgridview = (Button) findViewById(R.id.listgrid);
		expandableListView = (Button) findViewById(R.id.expandableListView);
		expandableGridView = (Button) findViewById(R.id.expandableGridView);
		
	}
	
	private void initListener() {
		listlistview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(mContext, ListListActivity.class);
				startActivity(intent);
			}
		});

		listgridview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(mContext, ScrollGridActivity.class);
				startActivity(intent);
			}
		});

		expandableListView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(mContext,
						ExpandableListViewActivity.class);
				startActivity(intent);
			}
		});

		expandableGridView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(mContext,
						ExpandableGridViewActivity.class);
				startActivity(intent);
			}
		});
		
	}

}
