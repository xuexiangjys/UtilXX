package com.nl.mathematical;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func2;

import com.nl.util.SimpleAction;
import com.nl.util.Utils;

/** 
 * 聚合操作
 * reduce： 对序列使用reduce()函数并发射最终的结果,内部使用OnSubscribeReduce实现。
 * collect： 使用collect收集数据到一个可变的数据结构。
 * count/countLong： 计算发射的数量，内部调用的是reduce.
 * @author xx
 * @Date 2017-7-23 上午1:24:28 
 */
public class reduce {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.print("reduce");
		Observable.just(2, 3, 4, 5)
		.reduce(new Func2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer sum, Integer item) {
				return sum + item;
			}
		}).subscribe(new SimpleAction<Integer>());
		
		Utils.print("collect");
		Observable.just(3, 4, 5, 6)
        .collect(new Func0<List<Integer>>() { //创建数据结构
            @Override
            public List<Integer> call() {
                return new ArrayList<Integer>();
            }
            
        }, new Action2<List<Integer>, Integer>() { //收集器
            @Override
            public void call(List<Integer> integers, Integer integer) {
                integers.add(integer);
            }
        })
       .subscribe(new SimpleAction<List<Integer>>());  //[3, 4, 5, 6]
		
		
		Utils.print("count");
		Observable.just(3, 4, 5, 6)
		.count().subscribe(new SimpleAction<Integer>()); //4

	}

}
