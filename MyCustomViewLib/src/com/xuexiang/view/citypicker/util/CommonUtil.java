package com.xuexiang.view.citypicker.util;

import java.util.regex.Pattern;

import android.text.TextUtils;

/**  
 * 创建时间：2016-12-27 下午10:55:56
 * 项目名称：MyCustomViewLib  
 * @author xuexiang
 * 文件名称：CommonUtil.java 
 **/
public class CommonUtil {
	/**
     * 获取拼音的首字母（大写）
     * @param pinyin
     * @return
     */
    public static String getFirstLetter(final String pinyin){
        if (TextUtils.isEmpty(pinyin)) return "定位";
        String c = pinyin.substring(0, 1);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c).matches()){
            return c.toUpperCase();
        } else if ("0".equals(c)){
            return "定位";
        } else if ("1".equals(c)){
            return "热门";
        }
        return "定位";
    }
    
    /**
     * 提取出城市或者县
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district){
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }
}
