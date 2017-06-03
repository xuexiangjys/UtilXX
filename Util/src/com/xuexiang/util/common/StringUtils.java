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
package com.xuexiang.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 字符串操作工具包 结合android.text.TextUtils使用
 * 
 * @author jingle1267@163.com
 */
public final class StringUtils {

	public static String EMPTY = "";

	/**
	 * Don't let anyone instantiate this class.
	 */
	private StringUtils() {
		throw new Error("Do not need instantiate!");
	}

	/**
	 * Returns true if the string is null or 0-length.
	 * 
	 * @param str
	 *            the string to be examined
	 * @return true if str is null or zero length
	 */
	public static boolean isEmpty(CharSequence str) {
		return TextUtils.isEmpty(str);
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * byte[]数组转换为16进制的字符串
	 * 
	 * @param data
	 *            要转换的字节数组
	 * @return 转换后的结果
	 */
	public static final String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase(Locale.getDefault());
	}

	/**
	 * 16进制表示的字符串转换为字节数组
	 * 
	 * @param s
	 *            16进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] d = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return d;
	}

	/**
	 * 将给定的字符串中所有给定的关键字标红
	 * 
	 * @param sourceString
	 *            给定的字符串
	 * @param keyword
	 *            给定的关键字
	 * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
	 */
	public static String keywordMadeRed(String sourceString, String keyword) {
		String result = "";
		if (sourceString != null && !"".equals(sourceString.trim())) {
			if (keyword != null && !"".equals(keyword.trim())) {
				result = sourceString.replaceAll(keyword, "<font color=\"red\">" + keyword + "</font>");
			} else {
				result = sourceString;
			}
		}
		return result;
	}

	/**
	 * 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
	 * 
	 * @param string
	 *            给定的字符串
	 * @return
	 */
	public static String addHtmlRedFlag(String string) {
		return "<font color=\"red\">" + string + "</font>";
	}

	/**
	 * 根据分隔符将String转换为List
	 * 
	 * @param str
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public List<String> StringToList(String str, String separator) {
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(str.split(separator));
		return list;
	}

	/**
	 * 根据分隔符将List转换为String
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public String ListToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * 过滤字符串中所有的特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceSpecialCharacter(String str) {
		String dest = "";
		if (str != null) {
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			dest = m.replaceAll("").trim();
		}
		return dest;
	}

	/**
	 * 过滤字符串中的[和]
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBracket(String str) {
		String dest = "";
		if (str != null) {
			String regEx = "[\\[\\]]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			dest = m.replaceAll("").trim();
		}
		return dest;
	}

	/**
	 * 截取字符串中[]中的内容，返回符合字符串的集合 "aaa[bbb|ccc|ddd]eee[ffff|ggg|hhh]iii" -->
	 * [bbb|ccc|ddd, ffff|ggg|hhh]
	 */
	public static List<String> matchStringbyBracket(String str) {
		List<String> results = new ArrayList<String>();
		while (str.indexOf("]") != -1) {
			results.add(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
			str = str.substring(str.indexOf("]") + 1, str.length());
		}
		return results;
	}

	/**
	 * Java文件操作 获取文件扩展名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 判断字符串是否为null或全为空格
	 * 
	 * @param s
	 *            待校验字符串
	 * @return {@code true}: null或全空格<br>
	 *         {@code false}: 不为null且不全空格
	 */
	public static boolean isTrimEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * 判断字符串是否为null或全为空白字符
	 * 
	 * @param s
	 *            待校验字符串
	 * @return {@code true}: null或全空白字符<br>
	 *         {@code false}: 不为null且不全空白字符
	 */
	public static boolean isSpace(String s) {
		if (s == null)
			return true;
		for (int i = 0, len = s.length(); i < len; ++i) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断两字符串是否相等
	 * 
	 * @param a
	 *            待校验字符串a
	 * @param b
	 *            待校验字符串b
	 * @return {@code true}: 相等<br>
	 *         {@code false}: 不相等
	 */
	public static boolean equals(CharSequence a, CharSequence b) {
		if (a == b)
			return true;
		int length;
		if (a != null && b != null && (length = a.length()) == b.length()) {
			if (a instanceof String && b instanceof String) {
				return a.equals(b);
			} else {
				for (int i = 0; i < length; i++) {
					if (a.charAt(i) != b.charAt(i))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断两字符串忽略大小写是否相等
	 * 
	 * @param a
	 *            待校验字符串a
	 * @param b
	 *            待校验字符串b
	 * @return {@code true}: 相等<br>
	 *         {@code false}: 不相等
	 */
	public static boolean equalsIgnoreCase(String a, String b) {
		return a == null ? b == null : a.equalsIgnoreCase(b);
	}

	/**
	 * null转为长度为0的字符串
	 * 
	 * @param s
	 *            待转字符串
	 * @return s为null转为长度为0字符串，否则不改变
	 */
	public static String null2Length0(String s) {
		return s == null ? "" : s;
	}

	/**
	 * 返回字符串长度
	 * 
	 * @param s
	 *            字符串
	 * @return null返回0，其他返回自身长度
	 */
	public static int length(CharSequence s) {
		return s == null ? 0 : s.length();
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 *            待转字符串
	 * @return 首字母大写字符串
	 */
	public static String upperFirstLetter(String s) {
		if (isEmpty(s) || !Character.isLowerCase(s.charAt(0)))
			return s;
		return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
	}

	/**
	 * 首字母小写
	 * 
	 * @param s
	 *            待转字符串
	 * @return 首字母小写字符串
	 */
	public static String lowerFirstLetter(String s) {
		if (isEmpty(s) || !Character.isUpperCase(s.charAt(0)))
			return s;
		return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
	}

	/**
	 * 反转字符串
	 * 
	 * @param s
	 *            待反转字符串
	 * @return 反转字符串
	 */
	public static String reverse(String s) {
		int len = length(s);
		if (len <= 1)
			return s;
		int mid = len >> 1;
		char[] chars = s.toCharArray();
		char c;
		for (int i = 0; i < mid; ++i) {
			c = chars[i];
			chars[i] = chars[len - i - 1];
			chars[len - i - 1] = c;
		}
		return new String(chars);
	}

}
