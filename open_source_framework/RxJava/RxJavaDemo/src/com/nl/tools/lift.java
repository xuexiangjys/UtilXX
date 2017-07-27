package com.nl.tools;

import com.nl.util.SimpleAction;
import com.nl.util.StringToInteger;

import rx.Observable;

/** 
 * 自定义操作符
 * @author xx
 * @Date 2017-7-24 下午3:36:44 
 */
public class lift {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just("1", "2", "3").lift(new StringToInteger()).subscribe(new SimpleAction<Integer>());
	}

}
