package com.xuexiang.view.dialog.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private MyViewHolder(Context context, int layoutId, ViewGroup parent, int position) {
		mViews = new SparseArray<View>();
		mPosition = position;
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static MyViewHolder getHolder(Context context, View convertView, int layoutId, ViewGroup parent, int position) {
		if (convertView == null) {
			return new MyViewHolder(context, layoutId, parent, position);
		} else {
			MyViewHolder holder = (MyViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	public View getConvertView() {
		return mConvertView;
	}

	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public int getPosition() {
		return mPosition;
	}
}
