package com.nl.combine;

import java.util.concurrent.TimeUnit;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/** 
 * combineLatest： 。当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果。类似于zip，但是，不同的是zip只有在每个Observable都发射了数据才工作，而combineLatest任何一个发射了数据都可以工作，每次与另一个Observable最近的数据压合。
 * @author xx
 * @Date 2017-7-22 下午3:34:13 
 */
public class combineLatest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> observable2 = Observable.interval(2, TimeUnit.SECONDS);
		Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Boolean>() {
			@Override
			public Boolean call(Long arg0, Long arg1) {
				return (arg0 + arg1) <= 100 ;
			}
		})
		.subscribeOn(Schedulers.io())
		.subscribe(new SimpleAction<Boolean>());
	}

}
