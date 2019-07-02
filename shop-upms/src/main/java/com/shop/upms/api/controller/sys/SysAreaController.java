package com.shop.upms.api.controller.sys;


import com.shop.upms.api.model.dto.response.area.AreaTreeResponse;
import com.shop.upms.api.model.dto.resquest.area.AddAreaResquest;
import com.shop.upms.api.model.dto.resquest.area.UpdateAreaResquest;
import com.shop.upms.api.service.ISysAreaService;
import com.yqkj.dto.AuthUserDto;
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
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/api/sys/v1.0/area")
@Api(value = "区域管理")
public class SysAreaController  {


    @Autowired
    private ISysAreaService sysAreaService;

    /**
    * 分页查询信息
    * @return 分页对象
    */
    @GetMapping("/tree")
    @ApiOperation(value = "区域树查询")
    @WriteLog(model = "区域管理" , tile = "区域树查询")
    public Response<List<AreaTreeResponse>> tree(@ApiIgnore  AuthUserDto authUserDto) throws ParamException {
        return ResponseToole.success(this.sysAreaService.tree(authUserDto));
    }
    /**
     * 添加
     */
    @PostMapping
    @ApiOperation(value = "区域新增")
    @WriteLog(model = "区域管理" , tile = "区域新增")
    public Response<Integer> add(@Valid @RequestBody AddAreaResquest addAreaResquest, @ApiIgnore  AuthUserDto authUserDto) {
        return ResponseToole.success(sysAreaService.add(addAreaResquest , authUserDto));
    }
    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "区域删除")
    @WriteLog(model = "区域管理" , tile = "删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
    public Response<Boolean> delete(@PathVariable String id) {
        return ResponseToole.success(sysAreaService.bathDel(id));
    }
    /**
     * 编辑
     * @return success/false
     */
    @PutMapping
    @ApiOperation(value = "区域编辑")
    @WriteLog(model = "区域管理" , tile = "编辑")
    public Response<Boolean> edit(@Valid @RequestBody UpdateAreaResquest updateAreaResquest, @ApiIgnore  AuthUserDto authUserDto ) throws ParamException {
        return ResponseToole.success(sysAreaService.update(updateAreaResquest, authUserDto));
    }
}
