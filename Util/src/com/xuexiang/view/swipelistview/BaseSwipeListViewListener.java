package com.xuexiang.view.swipelistview;

import android.view.View;

public abstract class BaseSwipeListViewListener implements SwipeListViewListener {
    @Override
    public void onOpened(int position, boolean toRight) {
    }

    @Override
    public void onClosed(int position, boolean fromRight) {
    }

    @Override
    public void onListChanged() {
    }

    @Override
    public void onMove(int position, float x) {
    }

    @Override
    public void onStartOpen(int position, int action, boolean right) {
    }

    @Override
    public void onStartClose(int position, boolean right) {
    }

    @Override
    public abstract void onClickFrontView(View view, int position);

    @Override
    public abstract void onClickBackView(int position);

    @Override
    public abstract void onDismiss(int[] reverseSortedPositions);

    @Override
    public int onChangeSwipeMode(int position) {
        return SwipeListView.SWIPE_MODE_DEFAULT;
    }
}
