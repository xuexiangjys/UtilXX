/*
 * Copyright (c) Joaquim Ley 2016. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mycustomview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.view.faboptions.FabOptions;

/**
 * Faboptions sample via XML {@see R.layout.activity_faboptions}
 */
public class FaboptionsActivity extends AppCompatActivity implements
		View.OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faboptions);
		
		ActivityUtil.initTitleBar(this, "FaboptionsActivity");
		FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
		fabOptions.setOnClickListener(this);

		FabOptions fabOptions1 = (FabOptions) findViewById(R.id.fab_options1);
		fabOptions1.setButtonsMenu(this, R.menu.menu_faboptions1);
		fabOptions1.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.faboptions_favorite:
			Toast("Favorite");
			break;

		case R.id.faboptions_textsms:
			Toast("Message");
			break;

		case R.id.faboptions_download:
			Toast("Download");
			break;

		case R.id.faboptions_share:
			Toast("Share");
			break;

		default:
			// no-op
		}
	}

	public void Toast(CharSequence hint) {
		Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
	}
}