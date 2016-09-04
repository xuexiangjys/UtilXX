package com.xuexiang.util.net.okhttp.builder;

import com.xuexiang.util.net.okhttp.OkHttpUtils;
import com.xuexiang.util.net.okhttp.request.OtherRequest;
import com.xuexiang.util.net.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
