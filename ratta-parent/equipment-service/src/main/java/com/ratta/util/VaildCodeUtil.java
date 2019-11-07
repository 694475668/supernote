package com.ratta.util;

import java.util.concurrent.ThreadLocalRandom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VaildCodeUtil {
    
	/** 
	 * 生成6位数的验证码
	 * 
	 * @return
	 */
	public static String sixNumber() {
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			char ch = str.charAt(ThreadLocalRandom.current().nextInt(
					str.length()));
			sb.append(ch);
		}
		log.info("验证码：" + sb.toString());
		return sb.toString();
	}

	/**
	 * 生成8位随机数
	 * 
	 * @return
	 */
	public static String eightRandom() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			char ch = str.charAt(ThreadLocalRandom.current().nextInt(
					str.length()));
			sb.append(ch);
		}
		log.info("随机数：" + sb.toString());
		return sb.toString();
	}

	/**
	 * 生成3位随机数
	 * 
	 */
	public static String threeNumber() {
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(3);
		for (int i = 0; i < 3; i++) {
			char ch = str.charAt(ThreadLocalRandom.current().nextInt(
					str.length()));
			sb.append(ch);
		}
		log.info("验证码：" + sb.toString());
		return sb.toString();
	}
	
	/**
     * 生成6位数的验证码
     * @return
     */
    public static String fourRandom() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(ThreadLocalRandom.current().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
