package com.example.testutil;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.xuexiang.app.BaseActivity;

/**
 * 测试代码
 */
public class MainActivity extends BaseActivity {
	
	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_main);
	        
	     initActionBar("主界面");
	}

	public void onClick(View v) {
	    Intent intent = new Intent();
		switch (v.getId()) {
        case R.id.btn_common:
        	mToastUtil.showToast("点击了CommonActivity");
        	intent.setClass(this, CommonActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_data:
        	mToastUtil.showToast("点击了DataActivity");
        	intent.setClass(this, DataActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_dapter:
        	mToastUtil.showToast("点击了BaseAdapterListviewActivity");
        	intent.setClass(this, AdapterListviewActivity.class);
            startActivity(intent);
            break;           
        case R.id.btn_service:
        	mToastUtil.showToast("点击了ServiceActivity");
        	intent.setClass(this, ServiceActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_file:
        	mToastUtil.showToast("点击了FileActivity");
        	intent.setClass(this, FileActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_net:
        	mToastUtil.showToast("点击了NetActivity");
        	intent.setClass(this, NetActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_resource:
        	mToastUtil.showToast("点击了ResourceActivity");
        	intent.setClass(this, ResourceActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_system:
        	mToastUtil.showToast("点击了SystemActivity");
        	intent.setClass(this, SystemActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_view:
        	mToastUtil.showToast("点击了ViewActivity");
        	intent.setClass(this, ViewActivity.class);
            startActivity(intent);
            break;
       }
	}

	/**  * 菜单、返回键响应  */
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if(keyCode == KeyEvent.KEYCODE_BACK) {    
			exitBy2Click(); 
		}  
		return false; 
	} 
}
