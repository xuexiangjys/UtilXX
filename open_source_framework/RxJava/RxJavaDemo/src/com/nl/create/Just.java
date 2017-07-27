package com.nl.create;

import rx.Observable;

import com.nl.util.SimpleAction;

/** 
 * just： 将一个或多个对象转换成发射这个或这些对象的一个Observable。如果是单个对象，内部创建的是ScalarSynchronousObservable对象。如果是多个对象，则是调用了from方法创建。
 * @author xx
 * @Date 2017-7-21 上午10:41:40 
 */
public class Just {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just("abc").subscribe(new SimpleAction<String>());
		
		Observable.just("abc", "def", "ghi").subscribe(new SimpleAction<String>());
	}

}
