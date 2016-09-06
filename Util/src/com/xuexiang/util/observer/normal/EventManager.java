package com.xuexiang.util.observer.normal;

import java.util.HashMap;
import java.util.Map;


/**  
 * 创建时间：2016-6-5 上午1:12:48  
 * @author xuexiang
 **/
public class EventManager {

	/**
	 * 存放所有被观察者对象的集合
	 */
	private static Map<String, BaseSubject> mEventManager = new HashMap<String, BaseSubject>();  //存放各种被观察者对象
	
	/**
	 * 获取被观察者对象
	 * @param subjectName 被观察者对象的标记
	 */
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
