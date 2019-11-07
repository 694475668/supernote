package com.ratta.service;

import com.ratta.dto.ResourceDTO;
import com.ratta.vo.*;

import java.util.List;


/**
 * @author 刘明
 * 资源服务
 * 2019-06-18
 */
public interface ResourceService {

    /**
     * 查询左边栏资源
     * @param pid
     * @param systemId
     * @param userId
     * @return
     */
    List<ResourceTreeVO> queryLeftResource(String pid, String systemId, String userId);

    /**
     * <p>获取所有资源</p>
     *
     * @param pid 父级
     */
    List<ResourceTreeVO> allTree(String pid);

    /**
     * 根据PID查询上级资源以及name参数查询接口
     *
     * @param pid 父级ID
     * @return
     */
    ResourceVO querySuperiorAndNameAmbiguous(String pid);

    /**
     * 添加资源
     *
     * @param resourceDTO 修改对象
     * @param userId      用户ID
     * @return
     */
    BaseVO addResource(ResourceDTO resourceDTO, String userId);

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    BaseVO delResourceById(String id);

    /**
     * 修改资源
     *
     * @param resourceDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateResource(ResourceDTO resourceDTO, String id, String userId);
}
