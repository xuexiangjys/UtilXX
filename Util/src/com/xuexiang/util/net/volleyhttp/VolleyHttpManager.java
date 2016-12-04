package com.xuexiang.util.net.volleyhttp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.xuexiang.app.SampleApplication;


/**  
 * 创建时间：2016-5-16 下午9:13:59  
 * 项目名称：VolleyDemo  
 * @author xuexiang
 * 文件名称：HttpGsonRequest.java  
 **/
public class VolleyHttpManager<T> {
	
	private HashMap<String, String> mParams;
	
	private Class<T> mClass;
	
	public VolleyHttpManager(Class<T> cls) {
		mClass = cls;
	}
	
	public VolleyHttpManager(String mode, String Json, Class<T> cls) {
		mClass = cls;
		setParams(mode, Json);
	}
	
	public void setParams(String mode, String Json) {
		mParams = new HashMap<String, String>();
		mParams.put("mode", mode);
		mParams.put("Json", Json);
	}
	
	public HashMap<String, String> getParams() {
		return mParams;
	}
	
	
/****************************************************返回的是一个类对象************************************************************************************************************************/	
	/**
	 * 获取返回为实体类的请求方法
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public void httpClassRequest(int requestMode, String url, Listener<T> listener,ErrorListener errorlistener) {
		SampleApplication.getVolleyRequestQueue().add(getHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public GsonRequest<T> getHttpRequest(int requestMode, String url, Listener<T> listener,ErrorListener errorlistener) {
        return getTagHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
	
	/**
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @param Tag 请求标志
	 * @return
	 */
	public GsonRequest<T> getTagHttpRequest(int requestMode, String url, Listener<T> listener, ErrorListener errorlistener, Object Tag) {
		GsonRequest<T> httpRequest;
		if(requestMode == Method.POST) {
			httpRequest = new GsonRequest<T>(requestMode, url, mClass, listener, errorlistener){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					return mParams;
				}
			};
		} else {
			httpRequest = new GsonRequest<T>(requestMode, url, mClass, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
        return httpRequest;
	}
	
/****************************************************返回的是一个字符串************************************************************************************************************************/		
	
	
	/**
	 * 获取返回为实体类的请求方法
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public void httpStringRequest(int requestMode, String url, Listener<String> listener,ErrorListener errorlistener) {
		SampleApplication.getVolleyRequestQueue().add(getStringHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public StringRequest getStringHttpRequest(int requestMode, String url, Listener<String> listener,ErrorListener errorlistener) {
        return getTagStringHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
		
	/**
	 * 获取返回为String字符串的请求方法
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public StringRequest getTagStringHttpRequest(int requestMode, String url, Listener<String> listener, ErrorListener errorlistener, Object Tag) {
		StringRequest httpRequest;
		if(requestMode == Method.POST) {
			httpRequest = new StringRequest(requestMode, url, listener, errorlistener){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					return mParams;
				}
			};
		} else {
			httpRequest = new StringRequest(requestMode, url, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
		return httpRequest;
	}
	
	
/****************************************************返回的是一个JSONObject************************************************************************************************************************/		
	
	/**
	 * 获取返回为实体类的请求方法
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public void httpJsonObjectRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener) {
		SampleApplication.getVolleyRequestQueue().add(getJsonObjectHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectHttpRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener) {
        return getTagJsonObjectHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
	
	/**
	 * 获取返回为String字符串的请求方法
	 * @param requestMode 请求方式
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public MyJsonObjectRequest getTagJsonObjectHttpRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener, Object Tag) {
		MyJsonObjectRequest httpRequest;
		if(requestMode == Method.POST) {
			String params = appendParameter(url, mParams);  		
			httpRequest = new MyJsonObjectRequest(requestMode, url, params, listener, errorlistener);
		} else {
			httpRequest = new MyJsonObjectRequest(requestMode, url, null, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
		return httpRequest;
	}
	
	private String appendParameter(String url,Map<String,String> params){  
        Uri uri = Uri.parse(url);  
        Uri.Builder builder = uri.buildUpon();  
        for(Map.Entry<String,String> entry:params.entrySet()){  
            builder.appendQueryParameter(entry.getKey(),entry.getValue());  
        }  
        return builder.build().getQuery();  
    }  
	
}
