package com.example.mycustomview;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.custom.CalendarActivity;
import com.example.mycustomview.custom.CircleMenuActivity;
import com.example.mycustomview.custom.CityPickerActivity;
import com.example.mycustomview.custom.DraglayoutExpandActivity;
import com.example.mycustomview.custom.FaboptionsActivity;
import com.example.mycustomview.custom.SlideMenuActivity;
import com.example.mycustomview.custom.SnowActivity;
import com.example.mycustomview.custom.StateButtonActivity;
import com.example.mycustomview.custom.WebViewMainActivity;
import com.example.mycustomview.jptabbar.JpTabbarActivity;
import com.example.mycustomview.pageslidingtab.ui.activity.PageSlidingTabActivity;
import com.xuexiang.app.BaseActivity;

public class MainActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_main);
    	initTitleBar("自定义控件主页", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				exitBy2Click();
			}
		});
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.circlemenu:
			mToastUtil.showToast("点击了CircleMenuActivity");
			startActivity(CircleMenuActivity.class);
			break;
		case R.id.statebutton:
			mToastUtil.showToast("点击了StateButtonActivity");
			startActivity(StateButtonActivity.class);
			break;
		case R.id.webview:
			mToastUtil.showToast("点击了WebViewMainActivity");
			startActivity(WebViewMainActivity.class);
			break;
		case R.id.jptabbar:
			mToastUtil.showToast("点击了JpTabbarActivity");
			startActivity(JpTabbarActivity.class);
			break;
		case R.id.faboptions:
			mToastUtil.showToast("点击了FaboptionsActivity");
			startActivity(FaboptionsActivity.class);
			break;
		case R.id.pageslidingtab:
			mToastUtil.showToast("点击了PageSlidingTabActivity");
			startActivity(PageSlidingTabActivity.class);
			break;
		case R.id.draglayoutexpand:
			mToastUtil.showToast("点击了DraglayoutExpandActivity");
			startActivity(DraglayoutExpandActivity.class);
			break;
		case R.id.snow:
			mToastUtil.showToast("点击了SnowActivity");
			startActivity(SnowActivity.class);
			break;
		case R.id.slidemenu:
			mToastUtil.showToast("点击了SlideMenuActivity");
			startActivity(SlideMenuActivity.class);
			break;
		case R.id.calendar:
			mToastUtil.showToast("点击了CalendarActivity");
			startActivity(CalendarActivity.class);
			break;
		case R.id.citypicker:
			mToastUtil.showToast("点击了CityPickerActivity");
			startActivity(CityPickerActivity.class);
			break;

		default:
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
