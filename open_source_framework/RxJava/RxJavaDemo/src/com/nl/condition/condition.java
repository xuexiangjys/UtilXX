package com.nl.condition;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/**
 * all： 判断所有的数据项是否满足某个条件，内部通过OperatorAll实现。
 * exists： 判断是否存在数据项满足某个条件。内部通过OperatorAny实现。
 * contains： 判断在发射的所有数据项中是否包含指定的数据，内部调用的其实是exists
 * isEmpty： 用于判断Observable发射完毕时，有没有发射数据。有数据false，如果只收到了onComplete通知则为true。
 * sequenceEqual： 用于判断两个Observable发射的数据是否相同（数据，发射顺序，终止状态）。
 * amb： 给定多个Observable，只让第一个发射数据的Observable发射全部数据，其他Observable将会被忽略。
 * switchIfEmpty： 如果原始Observable正常终止后仍然没有发射任何数据，就使用备用的Observable。
 * takeUntil： 当发射的数据满足某个条件后（包含该数据），或者第二个Observable发送完毕，终止第一个Observable发送数据。
 * takeWhile： 当发射的数据满足某个条件时（不包含该数据），Observable终止发送数据。
 * @author xx
 * @Date 2017-7-23 上午12:24:16
 */
public class condition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("all");
		Observable.just(2, 3, 4, 5)
		.all(new Func1<Integer, Boolean>() {
			@Override
			public Boolean call(Integer integer) {
				return integer > 3;
			}
		}).subscribe(new SimpleAction<Boolean>()); //false
		
		Utils.print("exists");
		Observable.just(2, 3, 4, 5)
        .exists(new Func1<Integer, Boolean>() {
			@Override
			public Boolean call(Integer integer) {
				return integer > 3;
			}
		}).subscribe(new SimpleAction<Boolean>()); //true
		
		Utils.print("contains");
		Observable.just(2, 3, 4, 5)
        .contains(3).subscribe(new SimpleAction<Boolean>()); //true
		
		Utils.print("sequenceEqual");
		Observable.sequenceEqual(Observable.just(2, 3, 4, 5), Observable.just(2, 3, 4, 5))
        .subscribe(new SimpleAction<Boolean>()); //true

		Utils.print("amb");
		Observable<Integer> observable1 = Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					subscriber.onError(e);
				}
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onCompleted();
			}
		}).subscribeOn(Schedulers.computation());
		
		Observable<Integer> observable2 = Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				subscriber.onNext(3);
				subscriber.onNext(4);
				subscriber.onCompleted();
			}
		});
		
		Observable.amb(observable1, observable2).subscribe(new SimpleAction<Integer>()); // 3,4
		
		Utils.print("switchIfEmpty");
		Observable.empty()
        	.switchIfEmpty(Observable.just(2, 3, 4))
        	.subscribe(new SimpleAction<Object>()); //2,3,4
		
		Utils.print("takeUntil");
		Observable.just(2, 3, 4, 5)
        .takeUntil(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer == 4;
            }
        }).subscribe(new SimpleAction<Integer>()); //2,3,4
		
		Utils.print("takeWhile");
		Observable.just(2, 3, 4, 5)
        .takeWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer == 4;
            }
        }).subscribe(new SimpleAction<Integer>()); //2,3
	}
}
