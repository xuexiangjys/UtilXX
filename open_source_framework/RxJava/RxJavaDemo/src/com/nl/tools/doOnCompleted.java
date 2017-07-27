package com.nl.tools;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/** 
 * doOnCompleted： 注册一个动作，对正常完成的Observable使用
 * @author xx
 * @Date 2017-7-24 下午2:14:44 
 */
public class doOnCompleted {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onNext(3);
				subscriber.onCompleted();
			}
		}).doOnCompleted(new Action0() {
			@Override
			public void call() {
				System.out.print("--doOnCompleted--");
			}
		}).subscribe(new SimpleAction<Integer>());

	}

}
