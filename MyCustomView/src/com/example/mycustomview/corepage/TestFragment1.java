package com.example.mycustomview.corepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycustomview.R;
import com.xuexiang.view.corepage.base.BaseFragment;

/**
 * User:lizhangqu(513163535@qq.com) Date:2015-07-20 Time: 16:29
 */
public class TestFragment1 extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_test1, container, false);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
		// refWatcher.watch(this);
	}

}
