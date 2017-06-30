package com.cgutech.filesystem.utils;


import java.io.UnsupportedEncodingException;

import com.changhong.security.tea.tea;

/**
 * TEA加解密工具类
 * 
 * @author Becan 
 * 
 */
public class TeaUtil {
	
	// 密钥
	private static final String KEY = "cgufilesystem";
	// 加密轮数
	private static final int ROUND = 16;

	/**
	 * 仅适用于cgu file stysem的加密
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月5日 下午3:40:26
	 * @parameter
	 * @retrun String
	 */
	public static String encrypt(String str) {
		try {
			return tea.hex_en(str, KEY, ROUND);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 仅适用于cgu file stysem的解密
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月5日 下午3:40:49
	 * @parameter
	 * @retrun String
	 */
	public static String decrypt(String str) {
		try {
			return tea.hex_de(str, KEY, ROUND);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @param key
	 * @param round
	 * @return
	 */
	public static String encrypt(String str, String key, int round) {
		try {
			return tea.hex_en(str, key, round);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param str
	 * @param key
	 * @param round
	 * @return
	 */
	public static String decrypt(String str, String key, int round) {
		try {
			return tea.hex_de(str, key, round);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
