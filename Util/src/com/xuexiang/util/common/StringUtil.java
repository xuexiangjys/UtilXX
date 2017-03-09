package com.xuexiang.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理字符串的工具类
 * 
 */
public class StringUtil {
	public static String EMPTY = "";

	public static boolean isEmpty(String x) {
		return x == null || x.length() == 0;
	}

	public static boolean isEmptyTrim(String x) {
		return x == null || x.trim().length() == 0;
	}

	public static String validatorText(String x) {
		return isEmptyTrim(x) ? "未知" : x;
	}
	
	public static String getString(String s) {
		return isEmptyTrim(s) ? "" : s;
	}
	
	/**
	 * 简单的将一个文本转换成密码风格
	 * @param text
	 * @return
	 */
	public static String convertToPasswordStyle(String text) {
		String password = "";
		if (!isEmpty(text)) {
			for (int i = 0; i < text.length(); i++) {
				password += "*";
			}
		}
		return password;
	}
	
	/**
	 *  将一段字符串根据某字段切割然后替换为另一个字段后拼接成几段的另一个字符串
	 * @param content 内容
	 * @param splitWord   根据这个字段切割
	 * @param replaceWord  将上面字段替换成这个字段
	 * @param few       需要切割成几份
	 * @return
	 */
	public static String splitAndReplace(String content, String splitWord, String replaceWord, int few) {
		String[] temp = content.split(splitWord);
		String temp2 = "";
		int count = 0;
		for (int i = 0; i < temp.length; i++) {
			if (count > few) {
				break;
			}
			if (!"".equals(temp[i].trim())) {
				temp2 = temp2 + temp[i] + replaceWord;
				count++;
			}
		}
		content = temp2;
		return content;
	}
	

	/**
	 * 从字符串中删除子串
	 * @param s 需要删除某个子串的字符串
	 * @param s1 需要删除的子串 
	 * @return
	 */
	public static String removeString(String s, String s1) {  
	    int postion = s.indexOf(s1);  
	    int length = s1.length();  
	    int Length = s.length();  
	    String newString = s.substring(0,postion) + s.substring(postion + length, Length);  
	    return newString;//返回已经删除好的字符串  
	} 

	/**
	 * 根据分隔符将String转换为List  例如:aa,bb,cc --> {"aa","bb","cc"}
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public static List<String> stringToList(String str, String separator){
		List<String> list = new ArrayList<String>(); 
		list = Arrays.asList(str.split(separator)); 
		return list;
	}
	
	/**
	 * list<String>的反序列化  例如:[aa,bb,cc] --> {"aa","bb","cc"}
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public static List<String> deserializationStringList(String listStr) {
//		String temp = listStr.substring(1, listStr.length() - 1);
		String temp = replaceBracket(listStr);
		return stringToList(temp, ",");
	}
	
	/**
	 * 过滤字符串中的[和]
	 * @param str
	 * @return
	 */
	public static String replaceBracket(String str) {
        String dest = "";
        if (str != null) {
        	String regEx="[\\[\\]]"; 
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").trim();
        }
        return dest;
    }
	
	/**
	 * 过滤字符串中的空格
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
	 * 截取字节数组
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}
	
}
