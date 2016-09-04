package com.xuexiang.util.net.okhttp.callback;

import okhttp3.Response;

import org.json.JSONObject;

public abstract class JSONObjectCallBack extends Callback<JSONObject>{
	public static final String JSONObjectData = "data";
	@Override
	public JSONObject parseNetworkResponse(Response response, int id)
			throws Exception {
		String string = response.body().string();
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSONObjectData, string);
		return jsonObject;
	}

}
