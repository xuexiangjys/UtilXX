/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

/**
 * 文件操作工具类
 * <p/>
 * <ul>
 * Read or write file
 * <li>{@link #readFile(String)} read file</li>
 * <li>{@link #readFileToList(String)} read file to string list</li>
 * <li>{@link #writeFile(String, String, boolean)} write file from String</li>
 * <li>{@link #writeFile(String, String)} write file from String</li>
 * <li>{@link #writeFile(String, List, boolean)} write file from String List</li>
 * <li>{@link #writeFile(String, List)} write file from String List</li>
 * <li>{@link #writeFile(String, InputStream)} write file</li>
 * <li>{@link #writeFile(String, InputStream, boolean)} write file</li>
 * <li>{@link #writeFile(File, InputStream)} write file</li>
 * <li>{@link #writeFile(File, InputStream, boolean)} write file</li>
 * </ul>
 * <ul>
 * Operate file
 * <li>{@link #copyFile(String, String)}</li>
 * <li>{@link #getFileExtension(String)}</li>
 * <li>{@link #getFileName(String)}</li>
 * <li>{@link #getFileNameWithoutExtension(String)}</li>
 * <li>{@link #getFileSize(String)}</li>
 * <li>{@link #deleteFile(String)}</li>
 * <li>{@link #isFileExist(String)}</li>
 * <li>{@link #isFolderExist(String)}</li>
 * <li>{@link #makeFolders(String)}</li>
 * <li>{@link #makeDirs(String)}</li>
 * </ul>
 * 
 * @author jingle1267@163.com
 */
public final class FileUtils {
	private static final String TAG = "FileUtils";
	public static final long GB = 1073741824; // 1024 * 1024 * 1024
	public static final long MB = 1048576; // 1024 * 1024
	public static final long KB = 1024;

	public static final int ICON_TYPE_ROOT = 1;
	public static final int ICON_TYPE_FOLDER = 2;
	public static final int ICON_TYPE_MP3 = 3;
	public static final int ICON_TYPE_MTV = 4;
	public static final int ICON_TYPE_JPG = 5;
	public static final int ICON_TYPE_FILE = 6;

	public static final String MTV_REG = "^.*\\.(mp4|3gp)$";
	public static final String MP3_REG = "^.*\\.(mp3|wav)$";
	public static final String JPG_REG = "^.*\\.(gif|jpg|png)$";

	private static final String FILENAME_REGIX = "^[^\\/?\"*:<>\\]{1,255}$";

	/**
	 * Don't let anyone instantiate this class.
	 */
	private FileUtils() {
		throw new Error("Do not need instantiate!");
	}

