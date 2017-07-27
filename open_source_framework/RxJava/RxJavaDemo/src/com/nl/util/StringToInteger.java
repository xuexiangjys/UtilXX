package com.nl.util;

import rx.Observable.Operator;
import rx.Subscriber;



/** 
 * @author xx
 * @Date 2017-7-24 下午3:29:59 
 */
public class StringToInteger implements Operator<Integer, String> {

	@Override
	public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
		 return new Subscriber<String>() {
             @Override
             public void onCompleted() {
            	 System.out.print("==onCompleted==");
                 subscriber.onCompleted();
             }

             @Override
             public void onError(Throwable e) {
            	 System.out.print("==onError==");
                 subscriber.onError(e);
             }

             @Override
             public void onNext(String s) {
            	 System.out.print("==onNext==");
                 int value = Integer.valueOf(s); //进行转换
                 subscriber.onNext(value);
             }
         };
	}



}
