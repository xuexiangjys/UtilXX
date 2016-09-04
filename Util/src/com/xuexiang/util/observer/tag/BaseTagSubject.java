package com.xuexiang.util.observer.tag;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**  
 * 创建时间：2016-6-5 下午2:15:25   
 * @author xuexiang 
 **/
public class BaseTagSubject implements ITagSubject {
	//存放数据观察者集合
    private Map<WeakReference<ITagObserver>, List<String>> mObservers = new HashMap<WeakReference<ITagObserver>, List<String>>();

	@Override
	public void register(ITagObserver observer, List<String> event ) {
		WeakReference<ITagObserver> obs = new WeakReference<ITagObserver>(observer);
		mObservers.put(obs, event);
	}

	@Override
	public void unregister(ITagObserver observer) {
		WeakReference<ITagObserver> obs = new WeakReference<ITagObserver>(observer);
		mObservers.remove(obs);
	}

	@Override
	public void notify(Event event) {
		  Iterator<Map.Entry<WeakReference<ITagObserver>, List<String>>> it = mObservers.entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry<WeakReference<ITagObserver>, List<String>> entry = it.next();
		   WeakReference<ITagObserver> weak = entry.getKey();
		   List<String> eventTagList = entry.getValue();
		   ITagObserver obs = weak.get();
		   if (obs != null) {
			  for (String tag : eventTagList) {
				if (event.getTag().equals(tag)) {
					obs.onChanged(event);
				}
			  }				
			} else {
				mObservers.remove(obs);
			}
		}
	}

}
