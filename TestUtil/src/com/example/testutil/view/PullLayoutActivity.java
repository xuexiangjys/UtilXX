package com.example.testutil.view;

import android.content.Intent;
import android.view.View;

import com.example.testutil.R;
import com.example.testutil.view.pulllayout.ZoomLayoutActivity;
import com.example.testutil.view.pulllayout.ZoomListActivity;
import com.xuexiang.app.activity.BaseActivity;

/**  
 * 创建时间：2016-6-13 上午12:00:21  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：PullLayoutActivity.java  
 **/
public class PullLayoutActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_pulllayout);
        initTitleBar(TAG);
                
	}
	
	public void zoomLayout(View view){
        Intent intent = new Intent(mContext,ZoomLayoutActivity.class);
        startActivity(intent);
	}


    public void zoomList(View view){
        Intent intent = new Intent(mContext,ZoomListActivity.class);
        startActivity(intent);
    }

}