	/**
	 * 递归删除文件和文件夹
	 * 
	 * @param file
	 *            要删除的根目录
	 */
	public static void DeleteFile(File file) {
		if (file.exists() == false) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					DeleteFile(f);
				}
				file.delete();
			}
		}
	}

	/**
	 * 重命名文件和文件夹
	 * 
	 * @param file
	 *            File对象
	 * @param newFileName
	 *            新的文件名
	 * @return 执行结果
	 */
	public static boolean renameFile(File file, String newFileName) {
		if (newFileName.matches(FILENAME_REGIX)) {
			File newFile = null;
			if (file.isDirectory()) {
				newFile = new File(file.getParentFile(), newFileName);
			} else {
				String temp = newFileName + file.getName().substring(file.getName().lastIndexOf('.'));
				newFile = new File(file.getParentFile(), temp);
			}
			if (file.renameTo(newFile)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 文件大小获取
	 * 
	 * @param file
	 *            File对象
	 * @return 文件大小字符串
	 */
	public static String getFileSize(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int length = fis.available();
			if (length >= GB) {
				return String.format("%.2f GB", length * 1.0 / GB);
			} else if (length >= MB) {
				return String.format("%.2f MB", length * 1.0 / MB);
			} else {
				return String.format("%.2f KB", length * 1.0 / KB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "未知";
	}

	/**
	 * 使用系统程序打开文件
	 * 
	 * @param activity
	 *            Activity
	 * @param file
	 *            File
	 * @throws Exception
	 */
	public static void openFile(Activity activity, File file) throws Exception {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), getMimeType(file, activity));
		activity.startActivity(intent);
	}

	/**
	 * 获取以后缀名为ID的值
	 * 
	 * @param file
	 *            File
	 * @param activity
	 *            Activity
	 * @return MimeType字符串
	 * @throws Exception
	 */
	public static String getMimeType(File file, Activity activity) throws Exception {

		String name = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
		int id = activity.getResources().getIdentifier(activity.getPackageName() + ":string/" + name, null, null);

		// 特殊处理
		if ("class".equals(name)) {
			return "application/octet-stream";
		}
		if ("3gp".equals(name)) {
			return "video/3gpp";
		}
		if ("nokia-op-logo".equals(name)) {
			return "image/vnd.nok-oplogo-color";
		}
		if (id == 0) {
			throw new Exception("未找到分享该格式的应用");
		}
		return activity.getString(id);
	}

	/**
	 * 用于递归查找文件夹下面的符合条件的文件
	 * 
	 * @param folder
	 *            文件夹
	 * @param filter
	 *            文件过滤器
	 * @return 符合条件的文件List
	 */
	public static List<HashMap<String, Object>> recursionFolder(File folder, FileFilter filter) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 获得文件夹下的所有目录和文件集合
		File[] files = folder.listFiles();
		/** 如果文件夹下没内容,会返回一个null **/
		// 判断适配器是否为空
		if (filter != null) {
			files = folder.listFiles(filter);
		}
		// 找到合适的文件返回
		if (files != null) {
			for (int m = 0; m < files.length; m++) {
				File file = files[m];
				if (file.isDirectory()) {
					// 是否递归调用
					list.addAll(recursionFolder(file, filter));

				} else {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("file", file);
					// 设置图标种类
					if (file.getAbsolutePath().toLowerCase().matches(MP3_REG)) {
						map.put("iconType", 3);
					} else if (file.getAbsolutePath().toLowerCase().matches(MTV_REG)) {
						map.put("iconType", 4);
					} else if (file.getAbsolutePath().toLowerCase().matches(JPG_REG)) {
						map.put("iconType", 5);
					} else {
						map.put("iconType", 6);
					}
					list.add(map);
				}
			}
		}
		return list;
	}

	/**
	 * 资源管理器,查找该文件夹下的文件和目录
	 * 
	 * @param folder
	 *            文件夹
	 * @param filter
	 *            文件过滤器
	 * @return 符合条件的List
	 */
	public static List<HashMap<String, Object>> unrecursionFolder(File folder, FileFilter filter) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 如果是SD卡路径,不添加父路径
		if (!folder.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("file", folder.getParentFile());
			map.put("iconType", ICON_TYPE_ROOT);
			list.add(map);
		}
		// 获得文件夹下的所有目录和文件集合
		File[] files = folder.listFiles();
		/** 如果文件夹下没内容,会返回一个null **/
		// 判断适配器是否为空
		if (filter != null) {
			files = folder.listFiles(filter);
		}
		if (files != null && files.length > 0) {
			for (File p : files) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("file", p);
				// 设置图标种类
				if (p.isDirectory()) {
					map.put("iconType", ICON_TYPE_FOLDER);
				} else {
					if (p.getAbsolutePath().toLowerCase().matches(MP3_REG)) {
						map.put("iconType", ICON_TYPE_MP3);
					} else if (p.getAbsolutePath().toLowerCase().matches(MTV_REG)) {
						map.put("iconType", ICON_TYPE_MTV);
					} else if (p.getAbsolutePath().toLowerCase().matches(JPG_REG)) {
						map.put("iconType", ICON_TYPE_JPG);
					} else {
						map.put("iconType", ICON_TYPE_FILE);
					}
				}
				// 添加
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 示例:"^.*\\.(mp3|mp4|3gp)$"
	 * 
	 * @param reg
	 *            目前允许取值 REG_MTV, REG_MP3, REG_JPG三种
	 * @return 文件过滤器
	 */
	public static FileFilter getFileFilter(final String reg, boolean isdir) {
		if (isdir) {
			return new FileFilter() {
				@Override
				public boolean accept(File pathname) {

					return pathname.getAbsolutePath().toLowerCase().matches(reg) || pathname.isDirectory();
				}
			};
		} else {
			return new FileFilter() {
				@Override
				public boolean accept(File pathname) {

					return pathname.getAbsolutePath().toLowerCase().matches(reg) && pathname.isFile();
				}
			};
		}
	}

	public final static String FILE_EXTENSION_SEPARATOR = ".";

	/**
	 * read file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charsetName
	 *            The name of a supported {@link java.nio.charset.Charset
	 *            </code>charset<code>}
	 * @return if file not exist, return null, else return content of file
	 * @throws RuntimeException
	 *             if an error occurs while operator BufferedReader
	 */
	public static StringBuilder readFile(String filePath, String charsetName) {
		File file = new File(filePath);
		StringBuilder fileContent = new StringBuilder("");
		if (!file.isFile()) {
			return null;
		}

		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
			reader = new BufferedReader(is);
			String line;
			while ((line = reader.readLine()) != null) {
				if (!fileContent.toString().equals("")) {
					fileContent.append("\r\n");
				}
				fileContent.append(line);
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * write file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            内容
	 * @param append
	 *            is append, if true, write to the end of file, else clear
	 *            content of file and write into it
	 * @return return false if content is empty, true otherwise
	 * @throws RuntimeException
	 *             if an error occurs while operator FileWriter
	 */
	public static boolean writeFile(String filePath, String content, boolean append) {
		if (TextUtils.isEmpty(content)) {
			return false;
		}

		FileWriter fileWriter = null;
		try {
			makeDirs(filePath);
			fileWriter = new FileWriter(filePath, append);
			fileWriter.write(content);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * write file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param contentList
	 *            内容List
	 * @param append
	 *            is append, if true, write to the end of file, else clear
	 *            content of file and write into it
	 * @return return false if contentList is empty, true otherwise
	 * @throws RuntimeException
	 *             if an error occurs while operator FileWriter
	 */
	public static boolean writeFile(String filePath, List<String> contentList, boolean append) {
		if (contentList == null || contentList.size() < 1) {
			return false;
		}

		FileWriter fileWriter = null;
		try {
			makeDirs(filePath);
			fileWriter = new FileWriter(filePath, append);
			int i = 0;
			for (String line : contentList) {
				if (i++ > 0) {
					fileWriter.write("\r\n");
				}
				fileWriter.write(line);
			}
			fileWriter.close();
			return true;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * write file, the string will be written to the begin of the file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            内容
	 * @return 执行结果
	 */
	public static boolean writeFile(String filePath, String content) {
		return writeFile(filePath, content, false);
	}

	/**
	 * write file, the string list will be written to the begin of the file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param contentList
	 *            内容List
	 * @return 执行结果
	 */
	public static boolean writeFile(String filePath, List<String> contentList) {
		return writeFile(filePath, contentList, false);
	}

	/**
	 * write file, the bytes will be written to the begin of the file
	 * 
	 * @param filePath
	 *            文件路径
	 * @param stream
	 *            InputStream
	 * @return 执行结果
	 * @see {@link #writeFile(String, InputStream, boolean)}
	 */
	public static boolean writeFile(String filePath, InputStream stream) {
		return writeFile(filePath, stream, false);
	}

	/**
	 * write file
	 * 
	 * @param filePath
	 *            the file to be opened for writing.
	 * @param stream
	 *            the input stream
	 * @param append
	 *            if <code>true</code>, then bytes will be written to the end of
	 *            the file rather than the beginning
	 * @return return true
	 * @throws RuntimeException
	 *             if an error occurs while operator FileOutputStream
	 */
	public static boolean writeFile(String filePath, InputStream stream, boolean append) {
		return writeFile(filePath != null ? new File(filePath) : null, stream, append);
	}

	/**
	 * write file, the bytes will be written to the begin of the file
	 * 
	 * @param file
	 *            File对象
	 * @param stream
	 *            InputStream
	 * @return
	 * @see {@link #writeFile(File, InputStream, boolean)}
	 */
	public static boolean writeFile(File file, InputStream stream) {
		return writeFile(file, stream, false);
	}

	/**
	 * write file
	 * 
	 * @param file
	 *            the file to be opened for writing.
	 * @param stream
	 *            the input stream
	 * @param append
	 *            if <code>true</code>, then bytes will be written to the end of
	 *            the file rather than the beginning
	 * @return return true
	 * @throws RuntimeException
	 *             if an error occurs while operator FileOutputStream
	 */
	public static boolean writeFile(File file, InputStream stream, boolean append) {
		OutputStream o = null;
		try {
			makeDirs(file.getAbsolutePath());
			o = new FileOutputStream(file, append);
			byte data[] = new byte[1024];
			int length = -1;
			while ((length = stream.read(data)) != -1) {
				o.write(data, 0, length);
			}
			o.flush();
			return true;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (o != null) {
				try {
					o.close();
					stream.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * copy file
	 * 
	 * @param sourceFilePath
	 *            源文件路径
	 * @param destFilePath
	 *            目标文件路径
	 * @return 执行结果
	 * @throws RuntimeException
	 *             if an error occurs while operator FileOutputStream
	 */
	public static boolean copyFile(String sourceFilePath, String destFilePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(sourceFilePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		}
		return writeFile(destFilePath, inputStream);
	}

	/**
	 * 输入流转byte[]
	 * 
	 * @param inStream
	 *            InputStream
	 * @return Byte数组
	 */
	public static final byte[] input2byte(InputStream inStream) {
		if (inStream == null)
			return null;
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return swapStream.toByteArray();
	}

	/**
	 * read file to string list, a element of list is a line
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charsetName
	 *            编码方式 The name of a supported {@link java.nio.charset.Charset
	 *            </code>charset<code>}
	 * @return if file not exist, return null, else return content of file
	 * @throws RuntimeException
	 *             if an error occurs while operator BufferedReader
	 */
	public static List<String> readFileToList(String filePath, String charsetName) {
		File file = new File(filePath);
		List<String> fileContent = new ArrayList<String>();
		if (!file.isFile()) {
			return null;
		}

		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
			reader = new BufferedReader(is);
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileContent.add(line);
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * get file name from path, not include suffix
	 * <p/>
	 * 
	 * <pre>
	 *      getFileNameWithoutExtension(null)               =   null
	 *      getFileNameWithoutExtension("")                 =   ""
	 *      getFileNameWithoutExtension("   ")              =   "   "
	 *      getFileNameWithoutExtension("abc")              =   "abc"
	 *      getFileNameWithoutExtension("a.mp3")            =   "a"
	 *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
	 *      getFileNameWithoutExtension("c:\\")              =   ""
	 *      getFileNameWithoutExtension("c:\\a")             =   "a"
	 *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
	 *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
	 *      getFileNameWithoutExtension("/home/admin")      =   "admin"
	 *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
	 * </pre>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return file name from path, not include suffix
	 * @see
	 */
	public static String getFileNameWithoutExtension(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		int filePosi = filePath.lastIndexOf(File.separator);
		if (filePosi == -1) {
			return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
		}
		if (extenPosi == -1) {
			return filePath.substring(filePosi + 1);
		}
		return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
	}

	/**
	 * get file name from path, include suffix
	 * <p/>
	 * 
	 * <pre>
	 *      getFileName(null)               =   null
	 *      getFileName("")                 =   ""
	 *      getFileName("   ")              =   "   "
	 *      getFileName("a.mp3")            =   "a.mp3"
	 *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
	 *      getFileName("abc")              =   "abc"
	 *      getFileName("c:\\")              =   ""
	 *      getFileName("c:\\a")             =   "a"
	 *      getFileName("c:\\a.b")           =   "a.b"
	 *      getFileName("c:a.txt\\a")        =   "a"
	 *      getFileName("/home/admin")      =   "admin"
	 *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
	 * </pre>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return file name from path, include suffix
	 */
	public static String getFileName(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
	}

	/**
	 * get folder name from path
	 * <p/>
	 * 
	 * <pre>
	 *      getFolderName(null)               =   null
	 *      getFolderName("")                 =   ""
	 *      getFolderName("   ")              =   ""
	 *      getFolderName("a.mp3")            =   ""
	 *      getFolderName("a.b.rmvb")         =   ""
	 *      getFolderName("abc")              =   ""
	 *      getFolderName("c:\\")              =   "c:"
	 *      getFolderName("c:\\a")             =   "c:"
	 *      getFolderName("c:\\a.b")           =   "c:"
	 *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
	 *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
	 *      getFolderName("/home/admin")      =   "/home"
	 *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
	 * </pre>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件夹名称
	 */
	public static String getFolderName(String filePath) {

		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
	}

	/**
	 * get suffix of file from path
	 * <p/>
	 * 
	 * <pre>
	 *      getFileExtension(null)               =   ""
	 *      getFileExtension("")                 =   ""
	 *      getFileExtension("   ")              =   "   "
	 *      getFileExtension("a.mp3")            =   "mp3"
	 *      getFileExtension("a.b.rmvb")         =   "rmvb"
	 *      getFileExtension("abc")              =   ""
	 *      getFileExtension("c:\\")              =   ""
	 *      getFileExtension("c:\\a")             =   ""
	 *      getFileExtension("c:\\a.b")           =   "b"
	 *      getFileExtension("c:a.txt\\a")        =   ""
	 *      getFileExtension("/home/admin")      =   ""
	 *      getFileExtension("/home/admin/a.txt/b")  =   ""
	 *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
	 * </pre>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static String getFileExtension(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		int filePosi = filePath.lastIndexOf(File.separator);
		if (extenPosi == -1) {
			return "";
		}
		return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
	}

	/**
	 * Creates the directory named by the trailing filename of this file,
	 * including the complete directory path required to create this directory. <br/>
	 * <br/>
	 * <ul>
	 * <strong>Attentions:</strong>
	 * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
	 * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
	 * </ul>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return true if the necessary directories have been created or the target
	 *         directory already exists, false one of the directories can not be
	 *         created.
	 *         <ul>
	 *         <li>if {@link FileUtils#getFolderName(String)} return null,
	 *         return false</li>
	 *         <li>if target directory already exists, return true</li>
	 *         <li>return {@link java.io.File#makeFolder}</li>
	 *         </ul>
	 */
	public static boolean makeDirs(String filePath) {
		String folderName = getFolderName(filePath);
		if (TextUtils.isEmpty(folderName)) {
			return false;
		}

		File folder = new File(folderName);
		return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
	}

	/**
	 * @param filePath
	 *            文件路径
	 * @return 执行结果
	 * @see #makeDirs(String)
	 */
	public static boolean makeFolders(String filePath) {
		return makeDirs(filePath);
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
	 * delete file or directory
	 * <ul>
	 * <li>if path is null or empty, return true</li>
	 * <li>if path not exist, return true</li>
	 * <li>if path exist, delete recursion. return true</li>
	 * <ul>
	 * 
	 * @param path
	 *            文件路径
	 * @return 是否删除成功
	 */
	public static boolean deleteFile(String path) {
		if (TextUtils.isEmpty(path)) {
			return true;
		}

		File file = new File(path);
		if (!file.exists()) {
			return true;
		}
		if (file.isFile()) {
			return file.delete();
		}
		if (!file.isDirectory()) {
			return false;
		}
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				f.delete();
			} else if (f.isDirectory()) {
				deleteFile(f.getAbsolutePath());
			}
		}
		return file.delete();
	}

	/**
	 * get file size
	 * <ul>
	 * <li>if path is null or empty, return -1</li>
	 * <li>if path exist and it is a file, return file size, else return -1</li>
	 * <ul>
	 * 
	 * @param path
	 *            文件路径
	 * @return returns the length of this file in bytes. returns -1 if the file
	 *         does not exist.
	 */
	public static long getFileSize(String path) {
		if (TextUtils.isEmpty(path)) {
			return -1;
		}

		File file = new File(path);
		return (file.exists() && file.isFile() ? file.length() : -1);
	}

	/**
	 * 把uri转为File对象
	 * 
	 * @param activity
	 *            Activity
	 * @param uri
	 *            文件Uri
	 * @return File对象
	 */
	public static File uri2File(Activity activity, Uri uri) {
		if (Build.VERSION.SDK_INT < 11) {
			// 在API11以下可以使用：managedQuery
			String[] proj = { MediaStore.Images.Media.DATA };
			@SuppressWarnings("deprecation")
			Cursor actualimagecursor = activity.managedQuery(uri, proj, null, null, null);
			int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			actualimagecursor.moveToFirst();
			String img_path = actualimagecursor.getString(actual_image_column_index);
			return new File(img_path);
		} else {
			// 在API11以上：要转为使用CursorLoader,并使用loadInBackground来返回
			String[] projection = { MediaStore.Images.Media.DATA };
			CursorLoader loader = new CursorLoader(activity, uri, projection, null, null, null);
			Cursor cursor = loader.loadInBackground();
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return new File(cursor.getString(column_index));
		}
	}

	/**
	 * 判断SD是否可以
	 * 
	 * @return
	 */
	public static boolean isSdcardExist() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 创建目录
	 * 
	 * @param context
	 * @param dirName
	 *            文件夹名称
	 * @return
	 */
	public static File createFileDir(Context context, String dirName) {
		String filePath;
		// 如SD卡已存在，则存储；反之存在data目录下
		if (isSdcardExist()) {
			// SD卡路径
			filePath = Environment.getExternalStorageDirectory() + File.separator + dirName;
		} else {
			filePath = context.getCacheDir().getPath() + File.separator + dirName;
		}
		File destDir = new File(filePath);
		if (!destDir.exists()) {
			boolean isCreate = destDir.mkdirs();
			Log.i("FileUtils", filePath + " has created. " + isCreate);
		}
		return destDir;
	}

	/**
	 * 创建根目录
	 * 
	 * @param path
	 *            目录路径
	 */
	public static void createDirFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 创建的文件
	 */
	public static File createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
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
	 * 移动目录
	 * 
	 * @param srcDirName
	 *            源目录完整路径
	 * @param destDirName
	 *            目的目录完整路径
	 * @return 目录移动成功返回true，否则返回false
	 */
	public static boolean moveDirectory(String srcDirName, String destDirName) {
		File srcDir = new File(srcDirName);
		if (!srcDir.exists() || !srcDir.isDirectory()) {
			return false;
		}

		File destDir = new File(destDirName);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}

		/**
		 * 如果是文件则移动，否则递归移动文件夹。删除最终的空源文件夹 注意移动文件夹时保持文件夹的树状结构
		 */
		File[] sourceFiles = srcDir.listFiles();
		for (File sourceFile : sourceFiles) {
			if (sourceFile.isFile()) {
				moveFile(sourceFile.getAbsolutePath(), destDir.getAbsolutePath());
			} else if (sourceFile.isDirectory()) {
				moveDirectory(sourceFile.getAbsolutePath(), destDir.getAbsolutePath() + File.separator + sourceFile.getName());
			}
		}
		return srcDir.delete();
	}

	/**
	 * 校验文件MD5码
	 */
	public static boolean checkMD5(String md5, File updateFile) {
		if (TextUtils.isEmpty(md5) || updateFile == null) {
			Log.e(TAG, "MD5 string empty or updateFile null");
			return false;
		}

		String calculatedDigest = getFileMD5(updateFile);
		if (calculatedDigest == null) {
			Log.e(TAG, "calculatedDigest null");
			return false;
		}
		return calculatedDigest.equalsIgnoreCase(md5);
	}

	/**
	 * 校验文件MD5码
	 */
	public static boolean checkMD5(String md5, InputStream is) {
		if (TextUtils.isEmpty(md5) || is == null) {
			Log.e(TAG, "MD5 string empty or updateFile null");
			return false;
		}

		String calculatedDigest = getFileMD5(is);
		if (calculatedDigest == null) {
			Log.e(TAG, "calculatedDigest null");
			return false;
		}
		return calculatedDigest.equalsIgnoreCase(md5);
	}

	/**
	 * 获取文件MD5码
	 */
	public static String getFileMD5(File updateFile) {
		InputStream is;
		try {
			is = new FileInputStream(updateFile);
		} catch (FileNotFoundException e) {
			Log.e(TAG, "Exception while getting FileInputStream", e);
			return null;
		}

		return getFileMD5(is);
	}

	/**
	 * 获取文件MD5码
	 */
	public static String getFileMD5(InputStream is) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG, "Exception while getting digest", e);
			return null;
		}

		byte[] buffer = new byte[8192];
		int read;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			String output = bigInt.toString(16);
			// Fill to 32 chars
			output = String.format("%32s", output).replace(' ', '0');
			return output;
		} catch (IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.e(TAG, "Exception on closing MD5 input stream", e);
			}
		}
	}

	/**
	 * 读取raw目录的文件内容
	 * 
	 * @param context
	 *            内容上下文
	 * @param rawFileId
	 *            raw文件名id
	 * @return
	 */
	public static String readRawValue(Context context, int rawFileId) {
		String result = "";
		try {
			InputStream is = context.getResources().openRawResource(rawFileId);
			int len = is.available();
			byte[] buffer = new byte[len];
			is.read(buffer);
			result = new String(buffer, "UTF-8");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取assets目录的文件内容
	 * 
	 * @param context
	 *            内容上下文
	 * @param fileName
	 *            文件名称，包含扩展名
	 * @return
	 */
	public static String readAssetsValue(Context context, String fileName) {
		String result = "";
		try {
			InputStream is = context.getResources().getAssets().open(fileName);
			int len = is.available();
			byte[] buffer = new byte[len];
			is.read(buffer);
			result = new String(buffer, "UTF-8");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取assets目录的文件内容
	 * 
	 * @param context
	 *            内容上下文
	 * @param fileName
	 *            文件名称，包含扩展名
	 * @return
	 */
	public static List<String> readAssetsListValue(Context context, String fileName) {
		List<String> list = new ArrayList<String>();
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				list.add(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取SharedPreferences文件内容
	 * 
	 * @param context
	 *            上下文
	 * @param fileNameNoExt
	 *            文件名称（不用带后缀名）
	 * @return
	 */
	public static Map<String, ?> readSharePerface(Context context, String fileNameNoExt) {
		SharedPreferences preferences = context.getSharedPreferences(fileNameNoExt, Context.MODE_PRIVATE);
		return preferences.getAll();
	}

	/**
	 * 写入SharedPreferences文件内容
	 * 
	 * @param context
	 *            上下文
	 * @param fileNameNoExt
	 *            文件名称（不用带后缀名）
	 * @param values
	 *            需要写入的数据Map(String,Boolean,Float,Long,Integer)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void writeSharePerface(Context context, String fileNameNoExt, Map<String, ?> values) {
		try {
			SharedPreferences preferences = context.getSharedPreferences(fileNameNoExt, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			for (Iterator iterator = values.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<String, ?> entry = (Map.Entry<String, ?>) iterator.next();
				if (entry.getValue() instanceof String) {
					editor.putString(entry.getKey(), (String) entry.getValue());
				} else if (entry.getValue() instanceof Boolean) {
					editor.putBoolean(entry.getKey(), (Boolean) entry.getValue());
				} else if (entry.getValue() instanceof Float) {
					editor.putFloat(entry.getKey(), (Float) entry.getValue());
				} else if (entry.getValue() instanceof Long) {
					editor.putLong(entry.getKey(), (Long) entry.getValue());
				} else if (entry.getValue() instanceof Integer) {
					editor.putInt(entry.getKey(), (Integer) entry.getValue());
				}
			}
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入应用程序包files目录下文件
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 */
	public static void write(Context context, String fileName, String content) {
		write(context, fileName, content.getBytes());
	}

	/**
	 * 写入应用程序包files目录下文件
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 */
	public static void write(Context context, String fileName, byte[] content) {
		try {

			FileOutputStream outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outStream.write(content);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入应用程序包files目录下文件
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名称
	 * @param modeType
	 *            文件写入模式（Context.MODE_PRIVATE、Context.MODE_APPEND、Context.
	 *            MODE_WORLD_READABLE、Context.MODE_WORLD_WRITEABLE）
	 * @param content
	 *            文件内容
	 */
	public static void write(Context context, String fileName, byte[] content, int modeType) {
		try {

			FileOutputStream outStream = context.openFileOutput(fileName, modeType);
			outStream.write(content);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定编码将内容写入目标文件
	 * 
	 * @param target
	 *            目标文件
	 * @param content
	 *            文件内容
	 * @param encoding
	 *            写入文件编码
	 * @throws Exception
	 */
	public static void write(File target, String content, String encoding) throws IOException {
		BufferedWriter writer = null;
		try {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, false), encoding));
			writer.write(content);

		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * 指定目录写入文件内容
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param content
	 *            文件内容
	 * @throws IOException
	 */
	public static void write(String filePath, byte[] content) throws IOException {
		FileOutputStream fos = null;

		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			fos.write(content);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param inputStream
	 *            下载文件的字节流对象
	 * @param filePath
	 *            文件的存放路径 (带文件名称)
	 * @throws IOException
	 */
	public static File write(InputStream inputStream, String filePath) throws IOException {
		OutputStream outputStream = null;
		// 在指定目录创建一个空文件并获取文件对象
		File mFile = new File(filePath);
		if (!mFile.getParentFile().exists())
			mFile.getParentFile().mkdirs();
		try {
			outputStream = new FileOutputStream(mFile);
			byte buffer[] = new byte[4 * 1024];
			int lenght = 0;
			while ((lenght = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, lenght);
			}
			outputStream.flush();
			return mFile;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				inputStream.close();
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}

			} catch (IOException e) {
			}
		}
	}

	/**
	 * 指定目录写入文件内容
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param bitmap
	 *            文件内容
	 * @throws IOException
	 */
	public static void saveAsJPEG(Bitmap bitmap, String filePath) throws IOException {
		FileOutputStream fos = null;

		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 指定目录写入文件内容
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param bitmap
	 *            文件内容
	 * @throws IOException
	 */
	public static void saveAsPNG(Bitmap bitmap, String filePath) throws IOException {
		FileOutputStream fos = null;

		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	private static final String LINE_SEP = System.getProperty("line.separator");

	/**
	 * 根据文件路径获取文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件
	 */
	public static File getFileByPath(String filePath) {
		return isSpace(filePath) ? null : new File(filePath);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return {@code true}: 存在<br>
	 *         {@code false}: 不存在
	 */
	public static boolean isFileExists(String filePath) {
		return isFileExists(getFileByPath(filePath));
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 *            文件
	 * @return {@code true}: 存在<br>
	 *         {@code false}: 不存在
	 */
	public static boolean isFileExists(File file) {
		return file != null && file.exists();
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param newName
	 *            新名称
	 * @return {@code true}: 重命名成功<br>
	 *         {@code false}: 重命名失败
	 */
	public static boolean rename(String filePath, String newName) {
		return rename(getFileByPath(filePath), newName);
	}

	/**
	 * 重命名文件
	 * 
	 * @param file
	 *            文件
	 * @param newName
	 *            新名称
	 * @return {@code true}: 重命名成功<br>
	 *         {@code false}: 重命名失败
	 */
	public static boolean rename(File file, String newName) {
		// 文件为空返回false
		if (file == null)
			return false;
		// 文件不存在返回false
		if (!file.exists())
			return false;
		// 新的文件名为空返回false
		if (isSpace(newName))
			return false;
		// 如果文件名没有改变返回true
		if (newName.equals(file.getName()))
			return true;
		File newFile = new File(file.getParent() + File.separator + newName);
		// 如果重命名的文件已存在返回false
		return !newFile.exists() && file.renameTo(newFile);
	}
	
	private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

	/**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) return false;
        // 文件存在并且删除失败返回false
        if (file.exists() && !file.delete()) return false;
        // 创建目录失败返回false
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制或移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @param isMove      是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    private static boolean copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove) {
        return copyOrMoveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), isMove);
    }

    /**
     * 复制或移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @param isMove  是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    private static boolean copyOrMoveDir(File srcDir, File destDir, boolean isMove) {
        if (srcDir == null || destDir == null) return false;
        // 如果目标目录在源目录中则返回false，看不懂的话好好想想递归怎么结束
        // srcPath : F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res
        // destPath: F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res1
        // 为防止以上这种情况出现出现误判，须分别在后面加个路径分隔符
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;
        if (destPath.contains(srcPath)) return false;
        // 源文件不存在或者不是目录则返回false
        if (!srcDir.exists() || !srcDir.isDirectory()) return false;
        // 目标目录不存在返回false
        if (!createOrExistsDir(destDir)) return false;
        File[] files = srcDir.listFiles();
        for (File file : files) {
            File oneDestFile = new File(destPath + file.getName());
            if (file.isFile()) {
                // 如果操作失败返回false
                if (!copyOrMoveFile(file, oneDestFile, isMove)) return false;
            } else if (file.isDirectory()) {
                // 如果操作失败返回false
                if (!copyOrMoveDir(file, oneDestFile, isMove)) return false;
            }
        }
        return !isMove || deleteDir(srcDir);
    }

    /**
     * 复制或移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @param isMove       是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    private static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove) {
        return copyOrMoveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), isMove);
    }

    /**
     * 复制或移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param isMove   是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    private static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove) {
        if (srcFile == null || destFile == null) return false;
        // 源文件不存在或者不是文件则返回false
        if (!srcFile.exists() || !srcFile.isFile()) return false;
        // 目标文件存在且是文件则返回false
        if (destFile.exists() && destFile.isFile()) return false;
        // 目标目录不存在返回false
        if (!createOrExistsDir(destFile.getParentFile())) return false;
        try {
            return FileIOUtils.writeFileFromIS(destFile, new FileInputStream(srcFile), false)
                    && !(isMove && !deleteFile(srcFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyDir(String srcDirPath, String destDirPath) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    /**
     * 复制目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyDir(File srcDir, File destDir) {
        return copyOrMoveDir(srcDir, destDir, false);
    }

    /**
     * 复制文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, false);
    }

    /**
     * 移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveDir(String srcDirPath, String destDirPath) {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    /**
     * 移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveDir(File srcDir, File destDir) {
        return copyOrMoveDir(srcDir, destDir, true);
    }

    /**
     * 移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, true);
    }

    /**
     * 删除目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir(String dirPath) {
        return deleteDir(getFileByPath(dirPath));
    }

    /**
     * 删除目录
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) return false;
        // 目录不存在返回true
        if (!dir.exists()) return true;
        // 不是目录返回false
        if (!dir.isDirectory()) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!deleteFile(file)) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(String dirPath) {
        return deleteFilesInDir(getFileByPath(dirPath));
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(File dir) {
        if (dir == null) return false;
        // 目录不存在返回true
        if (!dir.exists()) return true;
        // 不是目录返回false
        if (!dir.isDirectory()) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!deleteFile(file)) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return true;
    }
}
