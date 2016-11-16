package com.xuexiang.util.net.volleyhttp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

/**  
 * 创建时间：2016-5-17 下午8:19:34  
 * 项目名称：VolleyDemo  
 * @author xuexiang
 * 文件名称：JsonObjectRequest.java  
 **/
public class MyJsonObjectRequest extends JsonRequest<JSONObject> {  
	public static final String JSONObjectData = "data";
    private Map<String, String> headers;
    private Map<String, String> params;
  
    /** 
     * 这里的method必须是Method.POSJSONObject，也就是必须带参数。 
     * 如果不想带参数，可以用JsonObjectRequest，给它构造参数传null。GEJSONObject方式请求。 
     * @param stringRequest 格式应该是 "key1=value1&key2=value2" 
     */  
  
    public MyJsonObjectRequest(int method, String url, String stringRequest,  
                             Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {  
        super(method, url, stringRequest , listener, errorListener);  
    }  
    
    /**
     * @param builder requestBuilder
     */
    public MyJsonObjectRequest(RequestBuilder builder) {
        super(builder.method, builder.url, builder.requestBody, builder.successListener, builder.errorListener);
        params = builder.params;
        headers = builder.headers;
    }
  
   
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }
    
    @Override  
    public String getBodyContentType() {  
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();  
    }  
    
    @Override  
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {  
        try {  
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, HTTP.UTF_8));    
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSONObjectData, jsonString);
            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));  
        } catch (UnsupportedEncodingException e) {  
            return Response.error(new ParseError(e));  
        } catch (JSONException je) {  
            return Response.error(new ParseError(je));  
        }  
    }
    
    /**
     * requestBuilder  使用方法参见httpClientRequest
     */
    public static class RequestBuilder{
        private int method = Method.GET;
        private String url;
        private Listener<JSONObject> successListener;
        private Response.ErrorListener errorListener;
        private Map<String, String> headers;
        private Map<String, String> params;
        private String requestBody;

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder successListener(Listener<JSONObject> successListener) {
            this.successListener = successListener;
            return this;
        }

        public RequestBuilder errorListener(ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }

        public RequestBuilder post() {
            this.method = Method.POST;
            return this;
        }

        public RequestBuilder addHeader(String key, String value) {
            if (headers == null)
                headers = new HashMap<String, String>();
            headers.put(key, value);
            return this;
        }

        public RequestBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RequestBuilder params(Map<String, String> params) {
            post();
            this.params = params;
            return this;
        }

        public RequestBuilder addParams(String key, String value) {
            if (params == null) {
                params = new HashMap<String, String>();
                post();
            }
            params.put(key, value);
            return this;
        }

        public RequestBuilder toJSONString() {
            if (params == null) {
            	requestBody = null;
            } else {
            	requestBody = appendParameter(url, params);
            }
            return this;
        }

        public RequestBuilder JSONString(String jsonBody) {
            requestBody = jsonBody;
            post();
            return this;
        }
        
        private String appendParameter(String url, Map<String,String> params) {  
            Uri uri = Uri.parse(url);  
            Uri.Builder builder = uri.buildUpon();  
            for(Map.Entry<String,String> entry:params.entrySet()){  
                builder.appendQueryParameter(entry.getKey(),entry.getValue());  
            }  
            return builder.build().getQuery();  
        }  

        public MyJsonObjectRequest build() {
            return new MyJsonObjectRequest(this);
        }
    }
}
