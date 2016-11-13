package com.xuexiang.util.data;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.database.DataSetObserver;


/** 
 * @author  XX 
 * @date 创建时间：2016-5-25 上午11:11:10 
 */

public class DataObserver {

	//存放数据观察者集合
    private ArrayList<WeakReference<DataSetObserver>> mObservers = new ArrayList<WeakReference<DataSetObserver>>();
    
    /**
	 * Called when something changes in our data set. Cleans up any weak
	 * references that are no longer valid along the way.
	 */
	public void notifyObservers() {
		final ArrayList<WeakReference<DataSetObserver>> observers = mObservers;
		final int count = observers.size();
		for (int i = count - 1; i >= 0; i--) {
			WeakReference<DataSetObserver> weak = observers.get(i);
			DataSetObserver obs = weak.get();
			if (obs != null) {
				obs.onChanged();
			} else {
				observers.remove(i);
			}
		}

	}
	
	/**
	 * Adds an observer to be notified when the set of items held by this
	 * ImageManager changes.
	 */
	public void addObserver(DataSetObserver observer) {
		WeakReference<DataSetObserver> obs = new WeakReference<DataSetObserver>(observer);
		mObservers.add(obs);
	}
}
