package com.nl.filter;

import java.util.concurrent.TimeUnit;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action1;

/** 
 * timeout： 如果原始Observable过了指定的一段时长没有发射任何数据，就发射一个异常或者使用备用的Observable。
 * @author xx
 * @Date 2017-7-22 下午11:37:35 
 */
public class timeout {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Integer> observable1 = Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(1);
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            throw Exceptions.propagate(e);
		        }
		        subscriber.onNext(2);

		        subscriber.onCompleted();
			}
		});
		
		observable1.timeout(500, TimeUnit.MILLISECONDS, Observable.just(99, 100))//如果不指定备用Observable将会抛出异常
	      .subscribe(new SimpleAction<>(), new Action1<Throwable>() {
			@Override
			public void call(Throwable arg0) {
				System.out.print(arg0.getMessage());
			}
		}); //结果为1,99,100  如果不指定备用Observable结果为1,onError

		
		observable1.timeout(500, TimeUnit.MILLISECONDS)//如果不指定备用Observable将会抛出异常
	      .subscribe(new SimpleAction<>(), new Action1<Throwable>() {
			@Override
			public void call(Throwable arg0) {
				System.out.print("onError");
			}
		}); //结果为1,99,100  如果不指定备用Observable结果为1,onError
	}

}
