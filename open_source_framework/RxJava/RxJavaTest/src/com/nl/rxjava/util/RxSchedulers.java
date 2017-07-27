package com.nl.rxjava.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/** 
 * @author xx
 * @Date 2017-7-24 下午3:00:00 
 */
@SuppressWarnings("unchecked")
public class RxSchedulers {
	
	
	public static <T> Observable.Transformer<T, T> main() {
        return (Observable.Transformer) new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable)
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> io_main() {
        return (Observable.Transformer) new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
