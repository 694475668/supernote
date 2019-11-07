package com.ratta.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.EquipmentDO;
import com.ratta.domain.UserEquipmentDO;
import com.ratta.domain.UserEquipmentInfoDO;
import com.ratta.dto.ActivateEquipmentDTO;
import com.ratta.dto.BindEquipmentDTO;
import com.ratta.dto.FileHisSyncDTO;
import com.ratta.dto.QueryUserEquipmentDTO;
import com.ratta.dto.UnbindEquipmentDTO;
import com.ratta.dto.UserEquipmentDTO;
import com.ratta.dto.UserFileDTO;
import com.ratta.dto.UserInfoDTO;
import com.ratta.enums.ErrorCodeEnum;
import com.ratta.mapper.EquipmentMapper;
import com.ratta.mapper.UserEquipmentMapper;
import com.ratta.service.EquipmentService;
import com.ratta.service.SystemBusinessService;
import com.ratta.service.UserService;
import com.ratta.stream.EquipmentChannel;
import com.ratta.util.FormatUtil;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.BindStatusVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.EquipmentVO;
import com.ratta.vo.QueryUserEquipmentVO;
import com.ratta.vo.StockPartVO;
import com.ratta.vo.UserEquipmentListVO;
import com.ratta.vo.UserEquipmentVO;
import com.ratta.vo.UserInfoVo;
import com.ratta.vo.UserQueryByIdVO;

import lombok.extern.slf4j.Slf4j;


/**
 * @author yll
 *
 */
