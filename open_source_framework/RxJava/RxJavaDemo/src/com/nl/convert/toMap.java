package com.nl.convert;

import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

import com.nl.util.SimpleAction;

/**
 * toMap： 将序列数据转换为一个Map。我们可以根据数据项生成key和生成value。
 * 
 * @author xx
 * @Date 2017-7-23 上午1:51:44
 */
public class toMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(6, 2, 3, 4, 5).toMap(new Func1<Integer, String>() {
			@Override
			public String call(Integer integer) {
				return "key：" + integer; // 根据数据项生成map的key
			}
		}, new Func1<Integer, String>() {
			@Override
			public String call(Integer integer) {
				return "value：" + integer; // 根据数据项生成map的kvalue
			}
		}).subscribe(new SimpleAction<Map<String, String>>());
		// {key：2=value：2, key：5=value：5, key：6=value：6, key：3=value：3, key：4=value：4}
	}

}
