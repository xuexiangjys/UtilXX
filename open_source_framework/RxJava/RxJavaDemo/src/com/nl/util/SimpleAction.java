package com.nl.util;

import rx.functions.Action1;

/** 
 * @author xx
 * @Date 2017-7-21 上午10:43:22 
 */
public class SimpleAction<T> implements Action1<T> {

	@Override
	public void call(T arg0) {
		System.out.print("call:" + arg0 + "\n");
	}

}
