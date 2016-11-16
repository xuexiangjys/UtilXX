package com.xuexiang.util.net.okhttp.request;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import com.xuexiang.util.net.okhttp.callback.Callback;
import com.xuexiang.util.net.okhttp.utils.Exceptions;

/**
 * Created by zhy on 15/11/6.
 */
public abstract class OkHttpRequest
{
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers,int id)
    {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id ;

        if (url == null)
        {
            Exceptions.illegalArgument("url can not be null.");
        }

        initBuilder();
    }



    /**
     * 鍒濆鍖栦竴浜涘熀鏈弬鏁?url , tag , headers
     */
    private void initBuilder()
    {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    protected abstract Request buildRequest(RequestBody requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }


    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    public int getId()
    {
        return id  ;
    }

}
