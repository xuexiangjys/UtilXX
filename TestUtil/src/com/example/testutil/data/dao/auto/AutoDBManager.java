package com.example.testutil.data.dao.auto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.xuexiang.util.data.db.ormlite.auto.AutoDBService;

public class AutoDBManager {
	private Context mContext;	
	private static AutoDBManager sInstance;	
	
	private AutoDBManager(Context context) {
		mContext = context;
	}
	
	public static AutoDBManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new AutoDBManager(c.getApplicationContext());
		}
		return sInstance;
	}
	
	private Map<String, Object> mDBPool = new HashMap<String, Object>();  
	
	@SuppressWarnings("unchecked")
	public <T> AutoDBService<T> getAutoDataBase(Class<T> clazz) {
		AutoDBService<T> dbService = null;
		if (mDBPool.containsKey(clazz.getSimpleName())) {
			dbService = (AutoDBService<T>) mDBPool.get(clazz.getSimpleName());
		} else {
			try {
				dbService = new AutoDBService<T>(mContext, clazz);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mDBPool.put(clazz.getSimpleName(), dbService);
		}
		return dbService;
	}
}
