package com.nl.create;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * timer： 创建一个在给定的延时之后发射数据项为0的Observable<Long>,内部通过OnSubscribeTimerOnce工作
 * 
 * @author xx
 * @Date 2017-7-22 下午12:45:10
 */
public class timer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.timer(1, TimeUnit.SECONDS).map(new Func1<Long, String>() {
			@Override
			public String call(Long arg0) {
				return "" + arg0;
			}
		}).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				System.out.print("call : " + string);
			}
		});
		
		Observable.interval(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {

			@Override
			public String call(Long arg0) {
				return null;
			}
		});
	}

}
