package com.xuexiang.util.net.volleyhttp;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;



 /**
  * 自定义 请求方法
  * @author yuan
  * http://blog.csdn.net/guolin_blog/article/details/17612763
  * @param <T>
  */
public class GsonRequest<T> extends Request<T> {

	private Listener<T> mListener;
	
	private Gson mGson;
	
	private Class<T> mClass;
	
	/**
	 * 构造函数 ，可以有多个不同参数的，这里就不添加了！
	 * @param method
	 * @param url
	 * @param listener
	 */
	public GsonRequest(int method, String url,Class<T> clazz,Listener<T> listener,ErrorListener errorlistener) {
		super(method, url, errorlistener);
		//初始化 参数
		mGson = new Gson();
		mClass = clazz;
		mListener = listener;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			//将字符流转成字符串，并且设置 字符编码 ，来自响应信息的报文都不信息
			String jsonString=new String(response.data,HttpHeaderParser.parseCharset(response.headers));
		
			//返回信息 使用 gson 直接转 对象，第二个参数 设置编码
			return Response.success(mGson.fromJson(jsonString, mClass),HttpHeaderParser.parseCacheHeaders(response));
		
		} catch (UnsupportedEncodingException e) {
			// 出错的时候，将错误信息重新调出
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		// TODO Auto-generated method stub
       mListener.onResponse(response);
	}

}
