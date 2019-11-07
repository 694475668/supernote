package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.UserEquipmentDO;
import com.ratta.domain.UserEquipmentInfoDO;

@Mapper
public interface UserEquipmentMapper {

	UserEquipmentDO queryUserEquipment(@Param("equipmentNumber") String equipmentNumber);
	
	List<UserEquipmentDO> queryUserEquipmentByUserid(@Param("userId") Long userId);
	
	List<UserEquipmentInfoDO> queryUserEquipmentByParam(@Param("equipmentNumber") String equipmentNumber, @Param("userId") Long userId);
    
	int insert(UserEquipmentDO userEquipmentDO);
    
    int delete(@Param("equipmentNumber") String equipmentNumber, @Param("userId") String userId);

    int queryCountByUserId(@Param("userId") String userId);
    
}