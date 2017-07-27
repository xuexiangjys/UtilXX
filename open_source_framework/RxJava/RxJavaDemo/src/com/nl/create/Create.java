package com.nl.create;

import rx.Observable;
import rx.Subscriber;

/** 
 * create： 使用OnSubscribe从头创建一个Observable，这种方法比较简单。需要注意的是，使用该方法创建时，建议在OnSubscribe#call方法中检查订阅状态，以便及时停止发射数据或者运算。
 * @author xx
 * @Date 2017-7-21 上午10:15:52 
 */
public class Create {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscribe<String>() { //被订阅时触发
	        @Override
	        public void call(Subscriber<? super String> subscriber) {
	            subscriber.onNext("item1");
	            subscriber.onNext("item2");
	            subscriber.onCompleted();
	            System.out.print("onCompleted()");
	        }

	    }).subscribe();

	}

}
