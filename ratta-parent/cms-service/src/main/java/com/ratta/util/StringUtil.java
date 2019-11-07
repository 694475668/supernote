package com.ratta.util;

public class StringUtil {

	/**
	 * 根据图片链接地址，获取图片名字
	 * @param fileUrl
	 * @return filename
	 */
	public static String getFileNameByUrl(String fileUrl) {
		String[] arr = fileUrl.split("\\?");
		int i = arr[0].lastIndexOf("/");
		return arr[0].substring(i+1);
	}
}
