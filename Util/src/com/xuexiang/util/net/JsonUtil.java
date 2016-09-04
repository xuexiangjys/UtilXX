
package com.xuexiang.util.net;


import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	
	public static <T> T fromRequest(String body, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(body, classOfT);
	}
	
	public static <T> T fromRequest(String body, Type typeOfT) {
		Gson gson = new Gson();
		return gson.fromJson(body, typeOfT);
	}
	
	public static <T> List<T> fromListRequest(String body) {
		Gson gson = new Gson();
		List<T> list = gson.fromJson(body, new TypeToken<List<T>>() {}.getType());
		return list;
	}

	public static String toJson(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	
	public static JSONObject toJSONObject(Object src) {
		Gson gson = new Gson();
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(gson.toJson(src));
		} catch (JSONException e) {
			e.printStackTrace();
		}  
		return jsonObject;
	}
}
