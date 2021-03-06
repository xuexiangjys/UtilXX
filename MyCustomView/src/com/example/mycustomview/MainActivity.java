package com.example.mycustomview;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mycustomview.corepage.CorePageActivity;
import com.example.mycustomview.custom.AddressPickerActivity;
import com.example.mycustomview.custom.AddressWheelActivity;
import com.example.mycustomview.custom.AlerterActivity;
import com.example.mycustomview.custom.AlertviewActivity;
import com.example.mycustomview.custom.BottomDialogActivity;
import com.example.mycustomview.custom.CalendarActivity;
import com.example.mycustomview.custom.CircleMenuActivity;
import com.example.mycustomview.custom.CityPickerActivity;
import com.example.mycustomview.custom.CompoundIconTextViewActivity;
import com.example.mycustomview.custom.CookieBarActivity;
import com.example.mycustomview.custom.CustomDialogActivity;
import com.example.mycustomview.custom.CustomViewActivity;
import com.example.mycustomview.custom.DialogBuilderActivity;
import com.example.mycustomview.custom.DraglayoutExpandActivity;
import com.example.mycustomview.custom.FaboptionsActivity;
import com.example.mycustomview.custom.FilterMenuActivity;
import com.example.mycustomview.custom.FloatBallActivity;
import com.example.mycustomview.custom.FloatingViewActivity;
import com.example.mycustomview.custom.FlowlayoutActivity;
import com.example.mycustomview.custom.HintPopupWindowActivity;
import com.example.mycustomview.custom.ImagepickerActivity;
import com.example.mycustomview.custom.JsBridgeActivity;
import com.example.mycustomview.custom.LabelActivity;
import com.example.mycustomview.custom.LoadViewHelperActivity;
import com.example.mycustomview.custom.LoadingLayoutActivity;
import com.example.mycustomview.custom.MagicindicatorActivity;
import com.example.mycustomview.custom.MarqueenActivity;
import com.example.mycustomview.custom.MaterialDialogActivity;
import com.example.mycustomview.custom.PasswordEditTextActivity;
import com.example.mycustomview.custom.RatingBarActivity;
import com.example.mycustomview.custom.SVGParseActivity;
import com.example.mycustomview.custom.SearchBoxDialogActivity;
import com.example.mycustomview.custom.SettingItemActivity;
import com.example.mycustomview.custom.SignatureActivity;
import com.example.mycustomview.custom.SlideMenuActivity;
import com.example.mycustomview.custom.SmileRatingActivity;
import com.example.mycustomview.custom.SnowActivity;
import com.example.mycustomview.custom.StateButtonActivity;
import com.example.mycustomview.custom.StateLayoutActivity;
import com.example.mycustomview.custom.StatefulLayoutActivity;
import com.example.mycustomview.custom.StepViewHorizonalActivity;
import com.example.mycustomview.custom.StyleabletToastActivity;
import com.example.mycustomview.custom.SwitchIconActivity;
import com.example.mycustomview.custom.TimeLineActivity;
import com.example.mycustomview.custom.ToastyActivity;
import com.example.mycustomview.custom.UIViewActivity;
import com.example.mycustomview.custom.ValidateUIActivity;
import com.example.mycustomview.custom.ViewToolTipActivity;
import com.example.mycustomview.custom.WeatherViewActivity;
import com.example.mycustomview.custom.WebViewMainActivity;
import com.example.mycustomview.custom.ZXingActivity;
import com.example.mycustomview.easyindicator.EasyIndicatorActivity;
import com.example.mycustomview.easypop.EasyPopActivity;
import com.example.mycustomview.indicatordialog.IndicatorDialogActivity;
import com.example.mycustomview.jptabbar.JpTabbarActivity;
import com.example.mycustomview.pageslidingtab.ui.activity.PageSlidingTabActivity;
import com.example.mycustomview.pathanim.PathAnimActivity;
import com.example.mycustomview.pickers.PickersActivity;
import com.example.mycustomview.pickerview.PickerviewActivity;
import com.example.mycustomview.smoothprogressbar.SmoothProgressBarActivity;
import com.example.mycustomview.supertextview.SuperTextViewActivity;
import com.example.mycustomview.tabview.TabViewActivity;
import com.example.mycustomview.taglayout.TagLayoutActivity;
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
		case R.id.filtermenu:
			mToastUtil.showToast("点击了FilterMenuActivity");
			startActivity(FilterMenuActivity.class);
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
		case R.id.pickerview:
			mToastUtil.showToast("点击了PickerviewActivity");
			startActivity(PickerviewActivity.class);
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
		case R.id.smoothprogressbar:
			mToastUtil.showToast("点击了SmoothProgressBarActivity");
			startActivity(SmoothProgressBarActivity.class);
			break;
		case R.id.signature:
			mToastUtil.showToast("点击了SignatureActivity");
			startActivity(SignatureActivity.class);
			break;
		case R.id.alertview:
			mToastUtil.showToast("点击了AlertviewActivity");
			startActivity(AlertviewActivity.class);
			break;
		case R.id.flowlayout:
			mToastUtil.showToast("点击了FlowlayoutActivity");
			startActivity(FlowlayoutActivity.class);
			break;
		case R.id.searchboxdialog:
			mToastUtil.showToast("点击了SearchBoxDialogActivity");
			startActivity(SearchBoxDialogActivity.class);
			break;
		case R.id.customdialog:
			mToastUtil.showToast("点击了CustomDialogActivity");
			startActivity(CustomDialogActivity.class);
			break;
		case R.id.hintpopupwindow:
			mToastUtil.showToast("点击了HintPopupWindowActivity");
			startActivity(HintPopupWindowActivity.class);
			break;
		case R.id.timeline:
			mToastUtil.showToast("点击了TimeLineActivity");
			startActivity(TimeLineActivity.class);
			break;
			
		case R.id.stepviewhorizonal:
			mToastUtil.showToast("点击了StepViewHorizonalActivity");
			startActivity(StepViewHorizonalActivity.class);
			break;
			
		case R.id.alerter:
			mToastUtil.showToast("点击了AlerterActivity");
			startActivity(AlerterActivity.class);
			break;
			
		case R.id.materialdialog:
			mToastUtil.showToast("点击了MaterialDialogActivity");
			startActivity(MaterialDialogActivity.class);
			break;
		case R.id.settingitem:
			mToastUtil.showToast("点击了SettingItemActivity");
			startActivity(SettingItemActivity.class);
			break;
		case R.id.pickers:
			mToastUtil.showToast("点击了PickersActivity");
			startActivity(PickersActivity.class);
			break;
		case R.id.indicatordialog:
			mToastUtil.showToast("点击了IndicatorDialogActivity");
			startActivity(IndicatorDialogActivity.class);
			break;
		case R.id.statefullayout:
			mToastUtil.showToast("点击了StatefulLayoutActivity");
			startActivity(StatefulLayoutActivity.class);
			break;
		case R.id.statelayout:
			mToastUtil.showToast("点击了StateLayoutActivity");
			startActivity(StateLayoutActivity.class);
			break;
		case R.id.loadviewhelper:
			mToastUtil.showToast("点击了LoadViewHelperActivity");
			startActivity(LoadViewHelperActivity.class);
			break;
		case R.id.zxing:
			mToastUtil.showToast("点击了ZXingActivity");
			startActivity(ZXingActivity.class);
			break;
		case R.id.corepage:
			mToastUtil.showToast("点击了CorePageActivity");
			startActivity(CorePageActivity.class);
			break;
		case R.id.ratingbar:
			mToastUtil.showToast("点击了RatingBarActivity");
			startActivity(RatingBarActivity.class);
			break;
		case R.id.compoundicontextview:
			mToastUtil.showToast("点击了CompoundIconTextViewActivity");
			startActivity(CompoundIconTextViewActivity.class);
			break;
		case R.id.easyindicator:
			mToastUtil.showToast("点击了EasyIndicatorActivity");
			startActivity(EasyIndicatorActivity.class);
			break;
		case R.id.smilerating:
			mToastUtil.showToast("点击了SmileRatingActivity");
			startActivity(SmileRatingActivity.class);
			break;
		case R.id.viewtooltip:
			mToastUtil.showToast("点击了ViewToolTipActivity");
			startActivity(ViewToolTipActivity.class);
			break;
		case R.id.easypop:
			mToastUtil.showToast("点击了EasyPopActivity");
			startActivity(EasyPopActivity.class);
			break;
		case R.id.taglayout:
			mToastUtil.showToast("点击了TagLayoutActivity");
			startActivity(TagLayoutActivity.class);
			break;
		case R.id.jsbridge:
			mToastUtil.showToast("点击了JsBridgeActivity");
			startActivity(JsBridgeActivity.class);
			break;
		case R.id.dialogbuilder:
			mToastUtil.showToast("点击了DialogBuilderActivity");
			startActivity(DialogBuilderActivity.class);
			break;
		case R.id.validateui:
			mToastUtil.showToast("点击了ValidateUIActivity");
			startActivity(ValidateUIActivity.class);
			break;
		case R.id.cookiebar:
			mToastUtil.showToast("点击了CookieBarActivity");
			startActivity(CookieBarActivity.class);
			break;
		case R.id.uiview:
			mToastUtil.showToast("点击了UIViewActivity");
			startActivity(UIViewActivity.class);
			break;
		case R.id.supertextview:
			mToastUtil.showToast("点击了SuperTextViewActivity");
			startActivity(SuperTextViewActivity.class);
			break;
		case R.id.passwordedittext:
			mToastUtil.showToast("点击了PasswordEditTextActivity");
			startActivity(PasswordEditTextActivity.class);
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
