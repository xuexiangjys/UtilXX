package com.xuexiang.view.pickers.common;


import com.xuexiang.view.pickers.listeners.OnItemPickListener;
import com.xuexiang.view.pickers.widget.WheelView;

final public class OnItemPickedRunnable implements Runnable {
    final private  WheelView wheelView;
    private OnItemPickListener onItemPickListener;
    public OnItemPickedRunnable(WheelView wheelView, OnItemPickListener onItemPickListener) {
        this.wheelView = wheelView;
        this.onItemPickListener = onItemPickListener;
    }

    @Override
    public final void run() {
        onItemPickListener.onItemPicked(wheelView.getCurrentPosition(),wheelView.getCurrentItem());
    }
}
