package com.nl.filter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/** 
 * distinct：过滤重复数据，内部通过OperatorDistinct实现。
 * distinctUntilChanged：过滤掉连续重复的数据。内部通过OperatorDistinctUntilChanged实现
 * debounce：发射数据时，如果两次数据的发射间隔小于指定时间，就会丢弃前一次的数据,直到指定时间内都没有新数据发射时 
 * @author xx
 * @Date 2017-7-22 下午5:13:56 
 */
public class distinct {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("distinct");
		Observable.just(3, 4, 5, 6, 3, 3, 4, 9)
	       .distinct()
	       .subscribe(new SimpleAction<Integer>()); //3,4,5,6,9
		
		Utils.print("distinctUntilChanged");
		Observable.just(3, 4, 5, 5, 6, 3, 3, 4, 9)
			.distinctUntilChanged()
			.subscribe(new SimpleAction<Integer>()); //3,4,5,6,3,4,9
		
		Utils.print("throttleFirst");  //快速点击
		Observable.create(new Observable.OnSubscribe<Integer>(){
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }
		        subscriber.onNext(2);
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }

		        subscriber.onNext(3);
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }
		        subscriber.onNext(4);
		        subscriber.onNext(5);
		        subscriber.onCompleted();
			}
		}).throttleFirst(990, TimeUnit.MILLISECONDS)
		.subscribe(new SimpleAction<Integer>());  //结果为1,3,4
		
		Utils.print("debounce");  //搜索框输入监听
		Observable.create(new Observable.OnSubscribe<Integer>(){
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }
		        subscriber.onNext(2);
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }

		        subscriber.onNext(3);
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }
		        subscriber.onNext(4);
		        subscriber.onNext(5);
		        subscriber.onCompleted();
			}
		}).debounce(990, TimeUnit.MILLISECONDS)
		.subscribe(new SimpleAction<Integer>());  //结果为3,5

	}

}
