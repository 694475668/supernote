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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.domain.ReferenceDO;
import com.ratta.domain.UserDO;
import com.ratta.dto.ReferenceDTO;
import com.ratta.dto.ReferenceQueryDTO;
import com.ratta.dto.ReferenceVaguaDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.ReferenceMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.service.ReferenceService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/22 0022 12:52
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReferenceMapper referenceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * queryParam<br>
     * 根据条件查询系统参数<br>
     *
     * @param serial 参数编码
     * @param name   参数名称
     * @return 参数子类
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ReferenceVO> queryParam(String serial, String name) {
        List<ReferenceDO> referenceDOList = referenceMapper.queryByParamCode(serial, name);
        List<ReferenceVO> referenceVOList = ObjectConvertUtil.convertInstance().objectConvert(referenceDOList, ReferenceDO.class);
        return referenceVOList;
    }

    /**
     * <p>query</p>
     * <p>根据查询条件查询系统参数详细信息</p>
     *
     * @param referenceVaguaDTO 查询条件
     * @return 数据模型
     */
    @Override
    public CommonListVO<ReferenceVO> query(ReferenceVaguaDTO referenceVaguaDTO) {
        List<ReferenceVO> referenceVOList = new ArrayList<>();
        //创建commonListVO对象也就是前端展示数据对象
        CommonListVO<ReferenceVO> commonListVO = new CommonListVO<ReferenceVO>();
        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(referenceVaguaDTO.getSortField())) {
            PageHelper.startPage(referenceVaguaDTO.getPageNo(), referenceVaguaDTO.getPageSize(), referenceVaguaDTO.getSortField() + " " + referenceVaguaDTO.getSortRules());
        } else {
            PageHelper.startPage(referenceVaguaDTO.getPageNo(), referenceVaguaDTO.getPageSize());
        }
        List<ReferenceDO> query = referenceMapper.query(referenceVaguaDTO.getName());
        PageInfo<ReferenceDO> uInfo = new PageInfo<>(query);
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());
        for (ReferenceDO referenceDO : query) {
            ReferenceVO referenceVO = new ReferenceVO();
            BeanUtils.copyProperties(referenceDO, referenceVO);
            referenceVOList.add(referenceVO);
            commonListVO.setVoList(referenceVOList);
        }
        return commonListVO;
    }

    /**
     * <p>addReference</p>
     * <p>添加系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @param userId       操作员
     * @return
     */
    @Override
    public BaseVO addReference(ReferenceDTO referenceDTO, String userId) {
        UserDO userById = userMapper.getUserById(Long.valueOf(userId));
        ReferenceDO referenceDO = new ReferenceDO();
        BeanUtils.copyProperties(referenceDTO, referenceDO);
        referenceDO.setOpUser(userById.getUsername());
        //同一个业务码下不允许存在相同编码
        ReferenceDO getReference = referenceMapper.queryByParam(referenceDTO.getSerial(), referenceDTO.getName());
        if (getReference != null) {
            return new BaseVO(false, ErrorCodeEnum.E0731.getKey(), ErrorCodeEnum.E0731.getValue());
        }
        //添加
        int i = referenceMapper.saveReference(referenceDO);
        if (i > 0) {
            List<ReferenceDO> referenceDOList = referenceMapper.queryByParamCode("", referenceDTO.getName());
            List<ReferenceVO> referenceVOList = new ArrayList<>();
            for (ReferenceDO aDo : referenceDOList) {
                ReferenceVO referenceVO = new ReferenceVO();
                BeanUtils.copyProperties(aDo, referenceVO);
                referenceVOList.add(referenceVO);
            }
            if (referenceDOList.size() > 0) {
                redisTemplate.delete(referenceDO.getName());
                redisTemplate.opsForList().leftPushAll(referenceDO.getName(), referenceVOList);
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * <p>queryById</p>
     * <p>根据id查询系统参数详细信息</p>
     *
     * @param id 系统参数详细信息id
     * @return 系统参数详细信息
     */
    @Override
    public ReferenceVO queryById(Long id) {
        ReferenceDO referenceDO = referenceMapper.queryById(id);
        ReferenceVO referenceVO = new ReferenceVO();
        BeanUtils.copyProperties(referenceDO, referenceVO);
        return referenceVO;
    }

    /**
     * <p>update</p>
     * <p>更新系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @param id
     * @param userId
     * @return
     */
    @Override
    public BaseVO updateReference(ReferenceDTO referenceDTO, Long id, String userId) {
        ReferenceDO referenceDO = new ReferenceDO();
        BeanUtils.copyProperties(referenceDTO, referenceDO);
        ReferenceDO getReference = referenceMapper.queryById(id);
        //查询当前登陆用户信息
        UserDO userById = userMapper.getUserById(Long.valueOf(userId));
        if (getReference != null) {
            referenceDO.setId(getReference.getId());
            //设置修改人
            referenceDO.setOpUser(userById.getUsername());
            referenceDO.setOpTime(new Date());
            int row = referenceMapper.updateReference(referenceDO);
            if (row > 0) {
                List<ReferenceDO> referenceDOList = referenceMapper.queryByParamCode("", referenceDTO.getName());
                List<ReferenceVO> referenceVOList = new ArrayList<>();
                for (ReferenceDO aDo : referenceDOList) {
                    ReferenceVO referenceVO = new ReferenceVO();
                    BeanUtils.copyProperties(aDo, referenceVO);
                    referenceVOList.add(referenceVO);
                }
                if (referenceDOList.size() > 0) {
                    redisTemplate.delete(referenceDO.getName());
                    redisTemplate.opsForList().leftPushAll(referenceDO.getName(), referenceVOList);
                }
                return new BaseVO();
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0705.getKey(), ErrorCodeEnum.E0705.getValue());
    }

    /**
     * <p>deleteReference</p>
     * <p>根据id删除系统参数详细信息</p>
     *
     * @param id 参数明细id
     * @return
     */
    @Override
    public BaseVO deleteReference(Long id) {
        ReferenceDO referenceDO = referenceMapper.queryById(id);
        redisTemplate.delete(referenceDO.getName());
        int row = referenceMapper.deleteReference(id);
        if (row > 0) {
            List<ReferenceDO> referenceDOList = referenceMapper.queryByParamCode("", referenceDO.getName());
            List<ReferenceVO> referenceVOList = new ArrayList<>();
            for (ReferenceDO aDo : referenceDOList) {
                ReferenceVO referenceVO = new ReferenceVO();
                BeanUtils.copyProperties(aDo, referenceVO);
                referenceVOList.add(referenceVO);
            }
            if (referenceDOList.size() > 0) {
                redisTemplate.opsForList().leftPushAll(referenceDO.getName(), referenceVOList);
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0702.getKey(), ErrorCodeEnum.E0702.getValue());
    }

    /**
     * 根据编码和业务码查询参数信息
     *
     * @param referenceQueryDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ReferenceListVO queryReferenceByParam(ReferenceQueryDTO referenceQueryDTO) {
        List<ReferenceDO> referenceDOList = referenceMapper.queryByParamCode(referenceQueryDTO.getSerial(), referenceQueryDTO.getName());
        ReferenceListVO referenceListVO = new ReferenceListVO();
        if (referenceDOList.size() < 0) {
            referenceListVO.setSuccess(false);
            referenceListVO.setErrorCode("E0739");
            referenceListVO.setErrorMsg("请求数据为空！");
            return referenceListVO;
        }
        List<ReferenceVO> referenceVOList = ObjectConvertUtil.convertInstance().objectConvert(referenceDOList, ReferenceDO.class);
        referenceListVO.setReferenceVOList(referenceVOList);
        return referenceListVO;
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ReferenceRespVO getReference() {
        ReferenceRespVO referenceRespVO = new ReferenceRespVO();
        referenceRespVO.setRandom("SN100");
        List<ReferenceDO> referenceDOList = referenceMapper.queryParam();
        List<ReferenceInfoVO> list = ObjectConvertUtil.convertInstance().objectConvert(referenceDOList, ReferenceDO.class);
        referenceRespVO.setParamList(list);
        return referenceRespVO;
    }
}
