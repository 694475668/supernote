package com.ratta.util;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;


/**
 * @author yll
 *
 */
public class FormatUtil {

	public static void main(String[] args) {
	    System.out.println(isEmail("jiang_hu.i@21cn.com"));
	}
	 /**
              * 验证手机号码格式是否正确
     * @param mobiles
     * @return  true 表示正确  false表示不正确
     */
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
             *  验证邮箱格式是否正确
     * @param string
     * @return  true 表示正确  false表示不正确
     */
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
    public static boolean isNum(String phone) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    
	/**
	 * 当国家码为空时，默认为86
	 * 
	 * @param countryCode 国家码
	 * @return countryCode
	 */
	public static String getCountryCode(String countryCode) {
		if (StringUtils.isEmpty(countryCode)) {
			countryCode = "86";
		}
		return countryCode;

	}
	
	/**
	 * 加密url
	 */
	public static String encrypt(String innerName) {
		String url = getBase64(innerName);
		url = url.substring(0, 10) + VaildCodeUtil.eightRandom() + url.substring(10);
		return url; 
	}
	
	/**
	 * getBase64加密
	 */
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			byte[] base64Str = Base64.encodeBase64(b, false);
			s = new String(base64Str);
		}
		return s;
	}
	
	/**
	 * UUID生成随机数
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

}
