package com.ratta.mapper;

import com.ratta.domain.ResourceDO;
import com.ratta.domain.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * ******Title:程序的奥秘 ******
 * ******Description: 角色字段Maper映射接口******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:19
 */
public interface RoleMapper {

    /**
     * 添加角色和资源的关系
     *
     * @param roleDO
     * @return
     */
    int addResourceRole(RoleDO roleDO);

    /**
     * 根据资源ID删除角色
     *
     * @param resourceId
     * @return
     */
    int delRoleResource(@Param("resourceId") String resourceId);

    /**
     * 根据角色id删除角色和资源信息
     *
     * @param roleId
     * @return
     */
    int delRoleResourceByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色ID查询所属权限的资源  pid不为空
     *
     * @param roleId
     * @return
     */
    List<ResourceDO> queryResourceByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色ID查询所属权限的资源 pid为空
     *
     * @param roleId
     * @return
     */
    List<ResourceDO> queryResourceById(@Param("roleId") String roleId);

    /**
     * 添加角色
     *
     * @param roleDO
     * @return
     */
    int addRole(RoleDO roleDO);

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId
     * @return
     */
    RoleDO queryRoleById(@Param("roleId") String roleId);

    /**
     * 根据角色PID查询角色信息
     *
     * @param pid
     * @return
     */
    List<RoleDO> queryRoleByPid(@Param("pid") String pid);

    /**
     * 修改角色信息
     *
     * @param roleDO
     * @return
     */
    int updateRole(RoleDO roleDO);


    /**
     * <p>hasUser</p>
     * <p>此角色下是否有用户</p>
     *
     * @param roleId 角色 ID
     * @return 当前角色下是否有用户存在
     */
    long hasUser(String roleId);

    /**
     * <p>delete</p>
     * <p>根据ID删除角色</p>
     *
     * @param roleId 角色ID
     * @return 返回受影响的记录数
     * @throws Exception 异常
     */
    int delRole(String roleId);

    /**
     * 查询该角色已取消的资源
     *
     * @param obj
     * @return
     */
    List<String> queryRoleResource(Map<String, Object> obj);

    /**
     * 删除子角色已经取消的资源
     *
     * @param obj
     * @return
     */
    int deleteRoleResource(Map<String, Object> obj);
}