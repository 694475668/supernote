package com.ratta.service;

import com.ratta.dto.ActivateEquipmentDTO;
import com.ratta.dto.BindEquipmentDTO;
import com.ratta.dto.QueryUserEquipmentDTO;
import com.ratta.dto.UnbindEquipmentDTO;
import com.ratta.dto.UserEquipmentDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.BindStatusVO;
import com.ratta.vo.QueryUserEquipmentVO;
import com.ratta.vo.UserEquipmentListVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.EquipmentVO;
import com.ratta.vo.UserEquipmentVO;

public interface EquipmentService {

	/**
	 * 设备激活
	 * @param activateEquipmentDTO
	 */
	BaseVO activateEquipment(ActivateEquipmentDTO activateEquipmentDTO);
	
	/**
	 * 绑定设备
	 * @param bindEquipmentDTO
	 */
	BaseVO bindEquipment(BindEquipmentDTO bindEquipmentDTO);
	
	/**
	 * 解绑设备
	 * @param unbindEquipmentDTO
	 */
	BaseVO unbindEquipment(UnbindEquipmentDTO unbindEquipmentDTO);
	
	/**
	 * 查询绑定状态
	 * @param userId
	 */
	BindStatusVO querybindStatus(String userId);
	
	/**
	 * 根据条件查询用户设备
	 * @param queryUserEquipmentDTO
	 */
	CommonListVO<QueryUserEquipmentVO> queryUserEquipmentByParam(QueryUserEquipmentDTO queryUserEquipmentDTO);
	
	/**
	 * 查询用户设备
	 * @param userEquipmentDTO
	 */
	UserEquipmentVO queryUserEquipment(UserEquipmentDTO userEquipmentDTO);
	
	/**
	 * 查询设备信息
	 */
	EquipmentVO queryEquipmentByNo(String equipmentNo);
	
	/**
	 * 根据用户id查询用户设备
	 * @param userid
	 */
	UserEquipmentListVO queryUserEquipmentByUserId(Long userid);
}