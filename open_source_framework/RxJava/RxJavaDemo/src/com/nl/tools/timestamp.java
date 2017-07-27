package com.nl.tools;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * timestamp： 给Observable发射的每个数据项添加一个时间戳。
 * 
 * @author xx
 * @Date 2017-7-24 上午11:28:46
 */
public class timestamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3)
		.timestamp()
		.subscribe(new Action1<Timestamped<Integer>>() {
			@Override
			public void call(Timestamped<Integer> timestamped) {
				System.out.print(timestamped.getTimestampMillis() + " " + timestamped.getValue() + "\n");
				//1500866979702 1
				//1500866979702 2
				//1500866979702 3
			}
		});
	}

}
