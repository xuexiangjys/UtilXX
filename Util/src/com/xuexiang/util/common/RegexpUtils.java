package com.xuexiang.util.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 创建时间：2017-1-8 上午12:11:49 项目名称：Util
 * 
 * @author xuexiang 文件名称：RegexpUtils.java
 **/
public class RegexpUtils {
	private RegexpUtils() {
	}

	/**
	 * 匹配图象
	 * <p/>
	 * <p/>
	 * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
	 * <p/>
	 * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp
	 * <p/>
	 * <p/>
	 * 不匹配: c:/admins4512.gif
	 */
	public static final String ICON_REGEXP = "^(/{0,1}//w){1,}//.(gif|dmp|png|jpg)$|^//w{1,}//.(gif|dmp|png|jpg)$";

	/**
	 * 匹配email地址
	 * <p/>
	 * <p/>
	 * 格式: XXX@XXX.XXX.XX
	 * <p/>
	 * 匹配 : foo@bar.com 或 foobar@foobar.com.au
	 * <p/>
	 * 不匹配: foo@bar 或 $$$@bar.com
	 */
	public static final String EMAIL_REGEXP = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";

	/**
	 * 匹配并提取url
	 * <p/>
	 * <p/>
	 * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
	 * <p/>
	 * 匹配 : http://www.suncer.com 或news://www
	 * <p/>
	 * 不匹配: c:/window
	 */
	public static final String URL_REGEXP = "(//w+)://([^/:]+)(://d*)?([^#//s]*)";

	/**
	 * 匹配并提取http
	 * <p/>
	 * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或
	 * https://XXX
	 * <p/>
	 * 匹配 : http://www.suncer.com:8080/index.html?login=true
	 * <p/>
	 * 不匹配: news://www
	 */
	public static final String HTTP_REGEXP = "(http|https|ftp)://([^/:]+)(://d*)?([^#//s]*)";

	/**
	 * 匹配日期
	 * <p/>
	 * <p/>
	 * 格式(首位不为0): XXXX-XX-XX或 XXXX-X-X
	 * <p/>
	 * <p/>
	 * 范围:1900--2099
	 * <p/>
	 * <p/>
	 * 匹配 : 2005-04-04
	 * <p/>
	 * <p/>
	 * 不匹配: 01-01-01
	 */
	public static final String DATE_BARS_REGEXP = "^((((19){1}|(20){1})\\d{2})|\\d{2})-[0,1]?\\d{1}-[0-3]?\\d{1}$";

	/**
	 * 匹配日期
	 * <p/>
	 * <p/>
	 * 格式: XXXX/XX/XX
	 * <p/>
	 * <p/>
	 * 范围:
	 * <p/>
	 * <p/>
	 * 匹配 : 2005/04/04
	 * <p/>
	 * <p/>
	 * 不匹配: 01/01/01
	 */
	public static final String DATE_SLASH_REGEXP = "^[0-9]{4}/(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))$";

	/**
	 * 匹配电话
	 * <p/>
	 * <p/>
	 * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或
	 * <p/>
	 * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或
	 * XXXXXXXXXXX(11位首位不为0)
	 * <p/>
	 * <p/>
	 * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或
	 * 010-12345678 或 12345678912
	 * <p/>
	 * <p/>
	 * 不匹配: 1111-134355 或 0123456789
	 */
	public static final String PHONE_REGEXP = "^[1]([0-9]{2})[0-9]{8}$";
	/**
	 * 匹配姓名,只能是中文或英文，不能为数字或其他字符，汉字和字母不能同时出现
	 */
	public static final String NAME_REGEXP = "(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10}))";
	/**
	 * 匹配身份证
	 * <p/>
	 * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
	 * XXXXXXXXXXXXXXXXXX(18位)
	 * <p/>
	 * 匹配 : 0123456789123
	 * <p/>
	 * 不匹配: 0123456
	 */
	public static final String ID_CARD_REGEXP = "^//d{10}|//d{13}|//d{15}|//d{18}$";

	/**
	 * 匹配邮编代码
	 * <p/>
	 * 格式为: XXXXXX(6位)
	 * <p/>
	 * 匹配 : 012345
	 * <p/>
	 * 不匹配: 0123456
	 */
	public static final String ZIP_REGEXP = "^[0-9]{6}$";// 匹配邮编代码

	/**
	 * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠/
	 * 即空格,制表符,回车符等 )
	 * <p/>
	 * 格式为: x 或 一个一上的字符
	 * <p/>
	 * 匹配 : 012345
	 * <p/>
	 * 不匹配: 0123456 // ;,:-<>//s].+$";//
	 */
	public static final String NON_SPECIAL_CHAR_REGEXP = "^[^'/";
	// 匹配邮编代码

