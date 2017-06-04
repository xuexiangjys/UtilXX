package com.xuexiang.util.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xuexiang.util.app.ActivityUtil;

/**
 * 基类适配器
 * 
 * @author xx
 * @param <T>
 */

public abstract class BaseContentAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> mDataList;
	protected LayoutInflater mInflater;

	public BaseContentAdapter(Context context, List<T> list) {
		mContext = context.getApplicationContext();
		mInflater = LayoutInflater.from(mContext);
		mDataList = list;
	}

	@Override
	public int getCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public T getItem(int position) {
		return mDataList == null ? null : mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public List<T> getDataList() {
		return mDataList;
	}

	public void setDataList(List<T> dataList) {
		if (dataList != null && dataList.size() > 0) {
			mDataList = dataList;
			notifyDataSetChanged();
		}
	}
	
	public boolean existsData(){
		return mDataList != null && mDataList.size() > 0;
	}
	
	public void clear() {
		if (existsData()) {
			mDataList.clear();
		}
	}

	public void add(int position, T item) {
		if (item != null) {
			mDataList.add(position, item);
		}
	}
	
	public void add(T item) {
		if (item != null) {
			mDataList.add(item);
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getConvertView(position, convertView, parent);
	}

	public abstract View getConvertView(int position, View convertView, ViewGroup parent);

	public void Toast(String msg) {
		ActivityUtil.toastOnUIThread(msg);
	}
	
	/**
	 * 根据分隔符将String转换为List
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public List<String> StringToList(String str, String separator){
		List <String> list = new ArrayList <String> (); 
		list = Arrays.asList(str.split(separator)); 
		return list;
	}
	
	/**
	 * 根据分隔符将List转换为String
	 * @param list
	 * @param separator
	 * @return
	 */
	public String ListToString(List<String> list, String separator) {    
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {        
			sb.append(list.get(i)).append(separator);    
		}    
		return sb.toString().substring(0,sb.toString().length()-1);
	}
}
	
	
