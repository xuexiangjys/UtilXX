package com.xuexiang.service;

import com.xuexiang.util.app.AppUtils;
import com.xuexiang.util.view.BitmapUtil;
import com.xuexiang.util.view.DisplayUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

public abstract class BaseFloatViewManager implements OnTouchListener {
	private Context mContext;
	private WindowManager.LayoutParams wmParams;
	/** 创建浮动窗口设置布局参数的对象*/
	private WindowManager mWindowManager;
	/** 浮动窗口的父布局*/
	private View mFloatRootLayout;
	/** 系统状态栏的高度*/
 	private int mStatusBarHeight;
 	
 	private Location mLocation;
 	private OnClickListener mOnClickListener;
 	private OnFloatViewMoveListener mOnFloatViewMoveListener;
 	/** 悬浮窗口是否吸附*/
 	private boolean mIsAdsorb = false;
 	/** 吸附旋转的控件*/
	private ImageView mRotateView;
	private Bitmap mBitmap;
	/** 悬浮窗口是否显示*/
 	private boolean mIsShow = false;
	/**
	 * 初始化WindowManager
	 */
	public BaseFloatViewManager(Context context) {
		mContext = context;
		wmParams = new WindowManager.LayoutParams();
		// 获取WindowManagerImpl.CompatModeWrapper
		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		// 设置window type
		wmParams.type = LayoutParams.TYPE_PHONE;
		// 设置图片格式，效果为背景透明
		wmParams.format = PixelFormat.RGBA_8888;
		// 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
		wmParams.flags =
		// LayoutParams.FLAG_NOT_TOUCH_MODAL |
		LayoutParams.FLAG_NOT_FOCUSABLE
		// LayoutParams.FLAG_NOT_TOUCHABLE
		;
		// 调整悬浮窗显示的停靠位置为左侧置顶
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		mStatusBarHeight = AppUtils.getStatusBarHeight();
		setWindowManagerParams(0, 0, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
		mLocation = new Location();
		
		initFloatView();
	}
	
	/**
	 * 设置悬浮框的初始位置、尺寸参数
	 * @param Posx
	 * @param Posy
	 * @param width
	 * @param height
	 */
	public void setWindowManagerParams(int Posx, int Posy, int width, int height) {
		wmParams.x = Posx;
		wmParams.y = Posy;
		// 设置悬浮窗口长宽数据
		wmParams.width = width;
		wmParams.height = height;
	}
	
	/**
	 * 设置悬浮框的初始位置
	 */
	public void initFloatViewPosition(int Posx, int Posy) {
		wmParams.x = Posx;
		wmParams.y = Posy;
	}
	
	/**
	 * 初始化父布局
	 * @param layoutId  布局的资源ID（最好是LinearLayout)
	 * @return
	 */
	public View initFloatRootView(int layoutId) {
        //获取浮动窗口视图所在布局
        mFloatRootLayout = LayoutInflater.from(mContext).inflate(layoutId, null);  
        mFloatRootLayout.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return mFloatRootLayout;
	}
	
	public void setRotateView(ImageView rotateView, int resId) {
		mRotateView = rotateView;
		mBitmap = BitmapUtil.getBitmapFromDrawable(mContext.getResources().getDrawable(resId));
		mRotateView.setImageBitmap(mBitmap);
	}
	
	/**
	 * 更新悬浮框的位置参数
	 * @param Posx
	 * @param Posy
	 */
	public void updateViewPosition(int Posx, int Posy) {
		wmParams.x = Posx;
		wmParams.y = Posy;
		mWindowManager.updateViewLayout(mFloatRootLayout, wmParams);
	}
	
	/**
   	 * 隐藏悬浮框
   	 */
   	public void dismissFloatView() {
   		if (mFloatRootLayout != null && mIsShow) {
   			mWindowManager.removeView(mFloatRootLayout);
   			mIsShow = false;
   		}
   	}
   	
   	/**
   	 * 显示悬浮框
   	 */
   	public void showFloatView() {
		if (mFloatRootLayout != null && wmParams != null && !mIsShow) {
			mWindowManager.addView(mFloatRootLayout, wmParams);
			mIsShow = true;
		} 
   	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		// 手指按下时记录必要的数据,纵坐标的值都减去状态栏的高度
		case MotionEvent.ACTION_DOWN:
			// 获取相对与小悬浮窗的坐标
			mLocation.mXInView = event.getX();
			mLocation.mYInView = event.getY();
			// 按下时的坐标位置，只记录一次
			mLocation.mXDownInScreen = event.getRawX();
			mLocation.mYDownInScreen = event.getRawY() - mStatusBarHeight;
			break;
		case MotionEvent.ACTION_MOVE:
			// 时时的更新当前手指在屏幕上的位置
			mLocation.mXInScreen = event.getRawX();
			mLocation.mYInScreen = event.getRawY() - mStatusBarHeight;
			// 手指移动的时候更新小悬浮窗的位置
			if (mOnFloatViewMoveListener != null) {
				mOnFloatViewMoveListener.onMove(mLocation);
			} else {
				updateViewPosition((int)(mLocation.mXInScreen - mLocation.mXInView), (int)(mLocation.mYInScreen - mLocation.mYInView));
			}
			break;
		case MotionEvent.ACTION_UP:
			// 如果手指离开屏幕时，按下坐标与当前坐标相等，则视为触发了单击事件
			if (mLocation.getXDownInScreen() == event.getRawX() && mLocation.getYDownInScreen() == (event.getRawY() - mStatusBarHeight)) {
				if (mOnClickListener != null) {
					mOnClickListener.onClick(v);
				}
			} else {
				if (mIsAdsorb) {
					updateGravity(event);
				}
			}
			break;
		}
		return true;
	}
   	
