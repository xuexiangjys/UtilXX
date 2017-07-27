package com.nl.combine;

import rx.Observable;

import com.nl.util.SimpleAction;

/**
 * startWith： 在数据序列的开头增加一项数据。startWith的内部也是调用了concat
 * 
 * @author xx
 * @Date 2017-7-21 下午2:05:13
 */
public class startWith {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, 3, 4, 5).startWith(6, 7, 8).subscribe(new SimpleAction<Integer>());// 6,7,8,1,2,3,4,5
	}

}
