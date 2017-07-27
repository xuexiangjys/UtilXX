package com.nl.transform;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * groupBy：
 * 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据。
 * 
 * @author xx
 * @Date 2017-7-24 上午9:53:36
 */
public class groupBy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3, 5, 6).groupBy(new Func1<Integer, String>() {
			@Override
			public String call(Integer integer) {// 分组
				return integer % 2 == 0 ? "偶数" : "奇数";
			}
		}).subscribe(new Action1<GroupedObservable<String, Integer>>() {
			@Override
			public void call(final GroupedObservable<String, Integer> o) {
				o.subscribe(new Action1<Integer>() {
					@Override
					public void call(Integer integer) {
						System.out.print(o.getKey() + ":" + integer.toString()); // 偶数：2，奇数：3，...
					}
				});
			}
		});
		
		
		

	}

}
