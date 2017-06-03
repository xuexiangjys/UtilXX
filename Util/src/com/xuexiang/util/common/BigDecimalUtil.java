package com.xuexiang.util.common;



import java.math.BigDecimal;


/**
 * 主要功能： 资金运算工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class BigDecimalUtil {
	
	
	
	   private static final int DEF_DIV_SCALE = 10;  
	  
	    /** 
	     * 提供精确的加法运算  
	     * @param v1 被加数 
	     * @param v2 加数 
	     * @return 两个参数的和 
	     */  
	    public static double add(double v1, double v2) {  
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	        return b1.add(b2).doubleValue();  
	    }  
	      
	    /** 
	     * 提供精确的减法运算  
	     * @param v1 被减数 
	     * @param v2 减数 
	     * @return 两个参数的差 
	     */  
	    public static double substract(double v1, double v2) {  
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	        return b1.subtract(b2).doubleValue();  
	    }  
	      
	    /** 
	     * 提供精确的乘法运算 
	     * @param v1 被乘数 
	     * @param v2 乘数 
	     * @return 两个参数的积 
	     */  
	    public static double multiply(double v1, double v2) {  
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	        return b1.multiply(b2).doubleValue();  
	    }  
	  
	    /** 
	     * 提供（相对）精确的除法运算,当发生除不尽的情况时, 
	     * 精确到小数点以后10位,以后的数字四舍五入. 
	     * @param v1 被除数 
	     * @param v2 除数 
	     * @return 两个参数的商 
	     */  
	    public static double divide(double v1, double v2) {  
	        return divide(v1, v2, DEF_DIV_SCALE);  
	    }  
	  
	    /** 
	     * 提供（相对）精确的除法运算. 
	     * 当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入. 
	     *  
	     * @param v1 被除数 
	     * @param v2 除数 
	     * @param scale 表示需要精确到小数点以后几位 
	     * @return 两个参数的商 
	     */  
	    public static double divide(double v1, double v2, int scale) {  
	        if (scale < 0) {  
	            throw new IllegalArgumentException("The scale must be a positive integer or zero");  
	        }  
	          
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    }  
	  
	    /** 
	     * 提供精确的小数位四舍五入处理 
	     * @param v 需要四舍五入的数字 
	     * @param scale 小数点后保留几位 
	     * @return 四舍五入后的结果 
	     */  
	    public static double round(double v, int scale) {  
	        if (scale < 0) {  
	            throw new IllegalArgumentException("The scale must be a positive integer or zero");  
	        }  
	          
	        BigDecimal b = new BigDecimal(Double.toString(v));  
	        BigDecimal one = new BigDecimal("1");  
	        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    }  
	  
	    
	    /**
	     * 金额分割，四舍五人金额
	     * @param s
	     * @return
	     */
	    public static String formatMoney(BigDecimal s){
	        String retVal = "";
	        String str = "";
	        boolean is_positive_integer = false;
	        if(null == s){
	        	return "0.00";
	        }

	        if(0 == s.doubleValue()){
	        	return "0.00";
	        }
	        //判断是否正整数
	        if(s.toString().indexOf("-") != -1){
	        	is_positive_integer = true;
	        }else{
	        	is_positive_integer = false;
	        }
	        //是负整数
	        if(is_positive_integer){
	        	//去掉 - 号
	        	s = new BigDecimal(s.toString().substring(1, s.toString().length()));
	        }
	        str = s.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	        StringBuffer sb = new StringBuffer();
		    String[] strs = str.split("\\.");
		    int j = 1;
		    for(int i = 0; i < strs[0].length(); i++){
		          char a = strs[0].charAt(strs[0].length()-i-1);
		          sb.append(a);
	        	  if(j % 3 == 0 && i != strs[0].length()-1){
	        		  sb.append(",");
	        	  }
		          j++;
		    }
		    String str1 = sb.toString();
		    StringBuffer sb1 = new StringBuffer();
		    for(int i = 0; i < str1.length(); i++){
		          char a = str1.charAt(str1.length()-1-i);
		          sb1.append(a);
		    }
		        sb1.append(".");
		        sb1.append(strs[1]);
		        retVal = sb1.toString();
		        
		   if(is_positive_integer){
		        	retVal = "-" + retVal;
		   }
		   		return retVal;
	    }
	    
	    /**
	     * 四舍五人金额
	     * @param s
	     * @return
	     */
	    public static String formatMoney1(BigDecimal s){
	        String retVal = "";
	        String str = "";
	        boolean is_positive_integer = false;
	        if(null == s){
	        	return "0.00";
	        }

	        if(0 == s.doubleValue()){
	        	return "0.00";
	        }
	        //判断是否正整数
	        if(s.toString().indexOf("-") != -1){
	        	is_positive_integer = true;
	        }else{
	        	is_positive_integer = false;
	        }
	        //是负整数
	        if(is_positive_integer){
	        	//去掉 - 号
	        	s = new BigDecimal(s.toString().substring(1, s.toString().length()));
	        }
	        str = s.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	        StringBuffer sb = new StringBuffer();
		    String[] strs = str.split("\\.");
		    int j = 1;
		    for(int i = 0; i < strs[0].length(); i++){
		          char a = strs[0].charAt(strs[0].length()-i-1);
		          sb.append(a);
	        	  if(j % 3 == 0 && i != strs[0].length()-1){
	        		  sb.append("");
	        	  }
		          j++;
		    }
		    String str1 = sb.toString();
		    StringBuffer sb1 = new StringBuffer();
		    for(int i = 0; i < str1.length(); i++){
		          char a = str1.charAt(str1.length()-1-i);
		          sb1.append(a);
		    }
		        sb1.append(".");
		        sb1.append(strs[1]);
		        retVal = sb1.toString();
		        
		   if(is_positive_integer){
		        	retVal = "-" + retVal;
		   }
		   		return retVal;
	    }
	    /**
		 * @param amount   输入的数值 
		 * @param compare   被比较的数字
		 * @return  true 大于被比较的数 
		 */
		public static Boolean compareBigDecimal(String amount , double  compare){
			
			  BigDecimal lenth=new BigDecimal(amount);
		    	if(lenth.compareTo(BigDecimal.valueOf(compare))==-1){
		    		return false;
		    	}
				return true;
		}
	    
	     public static void main(String[] args) {  
	    	 System.out.println("金额0：         " + formatMoney1(new BigDecimal(0)));
	    	 System.out.println("金额0.0 ：         " + formatMoney1(new BigDecimal(0.0)));
	    	 System.out.println("金额0.00：         " + formatMoney1(new BigDecimal(0.00)));
	    	 System.out.println("金额0.58：         " + formatMoney1(new BigDecimal(0.58)));
	    	 System.out.println("金额5.58：         " + formatMoney1(new BigDecimal(5.58)));
	    	 System.out.println("金额5.54：          " + formatMoney1(new BigDecimal(5.54)));
	    	 System.out.println("金额512322.555555111：          " + formatMoney1(new BigDecimal(512322.555555111)));
	    	 System.out.println("金额3423423425.54：     " + formatMoney1(new BigDecimal(3423423425.54)));
	    	 System.out.println("金额3423423425.58：      " + formatMoney1(new BigDecimal(3423423425.58)));
	    	 System.out.println("金额1000000.543453：     " + formatMoney1(new BigDecimal(1000000.543453)));
	    	 System.out.println("金额9343788754.573453：     " + formatMoney1(new BigDecimal(-9343788754.573453)));
	    	 System.out.println("金额9343788756.577：     " + formatMoney1(new BigDecimal(-9343788756.577)));
	    	 System.out.println("金额-343788756.577：     " + formatMoney1(new BigDecimal(-343788756.577)));
	    	 System.out.println("金额-34756.54：     " + formatMoney1(new BigDecimal(-34756.54)));
	    	 System.out.println("金额-34756.556：     " + formatMoney1(new BigDecimal(-34756.556)));
	    	 
	    	 //DateUtils.rollDay(new Date(), -15);
	         //直接使用浮点数进行计算，得到的结果是有问题的  
	         //System.out.println(0.01 + 0.05);  
	           
	         //使用了BigDecimal类进行计算后，可以做到精确计算  
	         //System.out.println(BigDecimalMoney.add(0.0000000000005, 0.00000001));  
	    }  
	     
}
