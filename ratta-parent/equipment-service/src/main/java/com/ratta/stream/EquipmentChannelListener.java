package com.ratta.stream;

import com.ratta.domain.EquipmentLogDO;
import com.ratta.dto.OssDTO;
import com.ratta.dto.UpdateEquipmentDTO;
import com.ratta.mapper.EquipmentLogMapper;
import com.ratta.mapper.EquipmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@EnableBinding(EquipmentChannel.class)
public class EquipmentChannelListener {

    @Value("${Equipment_Log_BucketName}")
    private String equipment_Log_BucketName;

    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private EquipmentLogMapper equipmentLogMapper;

    @Autowired
    private OssDeleteOutput ossDeleteOutput;

    @StreamListener(value = EquipmentChannel.SYSTEM_BUSINESS_INPUT, condition = "headers['equipment']=='update'")
    public void updateEquipment(@Payload UpdateEquipmentDTO updateEquipmentDTO) {
        if (updateEquipmentDTO.getUpdateStatus().equals("2") && StringUtils.isEmpty(updateEquipmentDTO.getFirmwareVersion())) {
            log.error("版本号不能为空:{}", updateEquipmentDTO.getEquipmentNumber());
        }
        equipmentMapper.update(updateEquipmentDTO.getEquipmentNumber(), updateEquipmentDTO.getFirmwareVersion(), updateEquipmentDTO.getUpdateStatus());
    }

    @StreamListener(value = EquipmentChannel.EQUIPMENT_INPUT, condition = "headers['equipmentlog']=='delete'")
    public void equipmentLogClean(@Payload Long daysBefore) {
        log.info("================================{}", daysBefore);
        // 查询小于时间的所有记录
        List<EquipmentLogDO> equipmentLogList = equipmentLogMapper.selectByDays(daysBefore);
        if (equipmentLogList != null && equipmentLogList.size() > 0) {
            // 从ufile上删除小于时间的文件
            for (EquipmentLogDO equipmentLogDO : equipmentLogList) {
                //删除终端日志
                int result = equipmentLogMapper.delEquipmentLog(equipmentLogDO.getId());
                log.info("清理终端日志受影响行数【{}】", result);
                //删除ufile
                OssDTO ossDTO = new OssDTO(equipment_Log_BucketName, equipmentLogDO.getLogName());
                ossDeleteOutput.delete(ossDTO);
            }
        }
    }
}
