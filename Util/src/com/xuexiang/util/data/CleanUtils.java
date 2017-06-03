package com.xuexiang.util.data;

import java.io.File;

import android.content.Context;
import android.os.Environment;

import com.xuexiang.util.file.FileSizeUtil;
import com.xuexiang.util.file.FileUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/27
 *     desc  : 清除相关工具类
 * </pre>
 */
public final class CleanUtils {

	private CleanUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 清除内部缓存
	 * <p>
	 * /data/data/com.xxx.xxx/cache
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalCache(Context context) {
		return FileUtils.deleteFilesInDir(context.getCacheDir());
	}

	/**
	 * 清除内部文件
	 * <p>
	 * /data/data/com.xxx.xxx/files
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalFiles(Context context) {
		return FileUtils.deleteFilesInDir(context.getFilesDir());
	}

	/**
	 * 清除内部数据库
	 * <p>
	 * /data/data/com.xxx.xxx/databases
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalDbs(Context context) {
		return FileUtils.deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "databases");
	}

	/**
	 * 根据名称清除数据库
	 * <p>
	 * /data/data/com.xxx.xxx/databases/dbName
	 * </p>
	 * 
	 * @param dbName
	 *            数据库名称
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalDbByName(Context context, String dbName) {
		return context.deleteDatabase(dbName);
	}

	/**
	 * 清除内部SP
	 * <p>
	 * /data/data/com.xxx.xxx/shared_prefs
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalSP(Context context) {
		return FileUtils.deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "shared_prefs");
	}

	/**
	 * 清除外部缓存
	 * <p>
	 * /storage/emulated/0/android/data/com.xxx.xxx/cache
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanExternalCache(Context context) {
		return FileUtils.isSdcardExist() && FileUtils.deleteFilesInDir(context.getExternalCacheDir());
	}

	/**
	 * 清除自定义目录下的文件
	 * 
	 * @param dirPath
	 *            目录路径
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanCustomCache(String dirPath) {
		return FileUtils.deleteFilesInDir(dirPath);
	}

	/**
	 * 清除自定义目录下的文件
	 * 
	 * @param dir
	 *            目录
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanCustomCache(File dir) {
		return FileUtils.deleteFilesInDir(dir);
	}

	/**
	 * 清除本应用所有的数据
	 * 
	 * @param context
	 *            上下文
	 * @return int
	 */
	public static int cleanApplicationData(Context context) {
		// 清除本应用内部缓存数据
		cleanInternalCache(context);
		// 清除本应用外部缓存数据
		cleanExternalCache(context);
		// 清除本应用SharedPreference
		cleanInternalSP(context);
		// 清除本应用files文件
		cleanInternalFiles(context);
		return 1;
	}

	/**
	 * 获取App应用缓存的大小
	 * 
	 * @param context
	 *            上下文
	 * @return String
	 * @throws Exception
	 */
	public static String getAppClearSize(Context context) throws Exception {
		// 获得应用内部缓存大小
		long clearSize = FileSizeUtil.getFileSizes(context.getCacheDir());
		// 获得应用SharedPreference缓存数据大小
		clearSize += FileSizeUtil.getFileSizes(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
		// 获得应用data/data/com.xxx.xxx/files下的内容文件大小
		clearSize += FileSizeUtil.getFileSizes(context.getFilesDir());
		// 获取应用外部缓存大小
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			clearSize += FileSizeUtil.getFileSizes(context.getExternalCacheDir());
		}
		return FileSizeUtil.FormatFileSize(clearSize);
	}

}
