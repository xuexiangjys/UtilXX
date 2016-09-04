package com.example.testutil.data.dao.default_;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.xuexiang.util.data.db.ormlite.default_.DBService;

public class DBManager {
	private Context mContext;	
	private static DBManager sInstance;	
	
    public static String DATABASE_NAME = "myutil.db";
	public static int DATABASE_VERSION = 1;
	public static DefaultDatabase sIDataBase;
	
	
	private DBManager(Context context) {
		mContext = context;
	}
	
	public static DBManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new DBManager(c.getApplicationContext());
			sIDataBase = new DefaultDatabase();
		}
		return sInstance;
	}
	
	private Map<String, Object> mDBPool = new HashMap<String, Object>();  
	
	@SuppressWarnings("unchecked")
	public <T> DBService<T> getDataBase(Class<T> clazz) {
		DBService<T> dbService = null;
		if (mDBPool.containsKey(clazz.getSimpleName())) {
			dbService = (DBService<T>) mDBPool.get(clazz.getSimpleName());
		} else {
			try {
				dbService = new DBService<T>(mContext, clazz, DATABASE_NAME, DATABASE_VERSION, sIDataBase);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mDBPool.put(clazz.getSimpleName(), dbService);
		}
		return dbService;
	}
}
