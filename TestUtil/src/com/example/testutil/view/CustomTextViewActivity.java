package com.example.testutil.view;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.Colors;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.CustomTextView;

/**  
 * 创建时间：2016-6-26 下午9:46:07  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：CustomTextViewActivity.java  
 **/
public class CustomTextViewActivity extends BaseActivity {
	private CustomTextView mTextView1;
	
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_customtextview);
		
		initTitleBar(TAG);
        
	    mTextView1 = (CustomTextView) findViewById(R.id.tv_1);
        int radius = DisplayUtils.dip2px(mContext, 10);

        mTextView1.setSolidColor(Colors.ORANGE_DARK);
        mTextView1.setRadius(radius,radius,radius,radius);
        mTextView1.setSelectedTextColor(Colors.YELLOW, Colors.GREEN);
        mTextView1.setStrokeColorAndWidth(DisplayUtils.dip2px(mContext, 2), Colors.BLUE);
        mTextView1.setSelectedSolidColor(Colors.ORANGE_DARK, Colors.BLUE_DARK);
	}

}