	/**
	 * 匹配非负整数（正整数 + 0)
	 */
	public static final String NON_NEGATIVE_INTEGERS_REGEXP = "^//d+$";

	/**
	 * 匹配不包括零的非负整数（正整数 > 0)
	 */
	public static final String NON_ZERO_NEGATIVE_INTEGERS_REGEXP = "^[1-9]+//d*$";

	/**
	 * 匹配正整数
	 */
	public static final String POSITIVE_INTEGER_REGEXP = "^[0-9]*[1-9][0-9]*$";

	/**
	 * 匹配非正整数（负整数 + 0）
	 */
	public static final String NON_POSITIVE_INTEGERS_REGEXP = "^((-//d+)|(0+))$";

	/**
	 * 匹配负整数
	 */
	public static final String NEGATIVE_INTEGERS_REGEXP = "^-[0-9]*[1-9][0-9]*$";

	/**
	 * 匹配整数
	 */
	public static final String INTEGER_REGEXP = "^-?//d+$";

	/**
	 * 匹配非负浮点数（正浮点数 + 0）
	 */
	public static final String NON_NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^//d+(//.//d+)?$";

	/**
	 * 匹配正浮点数
	 */
	public static final String POSITIVE_RATIONAL_NUMBERS_REGEXP = "^(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*))$";

	/**
	 * 匹配非正浮点数（负浮点数 + 0）
	 */
	public static final String NON_POSITIVE_RATIONAL_NUMBERS_REGEXP = "^((-//d+(//.//d+)?)|(0+(//.0+)?))$";

	/**
	 * 匹配负浮点数
	 */
	public static final String NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^(-(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

	/**
	 * 匹配浮点数
	 */
	public static final String RATIONAL_NUMBERS_REGEXP = "^(-?//d+)(//.//d+)?$";

	/**
	 * 匹配由26个英文字母组成的字符串
	 */
	public static final String LETTER_REGEXP = "^[A-Za-z]+$";

	/**
	 * 匹配由26个英文字母的大写组成的字符串
	 */
	public static final String UPWARD_LETTER_REGEXP = "^[A-Z]+$";

	/**
	 * 匹配由26个英文字母的小写组成的字符串
	 */
	public static final String LOWER_LETTER_REGEXP = "^[a-z]+$";

	/**
	 * 匹配由数字和26个英文字母组成的字符串
	 */
	public static final String LETTER_NUMBER_REGEXP = "^[A-Za-z0-9]+$";

	/**
	 * 匹配由数字、26个英文字母或者下划线组成的字符串
	 */
	public static final String LETTER_NUMBER_UNDERLINE_REGEXP = "^//w+$";

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static final boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 验证银卡卡号
	 * 
	 * @param cardNo
	 * @return
	 */
	public static final boolean isBankCard(String cardNo) {
		Pattern p = Pattern
				.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
		Matcher m = p.matcher(cardNo);

		return m.matches();
	}

	/**
	 * 身份证验证
	 * 
	 * @param idCard
	 * @return
	 */
	public static final boolean validateIdCard(String idCard) {
		// 15位和18位身份证号码的正则表达式
		String regIdCard = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
		Pattern p = Pattern.compile(regIdCard);
		return p.matcher(idCard).matches();
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @return
	 */
	public static final String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhMMss");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static final String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	/**
	 * 格式化银行卡
	 * 
	 * @param cardNo
	 * @return
	 */
	public static final String formatCard(String cardNo) {
		String card = "";
		card = cardNo.substring(0, 4) + " **** **** ";
		card += cardNo.substring(cardNo.length() - 4);
		return card;
	}

	/**
	 * 银行卡后四位
	 * 
	 * @param cardNo
	 * @return
	 */
	public static final String formatCardEndFour(String cardNo) {
		String card = "";
		card += cardNo.substring(cardNo.length() - 4);
		return card;
	}

	public static final String fomatMoney(float money) {
		DecimalFormat myformat = new DecimalFormat();
		if (money == 0) {
			return "0.00";
		} else {
			myformat.applyPattern("##,###.00");
			return myformat.format(money);
		}
	}

	/**
	 * 根据指定的图像路径和大小来获取缩略图
	 */
	public static final Bitmap getImageToBitmap(String imagePath, int width,
			int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false; // 设为 false
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		// 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		return bitmap;
	}
}
