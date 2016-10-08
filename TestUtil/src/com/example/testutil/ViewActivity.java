package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.view.ActionSheetActivity;
import com.example.testutil.view.CircularFloatingActionMenuActivity;
import com.example.testutil.view.CustomProgressBarActivity;
import com.example.testutil.view.CustomTextViewActivity;
import com.example.testutil.view.CustomViewActivity;
import com.example.testutil.view.DragGridViewActivity;
import com.example.testutil.view.DropDownMenuActivity;
import com.example.testutil.view.ExpandableViewActivity;
import com.example.testutil.view.FlagTextViewActivity;
import com.example.testutil.view.FlipShareViewActivity;
import com.example.testutil.view.FloatingActionMenuActivity;
import com.example.testutil.view.FlowTagLayoutActivity;
import com.example.testutil.view.GesturePasswordWelComeActivity;
import com.example.testutil.view.GuidViewActivity;
import com.example.testutil.view.HeaderLayoutActivity;
import com.example.testutil.view.ImageCycleViewActivity;
import com.example.testutil.view.LoadingDialogActivity;
import com.example.testutil.view.MaterialDesignActivity;
import com.example.testutil.view.MultipleStatusViewActivity;
import com.example.testutil.view.NiftyDialogActivity;
import com.example.testutil.view.PassWordInputActivity;
import com.example.testutil.view.PatternViewActivity;
import com.example.testutil.view.PickerUIActivity;
import com.example.testutil.view.ProgressButtonActivity;
import com.example.testutil.view.PullLayoutActivity;
import com.example.testutil.view.RangeSliderViewActivity;
import com.example.testutil.view.SatelliteMenuActivity;
import com.example.testutil.view.SnakbarActivity;
import com.example.testutil.view.SwipeDismissListViewActivity;
import com.example.testutil.view.SwipeListViewActivity;
import com.example.testutil.view.SwipeMenuActivity;
import com.example.testutil.view.TabBarActivity;
import com.example.testutil.view.TimeLineActivity;
import com.example.testutil.view.TitleBarActivity;
import com.example.testutil.view.ViewPaperAndGridViewActivity;
import com.example.testutil.view.ViewpaperAdvActivity;
import com.example.testutil.view.avloadingindicatorview.AVLoadingIndicatorViewActivity;
import com.example.testutil.view.residemenu.ResideMenuMainFragmentPagerActivity;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.Colors;
import com.xuexiang.view.materialdesign.view.ButtonFloat;

/**  
 * 创建时间：2016-5-28 上午12:54:52  
 * 项目名称：TestUtil  
 * @author xuexiang
 * 文件名称：ViewActivity.java  
 **/
public class ViewActivity extends BaseActivity implements OnClickListener{
	private ButtonFloat mButtonFloat;
	@Override
	public void onCreateActivity() {
	    setContentView(R.layout.activity_view);
	        
	    initActionBar("自定义控件界面");
		
	    initFloatButton();
	}

