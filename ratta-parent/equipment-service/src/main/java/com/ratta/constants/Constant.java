package com.ratta.constants;

public class Constant {

    /**
     * 常用常量
     */
    public static final String Y = "Y";
    public static final String N = "N";

    /**
     * jwt 毫秒
     */
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    /**
     * 1小时
     */
    public static final int JWT_TTL = 3600000;
    /**
     * 30天
     */
    public static final long JWT_DAY_TTL = 2592000000L;
    /**
     * 55分钟
     */
    public static final int JWT_REFRESH_INTERVAL = 3300000;
    /**
     * 12小时
     */
    public static final int JWT_REFRESH_TTL = 43200000;
    /**
     * 永不失效
     */
    public static final int JWT_NEVER_FAOLING = -1;

    /**
     * 时间 秒
     */
    /**
     * 12小时
     */
    public static final int PASSWORD_ERROR_COUNT_TIMEOUT = 43200;
    /**
     * 一天
     */
    public static final int JWT_REFRESH_TIMEOUT = 86400;

    /**
     * REDIS
     */
    /**
     * cookies里面自定义的键
     */
    public static final String REDISKEY = "redisKey";
    /**
     * 登录时的随机数
     */
    public static final String RANDOM = "SN100";
    /**
     * 密码错误次数
     */
    public static final String PASSWORD_ERROR_COUNT = "_password_error_count";
    /**
     * 账号锁定
     */
    public static final String ACCOUNT_LOCK = "_account_lock";
    /**
     * 存储token
     */
    public static final String TOKEN = "_token";
    /**
     * 存储token的key
     */
    public static final String SSO_KEY = "_sso_key";
    public static final String LINE = "_";
    /**
     * 账号(手机号或邮箱)
     */
    public static final String ACCOUNT = "_account";
    /**
     * 图形验证码
     */
    public static final String VERIFYCODE = "_verifyCode";
    /**
     * 短信或邮箱验证码
     */
    public static final String VALIDCODE = "_validCode";
    /**
     * 1 为普通日志
     */
    public static final String ORDINARYLOGS = "1";

    /**
     * 2  为审阅
     */
    public static final String FLAG = "2";

    /**
     * 0：未下载
     */
    public static final String ISDOWNLOAD = "0";
    /**
     *字符最大长度
     */
    public static final int LENGTHLIMIT = 1000;

    /**
     * 状态
     */
    public static final String STATUS="true";
    
    //kafka通道定义常量
    public static final String EQUIPMENT="equipment";
    public static final String ACTIVATE="activate";
    public static final String FILEHISSYNC="filehissync";
    public static final String DELETE="delete";
    public static final String FILE="file";
    public static final String ADD="add";
}

