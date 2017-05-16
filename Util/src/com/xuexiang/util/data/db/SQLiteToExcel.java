package com.xuexiang.util.data.db;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.log.LogHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

/**
 * SQLite数据库转化为Excel文件
 * @author XUE 
 */
public class SQLiteToExcel {

	private static Handler mHandler = new Handler(Looper.getMainLooper());

	/**
	 * SQLite数据库
	 */
	private SQLiteDatabase database;
	/**
	 * 输出的根目录
	 */
	private String mExportPath;
	/**
	 * Excel操作对象
	 */
	private HSSFWorkbook mWorkbook;

	/**
	 * 默认输出的根目录
	 */
	public final static String DEFAULT_EXPORTPATH = Environment.getExternalStorageDirectory().toString() + File.separator;

	public SQLiteToExcel(Context context, String dbPath) {
		this(context, dbPath, DEFAULT_EXPORTPATH);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param dbPath
	 *            数据库的路径
	 * @param exportPath
	 *            导出数据库的根目录
	 */
	public SQLiteToExcel(Context context, String dbPath, String exportPath) {
		mExportPath = exportPath;
		try {
			database = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
		} catch (Exception e) {
			LogHelper.saveExceptionStackInfo(e);
		}
	}

	/**
	 * 获取所有表的表名
	 * 
	 * @return
	 */
	private List<String> getAllTablesName() {
		List<String> tables = new ArrayList<String>();
		Cursor cursor = database.rawQuery("select name from sqlite_master where type='table' order by name", null);
		while (cursor.moveToNext()) {
			tables.add(cursor.getString(0));
		}
		cursor.close();
		return tables;
	}

	/**
	 * 获取表的所有列名
	 * 
	 * @param table
	 *            表名
	 * @return
	 */
	private List<String> getColumnsName(String table) {
		List<String> columns = new ArrayList<String>();
		Cursor cursor = database.rawQuery("PRAGMA table_info(" + table + ")", null);
		while (cursor.moveToNext()) {
			columns.add(cursor.getString(1));
		}
		cursor.close();
		return columns;
	}

	/**
	 * 导出表的数据到Excel
	 * 
	 * @param tables
	 * @param fileName
	 * @throws Exception
	 */
	private void exportTablesDataToExcel(List<String> tables, String fileName) throws Exception {
		mWorkbook = new HSSFWorkbook();
		for (int i = 0; i < tables.size(); i++) {
			if (!tables.get(i).equals("android_metadata")) {
				HSSFSheet sheet = mWorkbook.createSheet(tables.get(i));
				createSheet(tables.get(i), sheet);
			}
		}
		File file = new File(mExportPath, fileName);
		FileOutputStream fos = new FileOutputStream(file);
		mWorkbook.write(fos);
		fos.flush();
		fos.close();
		mWorkbook.close();
		database.close();
	}

	/**
	 * 导出单表
	 * 
	 * @param table
	 *            表名
	 * @param fileName
	 *            导出的Excel文件名
	 * @param listener
	 */
	public void exportSingleTable(String table, String fileName, ExportListener listener) {
		List<String> tables = new ArrayList<String>();
		tables.add(table);
		startExportTables(tables, fileName, listener);
	}

	/**
	 * 导出多表
	 * 
	 * @param tables
	 *            表名集合
	 * @param fileName
	 *            导出的Excel文件名
	 * @param listener
	 */
	public void exportTables(List<String> tables, String fileName, ExportListener listener) {
		startExportTables(tables, fileName, listener);
	}

	/**
	 * 导出数据库下的所有表
	 * 
	 * @param fileName
	 *            导出的Excel文件名
	 * @param listener
	 */
	public void exportAllTables(String fileName, ExportListener listener) {
		List<String> tables = getAllTablesName();
		startExportTables(tables, fileName, listener);
	}

	/**
	 * 开始导出表至Excel
	 * 
	 * @param tables
	 *            表名集合
	 * @param fileName
	 *            导出的Excel文件名
	 * @param listener
	 */
	public void startExportTables(final List<String> tables, final String fileName, final ExportListener listener) {
		if (listener != null) {
			listener.onStart();
		}
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				try {
					exportTablesDataToExcel(tables, fileName);
					if (listener != null) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								listener.onCompleted(mExportPath + fileName);
							}
						});
					}
				} catch (final Exception e) {
					if (database != null && database.isOpen()) {
						database.close();
					}
					if (listener != null) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								listener.onError(e);
							}
						});
					}
				}
			}
		});
	}

	/**
	 * 根据数据库表名创建工作簿
	 * 
	 * @param table
	 *            表名
	 * @param sheet
	 *            工作簿对象
	 */
	private void createSheet(String table, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		List<String> columns = getColumnsName(table);
		for (int i = 0; i < columns.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(columns.get(i)));
		}
		insertItemToSheet(table, sheet, columns);
	}

	/**
	 * 插入表的数据内容到excel中
	 * 
	 * @param table
	 *            表名
	 * @param sheet
	 *            工作簿对象
	 * @param columns
	 *            列名集合
	 */
	private void insertItemToSheet(String table, HSSFSheet sheet, List<String> columns) {
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		Cursor cursor = database.rawQuery("select * from " + table, null);
		cursor.moveToFirst();
		int n = 1;
		while (!cursor.isAfterLast()) {
			HSSFRow row = sheet.createRow(n);
			for (int j = 0; j < columns.size(); j++) {
				HSSFCell cell = row.createCell(j);
				if (cursor.getType(j) == Cursor.FIELD_TYPE_BLOB) {
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) j, n, (short) (j + 1), n + 1);
					anchor.setAnchorType(3);
					patriarch.createPicture(anchor, mWorkbook.addPicture(cursor.getBlob(j), HSSFWorkbook.PICTURE_TYPE_JPEG));
				} else {
					cell.setCellValue(new HSSFRichTextString(cursor.getString(j)));
				}
			}
			n++;
			cursor.moveToNext();
		}
		cursor.close();
	}

	/**
	 * 导出监听
	 * 
	 * @author xx
	 * 
	 */
	public interface ExportListener {
		/**
		 * 开始导出
		 */
		void onStart();

		/**
		 * 导出完毕
		 * 
		 * @param filePath
		 *            导出的Excel文件名路径
		 */
		void onCompleted(String filePath);

		/**
		 * @param e
		 */
		void onError(Exception e);
	}

}
