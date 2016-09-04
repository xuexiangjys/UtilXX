package com.xuexiang.util.observer.tag;

import java.util.HashMap;
import java.util.Map;


/**  
 * 创建时间：2016-6-5 下午4:16:29  
 * @author xuexiang
 **/
public class TagEventManager {
	private static Map<String, BaseTagSubject> mEventManager = new HashMap<String, BaseTagSubject>();  //存放各种被观察者对象
	
	public static BaseTagSubject getTagSubject(String subjectName) {
		BaseTagSubject baseSubject;
		if (mEventManager.containsKey(subjectName)) {
			baseSubject = mEventManager.get(subjectName);
		} else {
			baseSubject = new BaseTagSubject();
			mEventManager.put(subjectName, baseSubject);
		}
		return baseSubject;
	}
}
