package com.nl.convert;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

import com.nl.util.SimpleAction;

/** 
 * toSortedList： 收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表。
 * @author xx
 * @Date 2017-7-23 上午1:44:58 
 */
public class toSortedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.just(6, 2, 3, 4, 5)
        .toSortedList(new Func2<Integer, Integer, Integer>() {//自定义排序
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer - integer2; //>0 升序 ，<0 降序
            }
        })
        .subscribe(new SimpleAction<List<Integer>>()); //[2, 3, 4, 5, 6]
	}

}
