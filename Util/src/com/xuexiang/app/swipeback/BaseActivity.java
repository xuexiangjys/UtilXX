package com.xuexiang.app.swipeback;

import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;


/**
 * Created by fhf11991 on 2016/7/25.
 */
public class BaseActivity extends FragmentActivity {

    private SwipeWindowHelper mSwipeWindowHelper;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!supportSlideBack()) {
            return super.dispatchTouchEvent(ev);
        }

        if(mSwipeWindowHelper == null) {
            mSwipeWindowHelper = new SwipeWindowHelper(getWindow());
        }
        return mSwipeWindowHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 是否支持滑动返回
     *
     * @return
     */
    protected boolean supportSlideBack() {
        return true;
    }
}