   	/**
   	 * 初始化悬浮控件
   	 */
   	public abstract void initFloatView();
   	
   	public void setOnFloatViewClickListener(OnClickListener mOnClickListener) {
		this.mOnClickListener = mOnClickListener;
	}
   	
   	public void setOnFloatViewMoveListener(OnFloatViewMoveListener mOnFloatViewMoveListener) {
		this.mOnFloatViewMoveListener = mOnFloatViewMoveListener;
	}
	
   	public Context getContext() {
		return mContext;
	}

   	public WindowManager.LayoutParams getWmParams() {
		return wmParams;
	}

	public void setWmParams(WindowManager.LayoutParams wmParams) {
		this.wmParams = wmParams;
	}
	
	public WindowManager getWindowManager() {
		return mWindowManager;
	}

	public void setWindowManager(WindowManager windowManager) {
		mWindowManager = windowManager;
	}
	
   	public View getFloatRootLayout() {
		return mFloatRootLayout;
	}

	public void setFloatRootLayout(View floatRootLayout) {
		mFloatRootLayout = floatRootLayout;
	}
	
	public int getStatusBarHeight() {
		return mStatusBarHeight;
	}
	
	public boolean isAdsorb() {
		return mIsAdsorb;
	}

	public void setIsAdsorb(boolean mIsAdsorb) {
		this.mIsAdsorb = mIsAdsorb;
	}
	
	/**
	 * 悬浮框移动监听
	 * @author xx
	 *
	 */
	public interface OnFloatViewMoveListener {
		void onMove(Location location);
	}
	
	/**
	 * 控件位置类型
	 * @author xx
	 *
	 */
	public enum PositionType {
		LEFT, RIGHT, TOP, BOTTOM
	}
	
	/**
	 * 获取控件的位置类型
	 * @param event
	 * @return
	 */
	private PositionType getPositionType(MotionEvent event) {
		PositionType type = PositionType.LEFT;
		int height = DisplayUtils.getScreenH(mContext) / 5;
		int width = DisplayUtils.getScreenW(mContext) / 2;
		if (event.getRawY() < height) {
			type = PositionType.TOP;
		} else if (event.getRawY() > (height * 4)) {
			type = PositionType.BOTTOM;
		} else {
			if (event.getRawX() > width) {
				type = PositionType.RIGHT;
			} else {
				type = PositionType.LEFT;
			}
		}
		return type;
	}
	
	private void updateGravity(MotionEvent event) {
		PositionType type = getPositionType(event);
		switch (type) {
		case TOP:
			updateRotateView(-90);
			updateViewPosition((int)(event.getRawX() - event.getX()) , 0);
			break;
		case BOTTOM:
			updateRotateView(90);
			updateViewPosition((int)(event.getRawX() - event.getX()) , DisplayUtils.getScreenH(mContext));		
			break;
		case RIGHT:
			mRotateView.setImageBitmap(mBitmap);
			updateViewPosition(DisplayUtils.getScreenW(mContext), (int)(event.getRawY() - event.getY()) - mStatusBarHeight);		
			break;
		case LEFT:
			updateRotateView(180);
			updateViewPosition(0, (int)(event.getRawY() - event.getY()) - mStatusBarHeight);	
			break;

		default:
			break;
		}
	}

	/**
	 * 旋转悬浮框图标
	 * @param degress
	 */
	private void updateRotateView(int degress) {
		if (mRotateView != null) {
			if (degress != 0) {
				mRotateView.setImageBitmap(BitmapUtil.rotate(mBitmap, degress));
			} else {
				mRotateView.setImageBitmap(mBitmap);
			}
			
		}
	}
	
	
}
