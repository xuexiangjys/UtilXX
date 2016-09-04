
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class JsonUtil {
	
	public static <T> T fromRequest(String body, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(body, classOfT);
	}

	public static String toJson(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	
	/**
	 * 将一个对象转换为json，并且保存到本地的临时文件
	 * @param src
	 * @param typeOfSrc
	 * @return
	 * @throws IOException
	 *//*
	public static File toJson(Object src,Type typeOfSrc,ExamineInfo exaInfo) throws IOException{
		String jsonPath = LocalDataUtil.LOCAL_DATA_PATH_ALREADY_CAPTURE
		+ exaInfo.getRepGuid() + "/";
		String jsonName=DateUtil.convertTimeToFileName(exaInfo.getCaptureTime(), ".json");
		
		File jsonFile=new File(jsonPath,jsonName);
		JsonWriter write=new JsonWriter(new BufferedWriter(new FileWriter(jsonFile)));
		
		Gson gson=new Gson();
		gson.toJson(src, typeOfSrc, write);
		write.flush();
		write.close();
		
		return jsonFile;
	}*/
}
