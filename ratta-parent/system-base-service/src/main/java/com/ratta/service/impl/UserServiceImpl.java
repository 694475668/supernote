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

import com.ratta.constants.Constant;
import com.ratta.domain.PwdHisDO;
import com.ratta.domain.RoleDO;
import com.ratta.domain.UserDO;
import com.ratta.dto.*;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.PwdHisMapper;
import com.ratta.mapper.ReferenceMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.service.UserService;
import com.ratta.util.MD5Util;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.TokenUtil;
import com.ratta.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:53
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户Mapper
     */
    @Autowired
    private UserMapper userMapper;


    /**
     * 参数Mapper
     */
    @Resource
    private ReferenceMapper referenceMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 修改密码历史Mapper
     */
    @Autowired
    private PwdHisMapper pwdHisMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户登录
     *
     * @param userLoginDTO 用户名  密码
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO login(UserLoginDTO userLoginDTO) {
        //根据用户名查询数据库是否存在该用户名
        UserDO getUserDao = userMapper.getUserByName(userLoginDTO.getUsername());
        if (getUserDao == null) {
            //如果用户名不存在就做出相应的提示
            return new BaseVO(false, ErrorCodeEnum.E0708.getKey(), ErrorCodeEnum.E0708.getValue());
        }
        //根据用户ID查询用户信息
        UserDO userDOById = userMapper.getUserById(getUserDao.getId());

        //判断当前用户账户是否停用
        if (Constant.DISCONTINUESTATUS.equals(userDOById.getStatus())) {
            return new BaseVO(false, ErrorCodeEnum.E0709.getKey(), ErrorCodeEnum.E0709.getValue());
        }
        //判断当前用户账户是否锁定
        if (Constant.LOCKSTATUS.equals(userDOById.getStatus())) {
            return new BaseVO(false, ErrorCodeEnum.E0710.getKey(), ErrorCodeEnum.E0710.getValue());
        }

        //创建UserVO对象
        UserVO userVO = new UserVO();
        //执行用户名和密码的校验
        UserDO userDO = userMapper.getUserByNameAndPWD(userLoginDTO.getUsername(), MD5Util.md5(userLoginDTO.getPassword()));

        //用户登录成功
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userVO);
            //清楚登陆密码输错次数
            userMapper.freshErrorLogin(userDO.getId());

            //记录最后一次登陆时间
            UserDO getUserDo = new UserDO();
            getUserDo.setId(userDO.getId());
            getUserDo.setLastLoginDate(new Date());
            userMapper.updateUser(getUserDo);
            UserTokenVO userTokenVO = new UserTokenVO();
            //设置token
            userTokenVO.setToken(TokenUtil.createToken(userDO.getId(), System.currentTimeMillis()));
            userTokenVO.setName(userDO.getName());
            return userTokenVO;
        } else {
            //用户登录失败
            //记录登陆密码次数
            userMapper.errorLogin(getUserDao.getId());

            //查询剩余密码登录次数
            Map<String, Object> map = new HashMap<>();
            map.put("id", getUserDao.getId());
            map.put("serial", Constant.REFERENCESERIAL);
            map.put("name", Constant.REFERENCENAME);
            long counts = userMapper.selectErrorCounts(map);
            if (counts == 0) {
                //进行锁定用户
                userMapper.lockUser(getUserDao.getId());
                return new BaseVO(false, ErrorCodeEnum.E0710.getKey(), ErrorCodeEnum.E0710.getValue());
            }
            //提示用户名或密码错误,剩余登录次数
            return new BaseVO(false, ErrorCodeEnum.E0711.getKey(), ErrorCodeEnum.E0711.getValue() + counts + "!");
        }
    }

    /**
     * 查看指定用户是否被锁定
     *
     * @param id 要查看的用户
     * @return
     */
    @Override
    public boolean isLocked(Long id) {
        //检查用户是否被锁定
        long count = userMapper.selectLocked(id);
        if (count > 0) {
            //锁定用户,数据库影响行数
            userMapper.lockUser(id);
        }
        return count > 0;
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public UserDO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    /**
     * 验证登录的用户是否是可用
     *
     * @param userId
     * @return
     */
    @Override
    public BaseVO verifvPassword(String userId) {
        //根据用户ID查询用户信息
        if (!StringUtils.isBlank(userId)) {
            UserDO userDO = userMapper.getUserById(Long.valueOf(userId));
            if (Constant.MODIFYPWD.equals(userDO.getModifyPwd())) {
                return new BaseVO(false, "746", "用户无效");
            }
            return new BaseVO();
        }
        return null;
    }

    @Override
    public BaseVO checkOldPwd(String userId, UserDTO userDTO) {
        //根据用户ID查询用户信息
        UserDO userDO = userMapper.getUserById(Long.valueOf(userId));
        //判断原密码是否正常
        if (userDO.getPwd().equalsIgnoreCase(MD5Util.md5(userDTO.getOldPwd()))) {
            return new BaseVO();
        }
        //原密码错误
        return new BaseVO(false, ErrorCodeEnum.E0714.getKey(), ErrorCodeEnum.E0714.getValue());
    }

    /**
     * editCurrentUserPwd 修改自己的密码
     *
     * @param userId  用户ID
     * @param userDTO 原始密码  新密码
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO editCurrentUserPwd(String userId, UserDTO userDTO) {
        //根据用户ID查询用户信息
        UserDO userDO = userMapper.getUserById(Long.valueOf(userId));
        //根据业务码查询参数信息
        String val = referenceMapper.queryByParamCode(Constant.REFERENCESERIAL, Constant.SAMEPASSCOUNTS).get(0).getValue();
        // 查询用户改密历史
        List<PwdHisDO> query = pwdHisMapper.query(userDO.getUsername(), Integer.parseInt(val));
        for (PwdHisDO pwdHisDO : query) {
            //判断新密码和原密码是否相同
            if (pwdHisDO.getPwd().equals(MD5Util.md5(userDTO.getPassword()))) {
                //提示密码不能与近期相同
                return new BaseVO(false, ErrorCodeEnum.E0713.getKey(), ErrorCodeEnum.E0713.getValue());
            }
        }
        //设置要修改的参数
        UserDO getUserDO = new UserDO();
        getUserDO.setId(userDO.getId());
        getUserDO.setPwd(MD5Util.md5(userDTO.getPassword()));
        // 0 未修改, 1 已修改
        getUserDO.setModifyPwd("1");
        getUserDO.setIsActive("Y");
        getUserDO.setUpdateTime(new Date());
        getUserDO.setUpdateUser(userDO.getUsername());
        //执行修改密码
        userMapper.updateUser(getUserDO);
        UserDO user = userMapper.getUserById(userDO.getId());
        UserBeanVO userBeanVO = new UserBeanVO();
        BeanUtils.copyProperties(user, userBeanVO);
        redisTemplate.opsForValue().set("systemUserKey" + userDO.getId().toString(), userBeanVO);
        //把密码添加到修改历史表中
        PwdHisDO pwdHisDO = new PwdHisDO();
        pwdHisDO.setPwd(MD5Util.md5(userDTO.getPassword()));
        pwdHisDO.setUsername(userDO.getUsername());
        int i = pwdHisMapper.savePwd(pwdHisDO);
        if (i <= 0) {
            return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
        }
        //修改成功
        return new BaseVO();
    }

    /**
     * dataGrid 获取用户数据信息
     *
     * @param userVagueDTO 参数
     * @return
     */
    @Override
    public CommonListVO<UserVO> dataGrid(UserVagueDTO userVagueDTO, String userId) {
        List<UserVO> userVOList = new ArrayList<>();
        //创建RoleListVO对象也就是前端展示数据对象
        CommonListVO<UserVO> commonListVO = new CommonListVO<UserVO>();
        List<UserDO> userDOList = new ArrayList<>();
        userDOList = getUserDOList(Long.valueOf(userId), userDOList);

        //模糊查询 用的是jdk1.8的lambda表达式以及按时间倒序排序
        // 倒序排序 sorted(Comparator.comparing(UserDO::getUpdateTime).reversed()

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        userDOList = userDOList.stream().filter(s ->
                s.getUsername().indexOf(userVagueDTO.getUsername()) != -1 &&
                        s.getRoleId().indexOf(userVagueDTO.getRoleId()) != -1 &&
                        s.getStatus().indexOf(userVagueDTO.getStatus()) != -1 &&
                        (simpleDateFormat.format(s.getUpdateTime()).compareTo(userVagueDTO.getStartDate()) == 0 || simpleDateFormat.format(s.getUpdateTime()).compareTo(userVagueDTO.getStartDate()) > 0 || "".equals(userVagueDTO.getStartDate())) &&
                        (simpleDateFormat.format(s.getUpdateTime()).compareTo(userVagueDTO.getEndDate()) == 0 || simpleDateFormat.format(s.getUpdateTime()).compareTo(userVagueDTO.getEndDate()) < 0 || "".equals(userVagueDTO.getEndDate()))
        ).sorted(Comparator.comparing(UserDO::getUpdateTime).reversed()).collect(Collectors.toList());

        //list的大小
        int count = userDOList.size();
        //总数据
        commonListVO.setTotal(Long.valueOf(count));
        //每页的开始数
        userVagueDTO.setPageNo(userVagueDTO.getPageNo() - 1);
        //list进行分页
        List<UserDO> userDOList2 = userDOList.subList(userVagueDTO.getPageNo(), count - userVagueDTO.getPageNo() > userVagueDTO.getPageSize() ? userVagueDTO.getPageNo() + userVagueDTO.getPageSize() : count);
        userDOList = new ArrayList<>();
        userDOList = ObjectConvertUtil.convertInstance().objectConvert(userDOList2, UserDO.class);


        //依次把userDO的数据赋值到userVO
        for (UserDO userDO : userDOList) {
            UserVO userVO = new UserVO();
            userVO.setId(userDO.getId());
            userVO.setName(userDO.getName());
            userVO.setEmail(userDO.getEmail());
            userVO.setPhone(userDO.getPhone());
            userVO.setStatus(userDO.getStatus());
            userVO.setUpdateTime(userDO.getUpdateTime());
            userVO.setUpdateUser(userDO.getUpdateUser());
            userVO.setUsername(userDO.getUsername());
            //设置角色数据
            for (RoleDO roleDO : userDO.getRoleVOList()) {
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(roleDO, roleVO);
                userVO.getRoleVOList().add(roleVO);
            }
            userVOList.add(userVO);
            commonListVO.setVoList(userVOList);
        }
        return commonListVO;
    }

    private List<UserDO> getUserDOList(Long userId, List<UserDO> userDOList) {
        List<UserDO> userDOS = userMapper.queryUserByPid(userId);
        for (UserDO userDO : userDOS) {
            userDOList.add(userDO);
            userDOList = getUserDOList(userDO.getId(), userDOList);
        }
        return userDOList;
    }

    /**
     * 添加用户
     *
     * @param userAddDTO
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO addUser(UserAddDTO userAddDTO, String userId) {
        //判断用户名是否已经存在了
        if (userMapper.getUserByName(userAddDTO.getUsername()) != null) {
            return new BaseVO(false, ErrorCodeEnum.E0733.getKey(), ErrorCodeEnum.E0733.getValue());
        }
        UserDO userDO = new UserDO();
        userDO.setCounts(0);
        userDO.setIsActive("N");
        userDO.setStatus("1");
        //创建用户
        UserDO userById = userMapper.getUserById(Long.valueOf(userId));
        userDO.setCreateUser(userById.getUsername());
        userDO.setUpdateUser(userById.getUsername());
        userDO.setModifyPwd("0");
        BeanUtils.copyProperties(userAddDTO, userDO);
        //设置pid
        userDO.setPid(userById.getId());
        userDO.setPwd(MD5Util.md5(userAddDTO.getPassword()));
        //添加用户
        userMapper.saveUser(userDO);
        //获取序列的最大值 也就是刚刚添加的用户的ID因为有事务，不能及时获取数据库刚刚添加用户id
        Long id = userMapper.getMaxSEQ();
        UserDO user = userMapper.getUserById(id);
        UserBeanVO userBeanVO = new UserBeanVO();
        BeanUtils.copyProperties(user, userBeanVO);
        redisTemplate.opsForValue().set("systemUserKey" + id.toString(), userBeanVO);
        //添加用户对应的角色信息
        String[] roleIds = userAddDTO.getIdList();
        for (String roleId : roleIds) {
            int i = userMapper.saveUserRole(String.valueOf(id), roleId);
            if (i <= 0) {
                return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
            }
        }
        return new BaseVO();
    }

    /**
     * 启用
     *
     * @param commonIdDTO 需要启用的ID
     * @param userId      当前登陆的用户ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO open(CommonIdDTO commonIdDTO, String userId) {
        //判断启用的用户是否是自己
        if (Arrays.asList(commonIdDTO.getIdList()).contains(userId)) {
            return new BaseVO(false, ErrorCodeEnum.E0716.getKey(), ErrorCodeEnum.E0716.getValue());
        }
        for (String id : commonIdDTO.getIdList()) {
            //查询用户信息
            UserDO userById = userMapper.getUserById(Long.valueOf(id));
            if ("1".equals(userById.getStatus())) {
                return new BaseVO(false, ErrorCodeEnum.E0732.getKey(), ErrorCodeEnum.E0732.getValue());
            }
        }

        for (String id : commonIdDTO.getIdList()) {
            //查询用户信息
            UserDO userById = userMapper.getUserById(Long.valueOf(id));
            if (userById != null) {
                UserDO userDO = new UserDO();
                userDO.setId(userById.getId());
                userDO.setStatus("1");
                userDO.setCounts(0);
                UserDO getUserDO = userMapper.getUserById(Long.valueOf(userId));
                //设置修改人
                userDO.setUpdateUser(getUserDO.getUsername());
                userDO.setUpdateTime(new Date());
                int row = userMapper.updateUser(userDO);
                UserDO user = userMapper.getUserById(userById.getId());
                UserBeanVO userBeanVO = new UserBeanVO();
                BeanUtils.copyProperties(user, userBeanVO);
                redisTemplate.opsForValue().set("systemUserKey" + userById.getId().toString(), userBeanVO);
                if (row <= 0) {
                    return new BaseVO(false, ErrorCodeEnum.E0715.getKey(), ErrorCodeEnum.E0715.getValue());
                }
            }
        }
        return new BaseVO();
    }

    /**
     * 停用
     *
     * @param commonIdDTO
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO close(CommonIdDTO commonIdDTO, String userId) {
        //判断停用的用户是否是自己
        if (Arrays.asList(commonIdDTO.getIdList()).contains(userId)) {
            return new BaseVO(false, ErrorCodeEnum.E0717.getKey(), ErrorCodeEnum.E0717.getValue());
        }

        for (String id : commonIdDTO.getIdList()) {
            //查询用户信息
            UserDO userById = userMapper.getUserById(Long.valueOf(id));
            if ("2".equals(userById.getStatus())) {
                return new BaseVO(false, ErrorCodeEnum.E0734.getKey(), ErrorCodeEnum.E0734.getValue());
            }
        }
        for (String id : commonIdDTO.getIdList()) {
            //查询用户信息
            UserDO userById = userMapper.getUserById(Long.valueOf(id));
            if (userById != null) {
                UserDO userDO = new UserDO();
                userDO.setId(userById.getId());
                userDO.setStatus("2");
                UserDO getUserDO = userMapper.getUserById(Long.valueOf(userId));
                //设置修改人
                userDO.setUpdateUser(getUserDO.getUsername());
                userDO.setUpdateTime(new Date());
                int row = userMapper.updateUser(userDO);
                UserDO userByIds = userMapper.getUserById(userById.getId());
                UserBeanVO userBeanVO = new UserBeanVO();
                BeanUtils.copyProperties(userByIds, userBeanVO);
                redisTemplate.opsForValue().set("systemUserKey" + userById.getId().toString(), userBeanVO);
                if (row <= 0) {
                    return new BaseVO(false, ErrorCodeEnum.E0718.getKey(), ErrorCodeEnum.E0718.getValue());
                }
            }
        }
        return new BaseVO();
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public UserBeanVO getBean(Long id) {
        UserDO userById = userMapper.getUserById(id);
        UserBeanVO userBeanVO = new UserBeanVO();
        BeanUtils.copyProperties(userById, userBeanVO);
        return userBeanVO;
    }

    /**
     * 修改用户
     *
     * @param userUpdateDTO
     * @param id
     * @return
     */
    @Override
    public BaseVO updateUser(UserUpdateDTO userUpdateDTO, Long id, String userId) {
        UserDO userDO = new UserDO();
        UserDO userById = userMapper.getUserById(Long.valueOf(id));
        if (userById != null) {
            userDO.setId(userById.getId());
            BeanUtils.copyProperties(userUpdateDTO, userDO);
            userDO.setModifyPwd("0");
            //获取当前操作的用户名
            UserDO getUser = userMapper.getUserById(Long.valueOf(userId));
            userDO.setUpdateUser(getUser.getUsername());
            userDO.setUpdateTime(new Date());
            int i = userMapper.updateUser(userDO);
            UserDO userByIds = userMapper.getUserById(userById.getId());
            UserBeanVO userBeanVO = new UserBeanVO();
            BeanUtils.copyProperties(userByIds, userBeanVO);
            redisTemplate.opsForValue().set("systemUserKey" + userById.getId().toString(), userBeanVO);
            if (i > 0) {
                return new BaseVO();
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 修改密码
     *
     * @param userPwdDTO
     * @param userId
     * @return
     */
    @Override
    public BaseVO updatePwd(UserPwdDTO userPwdDTO, String userId) {
        UserDO userDO = new UserDO();
        UserDO userById = userMapper.getUserById(Long.valueOf(userId));
        if (userById != null) {
            userDO.setId(userPwdDTO.getId());
            userDO.setUpdateUser(userById.getUsername());
            userDO.setUpdateTime(new Date());
            userDO.setCounts(0);
            userDO.setStatus("1");
            userDO.setModifyPwd("0");
            userDO.setPwd(MD5Util.md5(userPwdDTO.getPassword()));
            int row = userMapper.updateUser(userDO);
            UserDO userByIds = userMapper.getUserById(userPwdDTO.getId());
            UserBeanVO userBeanVO = new UserBeanVO();
            BeanUtils.copyProperties(userByIds, userBeanVO);
            redisTemplate.opsForValue().set("systemUserKey" + userPwdDTO.getId().toString(), userBeanVO);
            if (row > 0) {
                return new BaseVO();
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0705.getKey(), ErrorCodeEnum.E0705.getValue());
    }

    /**
     * 删除用户
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO deleteUser(Long id, String userId) {

        //1.不能操作自己
        if (id.equals(userId)) {
            return new BaseVO(false, ErrorCodeEnum.E0720.getKey(), ErrorCodeEnum.E0720.getValue());
        }
        //2.查询当前用户的有效标识状态
        UserDO userById = userMapper.getUserById(id);
        if (Constant.Y.equals(userById.getIsActive())) {
            return new BaseVO(false, ErrorCodeEnum.E0719.getKey(), ErrorCodeEnum.E0719.getValue());
        }
        //3.判断当前用户下是否还有子用户
        List<UserDO> userDOList = userMapper.queryUserByPid(id);
        if (userDOList.size() > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0741.getKey(), ErrorCodeEnum.E0741.getValue());
        }
        redisTemplate.delete("systemUserKey" + userById.getId() + "");
        //3.执行删除用户信息
        userMapper.delUser(userById.getId());
        //4.删除用户下的角色关系
        int row = userMapper.delUserRoles(userById.getId());
        if (row <= 0) {
            return new BaseVO(false, ErrorCodeEnum.E0702.getKey(), ErrorCodeEnum.E0702.getValue());
        }
        return new BaseVO();
    }

    /**
     * 解锁
     *
     * @param id     要解锁的用户id
     * @param userId 用户id
     * @return
     */
    @Override
    public BaseVO unlock(Long id, Long userId) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setCounts(0);
        userDO.setStatus("1");
        UserDO getUserDO = userMapper.getUserById(Long.valueOf(userId));
        userDO.setUpdateUser(getUserDO.getUsername());
        userDO.setUpdateTime(new Date());
        int row = userMapper.updateUser(userDO);
        UserDO userByIds = userMapper.getUserById(id);
        UserBeanVO userBeanVO = new UserBeanVO();
        BeanUtils.copyProperties(userByIds, userBeanVO);
        redisTemplate.opsForValue().set("systemUserKey" + id.toString(), userBeanVO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 授权
     *
     * @param userGrantDTO
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO grant(UserGrantDTO userGrantDTO, String userId) {
        //判断是多用户，多角色进行授权，还是单用户多角色授权
        if (userGrantDTO.getUserIdList().length > 1) {
            for (String userID : userGrantDTO.getUserIdList()) {
                userMapper.delUserRoles(Long.valueOf(userID));
                for (String roleId : userGrantDTO.getRoleIdList()) {
                    //添加角色和用户的关系
                    int row = userMapper.saveUserRole(userID, roleId);
                    if (row <= 0) {
                        return new BaseVO(false, ErrorCodeEnum.E0724.getKey(), ErrorCodeEnum.E0724.getValue());
                    }
                }
            }
            return new BaseVO();
        }
        //单用户多角色授权
        String userID = userGrantDTO.getUserIdList()[0];
        UserDO userById = userMapper.getUserById(Long.valueOf(userID));
        if (userById != null) {
            //删除用户原有的角色信息
            userMapper.delUserRoles(userById.getId());
            for (String roleId : userGrantDTO.getRoleIdList()) {
                //重新添加新的角色信息
                int row = userMapper.saveUserRole(String.valueOf(userById.getId()), roleId);
                if (row <= 0) {
                    return new BaseVO(false, ErrorCodeEnum.E0724.getKey(), ErrorCodeEnum.E0724.getValue());
                }
            }
        }
        return new BaseVO();
    }

    /**
     * 根据用户ID获取选中的角色
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public UserRoleVO findRoleByUserId(String id) {
        UserDO userById = userMapper.getUserById(Long.valueOf(id));
        UserRoleVO userRoleVO = new UserRoleVO();
        if (userById != null) {
            userRoleVO.setId(userById.getId());
            userRoleVO.setUsername(userById.getUsername());
            List<RoleDO> roleDOList = userMapper.queryRoleByUserId(id);
            List<RoleVO> roleVOList = ObjectConvertUtil.convertInstance().objectConvert(roleDOList, RoleDO.class);
            userRoleVO.setRoleVOList(roleVOList);
        }
        return userRoleVO;
    }

    /**
     * 多个用户id获取用户信息
     *
     * @param commonIdDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public CommonListVO<UserPartVO> getUserArray(CommonIdDTO commonIdDTO) {
        CommonListVO<UserPartVO> commonListVO = new CommonListVO<UserPartVO>();
        List<UserDO> userArray = userMapper.getUserArray(commonIdDTO.getIdList());
        List<UserPartVO> userPartVOList = ObjectConvertUtil.convertInstance().objectConvert(userArray, UserPartVO.class);
        commonListVO.setVoList(userPartVOList);
        return commonListVO;
    }

    @Override
    public BaseVO userGrant(UserGrantDTO userGrantDTO, String userId) {
        //1.用户不能给自己授权
        if (Arrays.asList(userGrantDTO.getUserIdList()).contains(userId)) {
            return new BaseVO(false, ErrorCodeEnum.E0723.getKey(), ErrorCodeEnum.E0723.getValue());
        }
        return new BaseVO();
    }

    @Override
    public BaseVO userUnlock(Long id, Long userId) {
        //2.查询当前用户的有效标识状态
        UserDO userById = userMapper.getUserById(id);
        //用户不能解锁自己
        if (userById.getId().equals(userId)) {
            return new BaseVO(false, ErrorCodeEnum.E0735.getKey(), ErrorCodeEnum.E0735.getValue());
        }
        if (!("0").equals(userById.getStatus())) {
            return new BaseVO(false, ErrorCodeEnum.E0721.getKey(), ErrorCodeEnum.E0721.getValue());
        }
        return new BaseVO();
    }
}
