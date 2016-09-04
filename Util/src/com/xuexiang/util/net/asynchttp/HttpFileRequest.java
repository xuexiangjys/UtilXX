/**
 * @FILE:HuiHenDuoFileRequest.java
 * @AUTHOR:xq
 * @DATE:2014-8-26 上午10:06:30
 **/
package com.xuexiang.util.net.asynchttp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.xuexiang.util.net.asynchttp.toolbox.CustomLog;
import com.xuexiang.util.net.asynchttp.toolbox.HttpError;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpSuccess;

/*******************************************
 * @COMPANY:
 * @CLASS:HttpFileRequest
 * @DESCRIPTION:
 * @AUTHOR:xx
 * @VERSION:v1.0
 *******************************************/
public class HttpFileRequest extends HttpRequest {
	private HashMap<String, Object> params;

	private HttpSuccess<String> mHttpSuccess;

	/**
	 * create a instance HttpRequest.
	 * 
	 * @param cls
	 * @param map
	 * @param httpSuccess
	 * @param httpError
	 */
	public HttpFileRequest(String url, HashMap<String, Object> map, HttpSuccess<String> httpSuccess, HttpError httpError) {
		mUrlString = url;
		this.params = map;
		mHttpSuccess = httpSuccess;
		mHttpError = httpError;

	}

	/**
	 * @description: 获取参数
	 * @author:xx
	 * @return:RequestParams
	 * @return
	 */

	@Override
	public RequestParams getParams() {
		RequestParams requestParams = new RequestParams();
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (params.get(key) instanceof File) {
				File tFile = (File) params.get(key);
				CustomLog.d("%s %d  %s %s  %s", "is file", tFile.length(), key, tFile.exists(), tFile.getAbsolutePath());
				try {
					requestParams.put(key, tFile, "application/octet-stream");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				String val = "";
				if (params.get(key) != null) {
					val = params.get(key).toString();
				}
				stringBuilder.append("&" + key + "=" + val);
				requestParams.put(key, params.get(key));
			}
		}
		CustomLog.d("提交参数为:", stringBuilder.toString());
		return requestParams;
	}

	/**
	 * @description:
	 * @author:xx
	 * @return:String
	 * @return
	 */

	@Override
	public String getUrlString() {
		return mUrlString;
	}

	/**
	 * @description:失败后调用
	 * @author:xx
	 * @return:void
	 * @param arg3
	 */

	@Override
	public void onFailure(Throwable throwable) {
		if (mHttpError != null)
			mHttpError.onError(throwable);
	}

	/**
	 * @description:成功后调用
	 * @author:xx
	 * @return:void
	 * @param result
	 */

	@Override
	public void onSuccess(String result) {
		CustomLog.d("结果是=%s", result);
		try {
			if (mHttpSuccess != null)
				mHttpSuccess.onSuccess(result);
		} catch (JsonSyntaxException e) {
			if (mHttpError != null)
				mHttpError.onError(e);
		}
	}

}
