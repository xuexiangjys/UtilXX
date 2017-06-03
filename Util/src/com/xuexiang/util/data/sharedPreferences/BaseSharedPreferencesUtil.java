package com.xuexiang.util.data.sharedPreferences;

import java.util.Map;

import com.xuexiang.util.common.JavaReflectUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;

/** 
 * SharedPreferences管理工具基类
 * @author  XX 
 */

public class BaseSharedPreferencesUtil {
	public SharedPreferences.Editor mEditor;
	public SharedPreferences mSharedPreferences;
	public Context mContext;
	
	//=======================================初始化构造==================================================//
	/**
	 * 获取自定义的SharedPreferences
	 * @param context
	 * @param spName 自定义SharedPreferences名
	 */
	public BaseSharedPreferencesUtil(Context context, String spName) {
		mContext = context;
		mSharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
	    mEditor = mSharedPreferences.edit();
	}
	
	/**
	 * 获取系统默认的SharedPreferences
	 * @param context
	 */
	public BaseSharedPreferencesUtil(Context context) {
		mContext = context;
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	    mEditor = mSharedPreferences.edit();
	}
	
	//=======================================键值保存==================================================//
	/**
	 * 自定义sharedpreferences设置boolean值
	 * @param key
	 * @param value
	 */
	public void putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置float值
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		mEditor.putFloat(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置long值
	 * @param key
	 * @param value
	 */
	public void putLong(String key, long value) {
		mEditor.putLong(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置String值
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		mEditor.putString(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * 自定义sharedpreferences设置int值
	 * @param key
	 * @param value
	 */
	public void putInt(String key, int value) {
		mEditor.putInt(key, value);
	    mEditor.commit();  
	}
	
   /**
    * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
    * @param key
    * @param object
    */
	public void put(String key, Object object) {
        if (object instanceof String) {
        	mEditor.putString(key, (String) object);
        } else if (object instanceof Integer) {
        	mEditor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
        	mEditor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
        	mEditor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
        	mEditor.putLong(key, (Long) object);
        } else {
        	mEditor.putString(key, object.toString());
        }
        mEditor.commit();  
    }

	//=======================================键值获取==================================================//
	/**
	 * 根据key获取boolean值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPreferences.getBoolean(key, defValue);
	}
	
	/**
	 * 根据key获取long值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, long defValue) {
		return mSharedPreferences.getLong(key, defValue);
	}
	
	/**
	 * 根据key获取float值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloat(String key, float defValue) {
		return mSharedPreferences.getFloat(key, defValue);
	}
	
	/**
	 * 根据key获取String值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getString(String key, String defValue) {
		return mSharedPreferences.getString(key, defValue);
	}
	
	/**
	 * 根据key获取int值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}
	
	/**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return mSharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return mSharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return mSharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return mSharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }
    
    /**
	 * 得到保存数据的方法
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return getAll().get(key);
	}
    
  //=======================================公共方法==================================================//
    /**
     * 查询某个key是否已经存在
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }
    
    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }
    
    public void remove(String key) {
		mEditor.remove(key);
	    mEditor.commit();  
	}	
    
	/**
	 * 清空销毁
	 */
	public void clear() {
		mEditor.clear();
    	mEditor.commit();
	}
    
	/**
	 * 根据资源id获取String值
	 * @param resourceid 资源id
	 * @return
	 */
	public String getResourceString(int resourceId) {
		return mContext.getResources().getString(resourceId);
	}
	
	/**
	 * apply方法
	 * @throws Exception
	 */
	public void apply() throws Exception {
		JavaReflectUtil.invokeMethod(mEditor, "apply", new Object[]{});
	}
	
	/**
	 * 获取ListPreference中输入的具体的值
	 * @param listpref
	 * @param matchvalue
	 * @return
	 */
	public String getListEntryFromValue(ListPreference listpref, String matchvalue) {
		CharSequence[] entries = listpref.getEntries();
		CharSequence[] entryValues = listpref.getEntryValues();
		for (int i = 0; i < entryValues.length; i++) {
			if (entryValues[i].equals((String) matchvalue))
				return (String) entries[i];
		}
		return "";
	}
	
}
