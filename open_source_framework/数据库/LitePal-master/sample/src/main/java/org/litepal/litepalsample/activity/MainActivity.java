/*
 * Copyright (C)  Tony Green, Litepal Framework Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litepal.litepalsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.litepal.litepalsample.R;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";

	private Button mManageTableBtn;

	private Button mCrudBtn;

	private Button mAggregateBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		mManageTableBtn = (Button) findViewById(R.id.manage_table_btn);
		mCrudBtn = (Button) findViewById(R.id.crud_btn);
		mAggregateBtn = (Button) findViewById(R.id.aggregate_btn);
		mManageTableBtn.setOnClickListener(this);
		mCrudBtn.setOnClickListener(this);
		mAggregateBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.manage_table_btn:
			ManageTablesActivity.actionStart(this);
			break;
		case R.id.crud_btn:
			CRUDActivity.actionStart(this);
			break;
		case R.id.aggregate_btn:
			AggregateActivity.actionStart(this);
			break;
		default:
			break;
		}
	}

}