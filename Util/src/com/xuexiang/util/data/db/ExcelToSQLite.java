package com.xuexiang.util.data.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.log.LogHelper;

/**
 * 
 * 将Excel表格中的数据导入到SQLite中
 * 
 * @author XUE
 * 
 */
public class ExcelToSQLite {

	private Handler mHandler = new Handler(Looper.getMainLooper());

	private Context mContext;
	/**
	 * SQLite数据库
	 */
	private SQLiteDatabase mDatabase;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param dbPath
	 *            数据库路径
	 */
	public ExcelToSQLite(Context context, String dbPath) {
		mContext = context;
		try {
			mDatabase = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
		} catch (Exception e) {
			LogHelper.saveExceptionStackInfo(e);
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param database
	 *            SQLite数据库
	 */
	public ExcelToSQLite(Context context, SQLiteDatabase database) {
		mContext = context;
		mDatabase = database;
	}

	/**
	 * 从Asset文件中读取Excel导入
	 * 
	 * @param assetFileName
	 * @param listener
	 */
	public void importFromAsset(final String assetFileName, final ImportListener listener) {
		try {
			InputStream in = mContext.getAssets().open(assetFileName);
			excuteImportExcelToSQLite(in, listener);
		} catch (IOException e) {
			onError(listener, e);
		}
	}

	/**
	 * 从文件中读取Excel导入
	 * 
	 * @param filePath
	 * @param listener
	 */
	public void importFromFile(String filePath, ImportListener listener) {
		File file = new File(filePath);
		try {
			InputStream in = new FileInputStream(file);
			excuteImportExcelToSQLite(in, listener);
		} catch (Exception e) {
			onError(listener, e);
		}

	}

	/**
	 * 导入excel数据到SQLite中
	 * 
	 * @param in
	 * @param listener
	 */
	public void importExcelToSQLite(final InputStream in, final ImportListener listener) {
		if (listener != null) {
			listener.onStart();
		}
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			@Override
			public void run() {
				excuteImportExcelToSQLite(in, listener);
			}
		});
	}

	/**
	 * 执行插入数据库的操作
	 * 
	 * @param in
	 *            数据流
	 * @param listener
	 *            导入监听
	 */
	private void excuteImportExcelToSQLite(final InputStream in, final ImportListener listener) {
		try {
			importToSQLite(in);
			if (listener != null) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						listener.onCompleted(mDatabase.getPath());
					}
				});
			}
		} catch (final Exception e) {
			if (mDatabase != null && mDatabase.isOpen()) {
				mDatabase.close();
			}
			onError(listener, e);
		}
	}

	private void onError(final ImportListener listener, final Exception e) {
		if (listener != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					listener.onError(e);
				}
			});
		}
	}

	/**
	 * 读取Excel数据流，插入数据库
	 * 
	 * @param in
	 * @throws Exception
	 */
	private void importToSQLite(InputStream in) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			createTable(workbook.getSheetAt(i));
		}
		mDatabase.close();
	}

	/**
	 * 为每一个工作簿创建一张表
	 * 
	 * @param sheet
	 */
	private void createTable(Sheet sheet) {
		StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		createTableSql.append(sheet.getSheetName());
		createTableSql.append("(");
		Iterator<Row> rit = sheet.rowIterator();
		Row rowHeader = rit.next();
		List<String> columns = new ArrayList<String>();
		for (int i = 0; i < rowHeader.getPhysicalNumberOfCells(); i++) {
			createTableSql.append(rowHeader.getCell(i).getStringCellValue());
			if (i == rowHeader.getPhysicalNumberOfCells() - 1) {
				createTableSql.append(" TEXT");
			} else {
				createTableSql.append(" TEXT,");
			}
			columns.add(rowHeader.getCell(i).getStringCellValue());
		}
		createTableSql.append(")");
		mDatabase.execSQL(createTableSql.toString());
		while (rit.hasNext()) {
			Row row = rit.next();
			ContentValues values = new ContentValues();
			for (int n = 0; n < row.getPhysicalNumberOfCells(); n++) {
				if (row.getCell(n).getCellType() == Cell.CELL_TYPE_NUMERIC) {
					values.put(columns.get(n), row.getCell(n).getNumericCellValue());
				} else {
					values.put(columns.get(n), row.getCell(n).getStringCellValue());
				}
			}
			long result = mDatabase.insert(sheet.getSheetName(), null, values);
			if (result < 0) {
				throw new RuntimeException("insert value failed!");
			}
		}
	}

	/**
	 * 导入监听
	 * 
	 * @author XUE
	 * 
	 */
	public interface ImportListener {
		/**
		 * 开始导入
		 */
		void onStart();

		/**
		 * 导入完毕
		 * 
		 * @param dbPath
		 *            导入数据库的路径
		 */
		void onCompleted(String dbPath);

		/**
		 * 出错
		 * 
		 * @param e
		 */
		void onError(Exception e);
	}

}