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

import com.ratta.dto.DictionaryDTO;
import com.ratta.dto.DictionaryQueryDTO;
import com.ratta.dto.DictionaryVagueDTO;
import com.ratta.service.DictionaryService;
import com.ratta.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:46
 */
@Api(description = "数据字典集合")
@RestController
public class DictionaryController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 根据数据名称类型查询
     *
     * @param name
     * @param language
     * @return
     */
    @ApiOperation(value = "根据数据名称类型查询接口")
    @GetMapping("/dictionary")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "参数为空，默认根据TRESOURCETYPE_ID查询数据字典，不为空根据数据名称类型查询接口"),
            @ApiImplicitParam(name = "language", value = "参数为空,默认是中文,如果参数值为US则为英文版")
    })
    public List<DictionarysVO> allResourceType(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "language", required = false) String language) {
        return dictionaryService.getResourceTypeList(name, language);
    }

    /**
     * 根据参数模糊查询
     *
     * @param dictionaryVagueDTO
     * @return
     */
    @ApiOperation(value = "根据参数模糊查询接口")
    @PostMapping("/dictionary")
    @ApiImplicitParam(name = "dictionaryVagueDTO", dataType = "DictionaryVagueDTO")
    public CommonListVO<DictionaryVagueVO> query(@RequestBody @Valid DictionaryVagueDTO dictionaryVagueDTO) {
        return dictionaryService.query(dictionaryVagueDTO);
    }

    /**
     * 添加数据字典信息
     *
     * @param dictionaryDTO
     * @return
     */
    @ApiOperation(value = "添加数据字典信息接口")
    @PostMapping("/dictionary/add")
    @ApiImplicitParam(name = "dictionaryDTO", dataType = "DictionaryDTO")
    public BaseVO save(@RequestBody @Valid DictionaryDTO dictionaryDTO) {
        return dictionaryService.saveDictionary(dictionaryDTO, request.getHeader("User-ID"));
    }

    /**
     * 修改添加数据字典信息
     *
     * @param dictionaryDTO
     * @param id
     * @return
     */
    @ApiOperation(value = "修改数据字典信息接口")
    @PutMapping("/dictionary/{id}")
    @ApiImplicitParam(name = "id", value = "字典ID")
    public BaseVO updateDictionary(@RequestBody @Valid DictionaryDTO dictionaryDTO, @PathVariable Long id) {
        return dictionaryService.updateDictionary(dictionaryDTO, id, request.getHeader("User-ID"));
    }

    /**
     * 删除添加数据字典信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除数据字典信息接口")
    @DeleteMapping("/dictionary/{id}")
    @ApiImplicitParam(name = "id", value = "字典ID")
    public BaseVO deleteDictionary(@PathVariable("id") Long id) {
        return dictionaryService.deleteDictionary(id);
    }

    /**
     * 根据id查询数据字典信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询数据字典信息接口")
    @GetMapping("/dictionary/{id}")
    @ApiImplicitParam(name = "id", value = "字典ID")
    public DictionaryVO queryDictionaryById(@PathVariable Long id) {
        return dictionaryService.queryDictionaryById(id);
    }

    /**
     * 根据业务码和编码查询数据字典接口
     *
     * @param dictionaryQueryDTO
     * @return
     */
    @ApiOperation(value = "根据业务码和编码查询数据字典接口")
    @PostMapping("/dictionary/param")
    @ApiImplicitParam(name = "dictionaryQueryDTO", dataType = "DictionaryQueryDTO")
    public DictionaryListVO queryDictionaryByNameAndValue(@RequestBody @Valid DictionaryQueryDTO dictionaryQueryDTO) {
        return dictionaryService.queryDictionaryByNameAndValue(dictionaryQueryDTO);
    }
}
