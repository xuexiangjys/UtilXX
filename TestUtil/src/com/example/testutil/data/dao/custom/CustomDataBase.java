package com.example.testutil.data.dao.custom;

import java.io.File;
import java.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testutil.data.entity.Student;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xuexiang.util.data.db.ormlite.custom.ICustomDataBase;

public class CustomDataBase implements ICustomDataBase {

	private int mDatabaseVersion;
    private String mDBPath;
    private String mDBName;
    
    private String mDatabasePath;
	
    public CustomDataBase(String dbpath, String dbName, int databaseVersion) {
    	mDBPath = dbpath;
    	mDBName = dbName;
    	mDatabaseVersion = databaseVersion;
    	mDatabasePath = mDBPath + mDBName;
    }
    
	
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Student.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		Log.i("xx", "数据库旧版本=======" + oldVersion);
		Log.i("xx", "数据库新版本=======" + newVersion);
	}

	@Override
	public void createOrOpenDB(ConnectionSource connectionSource) {
		File dbDir = new File(mDBPath);
    	if (!dbDir.exists()) {
    		dbDir.mkdirs();
    	}
    	new File(mDatabasePath);
    	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				mDatabasePath, null);
    	int oldVersionCode = db.getVersion();
    	if (oldVersionCode == 0) {
    		onCreate(db, connectionSource);
		}
    	db.setVersion(mDatabaseVersion);
    	if (oldVersionCode != mDatabaseVersion) {
    		onUpgrade(db, connectionSource, oldVersionCode, mDatabaseVersion);
		}
	}

}
