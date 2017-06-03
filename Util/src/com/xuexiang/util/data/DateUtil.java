package com.xuexiang.util.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;

import com.xuexiang.util.common.StringUtil;

/**
 * 日期格式化工具类
 * 
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
	/**
	 * MM/dd
	 */
	private static final ThreadLocal<DateFormat> MMdd = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM/dd");
		}
	};
	/**
	 * MMdd
	 */
	private static final ThreadLocal<DateFormat> MMdds = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MMdd");
		}
	};
	/**
	 * yyyy-MM-dd
	 */
	private static final ThreadLocal<DateFormat> yyyyMMdd = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	/**
	 * yyyyMMdd
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddNoSep = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};
	/**
	 * HH:mm:ss
	 */
	private static final ThreadLocal<DateFormat> HHmmss = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}
	};
	/**
	 * yyyy-MM-dd HH:mm:ss 【网络请求获得的统一时间格式】
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddHHmmss = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	/**
	 * yyyy-MM-dd HH:mm
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddHHmmss2 = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};
	/**
	 * yyyyMMddHHmmss
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddHHmmssNoSep = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddHHmmssSSS = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		}
	};
	/**
	 * yyyyMMdd.HHmmss
	 */
	private static final ThreadLocal<DateFormat> yyyyMMddHHmmss3 = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd.HHmmss");
		}
	};
	/**
	 * yyyy-MM
	 */
	private static final ThreadLocal<DateFormat> yyyyMM = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM");
		}
	};
	/**
	 * yyyyMM
	 */
	private static final ThreadLocal<DateFormat> yyyyMMNoSep = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMM");
		}
	};
	/**
	 * HH:mm:ss.S
	 */
	private static final ThreadLocal<DateFormat> MS = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss.S");
		}
	};
	/**
	 * HH:mm
	 */
	private static final ThreadLocal<DateFormat> HM = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	private static final ThreadLocal<DateFormat> printDateFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
	};

	// ==================================================日期转化==================================================================//
	/**
	 * 日期形式转化 oldFormat ---> newFormat
	 * 
	 * @param dateStr
	 * @param oldFormat
	 *            旧格式
	 * @param newFormat
	 *            新格式
	 * @return
	 */
	public static String dateFormatChange(String dateStr, DateFormat oldFormat, DateFormat newFormat) {
		if (StringUtil.isEmpty(dateStr)) {
			return StringUtil.EMPTY;
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
	 * 【网络请求获得的统一时间格式】yyyy-MM-dd HH:mm:ss---> ? 新的的时间格式
	 *
	 * @param netDateStr
	 * @param newFormat
	 * @return
	 */
	public static String translateNetDateToNewFormat(String netDateStr, DateFormat newFormat) {
		return dateFormatChange(netDateStr, yyyyMMddHHmmss.get(), newFormat);
	}

	/**
	 * 【网络请求获得的统一时间格式】yyyy-MM-dd HH:mm:ss---> HH:mm 的时间格式
	 *
	 * @param netDateStr
	 * @return
	 */
	public static String translateNetDateToHM(String netDateStr) {
		return dateFormatChange(netDateStr, yyyyMMddHHmmss.get(), HM.get());
	}

	/**
	 * yyyyMMddHHmmss--->yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String translateDateFormat(String dateStr) {
		return dateFormatChange(dateStr, yyyyMMddHHmmssNoSep.get(), yyyyMMddHHmmss.get());
	}

	/**
	 * yyyy-MM-dd HH:mm:ss---> yyyy/MM/dd HH:mm:ss 报告打印需要的时间格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String translateDateToPrintFormat(String dateStr) {
		return dateFormatChange(dateStr, yyyyMMddHHmmss.get(), printDateFormat.get());
	}

	/**
	 * yyyy-MM-dd HH:mm:ss--->yyyy-MM-dd
	 * 
	 * @param text
	 *            “yyyy-MM-dd HH:mm:ss”格式的日期
	 * @return
	 */
	public static String renderDate(String text) {
		if (StringUtil.isEmpty(text)) {
			return StringUtil.EMPTY;
		}
		int idx = text.indexOf(" ");
		if (idx <= 0) {
			return text;
		}
		return text.substring(0, idx).trim();
	}

	/**
	 * yyyy-MM-dd HH:mm:ss--->yyyy-MM-dd
	 * 
	 * @param text
	 *            “yyyy-MM-dd HH:mm:ss”格式的日期
	 * @return
	 */
	public static String renderTime(String text) {
		if (StringUtil.isEmpty(text)) {
			return StringUtil.EMPTY;
		}
		int idx = text.indexOf(" ");
		if (idx <= 0) {
			return text;
		}
		return text.substring(idx).trim();
	}

	/** yyyy-MM-dd HH:mm:ss --> yyyyMMddHHmmss */
	public static String formateDateString(String str) {
		return dateFormatChange(str, yyyyMMddHHmmss.get(), yyyyMMddHHmmssNoSep.get());
	}

	/**
	 * @param datetime
	 *            将yyyy-MM-dd HH:mm:ss类型的string转化为yyyyMMdd.HHmmss类型的string
	 * @return
	 * @throws ParseException
	 */
	public static String TranslateDate(String datetime) throws ParseException {
		return dateFormatChange(datetime, yyyyMMddHHmmss.get(), yyyyMMddHHmmss3.get());
	}

	/**
	 * 将yyyy/MM/dd HH:mm:ss类型的事件改为 HH:mm:ss类型
	 * 
	 * @param datetime
	 * @return
	 */
	public static String formatTime(String datetime) {
		return dateFormatChange(datetime, printDateFormat.get(), HHmmss.get());
	}

	/**
	 * yyyyMMddHHmmss --> yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return
	 */
	public static String formatDate2SepStr(String datetime) {
		return dateFormatChange(datetime, yyyyMMddHHmmssNoSep.get(), yyyyMMddHHmmss.get());
	}

	/** yyyy-MM-dd HH:mm:ss --> yyyyMMdd */
	public static String formateDateStrings(String datetime) {
		return dateFormatChange(datetime, yyyyMMddHHmmss.get(), yyyyMMddNoSep.get());
	}

	/**
	 * 将整秒转化为HH:mm:ss格式
	 * 
	 * @param seconds
	 * @return
	 */
	public static String getTime(int seconds) {
		int temp = 0;
		StringBuffer sb = new StringBuffer();
		temp = seconds / 3600;
		sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
		temp = seconds % 3600 / 60;
		sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
		temp = seconds % 3600 % 60;
		sb.append((temp < 10) ? "0" + temp : "" + temp);
		return sb.toString();
	}

	// =================================================="获取日期"==================================================================//
	/**
	 * 获取"yyyy-MM-dd"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDate(Date d) {
		if (d == null) {
			return StringUtil.EMPTY;
		}
		return yyyyMMdd.get().format(d);
	}

	/**
	 * 获取"yyyyMMdd"格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYMD(Date date) {
		String str = "";
		if (date == null) {
			str = yyyyMMddNoSep.get().format(getCurrentDate());
		} else {
			str = yyyyMMddNoSep.get().format(date);
		}
		str = str.replaceAll("-", "");
		return str;
	}

	public static String formatDate(String strDate) {
		if (strDate == null || strDate.trim().equals(""))
			return StringUtil.EMPTY;
		try {
			return yyyyMMdd.get().format(yyyyMMdd.get().parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return StringUtil.EMPTY;
		}
	}

	/**
	 * 获取"yyyy-MM-dd HH:mm:ss"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTime(Date d) {
		if (d == null) {
			return StringUtil.EMPTY;
		}
		return yyyyMMddHHmmss.get().format(d);
	}

	public static String formatDateTimeStr(String strTime) {
		Date date = null;
		if (strTime == null || strTime.length() <= 0) {
			return "";
		}
		try {
			date = yyyyMMddHHmmss.get().parse(strTime);
			return yyyyMMddHHmmss.get().format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取"yyyy-MM-dd HH:mm"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTime2(Date d) {
		if (d == null)
			return StringUtil.EMPTY;
		return yyyyMMddHHmmss2.get().format(d);
	}

	/**
	 * 获取“HH:mm:ss”格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatTime(Date d) {
		if (d == null)
			return StringUtil.EMPTY;
		return HHmmss.get().format(d);
	}

	/**
	 * 获取“yyyy-MM-dd HH:mm:ss:SSS”格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTimeMS(Date d) {
		if (d == null)
			return StringUtil.EMPTY;
		return yyyyMMddHHmmssSSS.get().format(d);
	}

	/**
	 * 获取“yyyyMMddHHmmss”格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTimeNoSep(Date d) {
		if (d == null)
			return StringUtil.EMPTY;
		return yyyyMMddHHmmssNoSep.get().format(d);
	}

	/**
	 * 获取“yyyy-MM”格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static String formatyyMM(Date d) {
		if (d == null) {
			return StringUtil.EMPTY;
		}
		return yyyyMM.get().format(d);
	}

	/**
	 * 获取“MM/dd”格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatMMDD(String date) {
		try {
			Date d = formatStrDate(date);
			return MMdd.get().format(new Date(d.getTime()));
		} catch (Exception e) {
			return MMdd.get().format(new Date());
		}
	}

	/**
	 * 获取“HH:mm:ss.S”格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatMS(Date date) {
		if (date == null)
			return StringUtil.EMPTY;
		return MS.get().format(date);
	}

	// =================================================="解析日期"==================================================================//
	/**
	 * 解析"yyyy-MM-dd"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date formatStrDate(String d) {
		if (StringUtil.isEmpty(d)) {
			return getCurrentDate();
		}
		Date date = null;
		try {
			date = yyyyMMdd.get().parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	/**
	 * 解析"yyyyMMdd"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date formatYMD(String d) {
		if (StringUtil.isEmpty(d)) {
			return getCurrentDate();
		}
		Date date = null;
		try {
			date = yyyyMMddNoSep.get().parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	/**
	 * 解析"yyyy-MM-dd HH:mm:ss"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date formatStrDateTime(String d) {
		if (StringUtil.isEmpty(d)) {
			return getCurrentDate();
		}

		Date date = null;
		try {
			date = yyyyMMddHHmmss.get().parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	/**
	 * 解析"yyyy-MM-dd HH:mm:ss"格式的日期
	 * 
	 * @param strTime
	 * @return
	 */
	public static Date formatDateTime(String strTime) {
		if (StringUtil.isEmpty(strTime)) {
			return getCurrentDate();
		}

		Date date = null;
		try {
			date = yyyyMMddHHmmss.get().parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	/**
	 * 解析"yyyy-MM-dd HH:mm"格式的日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date formatStrDateTime2(String d) {
		if (StringUtil.isEmpty(d)) {
			return getCurrentDate();
		}
		Date date = null;
		try {
			date = yyyyMMddHHmmss2.get().parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	// =================================================="日历相关"==================================================================//
	public static String formatYearMonth(Date date) {
		String str = "";
		if (date == null) {
			str = yyyyMMNoSep.get().format(getCurrentDate());
		} else {
			str = yyyyMMNoSep.get().format(date);
		}
		return str;
	}

	public static String formatNextMonthDate(String date) {
		String strDate = "";
		Date d = formatStrDate(date);
		Date currentDate = d;
		if (currentDate == null) {
			Date nextMonth = new Date(getCurrentDate().getTime() + (24 * 60 * 60 * 1000));
			strDate = formatDate(nextMonth);
		} else {
			Date nextMonth = new Date(currentDate.getTime() + (24 * 60 * 60 * 1000));
			strDate = formatDate(nextMonth);
		}
		return strDate;
	}

	public static String formatPrevMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTimeInMillis(date.getTime());
		}
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		String strTime = formatDate(new Date(cal.getTimeInMillis()));
		return strTime.replace("-", "").substring(0, 6);
	}

	public static String formatTime(int seconds) {
		String ret = "00:00:00";
		if (seconds <= 0)
			return ret;
		int sec = seconds % 60;
		int min = (seconds / 60) % 60;
		int hr = seconds / 3600;
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hr, min, sec);
		ret = String.format("%1$tH:%1$tM:%1$tS", new Date(cal.getTimeInMillis()));
		return ret;
	}

	public static String formatMonthDay(Date date) {
		String str = "";
		if (date == null) {
			str = MMdds.get().format(getCurrentDate());
		} else {
			str = MMdds.get().format(date);
		}
		return str;
	}

	public static String formatYearMonth() {
		return formatYearMonth(null);
	}

	public static String[] getOneWeekDateLable() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		String[] dateArr = new String[7];
		for (int i = 0; i < 7; i++) {
			dateArr[i] = DateUtil.formatDate(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dateArr;
	}

	public static String[] getFormatOneWeekDateLable(String[] dates) {
		String[] dateArr = new String[dates.length];
		for (int i = 0; i < 7; i++) {
			dateArr[i] = DateUtil.formatMMDD(dates[i]);
		}
		return dateArr;
	}

	/**
	 * 
	 * Return a date string formatted by YYYY-MM-DD.
	 * 
	 * @param spanType
	 *            The type you want to span, such as Calendar.MONTH
	 * @param span
	 *            The count you want to span
	 * @return
	 */
	public static String getDateStr(int spanType, int span) {
		Calendar cal = Calendar.getInstance();
		cal.add(spanType, span);
		return formatDate(new Date(cal.getTimeInMillis()));
	}

	/**
	 * 
	 * Judge if the given string is a valid date format
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidDate(String s) {
		try {
			if (s.length() > 19) {
				return false;
			} else {
				yyyyMMddHHmmss.get().parse(s);
				return true;
			}
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * 
	 * 根据年月 获取到这个月的最后一天 2011-11；
	 */
	public static String getLastDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		// 设置月份
		cal.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		String lastDayOfMonth = yyyyMMdd.get().format(cal.getTime());

		return lastDayOfMonth + " 00:00:00";
	}

	/**
	 * 
	 * @param dateStr
	 *            时间格式 2016-01-02
	 * @return
	 */
	public static int caculateWeek(String dateStr) {
		int i = 0;
		try {
			Calendar calendar = Calendar.getInstance();
			Date date;
			date = yyyyMMdd.get().parse(dateStr);
			calendar.setTime(date);
			i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (i == 0) {
				i = Calendar.DAY_OF_WEEK;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return i;
	}

	/** 2016-11-13 根据日期计算出在日历中的位置 */
	public static List<Integer> getLocationInCalendar(List<String> monthDay) {
		List<Integer> intList = new ArrayList<Integer>();
		if (monthDay == null || monthDay.size() == 0) {
			return null;
		}
		// 1104
		for (int i = 0; i < monthDay.size(); i++) {
			String dateStr = monthDay.get(i).substring(0, 4) + "-" + monthDay.get(i).substring(5, 7) + "-" + "01";
			int caculateWeek = caculateWeek(dateStr);// 计算出某年某月一号是星期几
			int location = caculateWeek + Integer.parseInt(monthDay.get(i).substring(8, 10)) - 1;
			if (!intList.contains(location)) {
				intList.add(location);
			}
		}
		return intList;
	}

	public static String formatTimes(int seconds) {
		String ret = "00:00:00";
		if (seconds <= 0)
			return ret;
		int sec = seconds % 60;
		int min = (seconds / 60) % 60;
		int hr = seconds / 3600;
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hr, min, sec);
		ret = String.format("%1$tH:%1$tM:%1$tS", new Date(cal.getTimeInMillis()));
		return ret;
	}

	/**
	 * 获取当前日期n天前的日期，返回String (yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param day 【-1：1天前， 1：1天后】
	 * @return
	 */
	public static String nDaysAftertoday(int day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTimeInMillis(System.currentTimeMillis());
		rightNow.add(Calendar.DAY_OF_MONTH, -(day - 1));
		return DateUtil.formatDate(rightNow.getTime()) + " 00:00:00";
	}

	// =================================================="当天时间获取"==================================================================//
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public static long getCurrentTimeMills() {
		return System.currentTimeMillis();
	}

	// =================================================="时间复杂处理"==================================================================//
	/**
	 * 
	 * Format the given string to date, if the time part is not "00:00:00",
	 * return the date part.
	 * 
	 * @param str
	 * @return
	 */
	public static String getDate(String str) {
		String s;
		try {
			Date d = yyyyMMddHHmmss.get().parse(str);
			s = formatDateTime(d);
			if (s.indexOf("00:00:00") != -1) {
				return s.substring(0, s.indexOf("00:00:00")).trim();
			} else {
				return s;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String saveDate(String createDate) {
		String dateCatalogue = DateUtil.formatDate((DateUtil.formatStrDateTime(createDate.replace("/", "-")))).replace("-", "_");
		return dateCatalogue;
	}

	public static String getFileDateTime(String dateTime) {
		char[] strArray = dateTime.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			sb.append(strArray[i]);
			if (i == 3) {
				sb.append("-");
			} else if (i == 5) {
				sb.append("-");
			} else if (i == 7) {
				sb.append(" ");
			} else if (i == 9) {
				sb.append(":");
			} else if (i == 11) {
				sb.append(":");
			}
		}
		return sb.toString();
	}

	public static Date readDate(String catalogue) {
		Date date = formatStrDateTime(catalogue.replace("_", "-") + " 10:00:00");
		return date;

	}

	/**
	 * 将时间转换为文件名称
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String convertTimeToFileName(String dateTime, String suffix) {
		return dateTime.replace("-", "").replace(":", "").replace(" ", "") + suffix;
	}

	public static String formatDate(SimpleDateFormat format, Date date) {
		if (date == null)
			return format.format(new Date());
		return format.format(date);
	}

	// =================================================="模糊时间处理"==================================================================//
	public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";

	private static ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(FORMAT_DATE_TIME);
		}
	};
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
	 * 
	 * @param strTime
	 *            要转换的String类型的时间
	 * @param formatType
	 *            时间格式
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
		if (!StringUtil.isEmpty(format)) {
			sdf.set(new SimpleDateFormat(format));
		}
		return sdf.get().format(new Date());
	}

	// =================================================="日期类型转换"==================================================================//
	/**
	 * date类型转换为String类型
	 * 
	 * @param data
	 *            Date类型的时间
	 * @param formatType
	 *            格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}

	/**
	 * string类型转换为date类型
	 * 
	 * @param strTime
	 *            要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd
	 *            HH:mm:ss//yyyy年MM月dd日
	 * @param formatType
	 *            时间格式必须要与formatType的时间格式相同
	 * @return
	 */
	public static Date stringToDate(String strTime, String formatType) {
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
	 * 
	 * @param strTime
	 *            要转换的String类型的时间 ,strTime的时间格式和formatType的时间格式必须相同
	 * @param formatType
	 *            时间格式
	 * @return
	 */
	public static long stringToLong(String strTime, String formatType) {
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
	 * 
	 * @param date
	 *            要转换的date类型的时间
	 * @return
	 */
	public static long dateToLong(Date date) {
		return date.getTime();
	}

	// =================================================="日期比较"==================================================================//
	/**
	 * 比较两个时间的大小
	 * 
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
	 * 判断解析时间是否成功
	 * 
	 * @param dateString
	 *            解析的时间
	 * @param format
	 *            解析的格式
	 * @return
	 */
	public static boolean isParseDateSuccess(String dateString, DateFormat format) {
		if (StringUtil.isEmpty(dateString)) {
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
	 * 通用的时间格式化方法
	 * 
	 * @param date
	 *            格式化的时间
	 * @param format
	 *            格式
	 * @return
	 */
	public static String formatDate(Date date, SimpleDateFormat format) {
		if (date == null) {
			return StringUtil.EMPTY;
		}
		return format.format(date);
	}

	/**
	 * 通用的时间解析方法
	 * 
	 * @param dateString
	 *            解析的时间
	 * @param format
	 *            解析的格式
	 * @return
	 */
	public static Date parseDate(String dateString, SimpleDateFormat format) {
		if (StringUtil.isEmpty(dateString)) {
			return getCurrentDate();
		}
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDate();
		}
		return date;
	}

	/**
	 * 计算距离今天的天数
	 * 
	 * @param dateString
	 *            比较的时间 【格式：yyyy_mm_dd】
	 * @return
	 */
	public static int calculateNumberofDays(String dateString) {
		return calculateNumberofDays(DateUtil.formatStrDate(dateString.replace("_", "-")));
	}

	/**
	 * 计算距离今天的天数
	 * 
	 * @param date
	 *            比较的时间
	 * @return
	 */
	public static int calculateNumberofDays(Date date) {
		return calculateTimeDistance(date, 1000 * 3600 * 24);
	}

	/**
	 * 计算距离现在多少分钟
	 *
	 * @param dateString
	 *            比较的时间 【格式：yyyy-MM-dd HH:mm:ss】
	 * @return
	 */
	public static int calculateNumberofMinutes(String dateString) {
		return calculateNumberofMinutes(DateUtil.formatStrDateTime(dateString));
	}

	/**
	 * 计算距离现在多少分钟
	 *
	 * @param date
	 *            比较的时间
	 * @return
	 */
	public static int calculateNumberofMinutes(Date date) {
		return calculateTimeDistance(date, 1000 * 60);
	}

	/**
	 * 计算时间距离
	 * @param date 时间
	 * @param unitInterval 单位时间
	 * @return
	 */
	public static int calculateTimeDistance(Date date, int unitInterval) {
		int result = (int) ((getCurrentDate().getTime() - date.getTime()) / unitInterval);
		return result;
	}

}
