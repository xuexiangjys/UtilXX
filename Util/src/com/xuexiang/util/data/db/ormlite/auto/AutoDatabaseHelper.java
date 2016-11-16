package com.xuexiang.util.data.db.ormlite.auto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

public class AutoDatabaseHelper<T> extends BaseDBHelper<T> {
	
	private Class<T> clazz;
	
	public AutoDatabaseHelper(Context context, Class<T> classOfT) {
		super(context);
		clazz = classOfT;
		createTableIfNotExist();
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, clazz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {	
		try {
			TableUtils.dropTable(connectionSource, clazz, isOpen());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     onCreate(db, connectionSource);
	}
	
	
	
	
	/**
     * 鏍规嵁娉ㄨВ鑾峰彇琛ㄥ悕
     * @return
     */
    public String getTableName(){
    	String tableName = null;
    	DatabaseTable myAnnotation  = clazz.getAnnotation(DatabaseTable.class);
         for (Method method : myAnnotation .annotationType().getDeclaredMethods()) {
             if (!method.isAccessible()) {
                 method.setAccessible(true);
             }
             Object invoke = null;
			try {
				invoke = method.invoke(myAnnotation );
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}			
            tableName = invoke.toString();
         }   	
		return tableName;   	
    }
    
    /**
     * 濡傛灉琛ㄤ笉瀛樺湪灏卞垱寤鸿〃
     */
    public void createTableIfNotExist(){
    	String tableName = getTableName();
        boolean result = false;
        if(tableName == null){
                return;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
                db = getWritableDatabase();
                String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' ";
                cursor = db.rawQuery(sql, null);
                if (cursor.moveToNext()) {
                        int count = cursor.getInt(0);
                        if (count > 0) {
                                result = true;
                        }
                }             
        } catch (Exception e) {
        }               
        if(!result){
        	try {
    			TableUtils.createTable(connectionSource, clazz);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
        
    }
    
	
}
