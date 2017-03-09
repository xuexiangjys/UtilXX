package com.example.testutil.view;

import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.PatternView;

/**  
 * 创建时间：2016-5-29 下午11:58:50  
 * 项目名称：TestUtil  
 * @author xuexiang
 * 文件名称：PatternViewActivity.java  
 **/
public class PatternViewActivity extends BaseActivity {

	private PatternView patternView;

	private String patternString;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_patternview);		
		
		initView();
	}
	private void initView() {
		 patternView = (PatternView) findViewById(R.id.patternView);
	        Toast.makeText(getApplicationContext(), "ENTER PATTERN", Toast.LENGTH_LONG).show();
	        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
	            
	            @Override
	            public void onPatternDetected() {
	                if (patternString == null) {
	                    patternString = patternView.getPatternString();
	                    patternView.clearPattern();
	                    return;
	                }
	                if (patternString.equals(patternView.getPatternString())) {
	                    Toast("PATTERN CORRECT");
	                    return;
	                }
	                Toast("PATTERN NOT CORRECT");
	                patternView.clearPattern();
	            }
	        });

	}

}
