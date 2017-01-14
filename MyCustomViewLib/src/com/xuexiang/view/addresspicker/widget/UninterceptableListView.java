package com.xuexiang.view.addresspicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by smartTop on 2016/10/19.
 */
public class UninterceptableListView extends ListView {
    public UninterceptableListView(Context context) {
        super(context);
    }

    public UninterceptableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UninterceptableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }
}
