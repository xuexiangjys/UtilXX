package com.nl.transform;

import rx.Observable;
import rx.functions.Func2;

import com.nl.util.SimpleAction;

/**
 * scan： 与reduce很像，对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值。
 * 
 * @author xx
 * @Date 2017-7-24 上午9:52:15
 */
public class scan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3, 5).scan(new Func2<Integer, Integer, Integer>() {
			@Override
			public Integer call(Integer sum, Integer item) {
				return sum + item;
			}
		}).subscribe(new SimpleAction<Integer>()); // 2,5,10

	}

}
