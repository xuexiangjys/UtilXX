package com.xuexiang.util.data.db.ormlite.custom;

import com.j256.ormlite.support.ConnectionSource;
import com.xuexiang.util.data.db.ormlite.default_.IDataBase;

/**
 * 自定义数据库操作接口
 * @author xx
 */
public interface ICustomDataBase extends IDataBase {
	/**
	 * 创建或者打开数据库
	 * @param connectionSource
	 */
	public void createOrOpenDB(ConnectionSource connectionSource);
	
}

