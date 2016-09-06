package com.xuexiang.util.observer.normal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**  
 * 被观察者
 * 创建时间：2016-6-5 上午1:03:27   
 * @author xuexiang
 **/
public class BaseSubject implements ISubject {
	
    /**
     * 存放数据观察者集合
     */
    private ArrayList<WeakReference<IObserver>> mObservers = new ArrayList<WeakReference<IObserver>>();

	@Override
	public void register(IObserver observer) {
		WeakReference<IObserver> obs = new WeakReference<IObserver>(observer);
		mObservers.add(obs);
	}

	@Override
	public void unregister(IObserver observer) {
		WeakReference<IObserver> obs = new WeakReference<IObserver>(observer);
		mObservers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		ArrayList<WeakReference<IObserver>> observers = mObservers;
	    int count = observers.size();
		for (int i = count - 1; i >= 0; i--) {
			WeakReference<IObserver> weak = observers.get(i);
			IObserver obs = weak.get();
			if (obs != null) {
				obs.onChanged();
			} else {
				observers.remove(i);
			}
		}

	}


}
