package com.xuexiang.util.data.db.ormlite.default_;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;

/**
 * 数据库操作接口
 * @author xx
 */
public interface IDataBase {

	/**
	 * 数据库创建
	 * @param database
	 * @param connectionSource
	 */
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource);
	
	/**
	 * 数据库升级和降级操作
	 * @param database
	 * @param connectionSource 
	 * @param oldVersion 旧版本
	 * @param newVersion 新版本
	 */
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion);
}
