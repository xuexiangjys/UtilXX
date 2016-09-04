package com.xuexiang.util.net.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

import com.google.gson.Gson;


/**
 * 网络请求返回的是一个实体对象
 * @author xx
 *
 * @param <T>
 */
public abstract class ClassCallBack<T> extends Callback<T> {
	private Class<T> mClass;
	public ClassCallBack(Class<T> clazz) {
		mClass = clazz;
	}
	
	@Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        T t = new Gson().fromJson(string, mClass);
        return t;
    }
}
