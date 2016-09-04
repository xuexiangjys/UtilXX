package com.xuexiang.util.observer.normal;

/**  
 * 观察者接口
 * 创建时间：2016-6-5 上午12:55:56  
 * @author xuexiang
 **/
public interface IObserver {

	 public void onChanged();
	 
	 public void onInvalidated();
}
