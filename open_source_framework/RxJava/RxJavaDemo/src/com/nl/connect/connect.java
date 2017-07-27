package com.nl.connect;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * ConnectableObservable与普通的Observable差不多，但是可连接的Observable在被订阅时并不开始发射数据，
 * 只有在它的connect()被调用时才开始。用这种方法，你可以等所有的潜在订阅者都订阅了这个Observable之后才开始发射数据。
 * ConnectableObservable.connect()指示一个可连接的Observable开始发射数据.
 * Observable.publish()将一个Observable转换为一个可连接的Observable
 * Observable.replay()确保所有的订阅者看到相同的数据序列的ConnectableObservable，即使它们在Observable开始发射数据之后才订阅。
 * ConnectableObservable.refCount()让一个可连接的Observable表现得像一个普通的Observable。
 * 
 * @author xx
 * @Date 2017-7-24 上午10:49:45
 */
public class connect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectableObservable<Integer> co = Observable.just(1, 2, 3).publish();

		co.subscribe(new SimpleAction<Integer>());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		co.connect();// 此时开始发射数据
	}

}
