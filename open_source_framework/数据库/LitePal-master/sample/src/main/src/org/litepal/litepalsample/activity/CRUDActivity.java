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

import org.litepal.litepalsample.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CRUDActivity extends Activity implements OnClickListener {

	private Button mSaveSampleBtn;

	private Button mUpdateSampleBtn;

	private Button mDeleteSampleBtn;

	private Button mQuerySampleBtn;

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, CRUDActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crud_layout);
		mSaveSampleBtn = (Button) findViewById(R.id.save_sample_btn);
		mUpdateSampleBtn = (Button) findViewById(R.id.update_sample_btn);
		mDeleteSampleBtn = (Button) findViewById(R.id.delete_sample_btn);
		mQuerySampleBtn = (Button) findViewById(R.id.query_sample_btn);
		mSaveSampleBtn.setOnClickListener(this);
		mUpdateSampleBtn.setOnClickListener(this);
		mDeleteSampleBtn.setOnClickListener(this);
		mQuerySampleBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_sample_btn:
			SaveSampleActivity.actionStart(this);
			break;
		case R.id.update_sample_btn:
			UpdateSampleActivity.actionStart(this);
			break;
		case R.id.delete_sample_btn:
			DeleteSampleActivity.actionStart(this);
			break;
		case R.id.query_sample_btn:
			QuerySampleActivity.actionStart(this);
			break;
		default:
			break;
		}
	}

}