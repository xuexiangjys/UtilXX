package com.nl.combine;

import com.nl.util.SimpleAction;

import rx.Observable;
import rx.functions.Func2;

/**
 * zip： 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。如果多个Observable发射的数据量不一样，
 * 则以最少的Observable为标准进行压合。内部通过OperatorZip进行压合。
 * 
 * @author xx
 * @Date 2017-7-21 下午2:18:17
 */
public class zip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
		Observable<Integer> observable2 = Observable.just(4, 5, 6);

		Observable.zip(observable1, observable2, new Func2<Integer, Integer, String>() {
			@Override
			public String call(Integer item1, Integer item2) {
				return item1 + "and" + item2;
			}
		}).subscribe(new SimpleAction<String>()); // 1and4,2and5,3and6
	}
}
