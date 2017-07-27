package com.nl.combine;

import com.nl.util.SimpleAction;

import rx.Observable;

/**
 * concat： 按顺序连接多个Observables。需要注意的是Observable.concat(a,b)等价于a.concatWith(b)。
 * 
 * @author xx
 * @Date 2017-7-21 下午2:02:40
 */
public class concat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
		Observable<Integer> observable2 = Observable.just(4, 5, 6);

		Observable.concat(observable2, observable1).subscribe(new SimpleAction<Integer>());
	}
}
