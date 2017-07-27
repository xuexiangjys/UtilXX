package com.nl.create;

import rx.Observable;
import rx.functions.Action1;

import com.nl.util.SimpleAction;

/** 
 * error： 创建一个什么都不做直接通知错误的Observable
 * @author xx
 * @Date 2017-7-21 上午10:47:51 
 */
public class Error {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.error(new RuntimeException("运行出错")).subscribe(new SimpleAction(), new Action1<Throwable>() {
			
			@Override
			public void call(Throwable throwable) {
				System.out.print("出现错误 ：" + throwable.getMessage());
			}
		});
	}

}
