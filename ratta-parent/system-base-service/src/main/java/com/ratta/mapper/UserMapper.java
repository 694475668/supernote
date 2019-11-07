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

import com.ratta.domain.RoleDO;
import com.ratta.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 用户映射接口******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param pwd
     * @return 用户
     */
    UserDO getUserByNameAndPWD(@Param("username") String username, @Param("pwd") String pwd);

    /**
     * getUserByName
     * 根据用户名查询用户(区分大小写)
     *
     * @param username 用户名
     * @return
     */
    UserDO getUserByName(@Param("username") String username);

    /**
     * <p>errorLogin</p>
     * <p>更新用户密码输错次数</p>
     *
     * @param id 用户表id
     * @return 数据库影响行数
     */
    int errorLogin(@Param("id") Long id);

    /**
     * freshErrorLogin
     * 清楚登陆密码输错次数
     *
     * @param id 用户id
     * @return 受影响的记录数
     */

    int freshErrorLogin(@Param("id") Long id);

    /**
     * getLocked
     * 根据用户id判断该用户是否锁定
     *
     * @param id 用户id
     * @param
     * @return 0表示没有锁定, 1表示锁定
     */
    long selectLocked(@Param("id") Long id);


    /**
     * <p>lockUser</p>
     * <p>锁定用户</p>
     *
     * @param id
     */
    int lockUser(@Param("id") Long id);

    /**
     * get
     * 根据用户id查询用户
     *
     * @param id 用户ID
     * @return 用户
     */
    UserDO getUserById(@Param("id") Long id);

    /**
     * 查询剩余密码登录次数
     *
     * @param map
     * @return
     */
    long selectErrorCounts(Map<String, Object> map);


    /**
     * 修改用户信息
     *
     * @param userDO
     * @return
     */
    int updateUser(UserDO userDO);


    /**
     * 根据pid查询用户信息
     *
     * @param id 参数
     * @return
     */
    List<UserDO> queryUserByPid(@Param("id") Long id);

    /**
     * 添加用户
     *
     * @param userDO
     * @return
     */
    int saveUser(UserDO userDO);


    /**
     * 获取序列的最大值getMaxSEQ
     *
     * @return 最大的序列值
     */
    long getMaxSEQ();

    /**
     * <p>saveUserRoles</p>
     * <p>保存用户与角色对应关系</p>
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 数据库受影响的记录数
     */
    int saveUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 删除用户的角色数据
     *
     * @param id
     * @return
     */
    int delUserRoles(@Param("id") Long id);

    /**
     * 物理删除用户
     *
     * @param id
     * @return
     */
    int delUser(@Param("id") Long id);

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId
     * @return
     */
    List<RoleDO> queryRoleByUserId(@Param("userId") String userId);

    /**
     * 多个用户id获取用户信息
     *
     * @param userId
     * @return
     */
    List<UserDO> getUserArray(String[] userId);
}
