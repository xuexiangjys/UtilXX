package com.nl.create;

import rx.Observable;
import rx.functions.Func0;

import com.nl.util.SimpleAction;

/** 
 * defer： 只有当订阅者订阅才创建Observable，为每个订阅创建一个新的Observable。内部通过OnSubscribeDefer在订阅时调用Func0创建Observable。
 * @author xx
 * @Date 2017-7-21 下午1:46:46 
 */
public class defer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Observable.defer(new Func0<Observable<String>>() {
		        @Override
		        public Observable<String> call() {
		            return Observable.just("hello");
		        }
		    }).subscribe(new SimpleAction<String>());
	}

}
