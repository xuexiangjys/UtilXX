package com.nl.tools;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import com.nl.util.SimpleAction;

/** 
 * doOnError： 注册一个动作，对发生错误的Observable使用
 * @author xx
 * @Date 2017-7-24 下午2:20:31 
 */
public class doOnError {

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
		}).doOnError(new Action1<Throwable>() {
			@Override
			public void call(Throwable arg0) {
				System.out.print("--doOnError--");
			}
		}).subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());

	}

}
