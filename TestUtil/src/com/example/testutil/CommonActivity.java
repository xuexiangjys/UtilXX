/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.common.CompressorActivity;
import com.example.testutil.common.ImageViewActivity;
import com.example.testutil.common.XMLParserActivity;
import com.xuexiang.app.activity.BaseActivity;


/**
 * Bitmap测试
 *
 * @author jingle1267@163.com
 */
public class CommonActivity extends BaseActivity implements OnClickListener{
	
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_common);
		initActionBar("普通工具类界面");
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch ( v.getId()) {
		case R.id.btn_imageview:
			mToastUtil.showToast("点击了ImageViewActivity");
        	intent.setClass(this, ImageViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_XMLParser:
			mToastUtil.showToast("点击了XMLParserActivity");
        	intent.setClass(this, XMLParserActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_image_compressor:
			mToastUtil.showToast("点击了CompressorActivity");
        	intent.setClass(this, CompressorActivity.class);
            startActivity(intent);			
			break;
		default:
			break;
		}
	}

}
