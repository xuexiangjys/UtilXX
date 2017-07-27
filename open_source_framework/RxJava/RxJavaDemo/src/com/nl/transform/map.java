package com.nl.transform;

import java.util.Arrays;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/** 
 * map： 对Observable发射的每一项数据都应用一个函数来变换。
 * flatMap： 将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable，内部采用merge合并。
 * cast： 在发射之前强制将Observable发射的所有数据转换为指定类型
 * flatMapIterable： 和flatMap的作用一样，只不过生成的是Iterable而不是Observable。
 * concatMap： 类似于flatMap，由于内部使用concat合并，所以是按照顺序连接发射。
 * switchMap： 和flatMap很像，将Observable发射的数据变换为Observables集合，当原始Observable发射一个新的数据（Observable）时，它将取消订阅前一个Observable。
 * @author xx
 * @Date 2017-7-23 上午2:00:44 
 */
public class map {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("map");
		Observable.just(6, 2, 3, 4, 5).map(new Func1<Integer, String>() {
			@Override
			public String call(Integer arg0) {
				return "item:" + arg0;
			}
		}).subscribe(new SimpleAction<String>()); // item:6,item:2....
		
		//===========================================================//
		Utils.print("flatMap");
		Observable.just(2, 3, 5).flatMap(new Func1<Integer, Observable<String>>() {
			@Override
			public Observable<String> call(final Integer integer) {
				return Observable.create(new Observable.OnSubscribe<String>() { // 被订阅时触发
							@Override
							public void call(Subscriber<? super String> subscriber) {
								subscriber.onNext(integer * 10 + "");
								subscriber.onNext(integer * 100 + "");
								subscriber.onCompleted();
							}

						});
			}
		}).subscribe(new SimpleAction<String>()); //20,200,30,300,50,500
		
		Utils.print("flatMapIterable");
		Observable.just(2, 3, 5).flatMapIterable(new Func1<Integer, Iterable<String>>() {
			@Override
			public Iterable<String> call(final Integer integer) {
				return Arrays.asList(integer * 10 + "", integer * 100 + "");
			}
		}).subscribe(new SimpleAction<String>()); // 20,200,30,300,50,500
		
		Utils.print("concatMap");
		Observable.just(2, 3, 5).concatMap(new Func1<Integer, Observable<String>>() {
			@Override
			public Observable<String> call(final Integer integer) {
				return Observable.create(new Observable.OnSubscribe<String>() { // 被订阅时触发
							@Override
							public void call(Subscriber<? super String> subscriber) {
								subscriber.onNext(integer * 10 + "");
								subscriber.onNext(integer * 100 + "");
								subscriber.onCompleted();
							}
						});
			}
		}).subscribe(new SimpleAction<String>());
	}

}
