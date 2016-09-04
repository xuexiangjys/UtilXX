package com.example.testutil.view.swipelistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testutil.R;

/**
 * http://blog.csdn.net/qq_16064871
 * @author Administrator
 */
public class ListViewItemShowActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_listview_recentitem);
		
		initView();
	}

	private void initView() {
		Bundle bundle = this.getIntent().getExtras();

		TextView name = (TextView) findViewById(R.id.recent_list_item_name);
		TextView num = (TextView) findViewById(R.id.unreadmsg);
		ImageView head = (ImageView) findViewById(R.id.icon);
		
		head.setImageResource(bundle.getInt("HeadImg"));
		name.setText(bundle.getString(("Name")));
		num.setText(String.valueOf(bundle.getInt("NewNum")));
	}
	
}
