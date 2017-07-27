package com.nl.filter;

import rx.Observable;

import com.nl.util.SimpleAction;

/**
 * ofType： 过滤指定类型的数据，与filter类似，
 * 
 * @author xx
 * @Date 2017-7-22 下午4:12:56
 */
public class ofType {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(1, 2, "3").ofType(Integer.class).subscribe(new SimpleAction<Integer>());

	}

}
