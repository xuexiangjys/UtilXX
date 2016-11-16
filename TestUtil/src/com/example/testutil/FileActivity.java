package com.example.testutil;

import com.xuexiang.app.BaseActivity;

/**  
 * 创建时间：2016-5-30 下午6:53:12  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：FileActivity.java  
 **/
public class FileActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_file);
	        
	     initActionBar("文件操作界面");
	}

}
