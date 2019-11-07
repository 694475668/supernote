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
import com.ratta.domain.EquipmentDO;
import com.ratta.domain.EquipmentLogDO;
import com.ratta.domain.EquipmentLogQueryDO;
import com.ratta.dto.*;
import com.ratta.enums.ErrorCodeEnum;
import com.ratta.mapper.EquipmentLogMapper;
import com.ratta.mapper.EquipmentMapper;
import com.ratta.service.EquipmentLogService;
import com.ratta.service.OssService;
import com.ratta.stream.OssDeleteOutput;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 15:25
 */
@Service
public class EquipmentLogServiceImpl implements EquipmentLogService {

    @Value("${Equipment_Log_BucketName}")
    private String equipment_Log_BucketName;

    @Autowired
    private EquipmentLogMapper equipmentLogMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private OssService ossService;

    @Autowired
    private OssDeleteOutput ossDeleteOutput;

    /**
     * <p>query</p>
     * <p>查询设备日志信息</p>
     *
     * @param equipmentLogQueryDTO 查询参数
     * @return 数据模型
     */
    @Override
    public CommonListVO<EquipmentLogVO> findAll(EquipmentLogQueryDTO equipmentLogQueryDTO) {
        List<EquipmentLogVO> equipmentLogVOList = new ArrayList<>();
        //创建commonListVO对象也就是前端展示数据对象
        CommonListVO<EquipmentLogVO> commonListVO = new CommonListVO<EquipmentLogVO>();
        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(equipmentLogQueryDTO.getSortField())) {
            PageHelper.startPage(equipmentLogQueryDTO.getPageNo(), equipmentLogQueryDTO.getPageSize(), equipmentLogQueryDTO.getSortField() + " " + equipmentLogQueryDTO.getSortRules());
        } else {
            PageHelper.startPage(equipmentLogQueryDTO.getPageNo(), equipmentLogQueryDTO.getPageSize());
        }
        EquipmentLogQueryDO equipmentLogQueryDO = new EquipmentLogQueryDO();
        BeanUtils.copyProperties(equipmentLogQueryDTO, equipmentLogQueryDO);
        List<EquipmentLogDO> equipmentLogDOList = equipmentLogMapper.query(equipmentLogQueryDO);

