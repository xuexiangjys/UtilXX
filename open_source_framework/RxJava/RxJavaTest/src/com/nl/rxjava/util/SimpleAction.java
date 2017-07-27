package com.nl.rxjava.util;

import rx.functions.Action1;
import android.util.Log;

/** 
 * @author xx
 * @Date 2017-7-21 上午10:43:22 
 */
public class SimpleAction<T> implements Action1<T> {

	@Override
	public void call(T arg0) {
		Log.d("SimpleAction", "call:" + arg0 + "\n");
	}

}
