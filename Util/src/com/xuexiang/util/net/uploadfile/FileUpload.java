package com.xuexiang.util.net.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;

public class FileUpload {
	/**
	 * 杩炴帴瓒呮椂
	 */
	public static final int CONNECTION_TIME_OUT = 1000 * 30;
	
	
	/**
	 * 鍝嶅簲瓒呮椂
	 */
	public static final int SO_TIMEOUT = 1000 *30 ;
	
	
	/**
	 * 榛樿缂栫爜
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	

	/**
	 * 鏃犲弬鏁版枃浠朵笂浼�
	 * @param url
	 * @param list
	 * @param encoding
	 * @param listener
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, List<File> list, ProgressListener listener) throws Exception{
		HttpParams params = new BasicHttpParams();												//瀹炰緥鍖朠ost鍙傛暟瀵硅薄
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIME_OUT);  				//璁剧疆璇锋眰瓒呮椂
		HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT); 	
        HttpClient client = new DefaultHttpClient(params);										//瀹炰緥鍖栦竴涓繛鎺ュ璞�
        HttpPost post = new HttpPost(url);														//鏍规嵁Post鍙傛暟,瀹炰緥鍖栦竴涓狿ost瀵硅薄
        
        CustomMultipartEntity entity = new CustomMultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,
                null, Charset.forName(DEFAULT_ENCODING));								//瀹炰緥鍖栬姹傚疄浣�,璇锋眰姝ｆ枃
        entity.setProgressListener(listener);													//璁剧疆杩涘害鍥炶皟鎺ュ彛
        for(int i = 0; i < list.size(); i++){
        	FileBody body = new FileBody(list.get(i));	
        	entity.addPart("file" + i,body);	//琛ㄥ崟瀛楁鍚�		
        }        
        post.setEntity(entity);																	//灏嗚姹傚疄浣撲繚瀛樺埌Post鐨勫疄浣撳弬鏁颁腑
		
		try {
			HttpResponse response = client.execute(post);										//鎵цPost鏂规硶	
			return EntityUtils.toString(response.getEntity(), DEFAULT_ENCODING);						//鏍规嵁瀛楃缂栫爜杩斿洖瀛楃涓�
		} catch (Exception e) {
			throw e;
		} finally{
			client.getConnectionManager().shutdown();											//閲婃斁杩炴帴鎵�鏈夎祫婧�
		}
	}	
	
	/**
	 * 鍙戣捣涓�涓猵ost璇锋眰,浠ヨ〃鍗曟枃浠朵笂浼�,骞惰繑鍥炴湇鍔″櫒杩斿洖鐨勫瓧绗︿覆
	 * @param url		鏈璇锋眰鐨刄RL璺緞
	 * @param map		璇锋眰鐨勫弬鏁�,璇ap闆嗗悎涓璙alue鍊煎彧浼氬彇涓ょ绫诲瀷,String & File<br>
	 * <B>娉ㄦ剰:</B><br> 
	 * <li>1. 濡傛灉Value涓嶆槸File绫诲瀷,鍒欎細璋冪敤Value.toString(),濡傛灉浣犱繚瀛樼殑鏄釜POJO瀵硅薄鐨勮瘽,璇烽噸鍐檛oString()<br>
	 * <li>2. 濡傛灉Value鏄疐ile绫诲瀷,骞朵笖鏂囦欢涓嶅瓨鍦ㄧ殑璇�,浼氭姏鍑� FileNotFoundException 寮傚父<br>
	 * @param listener 璇风敤杩涘害鐩戝惉鍣�
	 * @return			杩斿洖璇锋眰鐨勭粨鏋滃瓧绗︿覆
	 * @throws Exception	鍙兘鎶涘嚭澶氱缃戠粶鎴朓O寮傚父
	 */	
	public static String post(String url, Map<String, Object> map, ProgressListener listener) throws Exception {
		return post(url, map, DEFAULT_ENCODING, listener);
	}
	

