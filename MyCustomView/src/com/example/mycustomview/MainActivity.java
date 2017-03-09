package com.example.mycustomview;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.custom.AddressPickerActivity;
import com.example.mycustomview.custom.AddressWheelActivity;
import com.example.mycustomview.custom.BottomDialogActivity;
import com.example.mycustomview.custom.CalendarActivity;
import com.example.mycustomview.custom.CircleMenuActivity;
import com.example.mycustomview.custom.CityPickerActivity;
import com.example.mycustomview.custom.CustomViewActivity;
import com.example.mycustomview.custom.DraglayoutExpandActivity;
import com.example.mycustomview.custom.FaboptionsActivity;
import com.example.mycustomview.custom.FloatBallActivity;
import com.example.mycustomview.custom.FloatingViewActivity;
import com.example.mycustomview.custom.ImagepickerActivity;
import com.example.mycustomview.custom.LabelActivity;
import com.example.mycustomview.custom.LoadingLayoutActivity;
import com.example.mycustomview.custom.MagicindicatorActivity;
import com.example.mycustomview.custom.MarqueenActivity;
import com.example.mycustomview.custom.SVGParseActivity;
import com.example.mycustomview.custom.SlideMenuActivity;
import com.example.mycustomview.custom.SnowActivity;
import com.example.mycustomview.custom.StateButtonActivity;
import com.example.mycustomview.custom.StyleabletToastActivity;
import com.example.mycustomview.custom.SwitchIconActivity;
import com.example.mycustomview.custom.ToastyActivity;
import com.example.mycustomview.custom.WeatherViewActivity;
import com.example.mycustomview.custom.WebViewMainActivity;
import com.example.mycustomview.jptabbar.JpTabbarActivity;
import com.example.mycustomview.pageslidingtab.ui.activity.PageSlidingTabActivity;
import com.example.mycustomview.pathanim.PathAnimActivity;
import com.example.mycustomview.tabview.TabViewActivity;
import com.xuexiang.app.activity.BaseActivity;

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
		case R.id.bottomdialog:
			mToastUtil.showToast("点击了BottomDialogActivity");
			startActivity(BottomDialogActivity.class);
			break;
		case R.id.floatingview:
			mToastUtil.showToast("点击了FloatingViewActivity");
			startActivity(FloatingViewActivity.class);
			break;
		case R.id.customview:
			mToastUtil.showToast("点击了CustomViewActivity");
			startActivity(CustomViewActivity.class);
			break;
		case R.id.floatball:
			mToastUtil.showToast("点击了FloatBallActivity");
			startActivity(FloatBallActivity.class);
			break;
		case R.id.labelview:
			mToastUtil.showToast("点击了LabelActivity");
			startActivity(LabelActivity.class);
			break;
		case R.id.marqueen:
			mToastUtil.showToast("点击了MarqueenActivity");
			startActivity(MarqueenActivity.class);
			break;
		case R.id.addresspicker:
			mToastUtil.showToast("点击了AddressPickerActivity");
			startActivity(AddressPickerActivity.class);
			break;
		case R.id.addresswheel:
			mToastUtil.showToast("点击了AddressWheelActivity");
			startActivity(AddressWheelActivity.class);
			break;
		case R.id.loadinglayout:
			mToastUtil.showToast("点击了LoadingLayoutActivity");
			startActivity(LoadingLayoutActivity.class);
			break;
		case R.id.pathanim:
			mToastUtil.showToast("点击了PathAnimActivity");
			startActivity(PathAnimActivity.class);
			break;
		case R.id.weatherview:
			mToastUtil.showToast("点击了WeatherViewActivity");
			startActivity(WeatherViewActivity.class);
			break;
		case R.id.switchicon:
			mToastUtil.showToast("点击了SwitchIconActivity");
			startActivity(SwitchIconActivity.class);
			break;
		case R.id.svgparse:
			mToastUtil.showToast("点击了SVGParseActivity");
			startActivity(SVGParseActivity.class);
			break;
		case R.id.imagepicker:
			mToastUtil.showToast("点击了ImagepickerActivity");
			startActivity(ImagepickerActivity.class);
			break;
		case R.id.tabview:
			mToastUtil.showToast("点击了TabViewActivity");
			startActivity(TabViewActivity.class);
			break;
		case R.id.toasty:
			mToastUtil.showToast("点击了ToastyActivity");
			startActivity(ToastyActivity.class);
			break;
		case R.id.styleabletoast:
			mToastUtil.showToast("点击了StyleableToastActivity");
			startActivity(StyleabletToastActivity.class);
			break;
		case R.id.magicindicator:
			mToastUtil.showToast("点击了MagicindicatorActivity");
			startActivity(MagicindicatorActivity.class);
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
