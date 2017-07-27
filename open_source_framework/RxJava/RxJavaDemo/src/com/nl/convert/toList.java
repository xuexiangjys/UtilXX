package com.nl.convert;

import java.util.List;

import rx.Observable;

import com.nl.util.SimpleAction;

/**
 * toList： 收集原始Observable发射的所有数据到一个列表，然后返回这个列表.
 * 
 * @author xx
 * @Date 2017-7-23 上午1:40:36
 */
public class toList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3, 4, 5).toList().subscribe(new SimpleAction<List<Integer>>());

	}

}
