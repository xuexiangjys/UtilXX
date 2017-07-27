package com.nl.errorhandle;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/**
 * retry： 当原始Observable在遇到错误时进行重试。
 * 
 * @author xx
 * @Date 2017-7-24 上午10:23:42
 */
public class retry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("retry");
		Observable.just(1, "2", 3)
		.cast(Integer.class)
		.retry(3)
		.subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());
		//1,1,1,1,onerror
		
		Utils.print("retryWhen"); 
		Observable.just(1,"2",3)
	    .cast(Integer.class)
	    .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
	        @Override
	        public Observable<?> call(Observable<? extends Throwable> observable) {
	        	return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        return Observable.interval(0, 1, TimeUnit.SECONDS);
                    }
                });
	        }
	    })
	    .subscribe(new SimpleAction<Integer>(), new SimpleAction<Throwable>());
	    //1,1
	}

}
