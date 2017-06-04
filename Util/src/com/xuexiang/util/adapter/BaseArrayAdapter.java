package com.xuexiang.util.adapter;

import com.xuexiang.util.app.ActivityUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseArrayAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected T[] mDataArray;
	protected LayoutInflater mInflater;

	public BaseArrayAdapter(Context context, T[] array) {
		mContext = context.getApplicationContext();
		mInflater = LayoutInflater.from(mContext);
		mDataArray = array;
	}

	@Override
	public int getCount() {
		return mDataArray == null ? 0 : mDataArray.length;
	}

	@Override
	public T getItem(int position) {
		return getCount() < position ? mDataArray[position] : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getConvertView(position, convertView, parent);
	}

	public abstract View getConvertView(int position, View convertView, ViewGroup parent);

	public T[] getDataArray() {
		return mDataArray;
	}

	public void setDataArray(T[] array) {
		if (array != null) {
			mDataArray = array;
			notifyDataSetChanged();
		}
	}

	public boolean existsData() {
		return mDataArray != null && mDataArray.length > 0;
	}

	public void clear() {
		if (existsData()) {
			mDataArray = null;
		}
	}

	public void Toast(String msg) {
		ActivityUtil.toastOnUIThread(msg);
	}

}