        PageInfo<EquipmentLogDO> uInfo = new PageInfo<>(equipmentLogDOList);
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());
        for (EquipmentLogDO equipmentLogDO : equipmentLogDOList) {
            EquipmentLogVO equipmentLogVO = new EquipmentLogVO();
            BeanUtils.copyProperties(equipmentLogDO, equipmentLogVO);
            equipmentLogVOList.add(equipmentLogVO);
            commonListVO.setVoList(equipmentLogVOList);
        }
        return commonListVO;
    }

    /**
     * <p>delete</p>
     * <p>根据id查询删除设备日志信息</p>
     *
     * @param equipmentLogDeleteDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO deleteEquipmentLog(EquipmentLogDTO equipmentLogDeleteDTO) {
        EquipmentLogDO equipmentLogDO = equipmentLogMapper.queryEquipmentLogById(equipmentLogDeleteDTO.getId());
        if (Constant.ORDINARYLOGS.equals(equipmentLogDO.getType()) || Constant.FLAG.equals(equipmentLogDO.getFlag())) {
            //删除设备日志信息
            int result = equipmentLogMapper.delEquipmentLog(equipmentLogDeleteDTO.getId());
            if (result > 0) {
                //删除ufile
                OssDTO ossDTO = new OssDTO(equipment_Log_BucketName, equipmentLogDO.getLogName());
                ossDeleteOutput.delete(ossDTO);
                return new BaseVO();
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0550.getKey(), ErrorCodeEnum.E0550.getValue());
    }

    /**
     * 添加备注
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    @Override
    public BaseVO addRemark(EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO) {
        EquipmentLogDO getEquipmentLog = equipmentLogMapper.queryEquipmentLogById(equipmentLogAddRemarkDTO.getId());
        //未下载不允许添加备注
        if (Constant.ISDOWNLOAD.equals(getEquipmentLog.getIsDownload())) {
            return new BaseVO(false, ErrorCodeEnum.E0551.getKey(), ErrorCodeEnum.E0551.getValue());
        }
        if (equipmentLogAddRemarkDTO.getRemark() != null) {
            //判断最大字符数
            if (equipmentLogAddRemarkDTO.getRemark().length() > Constant.LENGTHLIMIT) {
                return new BaseVO(false, ErrorCodeEnum.E0552.getKey(), ErrorCodeEnum.E0552.getValue());
            }
        }
        System.out.println(equipmentLogAddRemarkDTO.getRemark());
        EquipmentLogDO equipmentLogDO = new EquipmentLogDO();
        BeanUtils.copyProperties(equipmentLogAddRemarkDTO, equipmentLogDO);
        //1为已查看
        equipmentLogDO.setFlag("1");
        int result = equipmentLogMapper.updateEquipment(equipmentLogDO);
        if (result > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0553.getKey(), ErrorCodeEnum.E0553.getValue());
    }

    /**
     * 审阅
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    @Override
    public BaseVO passed(EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO) {
        EquipmentLogDO getEquipmentLog = equipmentLogMapper.queryEquipmentLogById(equipmentLogAddRemarkDTO.getId());
        //仅允许审阅已查看的记录
        if (Constant.FLAG.equals(getEquipmentLog.getFlag())) {
            return new BaseVO(false, ErrorCodeEnum.E0554.getKey(), ErrorCodeEnum.E0554.getValue());
        }
        EquipmentLogDO equipmentLogDO = new EquipmentLogDO();
        equipmentLogDO.setId(equipmentLogAddRemarkDTO.getId());
        //已审阅
        equipmentLogDO.setFlag("2");
        int result = equipmentLogMapper.updateEquipment(equipmentLogDO);
        if (result > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0555.getKey(), ErrorCodeEnum.E0555.getValue());
    }

    /**
     * 下载完成后更新掉下载状态
     *
     * @param equipmentLogStatusDTO
     * @return
     */
    @Override
    public BaseVO updateIsDownload(EquipmentLogStatusDTO equipmentLogStatusDTO) {
        EquipmentLogDO getEquipmentLog = equipmentLogMapper.queryEquipmentLogByParam(equipmentLogStatusDTO.getId(), equipmentLogStatusDTO.getLogName());
        if (getEquipmentLog != null) {
            if (Constant.STATUS.equals(equipmentLogStatusDTO.getFlag())) {
                EquipmentLogDO equipmentLogDO = new EquipmentLogDO();
                equipmentLogDO.setId(getEquipmentLog.getId());
                //1为已下载
                equipmentLogDO.setIsDownload("1");
                int result = equipmentLogMapper.updateEquipment(equipmentLogDO);
                if (result > 0) {
                    return new BaseVO();
                }
                return new BaseVO(false, ErrorCodeEnum.E0557.getKey(), ErrorCodeEnum.E0557.getValue());
            }
        }
        return new BaseVO(false, ErrorCodeEnum.E0556.getKey(), ErrorCodeEnum.E0556.getValue());
    }

    /**
     * 下载文件
     *
     * @param equipmentLogDTO
     */
    @Override
    public EquipmentLogDownLoadUrlVO download(EquipmentLogDTO equipmentLogDTO) {
        EquipmentLogDO equipmentLogDO = equipmentLogMapper.queryEquipmentLogByParam(equipmentLogDTO.getId(), equipmentLogDTO.getLogName());
        EquipmentLogDownLoadUrlVO equipmentLogUploadUrlVO = new EquipmentLogDownLoadUrlVO();
        if (equipmentLogDO != null) {
            OssDownloadUrlDTO ossDownloadUrlDTO = new OssDownloadUrlDTO();
            ossDownloadUrlDTO.setBucketName(equipment_Log_BucketName);
            ossDownloadUrlDTO.setKey(equipmentLogDO.getLogName());
            ossDownloadUrlDTO.setPrivateFlag(true);
            ossDownloadUrlDTO.setTtl(20 * 60);
            //获取下载地址
            String url = ossService.getFileDownloadUrl(ossDownloadUrlDTO).getUrl();
            equipmentLogUploadUrlVO.setUrl(url);
        }
        return equipmentLogUploadUrlVO;
    }

    /**
     * 根据Id查询备注信息
     *
     * @param id
     * @return
     */
    @Override
    public EquipmentLogVO getEquipmentLog(Long id) {
        EquipmentLogVO equipmentLogVO = new EquipmentLogVO();
        EquipmentLogDO equipmentLogDO = equipmentLogMapper.queryEquipmentLogById(id);
        BeanUtils.copyProperties(equipmentLogDO, equipmentLogVO);
        return equipmentLogVO;
    }

    /**
     * 上传请求地址
     *
     * @param equipmentLogUpLoadApplyDTO
     * @return
     */
    @Override
    public EquipmentLogUpLoadUrlVO apply(EquipmentLogUpLoadApplyDTO equipmentLogUpLoadApplyDTO) {
        EquipmentLogUpLoadUrlVO equipmentLogUpLoadUrlVO = new EquipmentLogUpLoadUrlVO();
        EquipmentDO equipmentDO = equipmentMapper.queryEquipment(equipmentLogUpLoadApplyDTO.getEquipmentNo());
        if (equipmentDO == null) {
            equipmentLogUpLoadUrlVO.setSuccess(false);
            equipmentLogUpLoadUrlVO.setErrorCode(ErrorCodeEnum.E0501.getKey());
            equipmentLogUpLoadUrlVO.setErrorMsg(ErrorCodeEnum.E0501.getValue());
            return equipmentLogUpLoadUrlVO;
        }
        OssDTO ossDTO = new OssDTO(equipment_Log_BucketName, equipmentLogUpLoadApplyDTO.getLogName());
        OssAuthVO fileUploadUrl = ossService.getFileUploadUrl(ossDTO);
        equipmentLogUpLoadUrlVO.setAuthorization(fileUploadUrl.getPutAuthorization());
        equipmentLogUpLoadUrlVO.setUrl(fileUploadUrl.getUrl());
        return equipmentLogUpLoadUrlVO;
    }

    /**
     * 添加日志信息
     *
     * @return
     */
    @Override
    public BaseVO finish(EquipmentLogUpLoadFinishDTO equipmentLogUpLoadFinishDTO) {
        EquipmentLogDO equipmentLogDO = new EquipmentLogDO();
        BeanUtils.copyProperties(equipmentLogUpLoadFinishDTO, equipmentLogDO);
        equipmentLogDO.setEquipmentNumber(equipmentLogUpLoadFinishDTO.getEquipmentNo());
        int result = equipmentLogMapper.savaEquipmentLog(equipmentLogDO);
        if (result > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0558.getKey(), ErrorCodeEnum.E0558.getValue());
    }


}
