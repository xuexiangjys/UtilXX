package com.example.testutil.view.expandableview;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.expandableview.adapter.ClassifyMainAdapter;
import com.xuexiang.view.expandableview.adapter.ClassifyMoreAdapter;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ListListActivity extends BaseActivity {

	private ListView mainlist;
	private ListView morelist;
	private ArrayList<IconItem> mainList;
	ClassifyMainAdapter mainAdapter;
	ClassifyMoreAdapter moreAdapter;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_classify);
		initTitleBar("二级列表");
		initModle();
		initView();
	}

	private void initView() {
		mainlist = (ListView) findViewById(R.id.classify_mainlist);
		morelist = (ListView) findViewById(R.id.classify_morelist);
		mainAdapter = new ClassifyMainAdapter(ListListActivity.this, mainList);
		mainAdapter.setSelectItem(0);
		mainlist.setAdapter(mainAdapter);

		mainlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				initAdapter(Model.MORELISTTXT[position]);
				mainAdapter.setSelectItem(position);
				mainAdapter.notifyDataSetChanged();
			}
		});
		mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// 一定要设置这个属性，否则ListView不会刷新
		initAdapter(Model.MORELISTTXT[0]);

		morelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				moreAdapter.setSelectItem(position);
				Toast.makeText(ListListActivity.this, "点击了："+moreAdapter.getItem(position), Toast.LENGTH_SHORT).show();
				moreAdapter.notifyDataSetChanged();
			}
		});
	}

	private void initAdapter(String[] array) {
		moreAdapter = new ClassifyMoreAdapter(this, array);
		morelist.setAdapter(moreAdapter);
		moreAdapter.notifyDataSetChanged();
	}

	private void initModle() {
		mainList = new ArrayList<IconItem>();
		for (int i = 0; i < Model.LISTVIEWIMG.length; i++) {
			mainList.add(new IconItem(Model.LISTVIEWIMG[i],Model.LISTVIEWTXT[i]));
		}
	}
}