package com.nl.tools;

import java.util.concurrent.TimeUnit;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.schedulers.Schedulers;

/** 
 * delay： 延时发射Observable的结果。即让原始Observable在发射每项数据之前都暂停一段指定的时间段。效果是Observable发射的数据项在时间上向前整体平移了一个增量（除了onError，它会即时通知）。
 * @author xx
 * @Date 2017-7-24 下午2:48:43 
 */
public class delay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3, 4)
		.delay(1, TimeUnit.SECONDS)
		.subscribe(new SimpleAction<Integer>());
		
		
		Observable.just(1, 2, 3, 4)
		.delaySubscription(1, TimeUnit.SECONDS)
		.subscribe(new SimpleAction<Integer>());
	}

}
