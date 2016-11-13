package com.xuexiang.util.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** 
 * @author  XX 
 * @date 创建时间：2016-5-25 上午11:15:02 
 */

public class DataAdapter<T> extends DataObserver {
	//存放数据的集合
	private List<T> mItems = new ArrayList<T>();
	
	public void setItems(List<T> items) {
		if (items != null) {
			mItems = items;
		} else {
			mItems = new ArrayList<T>();
		}
		notifyObservers();
		
	}
	/**
	 * 获取list集合的对象
	 */
	public List<T> getItems() {
		return mItems;
	}
	
	public void add(int position, T item) {
		mItems.add(position, item);
	}
	
	public void add(T item) {
		mItems.add(item);
	}
	
	public void remove(int position) {
		mItems.remove(position);
	}
	
	public void remove(T item) {
		mItems.remove(item);
	}
	
	/**
	 * 遍历的方式删除list中的子项
	 */
	public void removeFromList(T item) {
		if (item != null) {
			Iterator<T> it = mItems.iterator();
			while (it.hasNext()) {
			   T e = it.next();
			   if (item.equals(e)) {
				   it.remove();
			   }
		   }
		}
	}
	
	/**
	 * 获取list集合的迭代器
	 */
	public Iterator<T> getIterator() {
		return mItems.iterator();
	}
	
	/**
	 * 清空list操作
	 */
	public void clear() {
		if (hasData()) {
			mItems.clear();
		}
	}
	
	public T get(int i) {
		return mItems.get(i);
	}

	/**
	 * 获取list集合子项的大小
	 */
	public int size() {
		return mItems.size();
	}

	/**
	 * list集合里是否有数据
	 */
	public boolean hasData() {
		return mItems != null && size() > 0;
	}

	
}
