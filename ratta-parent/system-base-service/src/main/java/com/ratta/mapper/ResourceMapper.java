package com.ratta.mapper;

import com.ratta.domain.ResourceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ******Title:程序的奥秘 ******
 * ******Description: 资源字段Maper映射接口******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:19
 */
public interface ResourceMapper {
    /**
     * 查询所有资源集合
     *
     * @param pid
     * @return
     */
    List<ResourceDO> queryAllResourceByParam(@Param("pid") String pid);

    /**
     * 查询当前用户所属的左边栏展示资源
     *
     * @param userId
     * @param systemId
     * @param pid
     * @return
     */
    List<ResourceDO> queryLeftResource(@Param("pid") String pid, @Param("systemId") String systemId, @Param("userId") String userId);

    /**
     * 根据父级ID和名称查询
     *
     * @param pid
     * @param name
     * @return
     */
    ResourceDO queryResourceByParam(@Param("pid") String pid);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ResourceDO queryResourceById(@Param("id") String id);

    /**
     * 添加资源
     *
     * @param resourceDO
     * @return
     */
    int addResource(ResourceDO resourceDO);

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    int delResource(@Param("id") String id);

    /**
     * 修改资源
     *
     * @param resourceDO
     * @return
     */
    int updateResource(ResourceDO resourceDO);

    /**
     * <p>hashRoleOwnResources</p>
     * <p>判断资源是否被某个角色所拥有</p>
     *
     * @param id 资源ID
     * @return 是否有角色拥有当前资源
     */
    Long hasRoleOwnResource(@Param("id") String id);
}