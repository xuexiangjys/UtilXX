/**
 * @FILE:HuiHenDuoRequest.java
 * @AUTHOR:xq
 * @DATE:2014-7-30 下午3:12:49
 **/
package com.xuexiang.util.net.asynchttp.toolbox;

import java.util.HashMap;

import com.loopj.android.http.RequestParams;

/*******************************************
 * @COMPANY:
 * @CLASS:HttpRequest
 * @DESCRIPTION:
 * @AUTHOR:hc
 * @VERSION:v1.0
 * @DATE:2014-7-30 下午3:12:49
 *******************************************/
public abstract class HttpRequest {

	public HashMap<String, String> mParams;
	public HttpError mHttpError;
	public String mUrlString;
	
	/**
	 * @description: 获取参数
	 * @author:hc
	 * @return:RequestParams
	 * @return
	 */

	public abstract RequestParams getParams();

	/**
	 * @description:
	 * @author:hc
	 * @return:String
	 * @return
	 */

	public abstract String getUrlString();

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg3
	 */

	public abstract void onFailure(Throwable throwable);

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg2
	 */

	public abstract void onSuccess(String result);
}
