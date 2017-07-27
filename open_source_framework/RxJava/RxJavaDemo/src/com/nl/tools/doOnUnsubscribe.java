package com.nl.tools;

import rx.Observable;
import rx.functions.Action0;

import com.nl.util.SimpleAction;

/** 
 * doOnUnsubscribe： 注册一个动作，在观察者取消订阅时使用。内部由OperatorDoOnUnsubscribe实现，在call中加入一个解绑动作。 
 * @author xx
 * @Date 2017-7-24 下午2:35:49 
 */
public class doOnUnsubscribe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3, 4)
				.doOnUnsubscribe(new Action0() {
					@Override
					public void call() {
						System.out.print("--doOnUnsubscribe--");
					}
				})
				.subscribe(new SimpleAction<Integer>())
				.unsubscribe();

	}

}
