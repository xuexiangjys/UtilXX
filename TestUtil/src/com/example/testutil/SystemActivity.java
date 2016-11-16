package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.system.LogHelperActivity;
import com.xuexiang.app.BaseActivity;

/**  
 * 创建时间：2016-5-30 下午6:57:59  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：SystemActivity.java  
 **/
public class SystemActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_system);
	        
		 initActionBar("系统操作界面");
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_loghelper:
			mToastUtil.showToast("点击了LogHelperActivity");
        	intent.setClass(this, LogHelperActivity.class);
            startActivity(intent);	
			break;

		default:
			break;
		}
		
	}

}
