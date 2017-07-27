package com.nl.tools;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

import com.nl.util.SimpleAction;

/** 
 * doOnSubscribe： 注册一个动作，在观察者订阅时使用。内部由OperatorDoOnSubscribe实现，
 * @author xx
 * @Date 2017-7-24 下午2:27:45 
 */
public class doOnSubscribe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onCompleted();
			}
		}).doOnSubscribe(new Action0() {
			@Override
			public void call() {
				System.out.print("--doOnSubscribe--");
			}
		}).subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());
	}

}
