package com.xuexiang.util.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Environment;
import android.util.Base64;

/**
 * 本地文件的IO操作类
 *
 */
public class LocalFileUtil {

	//根目录
	public static final String LOCAL_DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ "/myutil/";
	
	//日志目录
    public static final String LOG_DIR = LOCAL_DATA_PATH
				+ "/logs/";
    
	//数据库目录
    public static final String DATABASE_PATH = LOCAL_DATA_PATH
				+ "/database/";
		
	//头像路径
	public static final String HEAD_PHOTO_DIR = LOCAL_DATA_PATH
			+ "headphoto/";
	
	//图片收藏保存路径
	public static final String PICTURE_COLLECT_DIR = LOCAL_DATA_PATH
			+ "collect/";
	
	//截屏图片保存地址
	public static final String PICTURE_SCREENSHOT_DIR = LOCAL_DATA_PATH
			+ "screenshot/";
	
	/* 下载包安装路径 */
	public static final String APK_PATH = LOCAL_DATA_PATH + "apk/";
	
	/* 下载文件的路径 */
	public static final String DOWNLOAD_PATH = LOCAL_DATA_PATH + "download/";
	
	
	public static boolean existsSDCard() {
		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {// 如果SD卡存在
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取本地文件的数据
	 * 
	 * @param file
	 * @return
	 */
	public static String getLocalDataByFile(File fileName) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			byte buf[] = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			out.close();

		} catch (IOException e) {

		}

		return out.toString();
	}

	/**
	 * 读取字节文件
	 * 
	 * @param pathName
	 * @return
	 */
	public static byte[] getByteForFile(File inFile) throws IOException{
		FileInputStream in= new FileInputStream(inFile);
		byte[] buf = new byte[in.available()];
		in.read(buf);

		in.close();

		return buf;
	}

	/**
	 * 下载文件到本地路径file中
	 * 
	 * @param file
	 *            保存的路径
	 * @param str
	 *            要保存的字符串
	 * @return
	 */
	public static boolean downFile(File fileName, String str) {
		if (!fileName.getParentFile().exists()) {
			fileName.getParentFile().mkdirs();
		}

		boolean res = true;
		byte[] content = str.getBytes();
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			out.write(content);
			out.close();
		} catch (IOException e) {
			res = false;
			e.printStackTrace();
		}

		return res;
	}

	public static boolean exisFile(File file) {
		boolean exis = true;
		if (!file.exists()) {// 如果本地文件不存在，则下载

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			exis = false;
		}

		return exis;
	}

	
	/**
	 * 从一个目录中删除某个文件
	 * @param file
	 * @param cmd 条件
	 */
	public static boolean deleteFile(String path,String cmd){
		boolean res=false;
		
		File file=new File(path,cmd);
		if(file.exists()){
			res=file.delete();
		}
		
		return res;
	}
	
	/**
	 * 删除整个目录
	 * @param file
	 * @return
	 */
	public static void deleteFile(File file){
		
		if(file.exists()){
			if(file.listFiles()!=null){
				for(File f:file.listFiles()){
					f.delete();
				}
			}
			file.delete();
		}
		
	}
	
	/**
	 * 删除目录中的文件
	 * @param path
	 */
	public static void deleteDirectory(String path){
		File dir=new File(path);
		if(dir.exists()){
			if(dir.listFiles()!=null){
				for(File f:dir.listFiles()){
					if(f.isDirectory()){
						deleteFile(f);
					}else if(f.isFile())
						f.delete();
				}
			}
			
			dir.delete();
		}
		
	}
	

	
	/**
	 * 获取单个文件的json文件
	 * @param path 文件路径
	 * @return
	 */
	public static String getSingleFileData(String path){
		File fil=new File(path); 
		String json=getLocalDataByFile(fil);
		
		return json;
	}
	
	/**
	 * 对一个文件进行Base64编码
	 * @param file
	 * @return
	 */
	public static String encodeToString(File file){
		StringBuffer sb=new StringBuffer();
		if(file.exists()){
			FileInputStream is=null;
			byte[] buffer=new byte[1024];
			try {
				is=new FileInputStream(file);
				int c=0;
				while((c=is.read(buffer))!=-1){
					sb.append(Base64.encodeToString(buffer, 0, c, Base64.DEFAULT));
				}
				
				is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 获取一个目录中最长时间没有被改动的文件
	 * @param file
	 */
	public static File findOldFile(File file){
		File oldFile = file.listFiles()[0];
		long modifiedTime = file.listFiles()[0].lastModified();
		for(File f:file.listFiles()){//找出修改时间最小的文件
			if(f.lastModified() < modifiedTime){
				oldFile=f;
			}
			modifiedTime=f.lastModified();
		}
		
		return oldFile;
	}
	
	/**
	 * 删除一个目录中的所有文件
	 * @param dir
	 */
	public static void deleteFileList(File dir){
		if(dir.exists()&&dir.listFiles().length>0){
			for(File f:dir.listFiles()){
				f.delete();
			}
		}
	}
	
	
	 /** 移动文件
	 * @param src
	 * @param dest
	 */
	public static boolean moveFile(String src, String dest) {
		File srcFolder = new File(src);
		File destFolder = new File(dest);
		if(!destFolder.exists())
			destFolder.mkdirs();
		File newFile = new File(destFolder.getAbsoluteFile() + "/" + srcFolder.getName());
		boolean res = srcFolder.renameTo(newFile);
		return res;
	}
	
	public static boolean saveFile(String text, String path){
		File file = new File(path);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		FileOutputStream out = null;
		boolean result = false;
		try {
			out = new FileOutputStream(file);
			out.write(text.getBytes());
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
		
		return result;
	}
	
	/**
	 * 获取一个文本
	 * @param inputStream
	 * @return
	 */
	public static String readTextFile(InputStream inputStream) {

		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {

			reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			
			String s ="";
			while((s = reader.readLine()) != null){
				sb.append(s + "\r\n");
			}
			

			inputStream.close();
			reader.close();

		} catch (IOException e) {
		}

		return sb.toString();
	}
}
