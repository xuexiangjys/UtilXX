package com.xuexiang.util.common;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * ArrayUtil,一些基于byte数组的操作方法集
 * @author xx
 * @Date 2017-6-9 下午1:15:27
 */
public class ArrayUtil {
	/**
	 * 
	 * 查找并替换指定byte数组
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param search
	 *            of type byte[] 要查找的数组
	 * 
	 * @param replace
	 *            of type byte[] 要替换的数组
	 * 
	 * @param startIndex
	 *            of type int 开始搜索索引
	 * 
	 * @return byte[] 返回新的数组
	 * 
	 * @throws UnsupportedEncodingException
	 *             when
	 */

	public static byte[] arrayReplace(byte[] org, byte[] search, byte[] replace, int startIndex) throws UnsupportedEncodingException {

		int index = indexOf(org, search, startIndex);

		if (index != -1) {

			int newLength = org.length + replace.length - search.length;

			byte[] newByte = new byte[newLength];

			System.arraycopy(org, 0, newByte, 0, index);

			System.arraycopy(replace, 0, newByte, index, replace.length);

			System.arraycopy(org, index + search.length, newByte, index + replace.length, org.length - index - search.length);

			int newStart = index + replace.length;

			// String newstr = new String(newByte, "GBK");

			// System.out.println(newstr);

			if ((newByte.length - newStart) > replace.length) {

				return arrayReplace(newByte, search, replace, newStart);

			}

			return newByte;

		} else {

			return org;

		}

	}

	/**
	 * 
	 * 从指定数组的copy一个子数组并返回
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param to
	 *            合并一个byte[]
	 * 
	 * @return 合并的数据
	 */

	public static byte[] append(byte[] org, byte[] to) {

		byte[] newByte = new byte[org.length + to.length];

		System.arraycopy(org, 0, newByte, 0, org.length);

		System.arraycopy(to, 0, newByte, org.length, to.length);

		return newByte;

	}

	/**
	 * 
	 * 从指定数组的copy一个子数组并返回
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param to
	 *            合并一个byte
	 * 
	 * @return 合并的数据
	 */

	public static byte[] append(byte[] org, byte to) {

		byte[] newByte = new byte[org.length + 1];

		System.arraycopy(org, 0, newByte, 0, org.length);

		newByte[org.length] = to;

		return newByte;

	}

	/**
	 * 
	 * 从指定数组的copy一个子数组并返回
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param from
	 *            起始点
	 * 
	 * @param append
	 *            要合并的数据
	 */

	public static void append(byte[] org, int from, byte[] append) {

		System.arraycopy(append, 0, org, from, append.length);

	}

	/**
	 * 
	 * 从指定数组的copy一个子数组并返回
	 * 
	 * 
	 * 
	 * @param original
	 *            of type byte[] 原数组
	 * 
	 * @param from
	 *            起始点
	 * 
	 * @param to
	 *            结束点
	 * 
	 * @return 返回copy的数组
	 */

	public static byte[] copyOfRange(byte[] original, int from, int to) {

		int newLength = to - from;

		if (newLength < 0)

			throw new IllegalArgumentException(from + " > " + to);

		byte[] copy = new byte[newLength];

		System.arraycopy(original, from, copy, 0,

		Math.min(original.length - from, newLength));

		return copy;

	}

	public static byte[] char2byte(String encode, char... chars) {

		Charset cs = Charset.forName(encode);

		CharBuffer cb = CharBuffer.allocate(chars.length);

		cb.put(chars);

		cb.flip();

		ByteBuffer bb = cs.encode(cb);

		return bb.array();

	}

	/**
	 * 
	 * 查找指定数组的起始索引
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param search
	 *            of type byte[] 要查找的数组
	 * 
	 * @return int 返回索引
	 */

	public static int indexOf(byte[] org, byte[] search) {

		return indexOf(org, search, 0);

	}

	/**
	 * 
	 * 查找指定数组的起始索引
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param search
	 *            of type byte[] 要查找的数组
	 * 
	 * @param startIndex
	 *            起始索引
	 * 
	 * @return int 返回索引
	 */

	public static int indexOf(byte[] org, byte[] search, int startIndex) {

		KMPMatcher kmpMatcher = new KMPMatcher();

		kmpMatcher.computeFailure4Byte(search);

		return kmpMatcher.indexOf(org, startIndex);

		// return ArrayUtil.indexOf(org, search);

	}

	/**
	 * 
	 * 查找指定数组的最后一次出现起始索引
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param search
	 *            of type byte[] 要查找的数组
	 * 
	 * @return int 返回索引
	 */

	public static int lastIndexOf(byte[] org, byte[] search) {

		return lastIndexOf(org, search, 0);

	}

	/**
	 * 
	 * 查找指定数组的最后一次出现起始索引
	 * 
	 * 
	 * 
	 * @param org
	 *            of type byte[] 原数组
	 * 
	 * @param search
	 *            of type byte[] 要查找的数组
	 * 
	 * @param fromIndex
	 *            起始索引
	 * 
	 * @return int 返回索引
	 */

