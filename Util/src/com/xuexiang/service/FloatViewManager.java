package com.xuexiang.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xuexiang.util.app.BroadcastHelper;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.resource.RUtils;

/**
 * 悬浮框管理类
 * @author xx
 *
 */
public class FloatViewManager {
	private Context mContext;
	private ToastUtil mToastUtil;
	private static FloatViewManager instance;     
	//定义浮动窗口布局
    private LinearLayout mFloatLayout;
    private WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    private WindowManager mWindowManager;
	
    private Button mFloatView;

	private FloatViewManager(Context context) {
		mContext = context;
		mToastUtil = ToastUtil.getInstance(mContext);
	}
	
    public static FloatViewManager getInstance(Context c) {  
        if (instance == null) {  
            instance = new FloatViewManager(c.getApplicationContext());  
        }  
        return instance;  
    }
    
    /**
   	 * 初始化打印悬浮框
   	 */
   	public void initFloatView() {
		wmParams = new WindowManager.LayoutParams();
		//获取WindowManagerImpl.CompatModeWrapper
		mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		//设置window type
		wmParams.type = LayoutParams.TYPE_PHONE; 
		//设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888; 
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = 
//          LayoutParams.FLAG_NOT_TOUCH_MODAL |
          LayoutParams.FLAG_NOT_FOCUSABLE
//          LayoutParams.FLAG_NOT_TOUCHABLE
          ;
        
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;       
        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 0;
        wmParams.y = 0;
        
        //设置悬浮窗口长宽数据  
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(RUtils.getLayout(mContext, "service_floatview_layout"), null);       
        //浮动窗口按钮
        mFloatView = (Button)mFloatLayout.findViewById(RUtils.getId(mContext, "float_id"));
        
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //设置监听浮动窗口的触摸移动
        mFloatView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
				wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth()/2;
				//25为状态栏的高度
	            wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight()/2 - 25;
	             //刷新
	            mWindowManager.updateViewLayout(mFloatLayout, wmParams);
				return false;
			}
		});	
        
        mFloatView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mToastUtil.showToast("重启程序");
				BroadcastHelper.sendStartAppBroadCast(mContext);
			}
		});
   	}
   	
   	/**
   	 * 隐藏悬浮框
   	 */
   	public void dismissFloatView() {
   		if(mFloatLayout != null) {
   			mWindowManager.removeView(mFloatLayout);
   		}
   	}
   	
   	/**
   	 * 显示悬浮框
   	 */
   	public void showFloatView() {
   	    //添加mFloatLayout
		if(mFloatLayout != null && wmParams != null) {
			 mWindowManager.addView(mFloatLayout, wmParams); 
		}
   	}

}
