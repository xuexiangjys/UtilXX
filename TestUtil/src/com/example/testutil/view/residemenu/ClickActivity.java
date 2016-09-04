package com.example.testutil.view.residemenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testutil.R;

public class ClickActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click);

		TextView text = (TextView) findViewById(R.id.textView);

		Intent receive = getIntent();
		String flog = receive.getStringExtra("flog");

		text.setText(flog);

	}
}
