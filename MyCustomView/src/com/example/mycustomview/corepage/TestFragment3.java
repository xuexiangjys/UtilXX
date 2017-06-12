package com.example.mycustomview.corepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.view.corepage.base.BaseFragment;

/**
 * User:lizhangqu(513163535@qq.com) Date:2015-07-20 Time: 16:29
 */
public class TestFragment3 extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_test3, container, false);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			Toast.makeText(getActivity(), "get params:" + arguments.getString("key1") + " " + arguments.getString("key2") + " " + arguments.getString("test"), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
		// refWatcher.watch(this);
	}

}
