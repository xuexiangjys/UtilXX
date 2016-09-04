
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;


public class JsonUtil {
	
	public static final int ONE_PAGE_NUM = 10;
	
	public static <T> T fromRequest(String body, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(body, classOfT);
	}

	public static String toJson(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	
	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException
	{
		//获取post参数 
		StringBuffer sb = new StringBuffer() ; 
		InputStreamReader isr = new InputStreamReader(is,"utf-8");   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s=br.readLine())!=null){ 
		sb.append(s) ; 
		} 
		return sb.toString();
	}
}
