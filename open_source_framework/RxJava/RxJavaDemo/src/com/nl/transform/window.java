package com.nl.transform;

import rx.Observable;
import rx.functions.Action1;

/**
 * window： 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。
 * @author xx
 * @Date 2017-7-24 上午10:02:42
 */
public class window {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3, 5, 6).window(3).subscribe(new Action1<Observable<Integer>>() {
			@Override
			public void call(Observable<Integer> integerObservable) {
				integerObservable.subscribe(new Action1<Integer>() {
					@Override
					public void call(Integer integer) {
						System.out.print("call:" + integer);
					}
				});
				System.out.print("\n");
			}
		});
	}

}
