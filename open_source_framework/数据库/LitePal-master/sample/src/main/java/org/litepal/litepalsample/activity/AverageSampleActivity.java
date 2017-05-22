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

import org.litepal.crud.DataSupport;
import org.litepal.litepalsample.R;
import org.litepal.litepalsample.model.Singer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AverageSampleActivity extends Activity implements OnClickListener {

	private Button mAvgBtn1;

	private Button mAvgBtn2;

	private EditText mAgeEdit;

	private TextView mResultText;

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, AverageSampleActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.average_sample_layout);
		mAvgBtn1 = (Button) findViewById(R.id.avg_btn1);
		mAvgBtn2 = (Button) findViewById(R.id.avg_btn2);
		mAgeEdit = (EditText) findViewById(R.id.age_edit);
		mResultText = (TextView) findViewById(R.id.result_text);
		mAvgBtn1.setOnClickListener(this);
		mAvgBtn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		double result = 0;
		switch (view.getId()) {
		case R.id.avg_btn1:
			result = DataSupport.average(Singer.class, "age");
			mResultText.setText(String.valueOf(result));
			break;
		case R.id.avg_btn2:
			try {
				result = DataSupport.where("age > ?", mAgeEdit.getText().toString()).average(
						Singer.class, "age");
				mResultText.setText(String.valueOf(result));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
		}
	}

}