	public static int lastIndexOf(byte[] org, byte[] search, int fromIndex) {

		KMPMatcher kmpMatcher = new KMPMatcher();

		kmpMatcher.computeFailure4Byte(search);

		return kmpMatcher.lastIndexOf(org, fromIndex);

	}

	/**
	 * 
	 * KMP算法类
	 * 
	 * <p/>
	 * 
	 * Created on 2011-1-3
	 */

	static class KMPMatcher {

		private int[] failure;

		private int matchPoint;

		private byte[] bytePattern;

		/**
		 * 
		 * Method indexOf …
		 * 
		 * 
		 * 
		 * @param text
		 *            of type byte[]
		 * 
		 * @param startIndex
		 *            of type int
		 * 
		 * @return int
		 */

		public int indexOf(byte[] text, int startIndex) {

			int j = 0;

			if (text.length == 0 || startIndex > text.length)
				return -1;

			for (int i = startIndex; i < text.length; i++) {

				while (j > 0 && bytePattern[j] != text[i]) {

					j = failure[j - 1];

				}

				if (bytePattern[j] == text[i]) {

					j++;

				}

				if (j == bytePattern.length) {

					matchPoint = i - bytePattern.length + 1;

					return matchPoint;

				}

			}

			return -1;

		}

		/**
		 * 
		 * 找到末尾后重头开始找
		 * 
		 * 
		 * 
		 * @param text
		 *            of type byte[]
		 * 
		 * @param startIndex
		 *            of type int
		 * 
		 * @return int
		 */

		public int lastIndexOf(byte[] text, int startIndex) {

			matchPoint = -1;

			int j = 0;

			if (text.length == 0 || startIndex > text.length)
				return -1;

			int end = text.length;

			for (int i = startIndex; i < end; i++) {

				while (j > 0 && bytePattern[j] != text[i]) {

					j = failure[j - 1];

				}

				if (bytePattern[j] == text[i]) {

					j++;

				}

				if (j == bytePattern.length) {

					matchPoint = i - bytePattern.length + 1;

					if ((text.length - i) > bytePattern.length) {

						j = 0;

						continue;

					}

					return matchPoint;

				}

				// 如果从中间某个位置找，找到末尾没找到后，再重头开始找

				if (startIndex != 0 && i + 1 == end) {

					end = startIndex;

					i = -1;

					startIndex = 0;

				}

			}

			return matchPoint;

		}

		/**
		 * 
		 * 找到末尾后不会重头开始找
		 * 
		 * 
		 * 
		 * @param text
		 *            of type byte[]
		 * 
		 * @param startIndex
		 *            of type int
		 * 
		 * @return int
		 */

		public int lastIndexOfWithNoLoop(byte[] text, int startIndex) {

			matchPoint = -1;

			int j = 0;

			if (text.length == 0 || startIndex > text.length)
				return -1;

			for (int i = startIndex; i < text.length; i++) {

				while (j > 0 && bytePattern[j] != text[i]) {

					j = failure[j - 1];

				}

				if (bytePattern[j] == text[i]) {

					j++;

				}

				if (j == bytePattern.length) {

					matchPoint = i - bytePattern.length + 1;

					if ((text.length - i) > bytePattern.length) {

						j = 0;

						continue;

					}

					return matchPoint;

				}

			}

			return matchPoint;

		}

		/**
		 * 
		 * Method computeFailure4Byte …
		 * 
		 * 
		 * 
		 * @param patternStr
		 *            of type byte[]
		 */

		public void computeFailure4Byte(byte[] patternStr) {

			bytePattern = patternStr;

			int j = 0;

			int len = bytePattern.length;

			failure = new int[len];

			for (int i = 1; i < len; i++) {

				while (j > 0 && bytePattern[j] != bytePattern[i]) {

					j = failure[j - 1];

				}

				if (bytePattern[j] == bytePattern[i]) {

					j++;

				}

				failure[i] = j;

			}

		}

	}

	public static void main(String[] args) {

		try {

			byte[] org = "kadeadedcfdededghkk".getBytes("GBK");

			byte[] search = "kk".getBytes("GBK");

			int last = lastIndexOf(org, search, 19);

			long t1 = 0;

			long t2 = 0;

			int f1 = 0;

			int f2 = 0;

			for (int i = 0; i < 10000; i++) {

				long s1 = System.nanoTime();

				f1 = indexOf(org, search, 0);

				long s2 = System.nanoTime();

				f2 = ArrayUtil.indexOf(org, search);

				long s3 = System.nanoTime();

				t1 = t1 + (s2 - s1);

				t2 = t2 + (s3 - s2);

			}

			System.out.println("kmp=" + t1 / 10000 + ",ali=" + t2 / 10000);

			System.out.printf("f1=" + f1 + ",f2=" + f2);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

	}

}
