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
import com.ratta.constants.Constant;
import com.ratta.domain.DictionaryDO;
import com.ratta.domain.UserDO;
import com.ratta.dto.DictionaryDTO;
import com.ratta.dto.DictionaryQueryDTO;
import com.ratta.dto.DictionaryVagueDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.DictionaryMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.service.DictionaryService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ****** Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:17
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DictionarysVO> getResourceTypeList(String name, String language) {
        List<DictionarysVO> dictionaryVOList = new ArrayList<>();
        List<DictionaryDO> dictionaryDOList = null;
        //第一次请求是个空，所以我设置TRESOURCETYPE_ID查询资源类型
        if (StringUtils.isEmpty(name)) {
            dictionaryDOList = dictionaryMapper.findDictionaryByName(Constant.TRESOURCETYPE_ID, language);
        } else {
            dictionaryDOList = dictionaryMapper.findDictionaryByName(name, language);
        }
        for (DictionaryDO dictionaryDO : dictionaryDOList) {
            DictionarysVO dictionaryVO = new DictionarysVO();
            dictionaryVO.setId(dictionaryDO.getId());
            dictionaryVO.setValueMeaning(dictionaryDO.getValueMeaning());
            dictionaryVOList.add(dictionaryVO);
        }
        return dictionaryVOList;
    }

    /**
     * <p>query</p>
     * <p>查询数据字典信息</p>
     *
     * @param dictionaryVagueDTO
     * @return
     */
    @Override
    public CommonListVO<DictionaryVagueVO> query(DictionaryVagueDTO dictionaryVagueDTO) {
        List<DictionaryVagueVO> dictionaryVOList = new ArrayList<>();
        //创建RoleListVO对象也就是前端展示数据对象
        CommonListVO<DictionaryVagueVO> commonListVO = new CommonListVO<DictionaryVagueVO>();
        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(dictionaryVagueDTO.getSortField())) {
            PageHelper.startPage(dictionaryVagueDTO.getPageNo(), dictionaryVagueDTO.getPageSize(), dictionaryVagueDTO.getSortField() + " " + dictionaryVagueDTO.getSortRules());
        } else {
            PageHelper.startPage(dictionaryVagueDTO.getPageNo(), dictionaryVagueDTO.getPageSize());
        }
        List<DictionaryDO> query = dictionaryMapper.query(dictionaryVagueDTO.getName(), dictionaryVagueDTO.getValueMeaning());
        PageInfo<DictionaryDO> uInfo = new PageInfo<>(query);
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());
        for (DictionaryDO dictionaryDO : query) {
            DictionaryVagueVO dictionaryVO = new DictionaryVagueVO();
            BeanUtils.copyProperties(dictionaryDO, dictionaryVO);
            dictionaryVOList.add(dictionaryVO);
            commonListVO.setVoList(dictionaryVOList);
        }
        return commonListVO;
    }

    /**
     * 添加数据字典信息
     *
     * @param dictionaryDTO 数据字典信息
     * @param userId
     * @return
     */
    @Override
    public BaseVO saveDictionary(DictionaryDTO dictionaryDTO, String userId) {
        DictionaryDO dictionaryDO = new DictionaryDO();
        BeanUtils.copyProperties(dictionaryDTO, dictionaryDO);
        UserDO userById = userMapper.getUserById(Long.valueOf(userId));
        dictionaryDO.setOpUser(userById.getUsername());
        //同一个业务码下不允许存在相同编码
        List<DictionaryDO> dictionaryDOList = dictionaryMapper.checkPamram(dictionaryDTO.getName(), dictionaryDTO.getValue());
        if (dictionaryDOList.size() > 0) {
            return new BaseVO(false, ErrorCodeEnum.E0730.getKey(), ErrorCodeEnum.E0730.getValue());
        }
        //添加
        int row = dictionaryMapper.addDictionary(dictionaryDO);
        if (row > 0) {
            List<DictionaryDO> dictionaryDOS = dictionaryMapper.query(dictionaryDTO.getName(), "");
            List<DictionaryVO> dictionaryVOList = new ArrayList<>();
            for (DictionaryDO aDo : dictionaryDOS) {
                DictionaryVO dictionaryVO = new DictionaryVO();
                BeanUtils.copyProperties(aDo, dictionaryVO);
                dictionaryVOList.add(dictionaryVO);
            }
            if (dictionaryDOS.size() > 0) {
                redisTemplate.delete(dictionaryDO.getName());
                redisTemplate.opsForList().leftPushAll(dictionaryDO.getName(), dictionaryVOList);
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0701.getKey(), ErrorCodeEnum.E0701.getValue());
    }

    /**
     * 修改数据字典信息
     *
     * @param dictionaryDTO 数据字典信息
     * @param id
     * @param userId
     * @return
     */
    @Override
    public BaseVO updateDictionary(DictionaryDTO dictionaryDTO, Long id, String userId) {
        DictionaryDO dictionaryDO = new DictionaryDO();
        BeanUtils.copyProperties(dictionaryDTO, dictionaryDO);
        DictionaryDO getDictionary = dictionaryMapper.queryDictionaryById(id);
        redisTemplate.opsForList().remove(getDictionary.getName(), 0, getDictionary);
        if (getDictionary != null) {
            dictionaryDO.setId(getDictionary.getId());
            dictionaryDO.setOpTime(new Date());
            int row = dictionaryMapper.updateDictionary(dictionaryDO);
            if (row > 0) {
                List<DictionaryDO> dictionaryDOS = dictionaryMapper.query(dictionaryDTO.getName(), "");
                List<DictionaryVO> dictionaryVOList = new ArrayList<>();
                for (DictionaryDO aDo : dictionaryDOS) {
                    DictionaryVO dictionaryVO = new DictionaryVO();
                    BeanUtils.copyProperties(aDo, dictionaryVO);
                    dictionaryVOList.add(dictionaryVO);
                }
                if (dictionaryDOS.size() > 0) {
                    redisTemplate.delete(dictionaryDO.getName());
                    redisTemplate.opsForList().leftPushAll(dictionaryDO.getName(), dictionaryVOList);
                }
                return new BaseVO();
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0705.getKey(), ErrorCodeEnum.E0705.getValue());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public BaseVO deleteDictionary(Long id) {
        DictionaryDO dictionaryDO = dictionaryMapper.queryDictionaryById(id);
        redisTemplate.delete(dictionaryDO.getName());
        int row = dictionaryMapper.delDictionary(id);
        if (row > 0) {
            List<DictionaryDO> dictionaryDOS = dictionaryMapper.query(dictionaryDO.getName(), "");
            List<DictionaryVO> dictionaryVOList = new ArrayList<>();
            for (DictionaryDO aDo : dictionaryDOS) {
                DictionaryVO dictionaryVO = new DictionaryVO();
                BeanUtils.copyProperties(aDo, dictionaryVO);
                dictionaryVOList.add(dictionaryVO);
            }
            if (dictionaryDOS.size() > 0) {
                redisTemplate.opsForList().leftPushAll(dictionaryDO.getName(), dictionaryVOList);
            }
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0702.getKey(), ErrorCodeEnum.E0702.getValue());
    }

    /**
     * 根据ID查询数据字典信息
     *
     * @param id
     * @return
     */
    @Override
    public DictionaryVO queryDictionaryById(Long id) {
        DictionaryDO dictionaryDO = dictionaryMapper.queryDictionaryById(id);
        DictionaryVO dictionaryVO = new DictionaryVO();
        BeanUtils.copyProperties(dictionaryDO, dictionaryVO);
        return dictionaryVO;
    }

    /**
     * 根据参数查询数据字典
     *
     * @param dictionaryQueryDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public DictionaryListVO queryDictionaryByNameAndValue(DictionaryQueryDTO dictionaryQueryDTO) {
        DictionaryListVO dictionaryListVO = new DictionaryListVO();
        List<DictionaryDO> dictionaryDOList = dictionaryMapper.queryByPamram(dictionaryQueryDTO.getName(), dictionaryQueryDTO.getValue());
        if (dictionaryDOList.size() < 0) {
            dictionaryListVO.setSuccess(false);
            dictionaryListVO.setErrorCode("E0739");
            dictionaryListVO.setErrorMsg("请求数据为空！");
            return dictionaryListVO;
        }
        List<DictionarysVO> dictionarysVOList = ObjectConvertUtil.convertInstance().objectConvert(dictionaryDOList, DictionaryDO.class);
        dictionaryListVO.setDictionaryVOList(dictionarysVOList);
        return dictionaryListVO;
    }
}

    