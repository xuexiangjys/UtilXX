package com.xuexiang.util.data.cache;

/**
 * Created by wanglei on 2016/11/27.
 */

public interface ICache {
	 // #cache
    public static final String CACHE_SP_NAME = "config";
    public static final String CACHE_DISK_DIR = "cache";
    
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
