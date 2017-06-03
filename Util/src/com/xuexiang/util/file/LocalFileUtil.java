package com.xuexiang.util.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.xuexiang.util.data.DateUtil;
import com.xuexiang.util.log.LogHelper;

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
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 如果SD卡存在
			return true;
		} else {
			return false;
		}
	}

	// ====================================文件读取=========================================================//
	/**
	 * 获取本地文件的数据
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getLocalDataByFile(File fileName) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			byte buf[] = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			byte[] b = out.toByteArray();
			String s = new String(b);
			return s;
		} catch (IOException e) {
			return "";
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取字节文件
	 * 
	 * @param inFile
	 * @return
	 */
	public static byte[] getByteForFile(File inFile) throws IOException {
		FileInputStream in = new FileInputStream(inFile);
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		return buf;
	}

	/**
	 * 获取单个文件的json文件
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static String getSingleFileData(String path) {
		File file = new File(path);
		String json = getLocalDataByFile(file);
		return json;
	}

	/**
	 * 获取一个文本
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String readTextFile(InputStream inputStream) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			String s = "";
			while ((s = reader.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			inputStream.close();
			reader.close();

		} catch (IOException e) {
			LogHelper.saveExceptionStackInfo(e);
		}
		return sb.toString();
	}

	/**
	 * 文件转化为字节数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getBytesFromFile(File file) {
		byte[] ret = null;
		try {
			if (file == null) {
				return null;
			}
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			in.close();
			out.close();
			ret = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * 以只读方式打开指定文件的方法
	 * 
	 * @param path
	 *            文件路径
	 * @param length
	 *            读取的文件长度
	 * @param pointer
	 *            指针位置
	 * @return
	 */
	public static byte[] randomRead(String path, int length, int pointer) {
		byte[] buff = new byte[length];
		RandomAccessFile raf = null;
		try {
			/**
			 * model各个参数详解 r 代表以只读方式打开指定文件 rw 以读写方式打开指定文件 rws
			 * 读写方式打开，并对内容或元数据都同步写入底层存储设备 rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
			 * 
			 * **/
			raf = new RandomAccessFile(path, "r");
			raf.seek(pointer);// 移动文件指针位置
			raf.read(buff);
			return buff;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	// ====================================文件写操作=========================================================//

	/**
	 * 写文件的方法（将String写入到文件中）
	 * 
	 * @param file
	 *            保存的路径
	 * @param str
	 *            要保存的字符串
	 * @return
	 */
	public static boolean writeFile(File file, String str) {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		boolean res = true;
		byte[] content = str.getBytes();
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(content);
			out.close();
		} catch (IOException e) {
			res = false;
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 把指定内容写入到指定文件中
	 * 
	 * @param file
	 *            要写入文件
	 * @param content
	 *            要写入的内容
	 * @return 操作结果
	 */
	public static boolean writeFile(File file, byte[] content) {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		boolean result = true;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content);
			fos.close();
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 保持文件
	 * 
	 * @param text
	 *            文件内容
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static boolean saveFile(String text, String path) {
		return writeFile(new File(path), text);
	}

	/**
	 * 把字节数组保存为一个文件,覆盖原文件内容
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File bytesToFile(byte[] b, String outputFile) {
		File ret = null;
		BufferedOutputStream stream = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret, false);// fasle代表覆盖原文件内容//true代表追加
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 将解码后的压缩文件保存到本地
	 * 
	 * @param abyte 字节文件
	 * @param filename
	 *            zip文件
	 * @return zip文件的路径
	 */
	public static File writeDataToCacheFile(byte[] abyte, String filename) {
		if (existsSDCard()) {
			File dir = Environment.getExternalStorageDirectory();

			File file = new File(dir, filename);
			FileOutputStream fileoutputstream;
			try {
				fileoutputstream = new FileOutputStream(file);
				fileoutputstream.write(abyte);
				fileoutputstream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file;
		} else {
			return null;
		}
	}

	// ====================================文件移动、删除操作=========================================================//
	/**
	 * 从一个目录中删除某个文件
	 * 
	 * @param path
	 * @param fileName
	 *            文件名
	 */
	public static boolean deleteFile(String path, String fileName) {
		boolean res = false;
		File file = new File(path, fileName);
		if (file.exists()) {
			res = file.delete();
		}
		return res;
	}

	/**
	 * 删除整个目录
	 * 
	 * @param file
	 * @return
	 */
	public static void deleteFiles(File file) {
		if (file != null && file.exists()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					f.delete();
				}
			}
			file.delete();
		}
	}

	/**
	 * 删除目录中的文件
	 * 
	 * @param path
	 */
	public static void deleteDirectory(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					if (f.isDirectory()) {
						deleteFiles(f);
					} else if (f.isFile()) {
						f.delete();
					}
				}
			}
			dir.delete();
		}
	}

	/**
	 * 删除文件
	 */
	public static void deleteFile(File file) {
		if (file != null && file.exists()) {
			File parentFile = file.getParentFile();
			file.delete();
			File[] files = parentFile.listFiles();
			if (files != null && files.length <= 0) {// 当目录中不存在子文件时，删除目录
				parentFile.delete();
			}
		}
	}

	/**
	 * 删除一个目录中的所有文件
	 * 
	 * @param dir
	 */
	public static void deleteFileList(File dir) {
		if (dir != null && dir.exists()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					f.delete();
				}
			}
		}
	}

	/**
	 * 删除目录以及目录中所有文件
	 */
	public static void deleteAllFile(File file) {
		if (!file.exists()) {
			return;
		} else {
			if (file.isFile()) {
				deleteFileSafely(file);
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					deleteFileSafely(file);
					return;
				}
				for (File f : childFile) {
					deleteAllFile(f);
				}
				deleteFileSafely(file);
			}
		}
	}

	/**
	 * 安全删除文件.
	 * @param file
	 * @return
	 */
	public static boolean deleteFileSafely(File file) {
		if (file != null) {
			String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
			File tmp = new File(tmpPath);
			file.renameTo(tmp);
			return tmp.delete();
		}
		return false;
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFileName
	 *            源文件完整路径
	 * @param destDirName
	 *            目的目录完整路径
	 * @return 文件移动成功返回true，否则返回false
	 */
	public static boolean moveFile(String srcFileName, String destDirName) {
		File srcFile = new File(srcFileName);
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}

		File destDir = new File(destDirName);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return srcFile.renameTo(new File(destDirName + File.separator + srcFile.getName()));
	}

	/**
	 * get an asset using ACCESS_STREAMING mode. This provides access to files
	 * that have been bundled with an application as assets -- that is, files
	 * placed in to the "assets" directory.
	 * 
	 * @param context
	 * @param fileName
	 *            The name of the asset to open. This name can be hierarchical.
	 * @return
	 */
	public static String getFileFromAssets(Context context, String fileName, boolean isNeedAddLine) {
		if (context == null || TextUtils.isEmpty(fileName)) {
			return null;
		}

		StringBuilder s = new StringBuilder("");
		try {
			InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
			BufferedReader br = new BufferedReader(in);
			String line;
			if (isNeedAddLine) {
				while ((line = br.readLine()) != null) {
					s.append(line + "\n");
				}
			} else {
				while ((line = br.readLine()) != null) {
					s.append(line);
				}
			}

			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ====================================文件是否存在判断操作=========================================================//
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
	 * Indicates if this file represents a file on the underlying file system.
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 是否存在文件
	 */
	public static boolean isFileExist(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}

		File file = new File(filePath);
		return (file.exists() && file.isFile());
	}

	/**
	 * Indicates if this file represents a directory on the underlying file
	 * system.
	 * 
	 * @param directoryPath
	 *            文件夹路径
	 * @return 文件夹是否存在
	 */
	public static boolean isFolderExist(String directoryPath) {
		if (TextUtils.isEmpty(directoryPath)) {
			return false;
		}

		File dire = new File(directoryPath);
		return (dire.exists() && dire.isDirectory());
	}

	/**
	 * 文件不存在就创建
	 * 
	 * @param filePath
	 */
	public static File isFileNotExistCreate(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return null;
		}
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}

	/**
	 * 获取一个目录中最长时间没有被改动的文件
	 * 
	 * @param file
	 */
	private static File findOldFile(File file) {
		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			File oldFile = files[0];
			long modifiedTime = files[0].lastModified();
			for (File f : files) {// 找出修改时间最小的文件
				if (f.lastModified() < modifiedTime) {
					oldFile = f;
				}
				modifiedTime = f.lastModified();
			}
			return oldFile;
		}
		return null;
	}

	// ====================================文件复杂操作=========================================================//
	/**
	 * 对一个文件进行Base64编码
	 * 
	 * @param file
	 * @return
	 */
	public static String encodeToString(File file) {
		StringBuffer sb = new StringBuffer();
		if (file.exists()) {
			FileInputStream is = null;
			byte[] buffer = new byte[1024];
			try {
				is = new FileInputStream(file);
				int c = 0;
				while ((c = is.read(buffer)) != -1) {
					sb.append(Base64.encodeToString(buffer, 0, c, Base64.DEFAULT));
				}
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 获取心电原始数据文件
	 * 
	 * @param path
	 *            原始数据文件的根目录
	 * @param ecgFileName
	 * @return
	 */
	public static File getOriginalDataPath(String path, String ecgFileName) {
		deleteOldOriginalFile(path);
		File tempFile = new File(path + DateUtil.formatDate(new Date()).replace("-", "_") + "/", ecgFileName);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		return tempFile;
	}

	/**
	 * 删除过时的心电原始数据文件
	 * 
	 * @param path
	 */
	private static void deleteOldOriginalFile(String path) {
		File f = new File(path);
		if (f != null && f.exists()) {
			File[] files = f.listFiles();
			if (files != null && files.length > 0) {
				if (files.length > 3) {
					File oldFile = findOldFile(f);
					if (oldFile != null) {
						deleteFile(oldFile);
					}
				}
			}
		}
	}

	// ====================================文件大小计算=========================================================//
	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isFile()) {
					dirSize += file.length();
				} else if (file.isDirectory()) {
					dirSize += file.length();
					dirSize += getDirSize(file); // 递归调用继续统计
				}
			}
		}
		return dirSize;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
