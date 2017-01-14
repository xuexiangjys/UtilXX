package com.xuexiang.app.fragment;

import android.support.v4.app.Fragment;

/**
 * @author xx
 * @Date 2017-1-10 上午10:22:08
 */
public abstract class LazyFragment extends Fragment {
	protected boolean mIsVisible;

	/**
	 * 在这里实现Fragment数据的缓加载.
	 * 
	 * @param isVisibleToUser
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			mIsVisible = true;
			onVisible();
		} else {
			mIsVisible = false;
			onInvisible();
		}
	}

	protected void onVisible() {
		lazyLoad();
	}

	/**
	 * 懒加载
	 */
	protected abstract void lazyLoad();

	protected void onInvisible() {}
}
