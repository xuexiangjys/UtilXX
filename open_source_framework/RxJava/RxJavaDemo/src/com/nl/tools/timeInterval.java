package com.nl.tools;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.TimeInterval;

/** 
 * timeInterval：给Observable发射的两个数据项间添加一个时间差，实现在OperatorTimeInterval中 
 * @author xx
 * @Date 2017-7-24 上午11:31:59 
 */
public class timeInterval {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3)
		.timeInterval()
		.subscribe(new Action1<TimeInterval<Integer>>() {
			@Override
			public void call(TimeInterval<Integer> timeInterval) {
				System.out.print(timeInterval.toString() + "\n");
			}
		});
//		TimeInterval [intervalInMilliseconds=2, value=1]
//		TimeInterval [intervalInMilliseconds=1, value=2]
//		TimeInterval [intervalInMilliseconds=0, value=3]
	}

}
