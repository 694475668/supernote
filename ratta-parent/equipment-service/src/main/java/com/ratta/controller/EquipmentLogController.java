package com.ratta.controller;


import com.ratta.dto.*;
import com.ratta.service.EquipmentLogService;
import com.ratta.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 15:26
 */
@Api(description = "终端日志管理接口集合")
@RestController
public class EquipmentLogController {

    @Autowired
    private EquipmentLogService equipmentLogService;

    /**
     * <p>query</p>
     * <p>查询设备日志信息</p>
     *
     * @param equipmentLogQueryDTO 查询参数
     * @return 数据模型
     */
    @ApiOperation(value = "根据参数查询设备日志信息接口")
    @PostMapping("/log")
    public CommonListVO<EquipmentLogVO> findAll(@RequestBody @Valid EquipmentLogQueryDTO equipmentLogQueryDTO) {
        return equipmentLogService.findAll(equipmentLogQueryDTO);
    }

    /**
     * <p>delete</p>
     * <p>根据id查询删除设备日志信息</p>
     *
     * @param equipmentLogDeleteDTO
     * @return
     */
    @ApiOperation(value = "根据id删除设备日志信息接口")
    @DeleteMapping("/log/delete")
    public BaseVO deleteEquipmentLog(@RequestBody @Valid EquipmentLogDTO equipmentLogDeleteDTO) {
        return equipmentLogService.deleteEquipmentLog(equipmentLogDeleteDTO);
    }

    /**
     * 添加备注
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    @ApiOperation(value = "添加设备备注信息接口")
    @PutMapping("/log/add/remark")
    public BaseVO addRemark(@RequestBody @Valid EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO) {
        return equipmentLogService.addRemark(equipmentLogAddRemarkDTO);
    }

    /**
     * 审阅
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    @ApiOperation(value = "审阅设备信息接口")
    @PutMapping("/log/passed")
    public BaseVO passed(@RequestBody @Valid EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO) {
        return equipmentLogService.passed(equipmentLogAddRemarkDTO);
    }

    /**
     * 下载完成后更新掉下载状态
     *
     * @param equipmentLogStatusDTO
     * @return
     */
    @ApiOperation(value = "更新下载状态接口")
    @PutMapping("/log/update/status")
    public BaseVO updateIsDownload(@RequestBody EquipmentLogStatusDTO equipmentLogStatusDTO) {
        return equipmentLogService.updateIsDownload(equipmentLogStatusDTO);
    }

    /**
     * 下载文件
     *
     * @param equipmentLogDTO
     * @return
     */
    @ApiOperation(value = "下载文件接口")
    @PostMapping("/log/download")
    public EquipmentLogDownLoadUrlVO download(@RequestBody EquipmentLogDTO equipmentLogDTO) {
        return equipmentLogService.download(equipmentLogDTO);
    }

    /**
     * 根据Id查询备注信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据Id查询备注信息")
    @GetMapping("/log/{id}")
    @ApiImplicitParam(name = "id", value = "固件ID")
    public EquipmentLogVO getEquipmentLog(@PathVariable Long id) {
        return equipmentLogService.getEquipmentLog(id);
    }

    @ApiOperation(value = "上传申请接口")
    @ApiImplicitParam(name = "equipmentLogUpLoadApplyDTO", dataType = "EquipmentLogUpLoadApplyDTO")
    @PostMapping("/log/upload/apply")
    public EquipmentLogUpLoadUrlVO apply(@RequestBody EquipmentLogUpLoadApplyDTO equipmentLogUpLoadApplyDTO) {
        return equipmentLogService.apply(equipmentLogUpLoadApplyDTO);
    }

    @ApiOperation(value = "上传成功添加日志接口")
    @ApiImplicitParam(name = "equipmentLogUpLoadFinishDTO", dataType = "EquipmentLogUpLoadFinishDTO")
    @PostMapping("/log/upload/finish")
    public BaseVO finish(@RequestBody EquipmentLogUpLoadFinishDTO equipmentLogUpLoadFinishDTO) {
        return equipmentLogService.finish(equipmentLogUpLoadFinishDTO);
    }
}
