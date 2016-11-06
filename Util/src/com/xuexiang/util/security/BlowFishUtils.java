package com.xuexiang.util.security;

public class BlowFishUtils {
	private static final String private_key = "aecg_encryptor_key";
	
	/***
	 *  算法加密处理
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		byte[] keyByte = private_key.getBytes();
		Blowfish bf1 = new Blowfish();
		bf1.init(true, keyByte);
		return Blowfish.byteArr2HexStr(bf1.encrypt(str));
	}

	/***
	 *  解密处理
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str) throws Exception {
		byte[] keyByte = private_key.getBytes();
		Blowfish bf2 = new Blowfish();
		bf2.init(true, keyByte);
		return bf2.decryptString(Blowfish.hexStr2ByteArr(str));
	}

}
