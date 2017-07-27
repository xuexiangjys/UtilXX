package com.nl.rxjava.create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import com.nl.rxjava.ListSimpleActivity;
import com.nl.rxjava.util.RxSchedulers;
import com.nl.rxjava.util.SimpleAction;

/**
 * 创建操作
 * @author xx
 * @Date 2017-7-21 上午10:59:02
 */
public class RxJavaCreateActivity extends ListSimpleActivity {
	private Subscription mSubscription;

	@Override
	protected void init() {

	}

	@Override
	protected List<String> initSimpleData() {
		List<String> list = new ArrayList<String>();
		list.add("create");
		list.add("from");
		list.add("just");
		list.add("timer");
		list.add("error");
		list.add("range");
		list.add("defer");
		return list;
	}

	@Override
	protected void onItemClick(int position) {
		if (mSubscription != null && !mSubscription.isUnsubscribed()) {
			mSubscription.unsubscribe();
		}
		switch (position) {
		case 0:
			create();
			break;
		case 1:
			from();
			break;
		case 2:
			just();
			break;
		case 3:
			Timer();
			break;
		case 4:
			error();
			break;
		case 5:
			range();
			break;
		case 6:
			defer();
			break;

		default:
			break;
		}
	}

	/**
	 * create：
	 * 使用OnSubscribe从头创建一个Observable，这种方法比较简单。需要注意的是，使用该方法创建时，建议在OnSubscribe
	 * #call方法中检查订阅状态，以便及时停止发射数据或者运算。
	 */
	private void create() {
		Observable.create(new Observable.OnSubscribe<String>() { // 被订阅时触发
					@Override
					public void call(Subscriber<? super String> subscriber) {
						subscriber.onNext("item1");
						subscriber.onNext("item2");
						subscriber.onCompleted();
						print("onCompleted() \n");
					}
				}).subscribe(new SimpleAction<String>());
	}

	/**
	 * from： 将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable。
	 */
	private void from() {
		List<String> list = new ArrayList<String>();
		list.add("String1");
		list.add("String2");
		list.add("String3");
		list.add("String4");
		Observable.from(list).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				print("fromList :" + string + "\n");
			}
		});

		String[] strings = new String[] { "aaa", "bbb", "ccc", "ddd" };
		Observable.from(strings).subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				print(" fromString[] :" + string);
			}
		});

		Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "延迟1秒钟";
			}
		});

		Observable.from(future)
		.compose(RxSchedulers.io_main())
		.cast(String.class)
		.subscribe(new Action1<String>() {
			@Override
			public void call(String string) {
				print("fromFuture :" + string + "\n");
			}
		});
	}

	/**
	 * 将一个或多个对象转换成发射这个或这些对象的一个Observable。如果是单个对象，
	 * 内部创建的是ScalarSynchronousObservable对象。如果是多个对象，则是调用了from方法创建。
	 */
	private void just() {
		Observable.just("abc").subscribe(new SimpleAction<String>());

		Observable.just("abc", "def", "ghi").subscribe(new SimpleAction<String>());
	}

	/**
	 * timer： 创建一个在给定的延时之后发射数据项为0的Observable<Long>,内部通过OnSubscribeTimerOnce工作
	 * 
	 * interval：创建一个按照给定的时间间隔发射从0开始的整数序列的Observable<Long>
	 */
	private void Timer() {
		Observable.timer(1, TimeUnit.SECONDS).subscribe(new SimpleAction<Long>());

		mSubscription = Observable.interval(1, TimeUnit.SECONDS).subscribe(new SimpleAction<Long>());
	}

	/**
	 * error： 创建一个什么都不做直接通知错误的Observable
	 */
	private void error() {
		Observable.error(new RuntimeException("运行出错")).subscribe(new SimpleAction(), new Action1<Throwable>() {

			@Override
			public void call(Throwable throwable) {
				logs("出现错误 ：" + throwable.getMessage());
			}
		});
	}

	/**
	 * range：创建一个发射指定范围的整数序列的Observable<Integer>
	 */
	private void range() {
		Observable.range(2, 10).subscribe(new SimpleAction<Integer>());// 从2开始发射10个数据

	}

	/**
	 * defer： 只有当订阅者订阅才创建Observable，为每个订阅创建一个新的Observable。内部通过OnSubscribeDefer在订阅时调用Func0创建Observable。
	 */
	private void defer() {
		Observable.defer(new Func0<Observable<String>>() {
	        @Override
	        public Observable<String> call() {
	            return Observable.just("hello");
	        }
	    }).subscribe(new SimpleAction<String>());
	}

}
