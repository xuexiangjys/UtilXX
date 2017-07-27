package com.nl.errorhandle;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;

/**
 * onErrorReturn： 当原始Observable在遇到错误时发射一个特定的数据。
 * @author xx
 * @Date 2017-7-24 上午10:21:16
 */
public class onErrorReturn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, "2", 3)
		.cast(Integer.class)
		.onErrorReturn(new Func1<Throwable, Integer>() {
			@Override
			public Integer call(Throwable throwable) {
				return 4;
			}
		}).subscribe(new SimpleAction<Integer>());  //1,4
	}

}
