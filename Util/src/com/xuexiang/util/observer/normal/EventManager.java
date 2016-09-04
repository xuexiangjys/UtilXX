package com.xuexiang.util.observer.normal;

import java.util.HashMap;
import java.util.Map;


/**  
 * 创建时间：2016-6-5 上午1:12:48  
 * @author xuexiang
 **/
public class EventManager {

	private static Map<String, BaseSubject> mEventManager = new HashMap<String, BaseSubject>();  //存放各种被观察者对象
	
	public static BaseSubject getSubject(String subjectName) {
		BaseSubject baseSubject;
		if (mEventManager.containsKey(subjectName)) {
			baseSubject = mEventManager.get(subjectName);
		} else {
			baseSubject = new BaseSubject();
			mEventManager.put(subjectName, baseSubject);
		}
		return baseSubject;
	}

}
