package com.xuexiang.util.observer.rxbus;

import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import android.support.annotation.NonNull;

import com.xuexiang.util.log.LogHelper;

public class RxManager {
	private static RxManager sInstance;
	private RxBus mRxBus = RxBus.get();
	/** 管理Subscribers订阅，防止内存泄漏 */
	private ConcurrentHashMap<Object, CompositeSubscription> maps = new ConcurrentHashMap<Object, CompositeSubscription>();
	
	public static RxManager get() {
		if (sInstance == null) {
			synchronized (RxManager.class) {
				if (sInstance == null) {
					sInstance = new RxManager();
				}
			}
		}
		return sInstance;
	}
	/**
	 * 
	 * RxBus注入监听
	 * 
	 * @param eventName
	 * 
	 * @param action1
	 */
	public <T> void onMainThread(Object eventName, Action1<T> action1) {
		Observable<T> Observable = mRxBus.register(eventName);
		/* 订阅管理 */
		add(eventName, Observable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, new Action1<Throwable>() {
			@Override
			public void call(Throwable throwable) {
				LogHelper.saveExceptionStackInfo(throwable);
			}
		}));
	}

	/**
	 * 
	 * RxBus注入监听
	 * @param eventName 事件名
	 * @param action1
	 */
	public <T> void on(Object eventName, Action1<T> action1) {
		Observable<T> Observable = mRxBus.register(eventName);
		/* 订阅管理 */
		add(eventName, Observable.subscribe(action1, new Action1<Throwable>() {
			@Override
			public void call(Throwable throwable) {
				LogHelper.saveExceptionStackInfo(throwable);
			}
		}));
	}

	/**
	 * 单纯的Observables 和Subscribers管理
	 * 
	 * @param m
	 */
	public void add(Object eventName, Subscription m) {
		/* 订阅管理 */
		CompositeSubscription subscription = maps.get(eventName);
		if (subscription == null) {
			subscription = new CompositeSubscription();
			maps.put(eventName, subscription);
		} 
		subscription.add(m);
	}

	/**
	 * 
	 * 单个presenter生命周期结束，取消订阅和所有rxbus观察
	 */
	public void clear(@NonNull Object eventName) {
		CompositeSubscription subscription = maps.get(eventName);
		if (subscription != null) {
			subscription.unsubscribe(); //取消订阅
			maps.remove(eventName); 
		}
		mRxBus.unregisterAll(eventName);

	}

	// 发送rxbus
	public void post(Object content) {
		mRxBus.post(content);
	}

	// 发送rxbus
	public void post(Object tag, Object content) {
		mRxBus.post(tag, content);
	}

}
