package com.shop.upms.api.controller.sys;

import com.shop.upms.api.model.dto.response.dic.DictTreeResponse;
import com.shop.upms.api.model.dto.resquest.dic.AddSysDictResquest;
import com.shop.upms.api.model.dto.resquest.dic.UpdateSysDictResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysDict;
import com.shop.upms.api.service.ISysDictService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.KeyValueCommonDto;
import com.yqkj.dto.Response;
import com.yqkj.exception.ParamException;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/api/sys/v1.0/sysDict")
@Api(value = "字典管理")
public class SysDictController {

    @Autowired
    private ISysDictService sysDictService;

    /**
     * @return success/false
     */
    @PutMapping(value = "/switch")
    @ApiOperation(value = "状态")
    @WriteLog(model = "字典管理" , tile = "状态")
    public Response<Boolean> switchStatus(@RequestBody @Valid BathStatusUserRequest updateUserRequest , @ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysDictService.bathStatus(updateUserRequest ,authUserDto));
    }
    /**
    * 通过ID查询
    *
    * @param id ID
    * @return SysDict
    */
    @GetMapping("/{id}")
    public SysDict get(@PathVariable Integer id) {
        return sysDictService.selectById(id);
    }

    /**
     *
     * @return
     */
    @GetMapping("/query/{parentKey}")
    @WriteLog(tile = "根據ＫＥＹ字典信息")
    @ApiOperation("根據上級ＫＥＹ字典信息")
    public Response<List<KeyValueCommonDto>> queryDisct(@PathVariable("parentKey")String parentKey) {
        return ResponseToole.success(sysDictService.queryDisct(parentKey));
    }

    /**
     *
     * @return
     */
    @GetMapping("/tree")
    @WriteLog(tile = "字典列表")
    @ApiOperation("字典列表")
    public Response<List<DictTreeResponse>> tree() {
        return ResponseToole.success(sysDictService.queryTree());
    }
    /**
     * 添加
     */
    @PostMapping
    @ApiOperation("新增")
    @WriteLog(model = "字典管理" , tile = "新增")
    public Response<Long> add(@Valid @RequestBody AddSysDictResquest addSysDictResquest) {
        return  ResponseToole.success(sysDictService.add(addSysDictResquest));
    }

    /**
     * 编辑
     * @return success/false
     */
    @PutMapping
    @ApiOperation("編輯")
    @WriteLog(model = "字典管理" , tile = "编辑")
    public Response<Boolean> edit(@RequestBody @Valid UpdateSysDictResquest updateSysDictResquest) {
        return ResponseToole.success(sysDictService.edit(updateSysDictResquest));
    }
    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    @WriteLog(model = "字典管理" , tile = "删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
    public Response<Boolean> delete(@PathVariable String id) throws ParamException {

        return ResponseToole.success(sysDictService.bathDel(id));
    }
}
