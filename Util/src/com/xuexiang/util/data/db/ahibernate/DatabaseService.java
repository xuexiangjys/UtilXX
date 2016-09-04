
package com.xuexiang.util.data.db.ahibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hrw.framework.ahibernate.dao.AhibernateDao;

public class DatabaseService<T> {
    private Context mContext;

    private DatabaseHelper<T> mDatabaseHelper;

    private SQLiteDatabase mSQLiteDatabase;

    private AhibernateDao<T> mDao;   

    private Class<T> clazz;

	public DatabaseService (Context context, Class<T> classOfT) {		
		clazz = classOfT;
        this.mContext = context;
        this.mDatabaseHelper = new DatabaseHelper<T>(mContext,clazz);
        this.mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();                
        
        this.mDao = new AhibernateDao<T>(this.mSQLiteDatabase);
            
    }

    // ===================object begin===========================
    /**
     * 复杂查询对象集合
     * @param where 查询条件
     * @return
     */
    public List<T> getObjectsByWhere(Map<String, String> where) {
        List<T> objectList = mDao.queryList(clazz, where);
        return objectList;
    }
    
    /**
     * 复杂查询对象
     * @param where 查询条件
     * @return 对象
     */
    public T getObjectByWhere(Map<String, String> where) {
        List<T> objectList = mDao.queryList(clazz, where);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * 根据id查询对象
     * @param id
     * @return 对象
     */
    public T queryById(long id) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put("id", id+"");
        List<T> objectList = mDao.queryList(clazz, map);       
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * 根据列名查询对象
     * @param Column 列名
     * @param value 值
     * @return 对象
     */
    public List<T> getObjectsByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList;
        }
    }
    
    /**
     * 根据列名查询对象
     * @param Column 列名
     * @param value 值
     * @return 对象
     */
    public T queryByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * 根据列名查询对象
     * @param Column 列名
     * @param value 值
     * @return 对象
     */
    public T queryByColumn2(String Columnname1,String value1,String Columnname2,String value2) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname1, value1);
		map.put(Columnname2, value2);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }

    /**
     * 根据对象（部分属性）查询对象集合
     * @param t
     * @return 对象集合
     */
    public List<T> getObjects(T t) {
        List<T> objectList = mDao.queryList(t);
        return objectList;
    }
    
    /**
     * 根据对象（部分属性）查询对象
     * @param t
     * @return 对象
     */
    public T getObject(T t) {
        List<T> objectList = mDao.queryList(t);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }

    }
    

    /**
     * 添加对象到数据库
     * @param t
     * @return
     */
    public int add(T t) {
        return mDao.insert(t);
    }

    /**
     * 复杂对象更新
     * @param t  对象
     * @param where 更新条件
     */
    public void update(T t, Map<String, String> where) {
        mDao.update(t, where);
    }
    
    /**
     * 复杂对象更新
     * @param t  对象
     * @param where 更新条件
     */
    public void updateByColumn(T t, String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        mDao.update(t, map);
    }
    
    /**
     * 对象更新
     * @param t
     */
    public void update(T t) {
        mDao.update(t, null);
    }
   
    /**
     * 复杂条件删除
     * @param where 删除条件
     */
    public void delete(Map<String, String> where) {
        mDao.delete(clazz, where);
    }
    
    /**
     * 复杂条件删除
     * @param where 删除条件
     */
    public void deleteByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        mDao.delete(clazz, map);
    }
    
    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(long id) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put("id", id+"");
        mDao.delete(clazz, map);
    }
    /**
     * 删除指定对象
     * @param t
     */
    public void delete(T t) {
        mDao.delete(t);
    }
    // ===================object end===============================

    
    public void close() {
       if(mSQLiteDatabase!=null){
    	   mSQLiteDatabase.close();
    	   mSQLiteDatabase =null;
       }
       
       if(mDatabaseHelper!=null){
    	   mDatabaseHelper.close();
    	   mDatabaseHelper =null;
       }
       
       if(mDao!=null){
    	   mDao =null;
       }
       
    }
    
}
