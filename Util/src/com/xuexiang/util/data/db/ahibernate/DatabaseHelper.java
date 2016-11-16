
package com.xuexiang.util.data.db.ahibernate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hrw.framework.ahibernate.annotation.Table;
import com.hrw.framework.ahibernate.table.TableUtils;

public class DatabaseHelper<T> extends SQLiteOpenHelper {
	
	private Class<T> clazz;

    public static final String DATABASE_NAME = "ahibernate.db";

    public DatabaseHelper(Context context,Class<T> classOfT) {   	
        super(context, DATABASE_NAME, null, 1);
        clazz = classOfT;
        createTableIfNotExist();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableUtils.createTable(db, true, clazz);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TableUtils.dropTable(db, clazz);
        onCreate(db);
    }
    
    /**
     * 根据注解获取表名
     * @return
     */
    public String getTableName(){
    	String tableName =null;
    	 Table myAnnotation  = clazz.getAnnotation(Table.class);
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
     * 如果表不存在就创建表
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
                db = this.getReadableDatabase();
                String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tableName.trim()+"' ";
                cursor = db.rawQuery(sql, null);
                if(cursor.moveToNext()){
                        int count = cursor.getInt(0);
                        if(count>0){
                                result = true;
                        }
                }
                
        } catch (Exception e) {
        }               
        if(!result){
        	 TableUtils.createTable(db, true, clazz);
        }
        
    }

}
