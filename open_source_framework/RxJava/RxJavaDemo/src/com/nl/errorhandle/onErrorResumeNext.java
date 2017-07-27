package com.nl.errorhandle;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/**
 * onErrorResumeNext： 当原始Observable在遇到错误时，使用备用Observable。。
 * 
 * @author xx
 * @Date 2017-7-24 上午10:07:42
 */
public class onErrorResumeNext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, "2", 3)
		.cast(Integer.class)
		.onErrorResumeNext(Observable.just(1, 2, 3))
		.subscribe(new SimpleAction<Integer>()); // 1,1,2,3
		
		Utils.print("onErrorResumeNext");
		Observable.just(1, "2", 3)
		.cast(Integer.class)
		.onErrorResumeNext(new Func1<Throwable, Observable<Integer>>() {

			@Override
			public Observable<Integer> call(Throwable arg0) {
				return Observable.error(arg0);
			}
		})
		.subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>()); // 1,1,2,3
	}

}
