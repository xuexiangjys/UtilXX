package com.example.mycustomview.easyindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycustomview.R;

public class TestFragment extends Fragment {

	public final static String TITLE_KEY = "title_key";

	public static TestFragment newInstance(String title) {
		TestFragment fragment = new TestFragment();
		Bundle args = new Bundle();
		args.putString(TITLE_KEY, title);
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_test_item, container, false);
		Bundle args = getArguments();
		if (args != null) {
			TextView tv = (TextView) v.findViewById(R.id.title);
			tv.setText(args.getString(TITLE_KEY));
		}
		return v;
	}
}
