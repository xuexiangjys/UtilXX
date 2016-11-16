package com.xuexiang.util.data.db.ormlite.custom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * 数据库框架基础DBHelper类
 * @author xx
 */
public class CustomBaseDBHelper extends OrmLiteSqliteOpenHelper {
   
	/** 数据库路径*/
	private String mDatabasePath;	
	/** 具体某个应用程序数据库的*/
	private ICustomDataBase mIDataBase;
	
    /**
     * @param context
     * @param databasePath  数据库完整路径
     * @param databaseVersion  数据库版本号
     * @param idatabase  db操作接口类
     */
    public CustomBaseDBHelper(Context context, String databasePath, int databaseVersion, ICustomDataBase idatabase) {
    	super(context, null, null, databaseVersion);
    	mDatabasePath = databasePath ;
    	mIDataBase = idatabase;
    	
    	mIDataBase.createOrOpenDB(connectionSource);
	}

    @Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
    	mIDataBase.onCreate(database, connectionSource);
    }

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion){
		mIDataBase.onUpgrade(database, connectionSource, oldVersion, newVersion);
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		return SQLiteDatabase.openDatabase(mDatabasePath, null,
                SQLiteDatabase.OPEN_READWRITE);
	}
	
	@Override
	public SQLiteDatabase getReadableDatabase() {
		return SQLiteDatabase.openDatabase(mDatabasePath, null,
                SQLiteDatabase.OPEN_READWRITE);
	}
	
}
