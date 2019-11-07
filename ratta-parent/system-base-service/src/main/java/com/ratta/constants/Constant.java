package com.ratta.constants;

/**
 * @author 刘明
 */
public class Constant {

	/**
	 * 常用常量
	 */
	public static final String Y = "Y";
	public static final String N = "N";

	/**
	 * 数据名称
	 */
	public static final String TRESOURCETYPE_ID = "TRESOURCETYPE_ID";

	/**
	 * 状态1：正常，0：锁定，2：停用
	 */
	public static final String DISCONTINUESTATUS = "2";
	/**
	 * 状态1：正常，0：锁定，2：停用
	 */
	public static final String LOCKSTATUS = "0";

	/**
	 * 是否已经修改密码:0未修改,1已修改
	 */
	public static final String MODIFYPWD = "0";

	/**
	 * 序号
	 */
	public static final String REFERENCESERIAL = "01";

	/**
	 * 参数值
	 */
	public static final String REFERENCENAME = "PASSWORD_COUNT";

	/**
	 * 用户改密不可与最近N次密码相同
	 */
	public static final String SAMEPASSCOUNTS = "SAME_PASS_COUNTS";

	/**
	 * 清楚数据库
	 */
	public static final String DATABASECLEAN = "database_clean";
	/**
	 * 清除设备日志
	 */
	public static final String EQUIPMENTLOGCLEAN = "equipmentLog_clean";
	/**
	 * 清除cms垃圾文件
	 */
	public static final String CMSCLEAN = "cms_clean";
	/**
	 * 密钥
	 */
	public static final String KEY = "supernote";
	/**
	 * 1小时
	 */
	public static final int JWT_TTL = 86400000;

	/**
	 * cookies里设置的key
	 */
	public static final String REDISKEY = "redisKey";

	// kafka通道定义常量
	public static final String EQUIPMENTLOG = "equipmentlog";
	public static final String FILE = "file";
	public static final String DELETE = "delete";
	public static final String USERLONGIN = "userlongin";
}
