package com.ratta.domain;
/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 用户表对象User******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:39
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long pid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;


    private String roleId;


    private String roleName;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 登录密码
     */
    private String pwd;
    /**
     * 登录密码错误次数
     */
    private Integer counts;
    /**
     * 状态1：正常，0：锁定，2：停用
     */
    private String status;
    /**
     * 有效标识（Y：有效，N：无效）
     */
    private String isActive;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人员
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人员
     */
    private String updateUser;
    /**
     * 是否已经修改密码:0未修改,1已修改
     */
    private String modifyPwd;

    /**
     * 用户所属角色   一对多的关系
     */
    private List<RoleDO> roleVOList = new ArrayList<>();

}
