package com.xuexiang.util.data.db.ormlite.custom;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import android.content.Context;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

public class CustomDBService<T> {
	private Context mContext;
	/** 数据库表操作对象 */
	private Dao<T, Integer> mDao;
	/** 数据库助手 */
	private CustomBaseDBHelper mDatabaseHelper;

	/**
	 * @param context
	 * @param clazz
	 * @param databasePath
	 *            数据库完整路径
	 * @param databaseVersion
	 *            数据库版本号
	 * @param idatabase
	 *            db操作接口
	 * @throws SQLException
	 */
	public CustomDBService(Context context, Class<T> clazz, String databasePath, int databaseVersion, ICustomDataBase idatabase) throws SQLException {
		mContext = context;
		mDatabaseHelper = new CustomBaseDBHelper(mContext, databasePath, databaseVersion, idatabase);
		if (mDao == null) {
			mDao = mDatabaseHelper.getDao(clazz);
		}

	}
	
	public CustomBaseDBHelper getDatabaseHelper() {
		return mDatabaseHelper;
	}

	/************************************************* 插入 **********************************************/
	/**
	 * 插入单条数据
	 * 
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public int insert(T object) throws SQLException {
		return mDao.create(object);
	}

	/**
	 * 插入单条数据(返回被插入的对象）
	 * 
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public T insertData(T object) throws SQLException {
		return mDao.createIfNotExists(object);
	}

	/**
	 * 批量添加
	 * 
	 * @param collection
	 * @return
	 * @throws SQLException
	 */
	public int insertDatas(Collection<T> collection) throws SQLException {
		return mDao.create(collection);
	}

	/************************************************* 查询 **********************************************/
	/**
	 * 使用迭代器查询所有
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<T> queryAllData() throws IOException {
		List<T> datalist = new ArrayList<T>();
		CloseableIterator<T> iterator = mDao.closeableIterator();
		try {
			while (iterator.hasNext()) {
				T data = iterator.next();
				datalist.add(data);
			}

		} finally {
			iterator.close();
		}
		return datalist;
	}

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public List<T> queryAll() throws SQLException {
		return mDao.queryForAll();
	}

	/**
	 * 无条件排序查询 返回一个对象集合
	 * 
	 * @param columnName
	 *            排序的列名
	 * @param ascending
	 *            true:升序，false：降序
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryAllOrderBy(String columnName, boolean ascending) throws SQLException {
		return mDao.queryBuilder().orderBy(columnName, ascending).query();
	}

	/**
	 * 无条件排序查询 返回一个对象集合
	 * 
	 * @param columnName1
	 *            排序的列名1
	 * @param ascending
	 *            true:升序，false：降序
	 * @param columnName1
	 *            排序的列名2
	 * @param ascending
	 *            true:升序，false：降序
	 * @return
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryAllOrderBy(String columnName1, boolean ascending1, String columnName2, boolean ascending2) throws SQLException {
		return mDao.queryBuilder().orderBy(columnName1, ascending1).orderBy(columnName2, ascending2).query();
	}

	/**
	 * 有条件排序查询 返回一个对象集合
	 * 
	 * @param fieldName
	 *            查询条件列名
	 * @param value
	 *            查询条件列名的值
	 * @param columnName
	 *            排序的列名
	 * @param ascending
	 *            true:升序，false：降序
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryAndOrderBy(String fieldName, Object value, String columnName, boolean ascending) throws SQLException {
		QueryBuilder<T, Integer> querys = mDao.queryBuilder();
		querys.where().eq(fieldName, value);
		querys.orderBy(columnName, ascending);
		return querys.query();
	}

	/**
	 * 根据id查询出一条数据
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T queryById(Integer id) throws SQLException {
		return mDao.queryForId(id);
	}

	/**
	 * 根据条件查询(一个条件) 返回一个对象集合
	 */
	public List<T> queryByField(String fieldName, Object value) throws SQLException {
		return mDao.queryForEq(fieldName, value);
	}

	/**
	 * 根据条件查询(一个条件) 返回一个对象集合
	 */
	public List<T> queryByField(Map<String, Object> clause) throws SQLException {
		return mDao.queryForFieldValuesArgs(clause);
	}

