package com.xuexiang.util.net.volleyhttp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 */
public class MyStringRequest extends Request<String> {
    private final Listener<String> mListener;
    private Map<String, String> params;
    private Map<String, String> headers;

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MyStringRequest(int method, String url, Listener<String> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    /**
     * Creates a new GET request.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MyStringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }
    
    /**
     * @param builder requestBuilder
     */
    public MyStringRequest(RequestBuilder builder) {
    	this(builder.method, builder.url, builder.successListener, builder.errorListener);
    	headers = builder.headers;
    	params = builder.params;
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
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
    
    /**
     * requestBuilder  使用方法参见httpClientRequest
     */
    public static class RequestBuilder{
        private int method = Method.GET;
        private String url;
        private Listener<String> successListener;
        private Response.ErrorListener errorListener;
        private Map<String, String> headers;
        private Map<String, String> params;

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder successListener(Listener<String> successListener) {
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

        public MyStringRequest build() {
            return new MyStringRequest(this);
        }
    }
    
}
