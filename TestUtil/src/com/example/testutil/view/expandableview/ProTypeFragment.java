package com.example.testutil.view.expandableview;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.view.expandableview.adapter.GridViewAdapter;
import com.xuexiang.view.expandableview.entity.Type;

public class ProTypeFragment extends Fragment {

	private ArrayList<Type> list;
	private GridView gridView;
	private GridViewAdapter adapter;
	private Type type;
	private String typename;
	private int icon;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		gridView = (GridView) view.findViewById(R.id.listView);
		int index = getArguments().getInt("index");

		typename = Model.toolsList[index];
		icon = Model.iconList[index];

		((TextView) view.findViewById(R.id.toptype)).setText(typename);
		GetTypeList();
		adapter = new GridViewAdapter(getActivity(), list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Toast.makeText(getActivity(), "点击了："+adapter.getItem(position).getTypename(), Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}

	private void GetTypeList() {
		list = new ArrayList<Type>();
		for (int i = 1; i < 23; i++) {
			type = new Type(i, typename + i, icon);
			list.add(type);
		}
	}
}
