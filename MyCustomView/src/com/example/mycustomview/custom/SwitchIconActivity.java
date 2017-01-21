package com.example.mycustomview.custom;

import android.view.View;

import com.example.mycustomview.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.custom.SwitchIconView;

public class SwitchIconActivity extends BaseActivity {

	private SwitchIconView switchIcon1;
	private SwitchIconView switchIcon2;
	private SwitchIconView switchIcon3;
	private View button1;
	private View button2;
	private View button3;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_switchicon);

		initTitleBar(TAG);
		switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
		switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);
		switchIcon3 = (SwitchIconView) findViewById(R.id.switchIconView3);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switchIcon1.switchState();
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switchIcon2.switchState();
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switchIcon3.switchState();
			}
		});
	}
}
