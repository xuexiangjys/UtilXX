package com.example.mycustomview.custom;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.activity.BaseActivity;

public class SlideMenuActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_slidemenu);
		
		initTitleBar(TAG);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 50; i++) {
        	list.add("菜单项" + i);
		}
        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "点击菜单项" + (position + 1), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
