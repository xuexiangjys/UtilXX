/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

import com.xuexiang.util.common.StringUtils;

/**
 * 日期工具类
 *
 * @author jingle1267@163.com
 */
public final class DateUtils {

    /**
     * 日期类型 *
     */
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String HHmmss = "HH:mm:ss";
    public static final String hhmmss = "HH:mm:ss";
    public static final String LOCALE_DATE_FORMAT = "yyyy年M月d日 HH:mm:ss";
    public static final String DB_DATA_FORMAT = "yyyy-MM-DD HH:mm:ss";
    public static final String NEWS_ITEM_DATE_FORMAT = "hh:mm M月d日 yyyy";

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }
    
    /**
     * 将Date类型转换为日期字符串
     *
     * @param date Date对象
     * @param type 需要的日期格式
     * @return 按照需求格式的日期字符串
     */
    public static String formatDate(Date date, String type) {
    	if (date == null) {
    		return StringUtils.EMPTY;
    	}
        try {
            SimpleDateFormat df = new SimpleDateFormat(type);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 将日期字符串转换为Date类型
     *
     * @param dateStr 日期字符串
     * @param type    日期字符串格式
     * @return Date对象
     */
    public static Date parseDate(String dateStr, String type) {
    	if (TextUtils.isEmpty(dateStr)) {
    		return null;
    	}
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
	 * 判断解析时间是否成功
	 * @param dateString 解析的时间
	 * @param format 解析的格式
	 * @return
	 */
	public static boolean isDateVaild(String dateString, SimpleDateFormat format) {
		if (TextUtils.isEmpty(dateString)) {
			return false;
		}
        try {
        	Date date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
    
    /**
     * 日期格式转化   oldFormat --->  newFormat
     * @param dateStr
     * @param oldFormat 旧格式
     * @param newFormat 新格式
     * @return
     */
    public static String dateFormatChange(String dateStr, SimpleDateFormat oldFormat, SimpleDateFormat newFormat) {
        if (TextUtils.isEmpty(dateStr)) {
        	return StringUtils.EMPTY;
        }
        Date date = null;
        try {
            date = oldFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }
        return newFormat.format(date);
    }
    
    /**
     * 日期格式转化   oldFormatType --->  newFormatType
     * @param dateStr
     * @param oldFormatType 旧格式
     * @param newFormatType 新格式
     * @return
     */
    public static String dateFormatChange(String dateStr, String oldFormatType, String newFormatType) {
        if (TextUtils.isEmpty(dateStr)) {
        	return StringUtils.EMPTY;
        }
        Date date = parseDate(dateStr, oldFormatType);
        if (date != null) {
        	String newDateString = formatDate(date, newFormatType);
        	if (TextUtils.isEmpty(newDateString)) {
        		return dateStr;      //转化失败
        	} else {
        		return newDateString;  //转化成功
        	}
        } else {
        	return dateStr;      //转化失败
        }
    }
    
    /**
	 * 计算距离今天的天数
	 * @param date 比较的时间
	 * @return
	 */
	public static int calculateNumberofDays(Date date) {
		Date now = getCurrentDate();
		int dayCount = (int) ((now.getTime() - date.getTime()) / (1000 * 3600 * 24));
		return dayCount;
	}
	
	/**
	 * 比较两个时间的大小
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compareDate(Date d1, Date d2) {
		if (d1.getTime() > d2.getTime()) {
			return 1;
		} else if (d1.getTime() < d2.getTime()) {
			return -1;
		} else {// 相等
			return 0;
		}
	}
    
    /**
     * 得到年
     *
     * @param date Date对象
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到月
     *
     * @param date Date对象
     * @return 月
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    /**
     * 得到日
     *
     * @param date Date对象
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换日期 将日期转为今天, 昨天, 前天, XXXX-XX-XX, ...
     *
     * @param time 时间
     * @return 当前日期转换为更容易理解的方式
     */
    public static String translateDate(Long time) {
        long oneDay = 24 * 60 * 60 * 1000;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long todayStartTime = today.getTimeInMillis();

        if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
            return "今天";
        } else if (time >= todayStartTime - oneDay && time < todayStartTime) { // yesterday
            return "昨天";
        } else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) { // the day before yesterday
            return "前天";
        } else if (time > todayStartTime + oneDay) { // future
            return "将来某一天";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            return dateFormat.format(date);
        }
    }

    /**
     * 转换日期 转换为更为人性化的时间
     *
     * @param time 时间
     * @return
     */
    public static String translateDate(long time, long curTime) {
        long oneDay = 24 * 60 * 60;
        Calendar today = Calendar.getInstance();    //今天
        today.setTimeInMillis(curTime * 1000);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        long todayStartTime = today.getTimeInMillis() / 1000;
        if (time >= todayStartTime) {
            long d = curTime - time;
            if (d <= 60) {
                return "1分钟前";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "分钟前";
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("今天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        } else {
            if (time < todayStartTime && time > todayStartTime - oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("昨天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {

                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else  if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay){
                SimpleDateFormat dateFormat = new SimpleDateFormat("前天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        }
    }
    
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 365 * 24 * 60 * 60;// 年
	private static final int MONTH = 30 * 24 * 60 * 60;// 月
	private static final int DAY = 24 * 60 * 60;// 天
	private static final int HOUR = 60 * 60;// 小时
	private static final int MINUTE = 60;// 分钟
    
    /**
	 * 根据时间戳获取描述性时间，如3分钟前，1天前
	 * 
	 * @param timestamp
	 *            时间戳 单位为毫秒
	 * @return 时间字符串
	 */
	public static String getDescriptionTimeFromTimestamp(long timestamp) {
		long currentTime = System.currentTimeMillis();
		long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > YEAR) {
			timeStr = timeGap / YEAR + "年前";
		} else if (timeGap > MONTH) {
			timeStr = timeGap / MONTH + "个月前";
		} else if (timeGap > DAY) {// 1天以上
			timeStr = timeGap / DAY + "天前";
		} else if (timeGap > HOUR) {// 1小时-24小时
			timeStr = timeGap / HOUR + "小时前";
		} else if (timeGap > MINUTE) {// 1分钟-59分钟
			timeStr = timeGap / MINUTE + "分钟前";
		} else {// 1秒钟-59秒钟
			timeStr = "刚刚";
		}
		return timeStr;
	}
	
	/**
	 * 根据时间戳获取描述性时间，如3分钟前，1天前
	 * @param strTime 要转换的String类型的时间
	 * @param formatType 时间格式
	 * @return
	 */
	public static String getDescriptionTimeFromTimestamp(String strTime, String formatType) {
		return getDescriptionTimeFromTimestamp(stringToLong(strTime, formatType));
	}
	
	/**
	 * 获取当前日期的指定格式的字符串
	 * 
	 * @param format
	 *            指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
	 * @return
	 */
	public static String getCurrentTime(String format) {
		if (format == null || format.trim().equals("")) {
			sdf.applyPattern(FORMAT_DATE_TIME);
		} else {
			sdf.applyPattern(format);
		}
		return sdf.format(new Date());
	}
	
 	/**
 	 * date类型转换为String类型
 	 * @param data Date类型的时间
 	 * @param formatType 格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
 	 * @return
 	 */
 	public static String dateToString(Date data, String formatType) {
 		return new SimpleDateFormat(formatType).format(data);
 	}
 	
 	/**
 	 * string类型转换为date类型
 	 * @param strTime 要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
 	 * @param formatType 时间格式必须要与formatType的时间格式相同
 	 * @return
 	 */
 	public static Date stringToDate(String strTime, String formatType){
 		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
 		Date date = null;
 		try {
			date = formatter.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		return date;
 	}
 	
 	/**
 	 * string类型转换为long类型
 	 * @param strTime 要转换的String类型的时间 ,strTime的时间格式和formatType的时间格式必须相同
 	 * @param formatType 时间格式
 	 * @return
 	 */
 	public static long stringToLong(String strTime, String formatType){
 		Date date = stringToDate(strTime, formatType); // String类型转成date类型
 		if (date == null) {
 			return 0;
 		} else {
 			long currentTime = dateToLong(date); // date类型转成long类型
 			return currentTime;
 		}
 	}
 	
 	/**
 	 * date类型转换为long类型
 	 * @param date 要转换的date类型的时间
 	 * @return
 	 */
 	public static long dateToLong(Date date) {
 		return date.getTime();
 	}
 	
 	/**
	 * 更新刷新时间
	 * @param created
	 * @return
	 */
	public static String getUploadtime(long created) {
		StringBuffer when = new StringBuffer();
		int difference_seconds;
		int difference_minutes;
		int difference_hours;
		int difference_days;
		int difference_months;
		long curTime = System.currentTimeMillis();
		difference_months = (int) (((curTime / 2592000) % 12) - ((created / 2592000) % 12));
		if (difference_months > 0) {
			when.append(difference_months + "月");
		}

		difference_days = (int) (((curTime / 86400) % 30) - ((created / 86400) % 30));
		if (difference_days > 0) {
			when.append(difference_days + "天");
		}

		difference_hours = (int) (((curTime / 3600) % 24) - ((created / 3600) % 24));
		if (difference_hours > 0) {
			when.append(difference_hours + "小时");
		}

		difference_minutes = (int) (((curTime / 60) % 60) - ((created / 60) % 60));
		if (difference_minutes > 0) {
			when.append(difference_minutes + "分钟");
		}

		difference_seconds = (int) ((curTime % 60) - (created % 60));
		if (difference_seconds > 0) {
			when.append(difference_seconds + "秒");
		}

		return when.append("前").toString();
	}


}
