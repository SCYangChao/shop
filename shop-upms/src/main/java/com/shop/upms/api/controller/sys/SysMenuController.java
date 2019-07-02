package com.shop.upms.api.controller.sys;

import com.shop.upms.api.model.dto.response.menu.MenunTreeResponse;
import com.shop.upms.api.model.dto.resquest.menu.AddMenuResquest;
import com.shop.upms.api.model.dto.resquest.menu.UpdateMenuResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.service.ISysMenuService;
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
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/api/sys/1.0/sysMenu")
@Api(value = "菜單")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/tree")
    @ApiOperation(value = "獲取但前用戶菜單")
    @WriteLog(model = "菜单管理" , tile = "獲取但前用戶菜單")
    public Response<List<MenunTreeResponse>> tree(@ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysMenuService.tree(authUserDto));
    }
    /**
     * 添加
     * @return success/false
     */
    @PostMapping
    @ApiOperation("新增")
    @WriteLog(model = "菜单管理" , tile = "新增")
    public Response<Long> add(@RequestBody AddMenuResquest addMenuResquest) {
        return ResponseToole.success(sysMenuService.add(addMenuResquest));
    }
    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    @WriteLog(model = "菜单管理" , tile = "删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
    public Response<Boolean> delete(@PathVariable String id) throws ParamException {

        return ResponseToole.success(sysMenuService.bathDel(id));
    }
    /**
     * 编辑
     * @return success/false
     */
    @PutMapping
    @ApiOperation("修改")
    @WriteLog(model = "菜单管理" , tile = "编辑")
    public Response<Boolean> edit(@RequestBody UpdateMenuResquest updateMenuResquest) {

        return ResponseToole.success(sysMenuService.update(updateMenuResquest));
    }


    /**
     * @return success/false
     */
    @PutMapping(value = "/switch")
    @ApiOperation(value = "状态")
    @WriteLog(model = "菜单管理" , tile = "状态")
    public Response<Boolean> switchStatus(@RequestBody @Valid BathStatusUserRequest updateUserRequest , @ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysMenuService.bathStatus(updateUserRequest ,authUserDto));
    }
}
