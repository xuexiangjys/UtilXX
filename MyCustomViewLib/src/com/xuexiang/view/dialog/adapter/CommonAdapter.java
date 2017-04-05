package com.xuexiang.view.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
	private Context mContext;
	private List<T> mData;
	private int mLayoutId;

	public CommonAdapter(Context context, List<T> data, int layoutId) {
		mContext = context;
		mData = data;
		mLayoutId = layoutId;
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHolder holder = MyViewHolder.getHolder(mContext, convertView, mLayoutId, parent, position);
		convert(holder, getItem(position));
		return holder.getConvertView();
	}

	public abstract void convert(MyViewHolder holder, T bean);

}
