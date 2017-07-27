package com.nl.transform;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.nl.util.SimpleAction;

/**
 * switchMap： 和flatMap很像，将Observable发射的数据变换为Observables集合，当原始Observable发射一个新的数据（
 * Observable）时，它将取消订阅前一个Observable。
 * 
 * @author xx
 * @Date 2017-7-24 上午9:34:55
 */
public class switchMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				for (int i = 1; i < 4; i++) {
					subscriber.onNext(i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						throw Exceptions.propagate(e);
					} // 线程休眠500ms
				}
				subscriber.onCompleted();
			}
		})
		.switchMap(new Func1<Integer, Observable<Integer>>() {
			@Override
			public Observable<Integer> call(final Integer integer) {
				// 每当接收到新的数据，之前的Observable将会被取消订阅
				return Observable.create(new Observable.OnSubscribe<Integer>() {
					@Override
					public void call(Subscriber<? super Integer> subscriber) {
						subscriber.onNext(integer * 10);
						try {
							Thread.sleep(501);
						} catch (InterruptedException e) {
							throw Exceptions.propagate(e);
						} // 线程休眠500ms
						subscriber.onNext(integer * 100);
						subscriber.onCompleted();
					}
				}).subscribeOn(Schedulers.io());
			}
		})
		.subscribe(new SimpleAction<Integer>());// 10,20,30,300
	}
}
