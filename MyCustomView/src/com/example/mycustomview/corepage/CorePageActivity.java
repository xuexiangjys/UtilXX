package com.example.mycustomview.corepage;

import android.content.Intent;
import android.os.Bundle;

import com.xuexiang.view.corepage.base.BaseActivity;
import com.xuexiang.view.corepage.core.CoreAnim;

public class CorePageActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		openPage("main", null, CoreAnim.none);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
