package com.xuexiang.util.observer.normal;


/**  
 * 被观察者接口
 * 创建时间：2016-6-5 上午1:00:25  
 * @author xuexiang
 **/
public interface ISubject {
	/**
	 * 增加观察者
	 * @param observer 实现IObserver接口的对象
	 */
	public void register(IObserver observer);
	
	/**
	 * 删除观察者
	 * @param observer 实现IObserver接口的对象
	 */
	public void unregister(IObserver observer);

	/**
	 * 通知所有的观察者
	 */
	public void notifyObservers();
	
}
