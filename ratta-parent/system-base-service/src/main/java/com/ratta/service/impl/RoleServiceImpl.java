package com.ratta.service.impl;
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

import com.ratta.domain.ResourceDO;
import com.ratta.domain.RoleDO;
import com.ratta.domain.UserDO;
import com.ratta.dto.CommonIdDTO;
import com.ratta.dto.RoleDTO;
import com.ratta.dto.RoleVagueDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.RoleMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.service.RoleService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:26
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据名称分页模糊查询
     *
     * @param roleVagueDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public CommonListVO<RoleVO> RoleVOList(RoleVagueDTO roleVagueDTO, String userId) {

        CommonListVO<RoleVO> commonListVO = new CommonListVO<RoleVO>();
        //查询当前登录用户所属角色
        List<RoleDO> roleList = userMapper.queryRoleByUserId(userId);
        List<RoleDO> roleDOList = new ArrayList<>();
        for (RoleDO roleDO : roleList) {
            roleDOList = getRoleDOList(roleDO.getId(), roleDOList);
        }

        //资源权限去重
        //初始化HashSet对象，并把list对象元素赋值给HashSet对象
        HashSet set = new HashSet(roleDOList);
        //把List集合所有元素清空
        roleDOList.clear();
        //把HashSet对象添加至List集合
        roleDOList.addAll(set);
        //创建集合RoleVO
        List<RoleVO> roleVO = new ArrayList<>();

        //根据参数模糊查询，用的是jdk1.8的lambda表达式
        roleDOList = roleDOList.stream().filter(role -> role.getName().indexOf(roleVagueDTO.getRoleName()) != -1).sorted(Comparator.comparing(RoleDO::getModifyTime).reversed()).collect(Collectors.toList());

        //list的大小
        int count = roleDOList.size();
        //总数据
        commonListVO.setTotal(Long.valueOf(count));
        //每页的开始数
        roleVagueDTO.setPageNo(roleVagueDTO.getPageNo() - 1);
        //list进行分页
        List<RoleDO> roleDOList2 = roleDOList.subList(roleVagueDTO.getPageNo(), count - roleVagueDTO.getPageNo() > roleVagueDTO.getPageSize() ? roleVagueDTO.getPageNo() + roleVagueDTO.getPageSize() : count);
        roleDOList = new ArrayList<>();
        //赋值给roleDOList
        roleDOList = ObjectConvertUtil.convertInstance().objectConvert(roleDOList2, RoleDO.class);
        for (RoleDO roleDO : roleDOList) {
            RoleVO getRoleVO = new RoleVO();
            BeanUtils.copyProperties(roleDO, getRoleVO);
            //根据角色id查询资源
            List<ResourceDO> resourceDOList = roleMapper.queryResourceByRoleId(roleDO.getId());
            //do转为vo
            List<ResourceVO> list = ObjectConvertUtil.convertInstance().objectConvert(resourceDOList, ResourceDO.class);
            //设置对象getRoleVO中的集合数据
            getRoleVO.setResourceVOList(list);
            //添加到集合中
            roleVO.add(getRoleVO);
        }
        //在把集合赋值给前端要展示的对象
        commonListVO.setVoList(roleVO);
        return commonListVO;
    }


    private List<RoleDO> getRoleDOList(String id, List<RoleDO> roleDOList) {
        List<RoleDO> roleDOListInner = roleMapper.queryRoleByPid(id);
        for (RoleDO roleDO : roleDOListInner) {
            roleDOList.add(roleDO);
            roleDOList = getRoleDOList(roleDO.getId(), roleDOList);
        }
        return roleDOList;
    }

    /**
     * 添加角色
     *
     * @param roleDTO
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO addRole(RoleDTO roleDTO, String userId) {

        //获取用户名
        UserDO user = userMapper.getUserById(Long.valueOf(userId));

        List<RoleDO> roleDOList = userMapper.queryRoleByUserId(userId);

        //创建
        RoleDO roleDO = new RoleDO();
        //角色ID
        roleDO.setId(UUID.randomUUID().toString());
        //创建人
        roleDO.setCreateUser(user.getUsername());
        //修改人
        roleDO.setModifyUser(user.getUsername());
        //父级id
        roleDO.setPid(roleDOList.get(0).getId());
        BeanUtils.copyProperties(roleDTO, roleDO);
        //执行添加角色操作
        roleMapper.addRole(roleDO);
        String[] resources = roleDTO.getIdList();
        for (int i = 0; i < resources.length; i++) {
            ResourceDO resourceDO = new ResourceDO();
            resourceDO.setId(resources[i]);
            roleDO.setResourceDO(resourceDO);
            int row = roleMapper.addResourceRole(roleDO);
            if (row <= 0) {
                return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
            }
        }
        return new BaseVO();
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public RoleBeanVO queryRoleById(String roleId) {
        //通过ID看角色是否存在
        RoleDO roleDO = roleMapper.queryRoleById(roleId);
        RoleBeanVO roleBeanVO = new RoleBeanVO();
        if (roleDO != null) {
            roleBeanVO.setId(roleDO.getId());
            roleBeanVO.setName(roleDO.getName());
            roleBeanVO.setRemark(roleDO.getRemark());
            //查询当前角色所属资源
            List<ResourceDO> resourceDOList = roleMapper.queryResourceById(roleId);
            Iterator<ResourceDO> iterator = resourceDOList.iterator();
            List<ResourceVO> resourceVOList = new ArrayList<>();
            while (iterator.hasNext()) {
                ResourceDO resource = iterator.next();
                for (ResourceDO resourceDO : resourceDOList) {
                    if (resourceDO.getPid() != null) {
                        if (resourceDO.getPid().equals(resource.getId())) {
                            iterator.remove();
                            resource.setId("");
                            break;
                        }
                    }
                }
                if (resource.getId() != null && resource.getId() != "") {
                    ResourceVO resourceVO = new ResourceVO();
                    resourceVO.setId(resource.getId());
                    resourceVO.setName(resource.getName());
                    resourceVOList.add(resourceVO);
                }
            }
            List<ResourceVO> resourceVOList1 = ObjectConvertUtil.convertInstance().objectConvert(resourceVOList, ResourceDO.class);
            roleBeanVO.setResourceVOList(resourceVOList1);
        }
        return roleBeanVO;
    }

    /**
     * 修改角色信息
     *
     * @param roleDTO
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO updateRole(RoleDTO roleDTO, String roleId, String userId) {
        //获取用户名
        String username = userMapper.getUserById(Long.valueOf(userId)).getUsername();
        RoleDO getRole = roleMapper.queryRoleById(roleId);
        if (getRole != null) {
            RoleDO roleDO = new RoleDO();
            roleDO.setId(roleId);
            roleDO.setModifyUser(username);
            BeanUtils.copyProperties(roleDTO, roleDO);
            //1.先删除该角色下对应的所有资源对应关系
            roleMapper.delRoleResourceByRoleId(roleId);
            //2.执行角色修改
            int i = roleMapper.updateRole(roleDO);
            if (i > 0) {
                String[] resourceArray = roleDTO.getIdList();
                //3.添加角色和资源的关系
                return addRoleResource(resourceArray, roleId);
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO deleteRole(String roleId) {
        //查询当前角色是否还有用户
        long l = roleMapper.hasUser(roleId);
        if (l > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0707.getKey(), ErrorCodeEnum.E0707.getValue());
        }
        //如果删除的角色id还要子节点就不能进行删除
        List<RoleDO> roleDOList = roleMapper.queryRoleByPid(roleId);
        if (roleDOList.size() > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0740.getKey(), ErrorCodeEnum.E0740.getValue());
        }
        roleMapper.delRoleResourceByRoleId(roleId);
        int i = roleMapper.delRole(roleId);
        /**
         * 为什么我只判断第二个,如果第一个出错就不会删除成功,但是如果第二个如果条件为空进行删除也不会报错
         *但是第一个删除成功。了，所以不会进行回滚,所以我在第二个删除做了判断,如果受影响行数小于等于0
         *就手动抛出一个异常来进行回滚
         */
        if (i <= 0) {
            return new BaseVO(false, ErrorCodeEnum.E0702.getKey(), ErrorCodeEnum.E0702.getValue());
        }
        return new BaseVO();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ResourceVO> querySelectResource(String roleId) {
        //执行查询
        List<ResourceDO> resourceDOList = roleMapper.queryResourceByRoleId(roleId);
        List<ResourceVO> list = ObjectConvertUtil.convertInstance().objectConvert(resourceDOList, ResourceDO.class);
        return list;
    }


    /**
     * 授权资源
     * <p>更新角色与资源的对应关系</p>
     *
     * @param commonIdDTO 拼接的数组格式资源ID
     * @param roleId      角色ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO updateRoleResource(String roleId, CommonIdDTO commonIdDTO, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("resourceList", commonIdDTO.getIdList());
        //查询改角色取消的资源
        List<String> resource = roleMapper.queryRoleResource(map);
        //如果有数据那就说已经取消的资源
        if (resource.size() > 0) {
            //删除该角色下所有子角色已经授权的资源
            RoleVagueDTO roleVagueDTO = new RoleVagueDTO();
            roleVagueDTO.setPageNo(1);
            //因为是list分页所有我指定了大的页大小
            roleVagueDTO.setPageSize(999999999);
            roleVagueDTO.setRoleName("");
            //通过当前用户查询所有子角色
            List<RoleVO> roleVO = RoleVOList(roleVagueDTO, userId).getVoList();
            //通过角色id和资源数组一一删除
            for (RoleVO vo : roleVO) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("roleId", vo.getId());
                map1.put("resourceList", resource.toArray(new String[resource.size()]));
                //删除子角色所取消的资源
                roleMapper.deleteRoleResource(map1);
            }
        }
        RoleDO roleDO = roleMapper.queryRoleById(roleId);
        if (roleDO != null) {
            //1,先删除该角色下对应的所有资源对应关系
            roleMapper.delRoleResourceByRoleId(roleId);
            //2.拆分前端传的资源ID拼接的字符串
            String[] resourceArray = commonIdDTO.getIdList();
            //添加角色与资源的关系
            return addRoleResource(resourceArray, roleId);
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 获取所有的角色信息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public RoleAllVO findAllRole(String userId) {
        RoleVagueDTO roleVagueDTO = new RoleVagueDTO();
        roleVagueDTO.setPageNo(1);
        //因为是list分页所有我指定了大的页大小
        roleVagueDTO.setPageSize(999999999);
        roleVagueDTO.setRoleName("");
        //通过当前用户查询所有子角色
        CommonListVO<RoleVO> roleVOCommonListVO = RoleVOList(roleVagueDTO, userId);
        RoleAllVO roleAllVO = new RoleAllVO();
        List<RoleVO> roleVOList = ObjectConvertUtil.convertInstance().objectConvert(roleVOCommonListVO.getVoList(), RoleVO.class);
        roleAllVO.setListVO(roleVOList);
        return roleAllVO;
    }


    /**
     * 添加资源与角色的公共方法
     *
     * @param resourceArray
     * @param roleId
     * @returnaddRoleResource
     */
    private BaseVO addRoleResource(String[] resourceArray, String roleId) {
        //3.拆分前端传的资源ID拼接的字符串
        for (int i = 0; i < resourceArray.length; i++) {
            ResourceDO resourceDO = new ResourceDO();
            //设置resourceID
            resourceDO.setId(resourceArray[i]);
            RoleDO roleDO = new RoleDO();
            //设置roleId
            roleDO.setId(roleId);
            roleDO.setResourceDO(resourceDO);
            //3,保存新的角色资源对应关系
            int row = roleMapper.addResourceRole(roleDO);
            if (row <= 0) {
                return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
            }
        }
        return new BaseVO();
    }
}
