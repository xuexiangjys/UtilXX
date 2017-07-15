package com.xuexiang.util.net.volleyhttp;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thirdparty.volley.AuthFailureError;
import com.thirdparty.volley.NetworkResponse;
import com.thirdparty.volley.ParseError;
import com.thirdparty.volley.Request;
import com.thirdparty.volley.Request.Method;
import com.thirdparty.volley.Response;
import com.thirdparty.volley.Response.ErrorListener;
import com.thirdparty.volley.Response.Listener;
import com.thirdparty.volley.VolleyLog;
import com.thirdparty.volley.toolbox.HttpHeaderParser;

public class ClassListRequest<T>  extends Request<List<T>> {

	 /**
     * Default charset for JSON request.
     */
    protected static final String PROTOCOL_CHARSET = "utf-8";
    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private final Gson mGson = new Gson();
    private final Map<String, String> headers;
    private final Response.Listener<List<T>> listener;
    private final String mRequestBody;
    private Map<String, String> params;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url    URL of the request to make
     * @param clazz  Relevant class object, for Gson's reflection
     * @param params Map of request params
     */
    public ClassListRequest(String url, Class<T> clazz, Map<String, String> params,
                         Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.headers = null;
        this.params = params;
        this.listener = listener;
        this.mRequestBody = null;
    }

    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public ClassListRequest(int method, String url, Map<String, String> headers,
                         Map<String, String> params,
                         Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.params = params;
        this.listener = listener;
        this.mRequestBody = null;
    }

    public ClassListRequest(int method, String url, String requestBody, Response.Listener<List<T>> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = null;
        this.params = null;
        this.listener = listener;
        this.mRequestBody = requestBody;
    }

    /**
     * @param builder requestBuilder
     */
    public ClassListRequest(RequestBuilder<T> builder) {
        super(builder.method, builder.url, builder.errorListener);
        headers = builder.headers;
        listener = builder.successListener;
        mRequestBody = builder.requestBody;
        if (mRequestBody != null) {
            params = null;
        } else {
            params = builder.params;
        }
    }

    /**
     * Returns the content type of the POST or PUT body.
     */
    @Override
    public String getBodyContentType() {
        if (mRequestBody == null) {
            return super.getBodyContentType();
        }else{
            return PROTOCOL_CONTENT_TYPE;
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mRequestBody == null) {
            return super.getBody();
        } else {
            try {
                return mRequestBody.getBytes(PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, PROTOCOL_CHARSET);
                return null;
            }
        }

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
    protected void deliverResponse(List<T> response) {
        listener.onResponse(response);
    }

	@Override
    protected Response<List<T>> parseNetworkResponse(NetworkResponse response) {
		try {
			//将字符流转成字符串，并且设置 字符编码 ，来自响应信息的报文都不信息
			String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers, HTTP.UTF_8));
			//返回信息 使用 gson 直接转 对象，第二个参数 设置编码
			Type listType = new TypeToken<List<T>>(){}.getType(); 
		    List<T> list = mGson.fromJson(jsonString, listType);
			return Response.success(list, HttpHeaderParser.parseCacheHeaders(response));
		
		} catch (UnsupportedEncodingException e) {
			// 出错的时候，将错误信息重新调出
			return Response.error(new ParseError(e));
		}
    }

    /**
     * requestBuilder  使用方法参见httpClientRequest
     */
    public static class RequestBuilder<T> {
        private int method = Method.GET;
        private String url;
        private Listener<List<T>> successListener;
        private Response.ErrorListener errorListener;
        private Map<String, String> headers;
        private Map<String, String> params;
        private String requestBody;

        public RequestBuilder<T> url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder<T> successListener(Listener<List<T>> successListener) {
            this.successListener = successListener;
            return this;
        }

        public RequestBuilder<T> errorListener(ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }

        public RequestBuilder<T> post() {
            this.method = Method.POST;
            return this;
        }

        public RequestBuilder<T> addHeader(String key, String value) {
            if (headers == null)
                headers = new HashMap<String, String>();
            headers.put(key, value);
            return this;
        }

        public RequestBuilder<T> headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RequestBuilder<T> params(Map<String, String> params) {
            post();
            this.params = params;
            return this;
        }

        public RequestBuilder<T> addParams(String key, String value) {
            if (params == null) {
                params = new HashMap<String, String>();
                post();
            }
            params.put(key, value);
            return this;
        }

        public RequestBuilder<T> toJSON() {
            final JSONObject jsonObject;
            if (params == null) {
                jsonObject = null;
            } else {
                jsonObject = new JSONObject(params);
            }
            requestBody = (jsonObject == null) ? null : jsonObject.toString();
            return this;
        }

        public RequestBuilder<T> JSONString(String jsonBody) {
            requestBody = jsonBody;
            post();
            return this;
        }

        public ClassListRequest<T> build() {
            return new ClassListRequest<T>(this);
        }
    }


}
