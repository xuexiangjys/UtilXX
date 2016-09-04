package com.xuexiang.util.data.db.ormlite.auto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public abstract class BaseDBHelper<T> extends OrmLiteSqliteOpenHelper {

	public final static String DATABASE_NAME = "myAutoutil.db";
	public final static int DATABASE_VERSION = 1;
		
	/**
	 * @param context
	 * @param databaseName 数据库名
	 * @param databaseVersion  数据库版本号
	 * @param idatabase db操作接口
	 */
	public BaseDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public abstract void onCreate(SQLiteDatabase db, ConnectionSource connectionSource);

	@Override
	public abstract void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion);

	// 释放资源
	@Override
	public void close() {		
		super.close();
	}

}
