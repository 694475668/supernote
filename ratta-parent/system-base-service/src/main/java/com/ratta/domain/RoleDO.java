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
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 角色表对象Role******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/11 0011 22:30
 */
@Data
@EqualsAndHashCode
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;


    private String pid;

    /**
     * 角色描述
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 创建人员
     */
    private String createUser;
    /**
     * 修改人员
     */
    private String modifyUser;

    /**
     * 资源参数对象仅仅用来传输参数
     */
    private ResourceDO resourceDO;
}
