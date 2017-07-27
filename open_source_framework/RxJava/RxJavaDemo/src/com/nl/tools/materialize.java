package com.nl.tools;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;

/**
 * materialize： 将Observable转换成一个通知列表。
 * 
 * @author xx
 * @Date 2017-7-24 上午11:25:24
 */
public class materialize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3)
		.materialize()
		.subscribe(new Action1<Notification<Integer>>() {
			@Override
			public void call(Notification<Integer> notification) {
				System.out.print(notification.toString() + "\n");
			}
		});
		// OnNext 1
		// OnNext 2
		// OnNext 3
		// OnCompleted null
	}

}
