package com.xuexiang.util.net.volleyhttp;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.OkHttpStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

/**
 * DemoApp
 * Created by acer_april
 * on 2015/7/20
 * Description: httpRequest
 */
public class HttpClientRequest {

    private static Context sContext;
    public RequestQueue mRequestQueue;

    private HttpClientRequest() {
        mRequestQueue = getRequestQueue();
    }

    public static HttpClientRequest getInstance(Context context) {
        sContext = context.getApplicationContext();
        return ClientHolder.CLIENT_REQUEST;
    }
    private static class ClientHolder {
        private static final HttpClientRequest CLIENT_REQUEST = new HttpClientRequest();
    }
    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public void cancelAllRequests(String tag) {
        if (getRequestQueue() != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link RequestQueue}
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext(),
                    new OkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queue
     */
    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queuest
     * @param tag     is the tag identifying the request
     */
    public <T> void addRequest(Request<T> request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    
    /****************************************************返回的是一个类对象************************************************************************************************************************/		
	/**
	 * 自定义网络请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
                                   String url, 
                                   String mode, 
                                   String Json,  
                                   Response.Listener<T> listener, 
                                   Response.ErrorListener errorListener) {
	    CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
		                .post()//不设置的话默认GET 但是设置了参数就不需要了。。。
		                .url(url)//url会统一配置到requestUrl类中 必填
		                .addParams("mode", mode)//添加参数1
		                .addParams("Json", Json)//添加参数2
		                .clazz(clazz) //如果设置了返回类型，会自动解析返回model(Gson解析) 如果不设置会直接返回json数据;
		                .successListener(listener)//获取数据成功的listener
		                .errorListener(errorListener)//获取数据异常的listener
		                .build();       
		return request;
	}
	
	/**
	 * 自定义网络请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, String mode, String Json,  Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, mode, Json, listener, errorListener), clazz.getSimpleName());	
	}
	
	/**
	 * 无参数网络请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
						            String url, 
						            Response.Listener<T> listener, 
						            Response.ErrorListener errorListener) {
		CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
						.url(url)//url会统一配置到requestUrl类中 必填
						.clazz(clazz) //如果设置了返回类型，会自动解析返回model(Gson解析) 如果不设置会直接返回json数据;
						.successListener(listener)//获取数据成功的listener
						.errorListener(errorListener)//获取数据异常的listener
						.build();       
		return request;
    }
	
	/**
	 * 无参数网络请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, listener, errorListener), clazz.getSimpleName());	
	}
	
	/**
	 * map参数请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
						            String url, 
						            HashMap<String, String> params,
						            Response.Listener<T> listener, 
						            Response.ErrorListener errorListener) {
		CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
						.url(url)//url会统一配置到requestUrl类中 必填
						.clazz(clazz) //如果设置了返回类型，会自动解析返回model(Gson解析) 如果不设置会直接返回json数据;
						.params(params) //添加参数方法, 适用参数比较多的情况下
						.successListener(listener)//获取数据成功的listener
						.errorListener(errorListener)//获取数据异常的listener
						.build();   
		return request;
    }
	
	/**
	 * map参数请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, HashMap<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, params, listener, errorListener), clazz.getSimpleName());	
	}
	
	/****************************************************返回的是一个类对象的集合************************************************************************************************************************/			
	/**
	 * 自定义网络请求
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
                                   String mode, 
                                   String Json,  
                                   Response.Listener<List<T>> listener, 
                                   Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
		                .post()//不设置的话默认GET 但是设置了参数就不需要了。。。
		                .url(url)//url会统一配置到requestUrl类中 必填
		                .addParams("mode", mode)//添加参数1
		                .addParams("Json", Json)//添加参数2
		                .successListener(listener)//获取数据成功的listener
		                .errorListener(errorListener)//获取数据异常的listener
		                .build();       
		return request;
	}
	
	/**
	 * 自定义网络请求
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassListRequest(String url, String mode, String Json,  Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
						            Response.Listener<List<T>> listener, 
						            Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
						.url(url)//url会统一配置到requestUrl类中 必填
						.successListener(listener)//获取数据成功的listener
						.errorListener(errorListener)//获取数据异常的listener
						.build();       
		return request;
    }
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassListRequest(String url, Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, listener, errorListener));	
	}
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
						            HashMap<String, String> params,
						            Response.Listener<List<T>> listener, 
						            Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
						.url(url)//url会统一配置到requestUrl类中 必填
						.params(params) //添加参数方法, 适用参数比较多的情况下
						.successListener(listener)//获取数据成功的listener
						.errorListener(errorListener)//获取数据异常的listener
						.build();   
		return request;
    }
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public <T> void executeClassListRequest(String url, HashMap<String, String> params, Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, params, listener, errorListener));	
	}
		
	/****************************************************返回的是一个字符串************************************************************************************************************************/			
	/**
	 * 自定义网络请求
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              String mode, String Json, 
			                              Response.Listener<String> listener,
		                                  Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .addParams("mode", mode)//添加参数1
					        .addParams("Json", Json)//添加参数2
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
		return request;		
	}
	
	/**
	 * 自定义网络请求
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public void executeStringRequest(String url, String mode, String Json, Response.Listener<String> listener, Response.ErrorListener errorListener) {       
		addRequest(getStringRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              Response.Listener<String> listener,
                                          Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
        return request;			
	}
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public void executeStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {       
		addRequest(getStringRequest(url, listener, errorListener));		
	}
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              HashMap<String, String> params, 
			                              Response.Listener<String> listener,
                                          Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .params(params)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
        return request;		
	}
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param httpSuccess 请求成功的响应
	 * @param httpError 请求失败的响应
	 * @return
	 */
	public void executeStringRequest(String url, HashMap<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		addRequest(getStringRequest(url, params, listener, errorListener));		
	}
	
	/****************************************************返回的是一个JSONObject************************************************************************************************************************/			
	/**
	 * 自定义网络请求
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                         String mode, 
			                         String Json, 
			                         Response.Listener<JSONObject> listener,
                                     Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .addParams("mode", mode)//添加参数1
		                    .addParams("Json", Json)//添加参数2
		                    .toJSONString() //将参数转换为String
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();       
		return request;		
	}
	
	/**
	 * 自定义网络请求
	 * @param clazz 返回类类名
	 * @param url 请求服务地址
	 * @param mode 请求方法
	 * @param Json json字符串
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public void executeJsonObjectRequest(String url, String mode, String Json, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                                        Response.Listener<JSONObject> listener, 
			                                        Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();
        return request;			
	}
	
	/**
	 * 无参数网络请求
	 * @param url 请求服务地址
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public void executeJsonObjectRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, listener, errorListener));	
	}
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                                        HashMap<String, String> params, 
			                                        Response.Listener<JSONObject> listener, 
			                                        Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .params(params)
					        .toJSONString()
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();
        return request;		
	}
	
	/**
	 * map参数请求
	 * @param url 请求服务地址
	 * @param params 请求参数集合
	 * @param listener 请求成功的响应
	 * @param errorListener 请求失败的响应
	 * @return
	 */
	public void executeJsonObjectRequest(String url, HashMap<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, params, listener, errorListener));	
	}
	
}
