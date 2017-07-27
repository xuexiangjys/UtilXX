package com.nl.tools;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/** 
 * doOnTerminate：注册一个动作，对完成的Observable使用，无论是否发生错误
 * @author xx
 * @Date 2017-7-24 下午2:24:23 
 */
public class doOnTerminate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onError(new Throwable("出错了！！"));
			}
		}).doOnTerminate(new Action0() {
			@Override
			public void call() {
				System.out.print("--doOnTerminate--");
			}
		}).subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());

		
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onCompleted();
			}
		}).doOnTerminate(new Action0() {
			@Override
			public void call() {
				System.out.print("--doOnTerminate--\n");
			}
		}).subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());
		
		
		Utils.print("doAfterTerminate");
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onCompleted();
			}
		}).doAfterTerminate(new Action0() {
			@Override
			public void call() {
				System.out.print("--doAfterTerminate--");
			}
		}).subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());
	}

}
