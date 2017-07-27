package com.nl.block;

import java.util.Iterator;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * forEach： 对BlockingObservable发射的每一项数据调用一个方法，会阻塞直到Observable完成。
 * 
 * @author xx
 * @Date 2017-7-24 上午11:01:02
 */
public class forEach {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3).observeOn(Schedulers.io()).toBlocking().forEach(new Action1<Integer>() {
			@Override
			public void call(Integer integer) {
				System.out.print(integer.toString() + " " + Thread.currentThread().getName() + "\n");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.print(Thread.currentThread().getName() + "\n");
		
		
		Iterator<Integer> iterator  = Observable.just(2, 3, 6, 10)
		        .subscribeOn(Schedulers.io())
		        .toBlocking()
		        .getIterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString());
		}
		System.out.print("\n");
		
		Iterable<Integer> iterable = Observable.just(2, 3, 6, 10)
		        .subscribeOn(Schedulers.io())
		        .toBlocking()
		        .toIterable();

		for (Integer integer : iterable) {
			System.out.print(integer + " " + Thread.currentThread().getName() + "\n");
		}
	}

}
