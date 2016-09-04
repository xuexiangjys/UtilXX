package com.xuexiang.util.observer.tag;

import java.util.List;


/**  
 * 创建时间：2016-6-5 下午2:17:04  
 * @author xuexiang
 **/
public interface ITagSubject {

	/**
	 * 增加观察者
	 * @param observer 实现ITagObserver接口的对象
	 * @param eventTagList 注册事件标志的集合
	 */
	public void register(ITagObserver observer, List<String> eventTagList);
	
	/**
	 * 删除观察者
	 * @param observer 实现ITagObserver接口的对象
	 */
	public void unregister(ITagObserver observer);
	
	/**
	 * 通知符合要求的观察者
	 * @param event 同时的具体事件
	 */
	public void notify(Event event);
}
