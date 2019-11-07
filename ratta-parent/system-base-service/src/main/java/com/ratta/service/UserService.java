package com.ratta.service;
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

import com.ratta.domain.UserDO;
import com.ratta.dto.*;
import com.ratta.vo.*;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:52
 */
public interface UserService {
    /**
     * <p>login</p>
     * <p>用户登录</p>
     *
     * @param userLoginDTO 用户名 密码
     * @return 登陆的用户
     */
    BaseVO login(UserLoginDTO userLoginDTO);

    /**
     * <p>isLocked</p>
     * <p>查看指定用户是否被锁定</p>
     *
     * @param id 要查看的用户
     * @return true 已锁定 false 未锁定
     */
    boolean isLocked(Long id);

    /**
     * 验证登录的用户是否是可用
     *
     * @param userId
     * @return
     */
    BaseVO verifvPassword(String userId);

    /**
     * get
     * 根据用户id查询用户
     *
     * @param id 用户ID
     * @return 用户
     */
    UserDO getUserById(Long id);


    /**
     * editCurrentUserPwd 修改自己的密码
     *
     * @param userId  用户ID
     * @param userDTO 原始密码  新密码
     * @return
     */
    BaseVO editCurrentUserPwd(String userId, UserDTO userDTO);

    /**
     * 判断旧密码是否一致
     *
     * @param userId
     * @param userDTO
     * @return
     */
    BaseVO checkOldPwd(String userId, UserDTO userDTO);

    /**
     * dataGrid 获取用户数据信息
     *
     * @param userVagueDTO 参数
     * @param userId       参数
     * @return
     */
    CommonListVO<UserVO> dataGrid(UserVagueDTO userVagueDTO, String userId);

    /**
     * 添加用户信息
     *
     * @param userAddDTO
     * @param userId
     * @return
     */
    BaseVO addUser(UserAddDTO userAddDTO, String userId);

    /**
     * 启用
     *
     * @param commonIdDTO 需要启用的ID,批量
     * @param userId      当前登陆的用户ID
     * @return
     */
    BaseVO open(CommonIdDTO commonIdDTO, String userId);

    /**
     * 停用
     *
     * @param commonIdDTO
     * @param userId
     * @return
     */
    BaseVO close(CommonIdDTO commonIdDTO, String userId);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    UserBeanVO getBean(Long id);

    /**
     * 修改用户
     *
     * @param userUpdateDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateUser(UserUpdateDTO userUpdateDTO, Long id, String userId);

    /**
     * 修改密码
     *
     * @param userPwdDTO
     * @param userId
     * @return
     */
    BaseVO updatePwd(UserPwdDTO userPwdDTO, String userId);

    /**
     * 删除用户信息
     *
     * @param id
     * @param userId
     * @return
     */
    BaseVO deleteUser(Long id, String userId);

    /**
     * <p>unlock</p>
     * <p>解锁</p>
     *
     * @param id     要解锁的用户id
     * @param userId 用户id
     * @return 1 解锁成功 0 解锁失败
     */
    BaseVO unlock(Long id, Long userId);

    /**
     * 授权
     *
     * @param userGrantDTO
     * @param userId
     * @return
     */
    BaseVO grant(UserGrantDTO userGrantDTO, String userId);

    /**
     * 根据用户ID获取选中的角色
     *
     * @param id
     * @return
     */
    UserRoleVO findRoleByUserId(String id);

    /**
     * 多个用户id获取用户信息
     *
     * @param commonIdDTO
     * @return
     */
    CommonListVO<UserPartVO> getUserArray(CommonIdDTO commonIdDTO);

    /**
     * 批量授权验证是否是自己
     *
     * @param userGrantDTO
     * @param userId
     * @return
     */
    BaseVO userGrant(UserGrantDTO userGrantDTO, String userId);

    /**
     * 解锁验证是否是自己
     *
     * @param id
     * @param userId
     * @return
     */
    BaseVO userUnlock(Long id, Long userId);
}
