package com.ratta.service.impl;

import com.ratta.domain.DictionaryDO;
import com.ratta.domain.ResourceDO;
import com.ratta.domain.RoleDO;
import com.ratta.dto.ResourceDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.ResourceMapper;
import com.ratta.mapper.RoleMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.service.ResourceService;
import com.ratta.util.DateConvertUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.ResourceTreeVO;
import com.ratta.vo.ResourceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 刘明
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    /**
     * 注入ResourceMapper对象
     */
    @Autowired
    private ResourceMapper resourceMapper;


    /**
     * 注入RoleMapper对象
     */
    @Resource
    private RoleMapper roleMapper;


    /**
     * 注入UserMapper对象
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 返回前端的树形数据集合
     *
     * @param pid
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ResourceTreeVO> queryLeftResource(String pid, String systemId, String userId) {

        //创建ResourceTreeVO集合对象
        List<ResourceTreeVO> resourceTree = new ArrayList<>();

        //根据pid查询资源信息
        List<ResourceDO> resourceDOList = resourceMapper.queryLeftResource(pid, systemId, userId);

        //遍历集合对象
        for (ResourceDO resourceDO : resourceDOList) {
            //创建ResourceTreeVO对象
            ResourceTreeVO resourceTreeVO = new ResourceTreeVO();
            resourceTreeVO.setId(resourceDO.getId());
            resourceTreeVO.setLabel(resourceDO.getName());
            //创建map集合用来存树形自定义参数
            Map<String, Object> treeMap = new HashMap<>();
            treeMap.put("url", resourceDO.getUrl());
            //获取上级资源
            ResourceDO queryParentDO = resourceMapper.queryResourceByParam(resourceDO.getPid());
            //判断是否有上级节点,如果没有那这个节点就是根节点
            if (queryParentDO == null) {
                treeMap.put("superior", "");
            } else {
                treeMap.put("superior", queryParentDO.getName());
            }
            treeMap.put("remark", resourceDO.getRemark());

            //设置自定义属性
            resourceTreeVO.setAttributes(treeMap);

            //递归查询是否还有子节点
            pid = resourceDO.getId();
            List<ResourceTreeVO> resourceTreeVOS = queryLeftResource(pid, systemId, userId);
            //有子节点就添加到子节点集合中
            resourceTreeVO.setChildren(resourceTreeVOS);

            //把设置好的ResourceTreeVO对象添加到集合中
            resourceTree.add(resourceTreeVO);
        }
        return resourceTree;
    }


    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ResourceTreeVO> allTree(String pid) {

        //创建ResourceTreeVO集合对象
        List<ResourceTreeVO> resourceTree = new ArrayList<>();

        //根据pid查询资源信息
        List<ResourceDO> resourceDOList = resourceMapper.queryAllResourceByParam(pid);

        //遍历集合对象
        for (ResourceDO resourceDO : resourceDOList) {

            //创建ResourceTreeVO对象
            ResourceTreeVO resourceTreeVO = new ResourceTreeVO();
            resourceTreeVO.setId(resourceDO.getId());
            resourceTreeVO.setLabel(resourceDO.getName());
            //创建map集合用来存树形自定义参数
            Map<String, Object> treeMap = new HashMap<>();
            treeMap.put("url", resourceDO.getUrl());
            //设置资源类型名字
            treeMap.put("resourceType", resourceDO.getDictionaryDO().getValueMeaning());
            treeMap.put("systemType", resourceDO.getDictionaryDO().getSystemValueCn());
            //获取上级资源
            ResourceDO queryParentDO = resourceMapper.queryResourceByParam(resourceDO.getPid());
            //判断是否有上级节点,如果没有那这个节点就是根节点
            if (queryParentDO == null) {
                treeMap.put("superior", "");
            } else {
                treeMap.put("superior", queryParentDO.getName());
            }
            treeMap.put("remark", resourceDO.getRemark());

            //设置自定义属性
            resourceTreeVO.setAttributes(treeMap);
            //递归查询是否还有子节点
            pid = resourceDO.getId();
            List<ResourceTreeVO> resourceTreeVOS = allTree(pid);
            //有子节点就添加到子节点集合中
            resourceTreeVO.setChildren(resourceTreeVOS);

            //把设置好的ResourceTreeVO对象添加到集合中
            resourceTree.add(resourceTreeVO);
        }
        return resourceTree;
    }

    /**
     * 根据ID查询资源
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResourceVO querySuperiorAndNameAmbiguous(String id) {
        ResourceVO resourceVO = new ResourceVO();
        ResourceDO resourceDO = resourceMapper.queryResourceById(id);
        //获取父级pName
        ResourceDO getResourceDO = resourceMapper.queryResourceById(resourceDO.getPid());
        if (resourceDO == null) {
            resourceVO.setSuccess(false);
            resourceVO.setErrorCode(ErrorCodeEnum.E0706.getKey());
            resourceVO.setErrorMsg(ErrorCodeEnum.E0706.getValue());
            return resourceVO;
        }
        BeanUtils.copyProperties(resourceDO, resourceVO);
        if (getResourceDO != null) {
            resourceVO.setPName(getResourceDO.getName());
        }
        resourceVO.setSystemId(resourceDO.getSystemId());
        return resourceVO;
    }

    /**
     * 添加资源
     *
     * @param resourceDTO
     * @param userId
     * @return
     */
    @Override
    public BaseVO addResource(ResourceDTO resourceDTO, String userId) {
        //ResourceDO对象
        ResourceDO resourceDO = new ResourceDO();
        //ID
        String id = UUID.randomUUID().toString();
        //获取用户名
        String username = userMapper.getUserById(Long.valueOf(userId)).getUsername();
        //创建用户ID
        resourceDO.setCreateUser(username);
        resourceDO.setId(id);
        //创建DictionaryDO对象
        DictionaryDO dictionaryDO = new DictionaryDO();
        dictionaryDO.setId(Long.valueOf(resourceDTO.getResourceTypeId()));
        dictionaryDO.setValue(resourceDTO.getSystemId());
        //类型ID
        resourceDO.setDictionaryDO(dictionaryDO);
        //修改用户ID
        resourceDO.setUpdateUser(username);
        //创建时间，修改时间
        resourceDO.setCreateTime(DateConvertUtil.DateConvertToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        resourceDO.setUpdateTime(DateConvertUtil.DateConvertToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //DTO对象copy到DO上,前提属性要一致
        BeanUtils.copyProperties(resourceDTO, resourceDO);
        if (resourceDTO.getPid().equals("")) {
            resourceDO.setPid(null);
        }
        //执行添加操作
        int count = resourceMapper.addResource(resourceDO);
        //判断是否添加成功
        if (count > 0) {
            //添加角色ID和资源ID的关系到t_role_tresource表
            RoleDO roleDO = new RoleDO();
            //设置角色ID
            roleDO.setId("0");
            //设置资源ID
            roleDO.setResourceDO(resourceDO);

            int i = roleMapper.addResourceRole(roleDO);
            //是否添加角色成功
            if (i <= 0) {
                return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 修改资源
     *
     * @param resourceDTO
     * @param id
     * @return
     */
    @Override
    public BaseVO updateResource(ResourceDTO resourceDTO, String id, String userId) {
        //创建ResourceDO对象
        ResourceDO resourceDO = new ResourceDO();

        resourceDO.setId(id);
        //设置修改时间
        resourceDO.setUpdateTime(DateConvertUtil.DateConvertToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //设置类型ID
        DictionaryDO dictionaryDO = new DictionaryDO();
        dictionaryDO.setId(Long.valueOf(resourceDTO.getResourceTypeId()));
        dictionaryDO.setValue(resourceDTO.getSystemId());
        resourceDO.setDictionaryDO(dictionaryDO);
        //获取用户名
        String username = userMapper.getUserById(Long.valueOf(userId)).getUsername();
        resourceDO.setUpdateUser(username);
        //DTO对象copy到DO上,前提属性要一致
        BeanUtils.copyProperties(resourceDTO, resourceDO);
        //执行修改
        int i = resourceMapper.updateResource(resourceDO);
        if (i > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0705.getKey(), ErrorCodeEnum.E0705.getValue());
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @Override
    public BaseVO delResourceById(String id) {
        //查询当前要删除的ID是否还有子节点
        List<ResourceDO> resourceDOList = resourceMapper.queryAllResourceByParam(id);
        if (resourceDOList.size() > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0703.getKey(), ErrorCodeEnum.E0703.getValue());
        }

        //判断该资源是否已经分配了角色
        Long row = resourceMapper.hasRoleOwnResource(id);
        if (row > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0088.getKey(), ErrorCodeEnum.E0088.getValue());
        }
        //没有子节点就进行删除
        int i = resourceMapper.delResource(id);
        //判断是否删除成功
        if (i > 0) {
            //删除角色资源关系表
            roleMapper.delRoleResource(id);
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0702.getKey(), ErrorCodeEnum.E0702.getValue());
    }
}
