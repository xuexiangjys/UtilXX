package com.xuexiang.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class AssetsManager {

	private AssetManager mAssetManager;
	public static AssetsManager sInstance;
	//public static final String PRINT_DATA = "printdata";
	
	private AssetsManager(Context context) {
		mAssetManager = context.getAssets();
	}
	
	public static AssetsManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new AssetsManager(c.getApplicationContext());
		}
		return sInstance;
	}
	 
	/**
	 * @param mContext
	 * @param assetpath  asset下的路径
	 * @param sdpath     SDpath下保存路径
	 */
	public void copyFileFromAssetToSD(String assetpath,String sdpath ) {
		
		//循环的读取asset下的文件，并且写入到SD卡
		String[] filenames = null;
		FileOutputStream out = null;  
		InputStream in = null;
		try {
			filenames = mAssetManager.list(assetpath);
			if (filenames.length > 0) {//说明是目录
				//创建目录
				getDirectory(assetpath);
				
				for(String fileName:filenames){
					copyFileFromAssetToSD(assetpath + "/" + fileName, sdpath + "/" + fileName);
				}
			} else {//说明是文件，直接复制到SD卡
				File sdFlie = new File(sdpath);
				String  path = assetpath.substring(0, assetpath.lastIndexOf("/"));
				getDirectory(path);
				
				if (!sdFlie.exists()) {
					sdFlie.createNewFile();
				}
				//将内容写入到文件中
				in = mAssetManager.open(assetpath);
				out = new FileOutputStream(sdFlie);  
				byte[] buffer = new byte[1024];
				int byteCount=0;
				while ((byteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, byteCount);
				}
				out.flush();
				out.close();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//分级建立文件夹
	public void getDirectory(String path){
		//对SDpath进行处理，分层级建立文件夹
	    String[] s = path.split("/");
	    String str = Environment.getExternalStorageDirectory().toString();
	      for (int i = 0; i < s.length; i++) {
	        str = str + "/" + s[i];
	        File file=new File(str);
			if(!file.exists()){
				file.mkdir();
			}
		}
		
	}
	

	public static boolean isDirectoryExist(String path){
		File file = new File(Environment.getExternalStorageDirectory() + "/" + path);
	    if (file.exists()) {
	    	return true;
	    } else {
	    	return false;
	    }
		 
	}
	
	public String getTextFormSrc(String name){
		String str = null;
		
		try {
			InputStream is = mAssetManager.open(name);
			str = streamToString(is);
		} catch (IOException e) {
			Log.d("xuexiang", "获取文本异常："+e.getMessage());
		}
		return str;
	}

	
	/**
	 * 将输入流转换成string
	 * @param is
	 * @return
	 */
	public String streamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is),
				40 * 1024);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

}
