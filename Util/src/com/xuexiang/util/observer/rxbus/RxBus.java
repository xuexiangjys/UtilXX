package com.xuexiang.util.observer.rxbus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import android.support.annotation.NonNull;

/**
 * RxBus
 * 
 * @author xx
 * 
 */
public class RxBus {
	private ConcurrentHashMap<Object, List<Subject>> maps = new ConcurrentHashMap<Object, List<Subject>>();
	private static RxBus sInstance;

	public static RxBus get() {
		if (sInstance == null) {
			synchronized (RxBus.class) {
				if (sInstance == null) {
					sInstance = new RxBus();
				}
			}
		}
		return sInstance;
	}

	/**
	 * 简单以对象的类名注册
	 * @param o
	 * @return
	 */
	public <T> Observable<T> simpleRegister(@NonNull Object o) {
		return register(o.getClass().getSimpleName());
	}

	/**
	 * 注册订阅
	 * @param tag
	 * @return
	 */
	public <T> Observable<T> register(@NonNull Object tag) {
		List<Subject> subjects = maps.get(tag);
		if (subjects == null) {
			subjects = new ArrayList<Subject>();
			maps.put(tag, subjects);
		}
		Subject<T, T> subject = PublishSubject.<T> create();
		subjects.add(subject);
		return subject;
	}

	/**
	 * 注销tag制定的订阅
	 * @param tag
	 */
	public void unregister(@NonNull Object tag, @NonNull Observable observable) {
		List<Subject> subjects = maps.get(tag);
		if (subjects != null) {
			subjects.remove((Subject) observable);
			if (subjects.isEmpty()) {
				maps.remove(tag);
			}
		}
	}

	/**
	 * 注销tag所有的订阅
	 * @param tag
	 */
	public void unregisterAll(@NonNull Object tag) {
		List<Subject> subjects = maps.get(tag);
		if (subjects != null) {
			maps.remove(tag);
		}
	}

	public void post(@NonNull Object o) {
		post(o.getClass().getSimpleName(), o);
	}

	/**
	 * 发送指定tag的事件
	 * @param tag
	 * @param o
	 */
	@SuppressWarnings("unchecked")
	public void post(@NonNull Object tag, @NonNull Object o) {
		List<Subject> subjects = maps.get(tag);
		if (subjects != null && !subjects.isEmpty()) {
			for (Subject s : subjects) {
				s.onNext(o);
			}
		}
	}
}
