package com.example.testutil.data.dao.custom;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.xuexiang.util.data.db.ormlite.custom.CustomDBService;
import com.xuexiang.util.file.LocalFileUtil;

public class CustomDBManager {
	private Context mContext;	
	private static CustomDBManager sInstance;	
	
	public static String DATABASE_PATH = LocalFileUtil.DATABASE_PATH;
    public static String DATABASE_NAME = "myutil.db";    
	public static int DATABASE_VERSION = 1;
	public static CustomDataBase sIDataBase;
	
	
	private CustomDBManager(Context context) {
		mContext = context;
	}
	
	public static CustomDBManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new CustomDBManager(c.getApplicationContext());
			sIDataBase = new CustomDataBase(DATABASE_PATH, DATABASE_NAME, DATABASE_VERSION);
		}
		return sInstance;
	}
	
	private Map<String, Object> mDBPool = new HashMap<String, Object>();  
	
	@SuppressWarnings("unchecked")
	public <T> CustomDBService<T> getCustomDataBase(Class<T> clazz) {
		CustomDBService<T> dbService = null;
		if (mDBPool.containsKey(clazz.getSimpleName())) {
			dbService = (CustomDBService<T>) mDBPool.get(clazz.getSimpleName());
		} else {
			try {
				dbService = new CustomDBService<T>(mContext, clazz, DATABASE_PATH + DATABASE_NAME, DATABASE_VERSION, sIDataBase);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mDBPool.put(clazz.getSimpleName(), dbService);
		}
		return dbService;
	}
}
