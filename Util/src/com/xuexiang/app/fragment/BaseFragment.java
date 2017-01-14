package com.xuexiang.app.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** 
 * @author xx
 * @Date 2017-1-10 上午9:13:08 
 */
public abstract class BaseFragment extends Fragment {
	/**
	 * 标识fragment视图已经初始化完毕
	 */
	private boolean mIsViewPrepared;
    /**
     * 标识已经触发过懒加载数据
     */
    private boolean mHasFetchData; 

    protected View mRootView;
    /**
     * 布局的资源id
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 懒加载
     */
    protected abstract void lazyFetchData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return mRootView;
    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !mHasFetchData && mIsViewPrepared) {
            mHasFetchData = true;
            lazyFetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHasFetchData = false;
        mIsViewPrepared = false;
    }

    protected <T extends View> T findView(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }
}
