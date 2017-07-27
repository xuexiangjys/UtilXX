package com.nl.create;

import rx.Observable;

import com.nl.util.SimpleAction;

/** 
 * range： 创建一个发射指定范围的整数序列的Observable<Integer>
 * @author xx
 * @Date 2017-7-21 下午1:48:08 
 */
public class range {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.range(2, 10).subscribe(new SimpleAction<Integer>());
	}

}
