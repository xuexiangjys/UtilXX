package com.xuexiang.app;
import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.app.activityswitcher.ActivitySwitcher;
import com.xuexiang.util.app.ActivityManager;
import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.app.ActivityUtil.StartAnim;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;
import com.xuexiang.util.data.sharedPreferences.UserSharePreferenceUtil;
import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.system.EditTextShakeHelper;
import com.xuexiang.view.TitleBar;
import com.xuexiang.view.popwindow.ActionItem;
import com.xuexiang.view.popwindow.TitlePopup;
import com.xuexiang.view.popwindow.TitlePopup.OnItemOnClickListener;


/** activity的基类
  * @ClassName: BaseActivity
  * @Description: TODO
  * @author xx
  */
public abstract class BaseActivity extends FragmentActivity {
	public final String TAG = getClass().getSimpleName();
	
	public Context mContext;
	public BaseApplication myApplication;
	public UserSharePreferenceUtil mUserManager;	
	public SettingSharePreferenceUtil mSettingManager;
	public ActivityManager mActivityManager;
	public Handler mHandler;// 检测加载数据的结果
	
	public TextView mTitle;
	public ImageView mLeftMenu,mRightMenu;
	public TitleBar mTitleBar;
	public ImageView mTitleBarRightMenu;
    private TitlePopup mTitlePopup;
	
	public ProgressDialog mProgressDialog = null;  
	public ToastUtil mToastUtil;
	private Dialog mydialog;
	
	 private ActivitySwitcher mActivitySwitcher;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		
		initManager();

		onCreateActivity();
	}
	
	private void initManager() {
		mContext = this;
		myApplication = BaseApplication.getInstance();
		mUserManager = UserSharePreferenceUtil.getInstance(this);
		mSettingManager = SettingSharePreferenceUtil.getInstance(this);
		mActivityManager = ActivityManager.getInstance();
		mActivitySwitcher = ActivitySwitcher.getInstance();
		mActivityManager.addActivity(this);
		mToastUtil = ToastUtil.getInstance(this);
	}
	
	public abstract void onCreateActivity();

	/**
	 * 初始化ActionBar
	 */
	public void initActionBar(String title){
		mTitle = (TextView)findViewById(MResource.getIdByName(mContext, "id", "tv_title"));
		mTitle.setText(title);
		mLeftMenu = (ImageView)findViewById(MResource.getIdByName(mContext, "id", "left_btn"));
		mLeftMenu.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
                 finish();
			}});
		mRightMenu = (ImageView)findViewById(MResource.getIdByName(mContext, "id", "right_menu"));
		mRightMenu.setVisibility(View.GONE);
	}
	
	
	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public void initTitleBar(String title) {
		initTitleBar(title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });          
	}
	
	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public void initTitleBar(String title, OnClickListener listener){
		mTitleBar = (TitleBar) findViewById(RUtils.getId(mContext, "title_bar"));
		mTitleBar.setImmersive(false);

		mTitleBar.setBackgroundColor(Color.parseColor("#64b4ff"));

		mTitleBar.setLeftImageResource(RUtils.getDrawable(mContext, "back_white"));
        mTitleBar.setLeftText("返回");
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.setLeftClickListener(listener);        
        mTitleBar.setTitle(title);
        mTitleBar.setTitleColor(Color.WHITE);
        mTitleBar.setSubTitleColor(Color.WHITE);
        mTitleBar.setDividerColor(Color.GRAY);
        mTitleBar.setActionTextColor(Color.WHITE);
        
	}
	
	/**
	 * 利用TitleBar初始化ActionBar
	 */
	public void initTitleBarWithRightMenu(String title, ArrayList<ActionItem> actionItemlist, OnItemOnClickListener rightMenuAction ){
		initTitleBar(title);
		
		mTitlePopup = new TitlePopup(mContext, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mTitlePopup.setAction(actionItemlist);
		mTitlePopup.setItemOnClickListener(rightMenuAction);
		mTitleBarRightMenu = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(RUtils.getDrawable(mContext, "more_message")){
			@Override
			public void performAction(View view) {
				mTitlePopup.show(view);
		}});   
	}
	
	
	/**
	 * 检验EditText内容是否为空
	 * @param et  EditText控件
	 * @param msg 为空时的提示文字
	 * @return
	 */
	public boolean IsEditTextEmpty(EditText et, String msg){
		boolean result = false;
		if(TextUtils.isEmpty(et.getText().toString())){
			if(mSettingManager.isAllowVibrate()){
			  new EditTextShakeHelper(mContext).shake(et);
			}
			Toast(msg);
			result = true;
		}
		return result;
	}
	
	public void showLoadingDialog(String title) {
		mProgressDialog = ProgressDialog.show(this, null, title);  
	}

	public void closeLoadingDialog() {
		if (mydialog != null && mydialog.isShowing()) {
			mydialog.dismiss();
		}
	}

	/**
	 * 打log日志
	 * @param msg
	 */
	public void ShowLog(String msg){
		Log.e(TAG, msg);
	}
	
	public void Toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}	
	
	protected void onDestroy(){
		mActivityManager.finishActivity(this);
		super.onDestroy();
	}
	
	/***
	 * 动态设置listview的高度 item 总布局必须是linearLayout
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	
	protected void exitBy2Click(){
		mActivityManager.exitBy2Click(mContext);
	}
	
	public float dimen(@DimenRes int resId) {
        return getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }
    
    public Drawable drawable(@DrawableRes int resId) {
        return getResources().getDrawable(resId);
    }
    
    public void startActivity(Class<?> cls) {
    	ActivityUtil.startActivity(mContext, cls);
	}
    
    public void startActivity(Class<?> cls, Bundle bundle) {
    	ActivityUtil.startActivity(mContext, cls, bundle);
	}
    
    public void startActivity(Class<?> cls,  Bundle bundle, StartAnim startAnim) {
    	ActivityUtil.startActivity(mContext, cls, bundle, startAnim);
    }
    
    public void startActivity(Class<?> cls, StartAnim startAnim) {
    	ActivityUtil.startActivity(mContext, cls, startAnim);
    }

    @Override
    public void onBackPressed() {
        mActivitySwitcher.finishSwitch(this);
    }
    
    /**-------------------------------------点击非输入区域键盘消失--------------------------------------------**/       
    @Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
    	mActivitySwitcher.processTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();  
            if (isShouldHideInput(v, ev)) {  
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
                if (imm != null) {  
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  
                }  
            }  
            return super.dispatchTouchEvent(ev);  
        }  
        // 必不可少，否则所有的组件都不会有TouchEvent了  
        if (getWindow().superDispatchTouchEvent(ev)) {  
            return true;  
        }  
        return onTouchEvent(ev);  
    }  
    
    public  boolean isShouldHideInput(View v, MotionEvent event) {  
        if (v != null && (v instanceof EditText)) {  
            int[] leftTop = { 0, 0 };  
            //获取输入框当前的location位置  
            v.getLocationInWindow(leftTop);  
            int left = leftTop[0];  
            int top = leftTop[1];  
            int bottom = top + v.getHeight();  
            int right = left + v.getWidth();  
            if (event.getX() > left && event.getX() < right  
                    && event.getY() > top && event.getY() < bottom) {  
                // 点击的是输入框区域，保留点击EditText的事件  
                return false;  
            } else {  
                return true;  
            }  
        }  
        return false;  
    }
}