@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private UserEquipmentMapper userEquipmentMapper;
    @Autowired
    private SystemBusinessService systemBusinessService;
    @Autowired
    private UserService userService;
    @Resource(name = EquipmentChannel.EQUIPMENT_OUTPUT)
    private MessageChannel sendSnMessageChannel;
    @Resource(name = EquipmentChannel.FILE_OUTPUT)
    private MessageChannel sendFileMessageChannel;
    private static final String ORDERBY = "create_time desc";
    
    @Override
    public BaseVO activateEquipment(ActivateEquipmentDTO activateEquipmentDTO) {
        //根据设备号查询库存
        CommonVO<StockPartVO> commonVO = systemBusinessService.queryByEquipmentNumber(activateEquipmentDTO.getEquipmentNo());
        if(commonVO.isSuccess() == false) {
        	return new BaseVO(false, ErrorCodeEnum.E1203.getKey(), ErrorCodeEnum.E1203.getValue());
        }
        //判断是否已经出库
        log.info("stockPartVO:{}", commonVO.getVoT().getFlag());
        //设备不存在或设备不是出库状态
        if (commonVO.getVoT() == null || !"2".equals(commonVO.getVoT().getFlag())) {
            return new BaseVO(false, ErrorCodeEnum.E0501.getKey(), ErrorCodeEnum.E0501.getValue());
        }
        //添加设备信息
        	//先判断设备信息是否已经存在
        EquipmentDO equipmentDO = equipmentMapper.queryEquipment(activateEquipmentDTO.getEquipmentNo());
        if(equipmentDO != null) {
        	//已存在，默认激活成功
        	return new BaseVO();
        }
        //添加新设备
        equipmentMapper.insert(new EquipmentDO(commonVO.getVoT().getEquipmentNumber(), commonVO.getVoT().getFirmwareVersion(), null));
        //发kafka消息，库存模块收到消息后，执行修改设备状态的操作
        sendSnMessageChannel.send(MessageBuilder.withPayload(activateEquipmentDTO.getEquipmentNo()).setHeader(Constant.EQUIPMENT, Constant.ACTIVATE).build());
        return new BaseVO();
    }

    @Override
    public BaseVO bindEquipment(BindEquipmentDTO bindEquipmentDTO) {
        BaseVO baseVO = new BaseVO();
        String phone = null;
        String email = null;
        //此段代码并非主逻辑业务，可单独写成一个方法
        if (!StringUtils.isEmpty(bindEquipmentDTO.getAccount())) {
            if (bindEquipmentDTO.getAccount().contains("@")) {
                email = bindEquipmentDTO.getAccount();
                bindEquipmentDTO.setCountryCode(null);
            } else {
                phone = bindEquipmentDTO.getAccount();
                bindEquipmentDTO.setCountryCode(FormatUtil.getCountryCode(bindEquipmentDTO.getCountryCode()));
            }
        }
        //根据账号查询用户信息
        UserInfoVo userInfoVo = userService.queryUserOne(new UserInfoDTO(bindEquipmentDTO.getCountryCode(), phone, email));
        if (userInfoVo == null) {
            log.error("账号不存在:{}", bindEquipmentDTO.getAccount());
            return new BaseVO(false, ErrorCodeEnum.E0502.getKey(), ErrorCodeEnum.E0502.getValue());
        }
        //判断设备是否已经激活
        EquipmentDO equipmentDO = equipmentMapper.queryEquipment(bindEquipmentDTO.getEquipmentNo());
        if (equipmentDO == null) {
            log.error("账号不存在:{}", bindEquipmentDTO.getAccount());
            return new BaseVO(false, ErrorCodeEnum.E0501.getKey(), ErrorCodeEnum.E0501.getValue());
        }
        // 判断该设备是否已经绑定账户
        UserEquipmentDO userEquipmentDO = userEquipmentMapper.queryUserEquipment(bindEquipmentDTO.getEquipmentNo());
        //已绑定且用户ID不相同
        if (userEquipmentDO != null && !userEquipmentDO.getUserId().equals(userInfoVo.getUserId())) {
            log.error("设备已经绑定其他账号，无法再次绑定新账号!:{}", bindEquipmentDTO.getEquipmentNo());
            return new BaseVO(false, ErrorCodeEnum.E0503.getKey(), ErrorCodeEnum.E0503.getValue());
        }
        //已绑定且用户ID相同
        if (userEquipmentDO != null && userEquipmentDO.getUserId().equals(userInfoVo.getUserId())) {
            log.info("设备已经绑定成功");
            return new BaseVO();
        }
        //新增设备跟账户的绑定关系
        int result = userEquipmentMapper.insert(new UserEquipmentDO(bindEquipmentDTO.getEquipmentNo(), userInfoVo.getUserId(), bindEquipmentDTO.getName()));
        log.info("新增用户终端设备成功,数据库影响行数：{}", result);
        //修改云盘容量、添加标签页   //发送kafka处理
        UserFileDTO userFileDTO = new UserFileDTO();
        userFileDTO.setUserId(userInfoVo.getUserId());
        userFileDTO.setTotalCapacity(Long.parseLong(bindEquipmentDTO.getTotalCapacity()) * 1024);
        userFileDTO.setLabel(bindEquipmentDTO.getLabel());
		sendFileMessageChannel.send(MessageBuilder.withPayload(userFileDTO).setHeader(Constant.FILE, Constant.ADD).build());
        log.info("设备已经绑定成功");
        return baseVO;
    }

    @Override
    public BaseVO unbindEquipment(UnbindEquipmentDTO unbindEquipmentDTO) {
        UserEquipmentDO userEquipmentDO = userEquipmentMapper.queryUserEquipment(unbindEquipmentDTO.getEquipmentNo());
        if (userEquipmentDO == null) {
            log.info("设备未绑定账号,默认解绑成功：{}", unbindEquipmentDTO.getEquipmentNo());
            return new BaseVO();
        }
        // 解除用户和设备关系
        userEquipmentMapper.delete(unbindEquipmentDTO.getEquipmentNo(), String.valueOf(userEquipmentDO.getUserId()));
        // 删除历史表数据  //调用外部接口
        FileHisSyncDTO fileHisSyncDTO = new FileHisSyncDTO(userEquipmentDO.getUserId(), unbindEquipmentDTO.getEquipmentNo());
		sendFileMessageChannel.send(MessageBuilder.withPayload(fileHisSyncDTO).setHeader(Constant.FILEHISSYNC, Constant.DELETE).build());
        return new BaseVO();
    }

    @Override
    public BindStatusVO querybindStatus(String userId) {
        BindStatusVO bindStatusVO = new BindStatusVO();
        int result = userEquipmentMapper.queryCountByUserId(userId);
        if (result > 0) {
            bindStatusVO.setBindStatus(true);
        }
        return bindStatusVO;
    }

    @Override
    public UserEquipmentVO queryUserEquipment(UserEquipmentDTO userEquipmentDTO) {
        UserEquipmentVO userEquipmentVO = new UserEquipmentVO();
        UserEquipmentDO userEquipmentDO = userEquipmentMapper.queryUserEquipment(userEquipmentDTO.getEquipmentNo());
        if(userEquipmentDO == null) {
        	return userEquipmentVO;
        }
        BeanUtils.copyProperties(userEquipmentDO, userEquipmentVO);
        return userEquipmentVO;
    }


	@SuppressWarnings("unchecked")
	@Override
	public UserEquipmentListVO queryUserEquipmentByUserId(Long userid) {
		UserEquipmentListVO userEquipmentListVO = new UserEquipmentListVO();
		List<UserEquipmentDO> userEquipmentDOlist = userEquipmentMapper.queryUserEquipmentByUserid(userid);
		List<UserEquipmentVO> list = ObjectConvertUtil.convertInstance().objectConvert(userEquipmentDOlist, UserEquipmentVO.class);
		userEquipmentListVO.setEquipmentVOList(list);
		return userEquipmentListVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<QueryUserEquipmentVO> queryUserEquipmentByParam(QueryUserEquipmentDTO queryUserEquipmentDTO) {
		CommonListVO<QueryUserEquipmentVO> commonListVO = new CommonListVO<QueryUserEquipmentVO>();
		UserInfoVo userInfoVo = new UserInfoVo();
		Long userId = null;
		if(!StringUtils.isEmpty(queryUserEquipmentDTO.getTelephone()) || !StringUtils.isEmpty(queryUserEquipmentDTO.getEmail())) {
			//查询用户信息
			userInfoVo = userService.queryUserOne(new UserInfoDTO(getCountryCode(queryUserEquipmentDTO.getCountryCode(), queryUserEquipmentDTO.getTelephone()),  queryUserEquipmentDTO.getTelephone(), queryUserEquipmentDTO.getEmail()));
			 if(userInfoVo.getUserId() == null) {
				 commonListVO.setPages(0); 
				 commonListVO.setTotal(0L);
				 commonListVO.setVoList(new ArrayList<QueryUserEquipmentVO>());
				 return commonListVO;
			 }
			 userId = userInfoVo.getUserId();
		}
		Integer pageNo = Integer.valueOf(queryUserEquipmentDTO.getPageNo());
		Integer pageSize = Integer.valueOf(queryUserEquipmentDTO.getPageSize());
		Long user = userId;
		PageInfo<UserEquipmentInfoDO> pageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy(ORDERBY)
				.doSelectPageInfo(() -> this.userEquipmentMapper.queryUserEquipmentByParam(queryUserEquipmentDTO.getEquipmentNumber(), user));
		BeanUtils.copyProperties(pageInfo, commonListVO);
		commonListVO.setVoList(
				ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), QueryUserEquipmentVO.class));
		for (QueryUserEquipmentVO queryUserEquipmentVO : commonListVO.getVoList()) {
			UserQueryByIdVO  userQueryByIdVO  = userService.queryUser(Long.valueOf(queryUserEquipmentVO.getUserId()));
			queryUserEquipmentVO.setCountryCode(userQueryByIdVO.getCountryCode());
			queryUserEquipmentVO.setTelephone(userQueryByIdVO.getTelephone());
			queryUserEquipmentVO.setEmail(userQueryByIdVO.getEmail());
		}
		return commonListVO;
	}
	
	public String getCountryCode(String countryCode, String  phone) {
		if(StringUtils.isEmpty(phone)) {
			countryCode = null;
		}
		if(!StringUtils.isEmpty(phone) && StringUtils.isEmpty(countryCode)) {
			countryCode = "86";
		}
		return countryCode;
	}

	@Override
	public EquipmentVO queryEquipmentByNo(String equipmentNo) {
		EquipmentVO equipmentVO = new EquipmentVO();
		EquipmentDO equipmentDO = equipmentMapper.queryEquipment(equipmentNo);
        if(equipmentDO == null) {
        	return equipmentVO;
        }
        BeanUtils.copyProperties(equipmentDO, equipmentVO);
        return equipmentVO;
	}
}
