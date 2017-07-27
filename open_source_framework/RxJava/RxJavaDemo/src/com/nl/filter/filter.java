package com.nl.filter;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;

/**
 * filter： 过滤数据。内部通过OnSubscribeFilter过滤数据。
 * 
 * @author xx
 * @Date 2017-7-22 下午4:10:03
 */
public class filter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(3, 4, 5, 6).filter(new Func1<Integer, Boolean>() {
			@Override
			public Boolean call(Integer integer) {
				return integer > 4;
			}
		}).subscribe(new SimpleAction<Integer>()); // 5,6
	}

}
