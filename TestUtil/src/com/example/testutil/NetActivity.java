package com.example.testutil;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.testutil.net.AsyncHttpActivity;
import com.example.testutil.net.FileUpLoadAndDownLoadActivity;
import com.example.testutil.net.OkHttpActivity;
import com.example.testutil.net.VolleyHttpActivity;
import com.example.testutil.net.model.HttpConsts;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.app.BaseApplication;
import com.xuexiang.util.view.DialogUtil;

/**  
 * 创建时间：2016-5-30 下午6:56:42  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：NetActivity.java  
 **/
public class NetActivity extends BaseActivity implements OnClickListener{
	private ImageView settingappurl;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_net);
		initActionBar("网络操作界面");
		
		initNetAppUrl();
	}

	private void initNetAppUrl() {
		settingappurl = (ImageView) findViewById(R.id.settingappurl);
        settingappurl.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				final EditText edit = new EditText(mContext);
				DialogUtil.showFilloutDialog(mContext, "修改网络", edit, BaseApplication.app_url, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						mSettingManager.setAppUrl(edit.getText().toString());
						HttpConsts.setAppUrl(mSettingManager.getAppUrl());
					}
				});
			}}
		);		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_volley:
			mToastUtil.showToast("点击了VolleyHttpActivity");
        	intent.setClass(this, VolleyHttpActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_asynchttp:
			mToastUtil.showToast("点击了AsyncHttpActivity");
        	intent.setClass(this, AsyncHttpActivity.class);
            startActivity(intent);			
			break;		
		case R.id.btn_okhttp:
			mToastUtil.showToast("点击了OkHttpActivity");
        	intent.setClass(this, OkHttpActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_file_upload_downLoad:
			mToastUtil.showToast("点击了FileUpLoadAndDownLoadActivity");
        	intent.setClass(this, FileUpLoadAndDownLoadActivity.class);
            startActivity(intent);			
			break;	
		default:
			break;
		}
		
	}

}
