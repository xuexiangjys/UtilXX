package com.example.testutil.view.expandableview;

import java.util.ArrayList;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.expandableview.adapter.ExpandableListAdapter;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ExpandableListViewActivity extends BaseActivity {

	private ExpandableListView expandableListView;

	private ExpandableListAdapter adapter;

	private ArrayList<IconItem> mList;
	private String[][] child_text_array;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_expandable_listview);
		initTitleBar(TAG);
		init();
		initModle();
		setListener();
	}

	private void init() {
		expandableListView = (ExpandableListView) findViewById(R.id.list);

		child_text_array = Model.EXPANDABLE_MORELIST_TXT;
	}

	private void setListener() {
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(getApplicationContext(),
						child_text_array[groupPosition][childPosition],
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	private void initModle() {
		mList = new ArrayList<IconItem>();
		for (int i = 0; i < Model.EXPANDABLE_LISTVIEW_TXT.length; i++) {
			mList.add(new IconItem(Model.EXPANDABLE_LISTVIEW_IMG[i], Model.EXPANDABLE_LISTVIEW_TXT[i]));
		}
		adapter = new ExpandableListAdapter(this, mList, child_text_array);
		expandableListView.setAdapter(adapter);
	}

}
