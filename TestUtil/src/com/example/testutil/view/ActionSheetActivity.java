package com.example.testutil.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.ActionSheet;
import com.xuexiang.view.CompareIndicator;
import com.xuexiang.view.ActionSheet.ActionSheetListener;

/**  
 * 创建时间：2016-5-29 下午7:17:49  
 * 项目名称：TestUtil  
 * @author xuexiang
 * 文件名称：ActionSheetActivity.java  
 **/
public class ActionSheetActivity extends BaseActivity implements ActionSheetListener, OnClickListener{

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_actionsheet);
	        
         initActionBar("ActionSheet");
         
         initCompareIndicator();
	}


	public void showActionSheet() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Item1", "Item2", "Item3", "Item4")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }
	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancle) {
		Toast("dismissed isCancle = " + isCancle);
	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		  Toast.makeText(getApplicationContext(), "click item index = " + index,
	                Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		  switch (v.getId()) {
          case R.id.ios6:
              setTheme(R.style.ActionSheetStyleiOS6);
              break;
          case R.id.ios7:
              setTheme(R.style.ActionSheetStyleiOS7);
              break;
      }
      showActionSheet();
	}
	
	private void initCompareIndicator() {
		CompareIndicator CompareIndicator1 = (CompareIndicator) findViewById(R.id.CompareIndicator1);
		CompareIndicator CompareIndicator2 = (CompareIndicator) findViewById(R.id.CompareIndicator2);
		CompareIndicator CompareIndicator3 = (CompareIndicator) findViewById(R.id.CompareIndicator3);
        CompareIndicator1.updateView(10,90);
        CompareIndicator2.updateView(30,70);
        CompareIndicator3.updateView(70,30);		
	}
}