	/**
	 * 鍙戣捣涓�涓猵ost璇锋眰,浠ヨ〃鍗曟枃浠朵笂浼�,骞惰繑鍥炴湇鍔″櫒杩斿洖鐨勫瓧绗︿覆
	 * @param url		鏈璇锋眰鐨刄RL璺緞
	 * @param map		璇锋眰鐨勫弬鏁�,璇ap闆嗗悎涓璙alue鍊煎彧浼氬彇涓ょ绫诲瀷,String & File<br>
	 * <B>娉ㄦ剰:</B><br> 
	 * <li>1. 濡傛灉Value涓嶆槸File绫诲瀷,鍒欎細璋冪敤Value.toString(),濡傛灉浣犱繚瀛樼殑鏄釜POJO瀵硅薄鐨勮瘽,璇烽噸鍐檛oString()<br>
	 * <li>2. 濡傛灉Value鏄疐ile绫诲瀷,骞朵笖鏂囦欢涓嶅瓨鍦ㄧ殑璇�,浼氭姏鍑� FileNotFoundException 寮傚父<br>
	 * @param encoding	璇锋眰鍜屾帴鏀跺瓧绗︿覆鐨勭紪鐮� 鏍煎紡,濡傛灉鍥犱负缂栫爜涓嶆纭�,鍒欎細榛樿浣跨敤UTF-8杩涜缂栫爜
	 * @param listener 璇风敤杩涘害鐩戝惉鍣�
	 * @return			杩斿洖璇锋眰鐨勭粨鏋滃瓧绗︿覆
	 * @throws Exception	鍙兘鎶涘嚭澶氱缃戠粶鎴朓O寮傚父
	 */	
	public static String post(String url, Map<String, Object> map, String encoding, ProgressListener listener) throws Exception{
		HttpParams params = new BasicHttpParams();												//瀹炰緥鍖朠ost鍙傛暟瀵硅薄
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIME_OUT);  				//璁剧疆璇锋眰瓒呮椂
        HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT); 									//璁剧疆鍝嶅簲瓒呮椂
        HttpClient client = new DefaultHttpClient(params);										//瀹炰緥鍖栦竴涓繛鎺ュ璞�
        HttpPost post = new HttpPost(url);														//鏍规嵁Post鍙傛暟,瀹炰緥鍖栦竴涓狿ost瀵硅薄
        
        CustomMultipartEntity entity = new CustomMultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,
                null, Charset.forName(DEFAULT_ENCODING));								//瀹炰緥鍖栬姹傚疄浣�,璇锋眰姝ｆ枃
        entity.setProgressListener(listener);													//璁剧疆杩涘害鍥炶皟鎺ュ彛
		if(map != null && !map.isEmpty()){															
			for(Map.Entry < String, Object> entry : map.entrySet()){								//杩唬Map闆嗗悎
				Object obj = entry.getValue();													//鑾峰彇闆嗗悎涓殑鍊�
				ContentBody body = null;
				
				//*鑾峰彇闆嗗悎涓殑Value,濡傛灉褰撳墠鐨刅alue鏄疐ile绫诲瀷,鍒檔ew 涓�涓狥ileBody,濡傛灉鏄瓧绗︿覆绫诲瀷,鍒檔ew涓�涓猄tringBody
				//*骞跺皢璇ュ璞′繚瀛樺埌璇锋眰瀹炰綋涓� 
				 
				if (obj != null) {
					if (obj instanceof File){														
						File file = (File) obj;
						if (file.exists()) {
							body = new FileBody(file);										
						} else {
							throw new FileNotFoundException("File Not Found");
						}
					} else {													
						body = new StringBody(entry.getValue().toString(),Charset.forName(encoding));
					}
					entity.addPart(entry.getKey(), body);										//灏嗘鏂囦繚瀛樺埌璇锋眰瀹炰綋绫讳腑
				}
			}
		}
		post.setEntity(entity);																	//灏嗚姹傚疄浣撲繚瀛樺埌Post鐨勫疄浣撳弬鏁颁腑
		try {
			HttpResponse response = client.execute(post);										//鎵цPost鏂规硶	
			return EntityUtils.toString(response.getEntity(), encoding);						//鏍规嵁瀛楃缂栫爜杩斿洖瀛楃涓�
		} catch (Exception e) {
			throw e;
		} finally{
			client.getConnectionManager().shutdown();											//閲婃斁杩炴帴鎵�鏈夎祫婧�
		}
	}
}
