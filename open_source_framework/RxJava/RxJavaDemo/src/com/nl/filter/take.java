package com.nl.filter;

import java.util.concurrent.TimeUnit;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

import rx.Observable;
import rx.functions.Func1;

/**
 * take： 只发射开始的N项数据或者一定时间内的数据。内部通过OperatorTake和OperatorTakeTimed过滤数据。
 * takeLast： 只发射最后的N项数据或者一定时间内的数据。
 * 
 * @author xx
 * @Date 2017-7-22 下午4:18:54
 */
public class take {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("take");
		
		Observable.just(3, 4, 5, 6)
				.take(3)// 发射前三个数据项
				.take(100, TimeUnit.MILLISECONDS)// 发射100ms内的数据
				.subscribe(new SimpleAction<Integer>()); // 4,5,6

		Utils.print("takeLast");
		
		Observable.just(3, 4, 5, 6)
				.takeLast(3)
				.subscribe(new SimpleAction<Integer>());// 4,5,6
		
		Utils.print("takeFirst");
		
		Observable.just(3, 4, 5, 6)
				.takeFirst(new Func1<Integer, Boolean>() {
					@Override
					public Boolean call(Integer arg0) {
						return arg0 > 4;
					}
				})
				.subscribe(new SimpleAction<Integer>());// 5
		
		Utils.print("first");
		Observable.just(3, 4, 5, 6)
				.first()
				.subscribe(new SimpleAction<Integer>()); //3
		
		
		Utils.print("first");
		Observable.just(3, 4, 5, 6)
				.first(new Func1<Integer, Boolean>() {
					@Override
					public Boolean call(Integer integer) {
						return integer > 5;
					}
				}).subscribe(new SimpleAction<Integer>());// 6
		
		Utils.print("last");
		Observable.just(3, 4, 5, 6)
				.last()
				.subscribe(new SimpleAction<Integer>());// 6
		
		Utils.print("last");
		Observable.just(3, 4, 5, 6)
				.last(new Func1<Integer, Boolean>() {
					@Override
					public Boolean call(Integer arg0) {
						return arg0 < 5;
					}
				})
				.subscribe(new SimpleAction<Integer>());// 4
		
		Utils.print("skip");
		Observable.just(3, 4, 5, 6)
        		.skip(2)
        		.subscribe(new SimpleAction<Integer>());//5,6
		
		Utils.print("skipLast");
		Observable.just(3, 4, 5, 6)
        		.skipLast(2)
        		.subscribe(new SimpleAction<Integer>());//3,4
		
		Utils.print("elementAt");
		Observable.just(3, 4, 5, 6)
				.elementAt(3)    //相当于Integer[3]
				.subscribe(new SimpleAction<Integer>()); //6

	}
}
