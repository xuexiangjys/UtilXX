package com.example.testutil.view;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testutil.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.HeaderLayout;
import com.xuexiang.view.HeaderLayout.HeaderStyle;
import com.xuexiang.view.HeaderLayout.onLeftImageButtonClickListener;
import com.xuexiang.view.HeaderLayout.onRightImageButtonClickListener;

/**  
 * 创建时间：2016-6-11 下午10:39:34  
 * 项目名称：UtilTest  
 * @author xuexiang
 * 文件名称：HeaderLayoutActivity.java  
 **/
public class HeaderLayoutActivity extends BaseActivity implements OnClickListener {
	protected SlidingMenu side_drawer;
	private HeaderLayout mHeaderLayout;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_headerlayout);
		initSlidingMenu();
		initViews();
	}
	
	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	protected void initViews() {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.otherfeedlist_header);
		Button defaut = (Button) findViewById(R.id.btnDefaut);
		defaut.setOnClickListener(this);
		Button left = (Button) findViewById(R.id.btnLeft);
		left.setOnClickListener(this);
		Button right = (Button) findViewById(R.id.btnRight);
		right.setOnClickListener(this);
		Button doubles = (Button) findViewById(R.id.btnDouble);
		doubles.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnDefaut:
			Log.v("-------", "默认标题");
			mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
			mHeaderLayout.setTitleAndLeftImageButton("默认标题",
					R.drawable.head_camera_normal, leftButtonClickListener);
			break;

		case R.id.btnLeft:
			Log.v("-------", "左侧按钮");
			mHeaderLayout.init(HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
			mHeaderLayout.setTitleAndLeftImageButton("左侧按钮",
					R.drawable.head_camera_normal, leftButtonClickListener);
			break;
		case R.id.btnRight:
			Log.v("-------", "右侧按钮");
			mHeaderLayout.init(HeaderStyle.TITLE_RIGHT_IMAGEBUTTON);
			mHeaderLayout.setTitleAndRightImageButton("右侧按钮",
					R.drawable.head_locate_normal, rightButtonClickListener);
			break;
		case R.id.btnDouble:
			Log.v("-------", "双侧按钮");
			mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
			mHeaderLayout.setTitleAndLeftImageButton("双侧按钮",
					R.drawable.head_camera_normal, leftButtonClickListener);
			mHeaderLayout.setTitleAndRightImageButton("双侧按钮",
					R.drawable.head_locate_normal, rightButtonClickListener);
			break;

		default:
			break;
		}
	}

	private onLeftImageButtonClickListener leftButtonClickListener = new onLeftImageButtonClickListener() {

		public void onClick() {
			// TODO Auto-generated method stub
			// Toast.makeText(getApplicationContext(), "您点击了左侧按钮！",
			// Toast.LENGTH_SHORT).show();
			if (side_drawer.isMenuShowing()) {
				side_drawer.showContent();
			} else {
				side_drawer.showMenu();
			}
		}
	};
	private onRightImageButtonClickListener rightButtonClickListener = new onRightImageButtonClickListener() {

		public void onClick() {
			// TODO Auto-generated method stub
			// Toast.makeText(getApplicationContext(), "您点击了右侧按钮！",
			// Toast.LENGTH_SHORT).show();
			if (side_drawer.isSecondaryMenuShowing()) {
				side_drawer.showContent();
			} else {
				side_drawer.showSecondaryMenu();
			}
		}
	};

}
