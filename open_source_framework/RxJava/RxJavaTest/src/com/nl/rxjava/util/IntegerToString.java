package com.nl.rxjava.util;

import rx.Observable.Operator;
import rx.Subscriber;



/** 
 * @author xx
 * @param <R>
 * @Date 2017-7-24 下午3:29:59 
 */
public class IntegerToString implements Operator<Integer, String> {

	@Override
	public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
		 return new Subscriber<String>() {
             @Override
             public void onCompleted() {
                 subscriber.onCompleted();
             }

             @Override
             public void onError(Throwable e) {
                 subscriber.onError(e);
             }

             @Override
             public void onNext(String s) {
                 int value = Integer.valueOf(s); //进行转换
                 subscriber.onNext(value);
             }
         };
	}



}
