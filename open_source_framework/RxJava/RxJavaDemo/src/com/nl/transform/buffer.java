package com.nl.transform;

import java.util.List;

import rx.Observable;

import com.nl.util.SimpleAction;

/**
 * buffer： 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
 * @author xx
 * @Date 2017-7-24 上午9:59:50
 */
public class buffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(2, 3, 5, 6).buffer(3).subscribe(new SimpleAction<List<Integer>>());
		//[2, 3, 5] [6]
	}

}
