package com.nl.combine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;

/** 
 * merge： 将多个Observable合并为一个。不同于concat，merge不是按照添加顺序连接，而是按照时间线来连接。其中mergeDelayError将异常延迟到其它没有错误的Observable发送完毕后才发射。而merge则是一遇到异常将停止发射数据，发送onError通知。 
 * @author xx
 * @Date 2017-7-21 下午2:07:53 
 */
public class merge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("String1");
		list.add("String2");
		list.add("String3");
		list.add("String4");
		Observable<String> observable1 = Observable.from(list);

		String[] strings = new String[] { "aaa", "bbb", "ccc", "ddd" };
		Observable<String> observable2 = Observable.from(strings);

		Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "延迟1秒钟";
			}
		});

		Observable<String> observable3 = Observable.from(future);
		
		Observable<String> observable4 = Observable.timer(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
			@Override
			public String call(Long arg0) {
				return "延迟2秒钟";
			}
		});
		
		Observable.merge(observable4, observable2, observable1, observable3).subscribe(new SimpleAction<String>());
	}

}
