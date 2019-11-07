package com.ratta.controller;

import com.ratta.dto.ResourceDTO;
import com.ratta.service.ResourceService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.ResourceTreeVO;
import com.ratta.vo.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 刘明
 */
@Api(description = "资源接口集合")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取树状菜单接口
     *
     * @return
     */
    @ApiOperation(value = "获取左边栏资源菜单接口")
    @GetMapping(value = "/resource/left/{systemId}")
    public List<ResourceTreeVO> tree(@PathVariable("systemId") String systemId) {
        return resourceService.queryLeftResource(null, systemId, request.getHeader("User-ID"));
    }

    /**
     * 获取所有资源数据
     *
     * @return
     */
    @ApiOperation(value = "获取所有资源数据接口")
    @GetMapping(value = "/resource/all")
    public List<ResourceTreeVO> allTree() {
        return resourceService.allTree(null);
    }

    /**
     * 点击修改按钮根据id或者根据名称查询
     *
     * @return
     */
    @ApiOperation(value = "根据ID查询接口")
    @GetMapping(value = "/resource/{id}")
    @ApiImplicitParam(name = "id", value = "资源ID")
    public ResourceVO querySuperiorAndNameAmbiguous(@PathVariable(value = "id") String id) {
        return resourceService.querySuperiorAndNameAmbiguous(id);
    }


    /**
     * 添加资源接口
     *
     * @param resourceDTO
     * @return
     */
    @ApiOperation(value = "添加资源接口")
    @PostMapping(value = "/resource")
    @ApiImplicitParam(name = "resourceDTO", dataType = "ResourceDTO")
    public BaseVO addResource(@RequestBody @Valid ResourceDTO resourceDTO) {
        return resourceService.addResource(resourceDTO, request.getHeader("User-ID"));
    }

    /**
     * 删除资源接口
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除资源接口")
    @ApiImplicitParam(name = "id", value = "资源ID")
    @DeleteMapping("/resource/{id}")
    public BaseVO delResourceById(@PathVariable(value = "id") String id) {
        return resourceService.delResourceById(id);
    }

    /**
     * 修改资源接口
     *
     * @param resourceDTO
     * @return
     */
    @ApiOperation(value = "修改资源接口")
    @PutMapping("/resource/{id}")
    @ApiImplicitParam(name = "id", value = "资源ID")
    public BaseVO updateResourceById(@RequestBody @Valid ResourceDTO resourceDTO, @PathVariable String id) {
        return resourceService.updateResource(resourceDTO, id, request.getHeader("User-ID"));
    }
}
