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

import com.ratta.dto.*;
import com.ratta.service.UserService;
import com.ratta.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:39
 */
@RestController
@Api(description = "用户接口集合")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/sys/user/login")
    @ApiOperation("用户登陆接口")
    @ApiImplicitParam(name = "userLoginDTO", dataType = "UserLoginDTO")
    public BaseVO login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }


    /**
     * editCurrentUserPwd 修改自己的密码
     *
     * @param userDTO 原始密码  新密码
     * @return
     */
    @PutMapping("/sys/user/pwd")
    @ApiOperation("用户标识无效时修改密码接口")
    @ApiImplicitParam(name = "userDTO", dataType = "UserDTO")
    public BaseVO editCurrentUserPwd(@RequestBody @Valid UserDTO userDTO) {
        String userId;
        try {
            //防止报空指针自己做空指针异常处理
            userId = request.getHeader("User-ID");
        } catch (NullPointerException e) {
            userId = "";
        }
        return userService.editCurrentUserPwd(userId, userDTO);
    }

    /**
     * dataGrid 获取用户数据信息
     *
     * @param userVagueDTO 参数
     * @return
     */
    @PostMapping("/sys/user/role")
    @ApiOperation("根据查询条件获取用户信息接口")
    @ApiImplicitParam(name = "userVagueDTO", dataType = "UserVagueDTO")
    public CommonListVO<UserVO> dataGrid(@RequestBody @Valid UserVagueDTO userVagueDTO) {
        return userService.dataGrid(userVagueDTO, request.getHeader("User-ID"));
    }

    /**
     * 添加用户
     *
     * @param userAddDTO
     * @return
     */
    @PostMapping("/sys/user/add")
    @ApiOperation("添加用户接口")
    @ApiImplicitParam(name = "userAddDTO", dataType = "UserAddDTO")
    public BaseVO addUser(@RequestBody @Valid UserAddDTO userAddDTO) {
        return userService.addUser(userAddDTO, request.getHeader("User-ID"));
    }

    /**
     * 启用
     *
     * @param commonIdDTO
     * @return
     */
    @PutMapping("/sys/user/open")
    @ApiOperation("批量启用接口")
    @ApiImplicitParam(name = "commonIdDTO", dataType = "CommonIdDTO")
    public BaseVO open(@RequestBody @Valid CommonIdDTO commonIdDTO) {
        return userService.open(commonIdDTO, request.getHeader("User-ID"));
    }

    /**
     * 停用
     *
     * @param commonIdDTO
     * @return
     */
    @PutMapping("/sys/user/close")
    @ApiOperation("批量停用接口")
    @ApiImplicitParam(name = "commonIdDTO", dataType = "CommonIdDTO")
    public BaseVO close(@RequestBody @Valid CommonIdDTO commonIdDTO) {
        return userService.close(commonIdDTO, request.getHeader("User-ID"));
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/sys/user/{id}")
    @ApiOperation("根据用户ID查询用户信息接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public UserBeanVO getBean(@PathVariable Long id) {
        return userService.getBean(id);
    }

    /**
     * 根据当前用户id获取当前用户信息接口
     *
     * @return
     */
    @GetMapping("/sys/user/query")
    @ApiOperation("根据当前用户id获取当前用户信息接口")
    public UserBeanVO getBean() {
        return userService.getBean(Long.valueOf(request.getHeader("User-ID")));
    }

    /**
     * 修改用户
     *
     * @param userUpdateDTO
     * @param id
     * @return
     */
    @PutMapping("/sys/user/{id}")
    @ApiOperation("修改用户信息接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public BaseVO updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @PathVariable Long id) {
        return userService.updateUser(userUpdateDTO, id, request.getHeader("User-ID"));
    }

    /**
     * 修改密码
     *
     * @param userPwdDTO
     * @return
     */
    @PutMapping("/sys/user")
    @ApiOperation("修改密码接口")
    @ApiImplicitParam(name = "userPwdDTO", dataType = "UserPwdDTO")
    public BaseVO updatePwd(@RequestBody @Valid UserPwdDTO userPwdDTO) {
        return userService.updatePwd(userPwdDTO, request.getHeader("User-ID"));
    }

    /**
     * 验证登录的用户是否是可用
     *
     * @return
     */
    @GetMapping("/sys/user/verifvPassword")
    @ApiOperation("验证登录的用户是否是可用接口")
    public BaseVO verifvPassword() {
        return userService.verifvPassword(request.getHeader("User-ID"));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/sys/user/{id}")
    @ApiOperation(value = "删除用户接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public BaseVO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id, request.getHeader("User-ID"));
    }

    /**
     * 解锁
     *
     * @param id
     * @return
     */
    @PutMapping("/sys/user/unlock/{id}")
    @ApiOperation(value = "解锁用户接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public BaseVO unlock(@PathVariable Long id) {
        return userService.unlock(id, Long.valueOf(request.getHeader("User-ID")));
    }

    /**
     * 用户解锁验证是否是自己
     *
     * @param id
     * @return
     */
    @GetMapping("/sys/user/erificationUnlock/{id}")
    @ApiOperation(value = "用户解锁验证是否是自己接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public BaseVO userUnlock(@PathVariable Long id) {
        return userService.userUnlock(id, Long.valueOf(request.getHeader("User-ID")));
    }

    /**
     * 授权
     *
     * @param userGrantDTO
     * @return
     */

    @PostMapping("/sys/user/grant")
    @ApiOperation(value = "用户授权接口")
    @ApiImplicitParam(name = "userGrantDTO", dataType = "UserGrantDTO")
    public BaseVO grant(@RequestBody @Valid UserGrantDTO userGrantDTO) {
        return userService.grant(userGrantDTO, request.getHeader("User-ID"));
    }

    /**
     * 用户授权验证是否是自己
     *
     * @param userGrantDTO
     * @returnv
     */
    @PostMapping("/sys/user/erificationGrant")
    @ApiOperation(value = "用户授权验证是否本身接口")
    @ApiImplicitParam(name = "userGrantDTO", dataType = "UserGrantDTO")
    public BaseVO userGrant(@RequestBody @Valid UserGrantDTO userGrantDTO) {
        return userService.userGrant(userGrantDTO, request.getHeader("User-ID"));
    }


    /**
     * 根据用户ID获取选中的角色
     *
     * @param id
     * @return
     */
    @GetMapping("/sys/user/role/{id}")
    @ApiOperation(value = "根据用户ID获取选中的角色接口")
    @ApiImplicitParam(name = "id", value = "用户ID")
    public UserRoleVO findRoleByUserId(@PathVariable String id) {
        return userService.findRoleByUserId(id);
    }


    @PostMapping("/sys/user")
    @ApiOperation(value = "根据多用户ID获取信息")
    @ApiImplicitParam(name = "commonIdDTO", dataType = "CommonIdDTO")
    public CommonListVO<UserPartVO> getUserArray(@RequestBody @Valid CommonIdDTO commonIdDTO) {
        return userService.getUserArray(commonIdDTO);
    }

    @PostMapping("/sys/user/check")
    @ApiOperation(value = "验证原密码是否正确")
    BaseVO checkOldPwd(@RequestBody UserDTO userDTO) {
        return userService.checkOldPwd(request.getHeader("User-ID"), userDTO);
    }
}
