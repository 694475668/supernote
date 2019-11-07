package com.ratta.controller;

import com.ratta.constants.Constant;
import com.ratta.dto.*;
import com.ratta.service.ReferenceService;
import com.ratta.util.CookieUtils;
import com.ratta.vo.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: 刘 明
 * @date:Created in 2019/6/23 0023 12:33
 */

@Slf4j
@RestController
@RefreshScope
@Api(description = "参数集合集合")
public class ReferenceController {
    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private HttpServletRequest request;
	@Value("${domain}")
	private String domain;
    /**
     * <p>query</p>
     * <p>根据查询条件查询系统参数详细信息</p>
     *
     * @param referenceVaguaDTO 查询条件
     * @return 数据模型
     */
    @SuppressWarnings("rawtypes")
	@PostMapping("/reference")
    @ApiOperation(value = "查询系统参数详细信息接口")
    @ApiImplicitParam(name = "referenceVaguaDTO", dataType = "ReferenceVaguaDTO")
    public CommonListVO query(@RequestBody @Valid ReferenceVaguaDTO referenceVaguaDTO) {
        return referenceService.query(referenceVaguaDTO);
    }

    /**
     * <p>addReference</p>
     * <p>添加系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @return
     */
    @PostMapping("/reference/add")
    @ApiOperation(value = "添加系统参数详细信息接口")
    @ApiImplicitParam(name = "referenceDTO", dataType = "ReferenceDTO")
    public BaseVO addReference(@RequestBody @Valid ReferenceDTO referenceDTO) {
        return referenceService.addReference(referenceDTO, request.getHeader("User-ID"));
    }

    /**
     * <p>queryById</p>
     * <p>根据id查询系统参数详细信息</p>
     *
     * @param id 系统参数详细信息id
     * @return 系统参数详细信息
     */
    @GetMapping("/reference/{id}")
    @ApiOperation(value = "根据id查询系统参数详细信息")
    @ApiImplicitParam(name = "id", value = "参数ID")
    public ReferenceVO queryById(@PathVariable Long id) {
        return referenceService.queryById(id);
    }

    /**
     * <p>update</p>
     * <p>更新系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @param id
     * @return 参数详细返回枚举
     */
    @PutMapping("/reference/{id}")
    @ApiOperation(value = "更新系统参数详细信息接口")
    @ApiImplicitParam(name = "id", value = "参数ID")
    public BaseVO updateReference(@RequestBody @Valid ReferenceDTO referenceDTO, @PathVariable Long id) {
        return referenceService.updateReference(referenceDTO, id, request.getHeader("User-ID"));
    }

    /**
     * <p>deleteReference</p>
     * <p>根据id删除系统参数详细信息</p>
     *
     * @param id 参数明细id
     * @return
     */
    @DeleteMapping("/reference/{id}")
    @ApiOperation(value = "根据id删除系统参数详细信息接口")
    @ApiImplicitParam(name = "id", value = "参数ID")
    public BaseVO deleteReference(@PathVariable Long id) {
        return referenceService.deleteReference(id);
    }

    /**
     * 根据编码和业务码查询参数信息
     *
     * @param referenceQueryDTO
     * @return
     */
    @ApiOperation(value = "根据编码和业务码查询接口")
    @PostMapping("/reference/param")
    @ApiImplicitParam(name = "referenceQueryDTO", dataType = "ReferenceQueryDTO")
    public ReferenceListVO queryReferenceByParam(@RequestBody @Valid ReferenceQueryDTO referenceQueryDTO) {
        return referenceService.queryReferenceByParam(referenceQueryDTO);
    }

    /**
     * 查询参数接口
     *
     * @return
     */
    @ApiOperation(value = "查询参数接口")
    @PostMapping("/param")
    public ReferenceRespVO getReference(HttpServletRequest request, HttpServletResponse response) {
    	log.info("Domain={}",domain);
		response.addHeader("Set-Cookie", Constant.REDISKEY + "=" + CookieUtils.createRedisKey(request) + "; Path=/; Max-Age=2592000; Domain=" + domain + "; HttpOnly");
        return referenceService.getReference();
    }
}