	/**
	 * 初始化悬浮按钮
	 */
	private void initFloatButton() {
		mButtonFloat = (ButtonFloat) findViewById(R.id.buttonfloat);
		mButtonFloat.getBackground().setAlpha(150);
		mButtonFloat.setBackgroundColor(Colors.GREEN);
		mButtonFloat.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mToastUtil.showToast("点击了悬浮按钮");	
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch ( v.getId()) {
		case R.id.btn_customview:
			mToastUtil.showToast("点击了CustomViewActivity");
        	intent.setClass(this, CustomViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_materialdesign:
			mToastUtil.showToast("点击了MaterialDesignActivity");
        	intent.setClass(this, MaterialDesignActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_actionsheet:
			mToastUtil.showToast("点击了ActionSheetActivity");
        	intent.setClass(this, ActionSheetActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_gesturepassword:
			mToastUtil.showToast("点击了GesturePasswordWelComeActivity");
        	intent.setClass(this, GesturePasswordWelComeActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_PatternView:
			mToastUtil.showToast("点击了PatternViewActivity");
        	intent.setClass(this, PatternViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_loadingdialog:
			mToastUtil.showToast("点击了LoadingDialogActivity");
        	intent.setClass(this, LoadingDialogActivity.class);
            startActivity(intent);			
			break;			
		case R.id.btn_titleBar:
			mToastUtil.showToast("点击了TitleBarActivity");
        	intent.setClass(this, TitleBarActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_niftydialog:
			mToastUtil.showToast("点击了NiftyDialogActivity");
        	intent.setClass(this, NiftyDialogActivity.class);
            startActivity(intent);			
			break;			
		case R.id.btn_flagtextview:
			mToastUtil.showToast("点击了FlagTextViewActivity");
        	intent.setClass(this, FlagTextViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_headerlayout:
			mToastUtil.showToast("点击了HeaderLayoutActivity");
        	intent.setClass(this, HeaderLayoutActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_residemenu:
			mToastUtil.showToast("点击了ResideMenuMainFragmentPagerActivity");
        	intent.setClass(this, ResideMenuMainFragmentPagerActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_pickerui:
			mToastUtil.showToast("点击了PickerUIActivity");
        	intent.setClass(this, PickerUIActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_pulllayout:
			mToastUtil.showToast("点击了PullLayoutActivity");
        	intent.setClass(this, PullLayoutActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_dropdownmenu:
			mToastUtil.showToast("点击了DropDownMenuActivity");
        	intent.setClass(this, DropDownMenuActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_swipemenu:
			mToastUtil.showToast("点击了SwipeMenuActivity");
        	intent.setClass(this, SwipeMenuActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_tabbar:
			mToastUtil.showToast("点击了TabBarActivity");
        	intent.setClass(this, TabBarActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_expandableview:
			mToastUtil.showToast("点击了ExpandableViewActivity");
        	intent.setClass(this, ExpandableViewActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_viewpaperadv:
			mToastUtil.showToast("点击了ViewpaperAdvActivity");
        	intent.setClass(this, ViewpaperAdvActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_imagecycleview:
			mToastUtil.showToast("点击了ImageCycleViewActivity");
        	intent.setClass(this, ImageCycleViewActivity.class);
            startActivity(intent);			
			break;			
		case R.id.btn_progressbutton:
			mToastUtil.showToast("点击了ProgressButtonActivity");
        	intent.setClass(this, ProgressButtonActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_customprogressbar:
			mToastUtil.showToast("点击了CustomProgressBarActivity");
        	intent.setClass(this, CustomProgressBarActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_customtextView:
			mToastUtil.showToast("点击了CustomTextViewActivity");
        	intent.setClass(this, CustomTextViewActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_passwordinput:
			mToastUtil.showToast("点击了PassWordInputActivity");
        	intent.setClass(this, PassWordInputActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_timeline:
			mToastUtil.showToast("点击了TimeLineActivity");
        	intent.setClass(this, TimeLineActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_guidview:
			mToastUtil.showToast("点击了GuidViewActivity");
        	intent.setClass(this, GuidViewActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_swipelistview:
			mToastUtil.showToast("点击了SwipeListViewActivity");
        	intent.setClass(this, SwipeListViewActivity.class);
            startActivity(intent);			
			break;				
		case R.id.btn_swipedismisslistview:
			mToastUtil.showToast("点击了SwipeDismissListViewActivity");
        	intent.setClass(this, SwipeDismissListViewActivity.class);
            startActivity(intent);			
			break;				
		case R.id.btn_viewPaperandgridview:
			mToastUtil.showToast("点击了ViewPaperAndGridViewActivity");
        	intent.setClass(this, ViewPaperAndGridViewActivity.class);
            startActivity(intent);			
			break;	
		case R.id.btn_draggridView:
			mToastUtil.showToast("点击了DragGridViewActivity");
        	intent.setClass(this, DragGridViewActivity.class);
            startActivity(intent);			
			break;			
		case R.id.btn_flowtaglayout:
			mToastUtil.showToast("点击了FlowTagLayoutActivity");
        	intent.setClass(this, FlowTagLayoutActivity.class);
            startActivity(intent);			
			break;				
		case R.id.btn_circularfloatingactionmenu:
			mToastUtil.showToast("点击了CircularFloatingActionMenuActivity");
        	intent.setClass(this, CircularFloatingActionMenuActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_rangesliderview:
			mToastUtil.showToast("点击了RangeSliderViewActivity");
        	intent.setClass(this, RangeSliderViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_snakbar:
			mToastUtil.showToast("点击了SnakbarActivity");
        	intent.setClass(this, SnakbarActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_flipshareview:
			mToastUtil.showToast("点击了FlipShareViewActivity");
        	intent.setClass(this, FlipShareViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_satellitemenu:
			mToastUtil.showToast("点击了SatelliteMenuActivity");
        	intent.setClass(this, SatelliteMenuActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_floatingactionmenu:
			mToastUtil.showToast("点击了FloatingActionMenuActivity");
        	intent.setClass(this, FloatingActionMenuActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_avloadingindicatorview:
			mToastUtil.showToast("点击了AVLoadingIndicatorViewActivity");
        	intent.setClass(this, AVLoadingIndicatorViewActivity.class);
            startActivity(intent);			
			break;
		case R.id.btn_multiplestatusview:
			mToastUtil.showToast("点击了MultipleStatusViewActivity");
        	intent.setClass(this, MultipleStatusViewActivity.class);
            startActivity(intent);			
			break;

		default:
			break;
		}
	}



	
}
