package com.ratta.controller;
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

import com.ratta.dto.CommonIdDTO;
import com.ratta.dto.RoleDTO;
import com.ratta.dto.RoleVagueDTO;
import com.ratta.service.RoleService;
import com.ratta.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 12:31
 */
@Api(description = "角色接口集合")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 根据名称分页模糊查询
     *
     * @param roleVagueDTO
     * @return
     */
    @PostMapping("/role/resource")
    @ApiOperation(value = "获取角色列表|模糊查询接口")
    @ApiImplicitParam(name = "roleVagueDTO", dataType = "RoleVagueDTO")
    public CommonListVO<RoleVO> allRoleList(@RequestBody @Valid RoleVagueDTO roleVagueDTO) {
        return roleService.RoleVOList(roleVagueDTO, request.getHeader("User-ID"));
    }

    /**
     * 添加角色
     *
     * @param roleDTO
     * @return
     */
    @PostMapping("/role")
    @ApiOperation(value = "添加角色接口")
    @ApiImplicitParam(name = "roleDTO", dataType = "RoleDTO")
    public BaseVO addRole(@RequestBody @Valid RoleDTO roleDTO) {
        return roleService.addRole(roleDTO, request.getHeader("User-ID"));
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param id
     * @return
     */
    @GetMapping("/role/{id}")
    @ApiOperation(value = "根据角色ID查询角色信息接口")
    @ApiImplicitParam(name = "id", value = "角色ID")
    public RoleBeanVO getRole(@PathVariable String id) {
        return roleService.queryRoleById(id);
    }

    /**
     * 修改角色
     *
     * @param id
     * @param roleDTO
     * @return
     */
    @PutMapping("/role/{id}")
    @ApiOperation(value = "修改角色接口")
    @ApiImplicitParam(name = "id", value = "角色ID")
    public BaseVO updateRole(@PathVariable String id, @RequestBody @Valid RoleDTO roleDTO) {
        return roleService.updateRole(roleDTO, id, request.getHeader("User-ID"));
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @ApiImplicitParam(name = "id", value = "角色ID")
    public BaseVO deleteRole(@PathVariable String id) {
        return roleService.deleteRole(id);
    }

    /**
     * 根据角色ID获取选中资源
     *
     * @param id
     * @return
     */
    @GetMapping("/role/resource/{id}")
    @ApiOperation(value = "根据角色ID获取选中资源接口")
    @ApiImplicitParam(name = "id", value = "角色ID")
    public List<ResourceVO> querySelectResource(@PathVariable String id) {
        return roleService.querySelectResource(id);
    }

    /**
     * 授权资源
     * <p>更新角色与资源的对应关系</p>
     *
     * @param commonIdDTO 拼接的数组格式资源ID
     * @param id          角色ID
     * @return
     */
    @PutMapping("/role/resource/{id}")
    @ApiOperation(value = "授权资源接口")
    @ApiImplicitParam(name = "id", value = "角色ID")
    public BaseVO updateRoleResource(@RequestBody @Valid CommonIdDTO commonIdDTO, @PathVariable String id) {
        return roleService.updateRoleResource(id, commonIdDTO, request.getHeader("User-ID"));
    }

    /**
     * 获取所有的角色信息
     *
     * @return
     */
    @GetMapping("/role")
    @ApiOperation(value = "获取所有角色接口")
    public RoleAllVO findAllRole() {
        return roleService.findAllRole(request.getHeader("User-ID"));
    }
}
