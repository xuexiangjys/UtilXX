package com.xuexiang.util.common;

import java.text.DecimalFormat;


public class NumberUtils {
	public static int toInt(Object value) {
		try {
			return Integer.parseInt(value.toString());
		} catch (Exception e) {
		}
		return 0;
	}
	
	public static double toDouble(Object value) {
		try {
			return Double.parseDouble(value.toString());
		} catch (Exception e) {
		}
		return 0;
	}
	
	public static String toString(Object value) {
		try {
			return String.valueOf(value);
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 格式化double类型的数据，是结果保留两位小数
	 * @param value
	 * @return
	 */
	public static String format(double value){
		//对double类型的数据进行格式化的类
		return format(value,"######0.00");
	}
	
	/**
	 * 格式化double类型的数据
	 * @param value
	 * @return
	 */
	public static String format(double value,String format){
		//对double类型的数据进行格式化的类
		DecimalFormat  deFormat=new DecimalFormat(format);
		return deFormat.format(value);
	}
	
	public static String distanceFormat(double distance){
		return distance<1000?format(distance,"######")+"米":format(distance/1000,"######0.0")+"千米";
	}
	
	public static String avgPriceFormat(double avgPrice){
		return "人均:"+priceFormat(avgPrice);
	}
	

	public static String priceFormat(double price){
		return format(price,"######0.00")+"元";
	}
	

	/**
	 * 计算两个坐标间的距离,单位米
	 * @param longt1
	 * @param lat1
	 * @param longt2
	 * @param lat2
	 * @return
	 */
	public static double calcDistance(double longt1, double lat1, double longt2,double lat2) {
        double x, y, distance;
        x = (longt2 - longt1) * PI * ER
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * ER / 180;
        distance = Math.hypot(x, y);
        return distance;
    }
	
	private final static double PI = 3.14159265358979323; // 圆周率
    private final static double ER = 6371229; // 地球的半径
	
}
