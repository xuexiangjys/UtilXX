/**
 * Copyright 2013 Mani Selvaraj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.util.net.volleyhttp.uploadfile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.thirdparty.volley.NetworkResponse;
import com.thirdparty.volley.Request;
import com.thirdparty.volley.Request.Method;
import com.thirdparty.volley.Response;
import com.thirdparty.volley.Response.ErrorListener;
import com.thirdparty.volley.Response.Listener;
import com.thirdparty.volley.toolbox.HttpHeaderParser;
import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;

/**
 * MultipartRequest - To handle the large file uploads.
 * Extended from JSONRequest. You might want to change to StringRequest based on your response type.
 * @author Mani Selvaraj
 *
 */
public class MultiPartStringRequest extends Request<String> implements MultiPartRequest{

	private final Listener<String> mListener;
	/* To hold the parameter name and the File to upload */
	private Map<String,File> fileUploads = new HashMap<String,File>();
	
	/* To hold the parameter name and the string content to upload */
	private Map<String,String> stringUploads = new HashMap<String,String>();
	
    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartStringRequest(int method, String url, Listener<String> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }
    
    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartStringRequest(int method, String url, ProgressListener progressListener, Listener<String> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
//        mProgressListener = progressListener;
    }

    /**
     * @param builder requestBuilder
     */
    public MultiPartStringRequest(RequestBuilder builder) {
    	this(builder.method, builder.url, builder.progressListener, builder.successListener, builder.errorListener);
    	fileUploads = builder.files;
    	stringUploads = builder.params;
    }


    public void addFileUpload(String param,File file) {
    	fileUploads.put(param,file);
    }
    
    public void addStringUpload(String param,String content) {
    	stringUploads.put(param,content);
    }
    
    /**
     * 要上传的文件
     */
    public Map<String, File> getFileUploads() {
    	return fileUploads;
    }
    
    /**
     * 要上传的参数
     */
    public Map<String, String> getStringUploads() {
    	return stringUploads;
    }
    

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

	@Override
	protected void deliverResponse(String response) {
		if(mListener != null) {
			mListener.onResponse(response);
		}
	}
	
	/**
	 * 空表示不上传
	 */
    public String getBodyContentType() {
        return null;
    }
    
    /**
     * requestBuilder  使用方法参见httpClientRequest
     */
    public static class RequestBuilder {
        private int method = Method.GET;
        private String url;
        private ProgressListener progressListener;
        private Listener<String> successListener;
        private Response.ErrorListener errorListener;
        private Map<String, File> files;
        private Map<String, String> params;

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }
        
        public RequestBuilder progressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
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

        public RequestBuilder addFile(String key, File value) {
            if (files == null)
            	files = new HashMap<String, File>();
            files.put(key, value);
            return this;
        }

        public RequestBuilder Files(Map<String, File> files) {
            this.files = files;
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

        public MultiPartStringRequest build() {
            return new MultiPartStringRequest(this);
        }
    }
}
