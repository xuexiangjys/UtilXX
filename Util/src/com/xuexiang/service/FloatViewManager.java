package com.xuexiang.service;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.xuexiang.util.app.BroadcastHelper;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.resource.RUtils;

/**
 * 悬浮框管理类
 * @author xx
 *
 */
public class FloatViewManager extends BaseFloatViewManager{
	private ToastUtil mToastUtil;
	private static FloatViewManager sInstance;     
	//定义浮动窗口布局
    private Button mFloatButton;

	private FloatViewManager(Context context) {
		super(context);
		mToastUtil = ToastUtil.getInstance(context);
	}
	
    public static FloatViewManager getInstance(Context c) {  
        if (sInstance == null) {  
            sInstance = new FloatViewManager(c.getApplicationContext());  
        }  
        return sInstance;  
    }

    /**
   	 * 初始化悬浮框
   	 */
	@Override
   	public void initFloatView() {
        View rootView = initFloatRootView(RUtils.getLayout(getContext(), "service_floatview_layout"));
        //浮动窗口按钮
        mFloatButton = (Button) rootView.findViewById(RUtils.getId(getContext(), "float_id"));
        //设置监听浮动窗口的触摸移动
        mFloatButton.setOnTouchListener(this);
//        mFloatButton.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				//getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
//				int currentX = (int) event.getRawX() - mFloatButton.getMeasuredWidth()/2;
//				//25为状态栏的高度
//				int currentY = (int) event.getRawY() - mFloatButton.getMeasuredHeight()/2 - getStatusBarHeight();
//	             //刷新
//				updateViewPosition(currentX, currentY);
//				return false;
//			}
//		});	
        
        setOnFloatViewClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mToastUtil.showToast("重启程序");
				BroadcastHelper.sendStartAppBroadCast(getContext());
			}
		});
        setIsAdsorb(true);
   	}

}
