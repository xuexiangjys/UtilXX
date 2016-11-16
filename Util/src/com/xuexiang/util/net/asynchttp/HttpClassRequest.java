package com.xuexiang.util.net.asynchttp;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.xuexiang.util.net.asynchttp.toolbox.CustomLog;
import com.xuexiang.util.net.asynchttp.toolbox.HttpError;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpSuccess;

public class HttpClassRequest<T> extends HttpRequest {
	
	private HttpSuccess<T> mHttpSuccess;
	private Class<T> mClass;	

	/**
	 * create a instance HttpRequest.
	 * 
	 * @param cls
	 * @param map
	 * @param httpSuccess
	 * @param httpError
	 */
	public HttpClassRequest(Class<T> cls, String url, HashMap<String, String> map, HttpSuccess<T> httpSuccess, HttpError httpError) {
		mClass = cls;
		mUrlString = url;
		mParams = map;
		mHttpSuccess = httpSuccess;
		mHttpError = httpError;

	}

	/**
	 * @description: 获取参数
	 * @author:hc
	 * @return:RequestParams
	 * @return
	 */

	@Override
	public RequestParams getParams() {
		if (mParams == null) {
			return null;
		}
		RequestParams requestParams = new RequestParams();
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = mParams.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			requestParams.put(key, mParams.get(key));
			String val = mParams.get(key);
			stringBuilder.append("&" + key + "=" + val);
		}
		CustomLog.d("提交参数为   %s", "=" + stringBuilder.toString());
		return requestParams;
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:String
	 * @return
	 */

	@Override
	public String getUrlString() {
		return mUrlString;
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg3
	 */

	@Override
	public void onFailure(Throwable throwable) {
		if (mHttpError != null)
			mHttpError.onError(throwable);
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg2
	 */

	@Override
	public void onSuccess(String result) {
		Gson gson = new Gson();
		CustomLog.d("结果是=%s", result);
		try {
			if (mHttpSuccess != null)
				mHttpSuccess.onSuccess(gson.fromJson(result, mClass));
		} catch (JsonSyntaxException e) {
			if (mHttpError != null)
				mHttpError.onError(e);
		}
	}
}
