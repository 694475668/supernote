package com.ratta.util;

import java.util.UUID;

/**
 * 生成id工具类
 * 
 * @author gxy
 *
 */
public class GenerateUtil {

	public static String getInnerName(String fileName) {

		UUID id = UUID.randomUUID();
		String innerName = "";
		String type = "";
		if (fileName.lastIndexOf(".") != -1) {
			type = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		if (type.equals(".pdf") || type.equals(".zip")) {
			innerName = fileName;
		} else {
			innerName = id + type;
		}
		return innerName;
	}
}
