/**
 * @FILE:HttpJsonObjectRequest.java
 * @AUTHOR:hc
 * @DATE:2014-7-30 下午3:12:35
 **/
package com.xuexiang.util.net.asynchttp;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.xuexiang.util.net.asynchttp.toolbox.CustomLog;
import com.xuexiang.util.net.asynchttp.toolbox.HttpError;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpSuccess;

/*******************************************
 * @COMPANY:
 * @CLASS:HttpJsonObjectRequest
 * @DESCRIPTION:
 * @AUTHOR:xx
 * @VERSION:v1.0
 * @DATE:2014-7-30 下午3:12:35
 *******************************************/
public class HttpJSONObjectRequest extends HttpRequest {

	private HttpSuccess<JSONObject> mHttpSuccess;
	public static final String JSONObjectData = "data";
	/**
	 * create a instance HttpRequest.
	 * 
	 * @param cls
	 * @param map
	 * @param httpSuccess
	 * @param httpError
	 */
	public HttpJSONObjectRequest(String url, HashMap<String, String> map, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		mUrlString = url;
		mParams = map;
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
		CustomLog.d("提交参数为: %s", "=" + stringBuilder.toString());
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
	 * @param arg2
	 */

	@Override
	public void onSuccess(String result) {
		CustomLog.d("结果是：%s", result);
		try {
			JSONObject jsonObject = new JSONObject();
	        jsonObject.put(JSONObjectData, result);
			if (mHttpSuccess != null)
				mHttpSuccess.onSuccess(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
			if (mHttpError != null)
				mHttpError.onError(new Throwable());
		}
	}

}
