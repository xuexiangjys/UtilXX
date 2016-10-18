package com.example.testutil.view.ioniconview;

import android.widget.ListView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;

public class IonIconViewActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_ioniconview);

		initTitleBar(TAG);
        IconListAdapter ila = new IconListAdapter(this);
        ( (ListView) findViewById(R.id.iconlist) ).setAdapter(ila);
	}


}
