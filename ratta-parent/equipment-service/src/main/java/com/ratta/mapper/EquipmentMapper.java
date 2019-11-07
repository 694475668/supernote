package com.ratta.mapper;

import com.ratta.domain.EquipmentDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EquipmentMapper {
    /**
     * 添加设备
     */
    int insert(EquipmentDO equipmentDO);

    /**
     * 查询设备
     */
    EquipmentDO queryEquipment(@Param("equipmentNo") String equipmentNo);

    /**
     * 更新设备
     * @param equipmentNumber 设备号
     * @param firmwareVersion 版本号
     * @param updateStatus 更新状态
     */
    int update(@Param("equipmentNumber") String equipmentNumber,
               @Param("firmwareVersion") String firmwareVersion, @Param("updateStatus") String updateStatus);

}
