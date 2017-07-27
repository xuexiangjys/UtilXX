package com.nl.create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import rx.Observable;
import rx.functions.Action1;

/**
 * from： 将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable。Future转换为OnSubscribe是通过OnSubscribeToObservableFuture进行的，Iterable转换通过OnSubscribeFromIterable进行。数组通过OnSubscribeFromArray转换。 
 * @author xx
 * @Date 2017-7-21 上午10:28:47
 */
public class From {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("String1");
		list.add("String2");
		list.add("String3");
		list.add("String4");
		Observable.from(list).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				System.out.print("fromList :" + string + "\n");
			}
		});

		String[] strings = new String[] { "aaa", "bbb", "ccc", "ddd" };
		Observable.from(strings).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				System.out.print(" fromString[] :" + string);
			}
		});

		Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "延迟1秒钟";
			}
		});

		Observable.from(future).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				System.out.print("fromFuture :" + string + "\n");

			}
		});

	}

}
