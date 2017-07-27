package com.nl.tools;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;

import com.nl.util.SimpleAction;

/**
 * doOnEach： 注册一个动作，对Observable发射的每个数据项使用
 * 
 * @author xx
 * @Date 2017-7-24 下午12:37:19
 */
public class doOnEach {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3)
		.doOnEach(new Action1<Notification<? super Integer>>() {
			@Override
			public void call(Notification<? super Integer> notification) {
				System.out.print("--doOnEach--" + notification.toString());
			}
		}).subscribe(new SimpleAction<Integer>());
	}

}
