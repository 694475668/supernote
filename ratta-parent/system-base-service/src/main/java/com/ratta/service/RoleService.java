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

import com.ratta.dto.CommonIdDTO;
import com.ratta.dto.RoleDTO;
import com.ratta.dto.RoleVagueDTO;
import com.ratta.vo.*;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 角色服务******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
public interface RoleService {

    /**
     * 根据名称分页模糊查询
     *
     * @param roleVagueDTO
     * @param userId
     * @return
     */
    CommonListVO<RoleVO> RoleVOList(RoleVagueDTO roleVagueDTO, String userId);

    /**
     * 添加角色
     *
     * @param roleDTO
     * @param userId
     * @return
     */
    BaseVO addRole(RoleDTO roleDTO, String userId);


    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId
     * @return
     */
    RoleBeanVO queryRoleById(String roleId);

    /**
     * 修改角色信息
     *
     * @param roleId
     * @param roleDTO
     * @param userId
     * @return
     */
    BaseVO updateRole(RoleDTO roleDTO, String roleId, String userId);


    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    BaseVO deleteRole(String roleId);

    /**
     * <p>根据资源id 查询指定的资源</p>
     *
     * @param roleId 角色ID
     * @return 资源集合
     */
    List<ResourceVO> querySelectResource(String roleId);

    /**
     * 授权资源
     * <p>更新角色与资源的对应关系</p>
     *
     * @param commonIdDTO 拼接的数组格式资源ID
     * @param roleId      角色ID
     * @param userId
     * @return
     */
    BaseVO updateRoleResource(String roleId, CommonIdDTO commonIdDTO, String userId);


    /**
     * 获取所有的角色信息
     *
     * @param userId
     * @return
     */
    RoleAllVO findAllRole(String userId);
}
