package com.xuexiang.service;

import android.content.Context;
import android.view.View;

import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.system.SystemKeyboard;
import com.xuexiang.view.floatingactionmenu.FloatingActionButton;
import com.xuexiang.view.floatingactionmenu.FloatingActionMenu;

public class ButtonSwitchManager extends BaseFloatViewManager {
	private ToastUtil mToastUtil;
	private static ButtonSwitchManager sInstance;

	private FloatingActionMenu mMenu;

	private FloatingActionButton fab1;
	private FloatingActionButton fab2;
	private FloatingActionButton fab3;
	private FloatingActionButton fab4;
	private FloatingActionButton fab5;

	private ButtonSwitchManager(Context context) {
		super(context);
		mToastUtil = ToastUtil.getInstance(context);
	}

	public static ButtonSwitchManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new ButtonSwitchManager(c.getApplicationContext());
		}
		return sInstance;
	}

	@Override
	public void initFloatView() {
		View rootView = initFloatRootView(RUtils.getLayout(getContext(),"service_buttonswitch_layout"));
		mMenu = (FloatingActionMenu) rootView.findViewById(RUtils.getId(getContext(), "floating_menu"));
		fab1 = (FloatingActionButton) rootView.findViewById(RUtils.getId(getContext(), "fab1"));
		fab2 = (FloatingActionButton) rootView.findViewById(RUtils.getId(getContext(), "fab2"));
		fab3 = (FloatingActionButton) rootView.findViewById(RUtils.getId(getContext(), "fab3"));
		fab4 = (FloatingActionButton) rootView.findViewById(RUtils.getId(getContext(), "fab4"));
		fab5 = (FloatingActionButton) rootView.findViewById(RUtils.getId(getContext(), "fab5"));

		mMenu.setOnTouchListener(this);
		setOnFloatViewMoveListener(new OnFloatViewMoveListener() {
			
			@Override
			public void onMove(Location location) {
				updateViewPosition((int)(location.mXInScreen - location.mXInView), (int)(location.mYInScreen - location.mYInView / 2));
			}
		});
//		mMenu.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
//				if (!mMenu.isOpened()) {
//					int currentX = (int) event.getRawX() - mMenu.getMeasuredWidth() / 2;
//					// 25为状态栏的高度
//					int currentY = (int) event.getRawY() - mMenu.getMeasuredHeight() / 2 - getStatusBarHeight();
//					// 刷新
//					updateViewPosition(currentX, currentY);
//				}
//				return false;
//			}
//		});
		
		fab1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemKeyboard.toHome(getContext());
				mToastUtil.showToast("toHome");
			}
		});
		
        fab2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemKeyboard.toBack();
				mToastUtil.showToast("toBack");
			}
		});
        
        fab3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemKeyboard.toMenu();
				mToastUtil.showToast("toMenu");
			}
		});
        
        fab4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemKeyboard.toRecent();
				mToastUtil.showToast("toRecent");
			}
		});
        fab5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemKeyboard.volumeAdjustment(getContext(), false);
			}
		});
        setIsAdsorb(true);
	}
}
