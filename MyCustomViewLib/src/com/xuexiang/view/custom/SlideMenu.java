package com.xuexiang.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 继承自FrameLayout:
 * 1.因为FrameLayout帮我们实现了onMeasure方法，不需要我们自己实现
 * 2.因为FrameLayout代码最少，在四大布局中属于轻量级
 */
public class SlideMenu extends FrameLayout{

    /*左边菜单的view*/
    private View mLeftMenuView;
    /*左边菜单的宽度*/
    private int mLeftMenuWidth;
    /*左边菜单的高度*/
    private int mLeftMenuHeight;
    /*主界面的view*/
    private View mMainView;
    /*主界面的宽度*/
    private int mMainWidth;
    private Scroller mScroller;
    private float mDownX;
    private float mDownY;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
    }

    /**当完成从布局文件加载VIew的时候,该方法执行完后就知道自己又几个子view*/
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    /**这个方法是onMeasure执行之后执行，所以在这个方法中可以获取所有子view的宽高*/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLeftMenuWidth = mLeftMenuView.getMeasuredWidth();
        mLeftMenuHeight = mLeftMenuView.getMeasuredHeight();
        mMainWidth = mMainView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mLeftMenuView.layout(-mLeftMenuWidth,0,0,mLeftMenuHeight);
        mMainView.layout(0,0,mMainWidth,mLeftMenuHeight);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();
                float deltaX = moveX - mDownX;/*x方向滑动的距离*/
                float deltaY = moveY - mDownY;/*y方向滑动的距离*/
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    /*如果move的方向偏于水平方向，此时才拦截
                    *如果move的方向偏于垂直方向，此时不应该拦截
                    */
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float deltaX = moveX - mDownX;
                int newScrollX = (int) (getScrollX()-deltaX);
                if(newScrollX<-mLeftMenuWidth){
                    newScrollX = -mLeftMenuWidth;
                }
                if(newScrollX>0){
                    newScrollX = 0;
                }
                scrollTo(newScrollX, 0);
                mDownX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                if(getScrollX()>=-mLeftMenuWidth/2){
                    closeLeftMenu();
                }else {
                    openLeftMenu();
                }
                break;
        }
        return true;
    }

    /**
     * 切换菜单
     */
    public void switchMenu(){
        if(getScrollX()==-mLeftMenuWidth){
            closeLeftMenu();/*此时是开着的，应该关*/
        }else {
            openLeftMenu();/*应该打开*/
        }
    }

    /**关闭左边菜单*/
    private void closeLeftMenu(){
        mScroller.startScroll(getScrollX(), 0, 0-getScrollX(), 0,100);
        invalidate();
    }
    /**打开左边菜单*/
    private void openLeftMenu(){
        mScroller.startScroll(getScrollX(), 0, -mLeftMenuWidth-getScrollX(), 0,100);
        invalidate();
    }

    /**
     * 由于computeScroll方法不会自动调用，所有invalidate来调该方法
     * invalidate->draw->computeScroll
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){/*如果返回true，表示动画没有结束，反之就结束*/
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
