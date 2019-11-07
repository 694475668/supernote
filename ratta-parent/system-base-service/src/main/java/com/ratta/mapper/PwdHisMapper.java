package com.ratta.mapper;
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

import com.ratta.domain.PwdHisDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 修改密码历史映射接口******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
public interface PwdHisMapper {

    /**
     * <p>query</p>
     * <p>查询最近N次</p>
     *
     * @param times    查询最近的改密次数
     * @param username 用户名
     * @return 最近N此的改密信息
     */
    List<PwdHisDO> query(@Param("username") String username, @Param("times") Integer times);


    /**
     * <p>save</p>
     * <p>添加密码历史操作</p>
     *
     * @param pwdHisDO 密码历史操作
     * @return 数据库影响行数
     */
    int savePwd(PwdHisDO pwdHisDO);
}
