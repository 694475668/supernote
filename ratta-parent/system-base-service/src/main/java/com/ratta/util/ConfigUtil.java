package com.ratta.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 刘明
 */
public class ConfigUtil {

    private final static Properties ps = new Properties();
    /**
     * nfs文件目录
     */
    public static final String NFS_DIR_LOGBACK = "nfs_dir_logback";
    /**
     * tomcat备份路径
     */
    public static final String NFS_DIR_LOGBACK_TOMCAT = "nfs_dir_logback_tomcat";

    private ConfigUtil() {

    }

    static {
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
                ps.load(is);
            } else {
                String path = System.getProperty("user.dir") + File.separator + "etc" + File.separator
                        + "config.properties";
                InputStream is = new FileInputStream(new File(path));
                ps.load(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过键获取值
     *
     * @param key
     * @return
     */
    public static final String get(String key) {
        if (ps == null) {
            return null;
        }
        return ps.getProperty(key).trim();
    }
}