	/**
	 * 根据条件查询(一个条件) 返回一个对象集合
	 */
	public List<T> queryByColumn(String columnName, Object value) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName, value).query();
	}

	/**
	 * 根据条件查询(一个条件) 返回一个对象
	 */
	public T queryForColumn(String columnName, Object value) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName, value).queryForFirst();
	}

	/**
	 * 根据条件查询(二个条件) 返回一个对象集合
	 */
	public List<T> queryByColumn(String columnName1, Object value1, String columnName2, Object value2) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName1, value1).and().eq(columnName2, value2).query();
	}

	/**
	 * 根据条件查询(二个条件) 返回一个对象
	 */
	public T queryForColumn(String columnName1, Object value1, String columnName2, Object value2) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName1, value1).and().eq(columnName2, value2).queryForFirst();
	}

	/**
	 * 根据条件模糊查询(一个条件) 返回一个对象集合
	 */
	public List<T> indistinctQueryForColumn(String columnName, Object value) throws SQLException {
		return mDao.queryBuilder().where().like(columnName, "%" + value + "%").query();
	}

	/**
	 * 根据条件模糊查询(2条件) 返回一个对象集合
	 */
	public List<T> indistinctQueryForColumn(String columnName1, Object value1, String columnName2, Object value2) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName1, value1).and().like(columnName2, "%" + value2 + "%").query();
	}

	/**
	 * 根据sql语句查询
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<String[]> queryDataBySql(String sql) throws SQLException {
		GenericRawResults<String[]> rawResults = mDao.queryRaw(sql);
		List<String[]> results = rawResults.getResults();
		return results;
	}

	/**
	 * 根据sql语句查询
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryDataBySql(String sql, RawRowMapper<T> rawRowMapper) throws SQLException {
		GenericRawResults<T> rawResults = mDao.queryRaw(sql, rawRowMapper);
		List<T> results = rawResults.getResults();
		return results;
	}

	/************************************************* 更新 **********************************************/
	/**
	 * 使用对象更新一条记录
	 */
	public int updateData(T object) throws SQLException {
		return mDao.update(object);
	}

	/**
	 * 根据某一条件更新对象
	 * 
	 * @param updatecolumnName
	 *            更新列名
	 * @param updatevalue
	 *            更新值
	 * @param columnName
	 *            更新条件列名
	 * @param value
	 *            更新条件值
	 * @return
	 * @throws SQLException
	 */
	public int updateDataByColumn(String updatecolumnName, Object updatevalue, String columnName, Object value) throws SQLException {
		String sql = mDao.updateBuilder().updateColumnValue(updatecolumnName, updatevalue).where().eq(columnName, value).prepare().getStatement();
		return updateDatabySQL(sql);
	}

	/**
	 * 根据某一条件更新对象的多列属性
	 * 
	 * @param clause
	 *            更新列名和更新值的集合
	 * @param columnName
	 *            更新条件列名
	 * @param value
	 *            更新条件值
	 * @return
	 * @throws SQLException
	 */
	public int updateDataByColumn(Map<String, Object> clause, String columnName, Object value) throws SQLException {
		UpdateBuilder<T, Integer> updates = mDao.updateBuilder();
		for (String key : clause.keySet()) {
			updates.updateColumnValue(key, clause.get(key));
		}
		String sql = updates.where().eq(columnName, value).prepare().getStatement();
		return updateDatabySQL(sql);
	}

	/**
	 * 根据条件做update时直接使用sql语句进行更新
	 * 
	 * @Description statement 更新的SQL语句必须包含关键字INSERT，DELETE， UPDATE
	 */
	public int updateDatabySQL(String sql) throws SQLException {
		return mDao.updateRaw(sql);

	}

	/************************************************* 删除 **********************************************/
	/**
	 * 根据对象删除一条记录
	 * 
	 * @param object
	 * @throws SQLException
	 */
	public int deleteData(T object) throws SQLException {
		return mDao.delete(object);
	}

	/**
	 * 批量删除
	 * 
	 * @param datas
	 * @return
	 * @throws SQLException
	 */
	public int deleteDataList(Collection<T> datas) throws SQLException {
		return mDao.delete(datas);
	}

	/**
	 * 删除所有
	 * 
	 * @throws SQLException
	 */
	public int deleteAll() throws SQLException {
		List<T> datas = mDao.queryForAll();
		return mDao.delete(datas);

	}

	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public int deleteById(Integer id) throws SQLException {
		return mDao.deleteById(id);
	}

	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public int deleteById(int id) throws SQLException {
		return mDao.deleteById(id);
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int executeSql(String sql) throws SQLException {
		int result = mDao.executeRaw(sql);
		return result;
	}

	// ========================Builder===============================//
	/**
	 * 返回QueryBuilder
	 */
	public QueryBuilder<T, Integer> getQueryBuilder() {
		return mDao.queryBuilder();
	}

	/**
	 * 返回UpdateBuilder
	 */
	public UpdateBuilder<T, Integer> getUpdateBuilder() {
		return mDao.updateBuilder();
	}

	/**
	 * 返回DeleteBuilder
	 */
	public DeleteBuilder<T, Integer> getDeleteBuilder() {
		return mDao.deleteBuilder();
	}

	/**
	 * 可进行批量操作，需要进行批量操作时直接将代码放到callable的call()中即可
	 */
	public <A> void doBatchTasks(Callable<A> callable) throws Exception {
		mDao.callBatchTasks(callable);
	}

	// /**
	// * 关闭数据库
	// */
	// public void close() {
	// if (mDatabaseHelper != null) {
	// mDatabaseHelper.close();
	// mDatabaseHelper = null;
	// }
	// if (mDao != null) {
	// mDao = null;
	// }
	// }
}
