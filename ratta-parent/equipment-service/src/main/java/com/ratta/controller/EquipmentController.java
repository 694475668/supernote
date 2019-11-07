package com.ratta.controller;

import com.ratta.dto.*;
import com.ratta.service.EquipmentService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.BindStatusVO;
import com.ratta.vo.QueryUserEquipmentVO;
import com.ratta.vo.UserEquipmentListVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.EquipmentVO;
import com.ratta.vo.UserEquipmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author yll
 *
 */
@Api(description = "设备相关接口")
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation(value = "激活设备")
    @ApiImplicitParam(name = "activateEquipmentDTO", dataType = "ActivateEquipmentDTO")
    @PostMapping("/activate")
    public BaseVO activateEquipment(@RequestBody @Valid ActivateEquipmentDTO activateEquipmentDTO) {
        return equipmentService.activateEquipment(activateEquipmentDTO);
    }

    @ApiOperation(value = "绑定设备")
    @ApiImplicitParam(name = "bindEquipmentDTO", dataType = "BindEquipmentDTO")
    @PostMapping("/bind")
    public BaseVO bindEquipment(@RequestBody @Valid BindEquipmentDTO bindEquipmentDTO) {
        return equipmentService.bindEquipment(bindEquipmentDTO);
    }

    @ApiOperation(value = "解绑设备")
    @ApiImplicitParam(name = "unbindEquipmentDTO", dataType = "UnbindEquipmentDTO")
    @PostMapping("/unbind")
    public BaseVO unbindEquipment(@RequestBody @Valid UnbindEquipmentDTO unbindEquipmentDTO) {
        return equipmentService.unbindEquipment(unbindEquipmentDTO);
    }

    @ApiOperation(value = "绑定状态")
    @PostMapping("/bind/status")
    public BindStatusVO bindStatus(HttpServletRequest request) {
        String userId = request.getHeader("User-ID");
        return equipmentService.querybindStatus(userId);
    }
    
    @ApiOperation(value = "根据条件查询用户设备")
    @ApiImplicitParam(name = "queryUserEquipmentDTO", dataType = "QueryUserEquipmentDTO")
    @PostMapping("/query/user/equipment")
    public CommonListVO<QueryUserEquipmentVO> queryUserEquipment(@RequestBody @Valid QueryUserEquipmentDTO queryUserEquipmentDTO) {
        return equipmentService.queryUserEquipmentByParam(queryUserEquipmentDTO);
    }
    
    @ApiOperation(value = "根据设备号查询用户设备")
    @ApiImplicitParam(name = "userEquipmentDTO", dataType = "UserEquipmentDTO")
    @PostMapping("/query/by/equipmentno")
    public UserEquipmentVO queryUserEquipmentByNo(@RequestBody @Valid UserEquipmentDTO userEquipmentDTO) {
        return equipmentService.queryUserEquipment(userEquipmentDTO);
    }
    
    @ApiOperation(value = "根据设备号查询设备(内部调用)")
    @GetMapping("/query/{equipmentNo}")
    public EquipmentVO queryEquipmentByNo(@PathVariable("equipmentNo") String equipmentNo) {
        return equipmentService.queryEquipmentByNo(equipmentNo);
    }
    
    
    @ApiOperation(value = "根据用户id查询用户设备")
    @GetMapping("/query/by/{userId}")
    public UserEquipmentListVO queryUserEquipmentByUserId(@PathVariable("userId") Long userId) {
        return equipmentService.queryUserEquipmentByUserId(userId);
    }
}
