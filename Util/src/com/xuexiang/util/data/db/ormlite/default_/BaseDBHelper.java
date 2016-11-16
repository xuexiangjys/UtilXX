package com.xuexiang.util.data.db.ormlite.default_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class BaseDBHelper<T> extends OrmLiteSqliteOpenHelper {
	
	/** 具体某个应用程序数据库的*/
	private IDataBase mIDataBase;

	/**
	 * @param context
	 * @param databaseName 数据库名
	 * @param databaseVersion  数据库版本号
	 * @param idatabase db操作接口
	 */
	public BaseDBHelper(Context context, String databaseName, int databaseVersion, IDataBase idatabase) {
		super(context, databaseName, null, databaseVersion);
		mIDataBase = idatabase;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		mIDataBase.onCreate(db, connectionSource);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		mIDataBase.onUpgrade(db, connectionSource, oldVersion, newVersion);
	}

	// 释放资源
	@Override
	public void close() {		
		super.close();
	}

}
