package com.nl.rxjava.combine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import com.nl.rxjava.ListSimpleActivity;
import com.nl.rxjava.util.SimpleAction;

/** 
 * 合并操作
 * @author xx
 * @Date 2017-7-22 下午3:48:38 
 */
public class RxJavaCombineActivity extends ListSimpleActivity  {
	private Subscription mSubscription;
	
	@Override
	protected void init() {
		
	}

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<String>();
		list.add("concat");
		list.add("startWith");
		list.add("merge");
		list.add("zip");
		list.add("combineLatest");
		return list;
	}

	@Override
	protected void onItemClick(int position) {
		if (mSubscription != null && !mSubscription.isUnsubscribed()) {
			mSubscription.unsubscribe();
		}
		switch (position) {
		case 0:
			concat();
			break;
		case 1:
			startWith();
			break;
		case 2:
			merge();
			break;
		case 3:
			zip();
			break;
		case 4:
			combineLatest();
			break;
		default:
			break;
		}
	}

	private void concat() {
		Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
		Observable<Integer> observable2 = Observable.just(4, 5, 6);

		Observable.concat(observable2, observable1).subscribe(new SimpleAction<Integer>());
	}

	private void startWith() {
		Observable.just(1, 2, 3, 4, 5).startWith(6, 7, 8).subscribe(new SimpleAction<Integer>());// 6,7,8,1,2,3,4,5
	}

	/**
	 * 按触发的时间顺序
	 */
	private void merge() {
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

		Observable<String> observable3 = Observable.from(future).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
		
		Observable<String> observable4 = Observable.timer(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
			@Override
			public String call(Long arg0) {
				return "延迟2秒钟";
			}
		});
		
		Observable.merge(observable4, observable2, observable1, observable3).subscribe(new SimpleAction<String>());
	}

	/**
	 * 按加入的顺序组合
	 */
	private void zip() {
		Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
		Observable<Integer> observable2 = Observable.just(4, 5, 6);

		Observable.zip(observable1, observable2, new Func2<Integer, Integer, String>() {
			@Override
			public String call(Integer item1, Integer item2) {
				return item1 + "and" + item2;
			}
		}).subscribe(new SimpleAction<String>()); // 1and4,2and5,3and6
	}

	/**
	 * 最新的两个组合（可用于复杂的验证）
	 */
	private void combineLatest() {
		Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> observable2 = Observable.interval(2, TimeUnit.SECONDS);
		mSubscription = Observable.combineLatest(observable1, observable2, new Func2<Long, Long, String>() {
			@Override
			public String call(Long arg0, Long arg1) {
				return "observable1: " + String.valueOf(arg0) + ",  observable2:" + String.valueOf(arg1);
			}
		})
		.subscribeOn(Schedulers.io())
		.subscribe(new SimpleAction<String>());
	}
	
	@Override
	protected void onDestroy() {
		if (mSubscription != null && !mSubscription.isUnsubscribed()) {
			mSubscription.unsubscribe();
		}
		super.onDestroy();
	}


}
