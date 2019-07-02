package com.shop.upms.api.controller.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.role.ListRolePageResponse;
import com.shop.upms.api.model.dto.response.role.RoleInfoReponse;
import com.shop.upms.api.model.dto.resquest.role.AddRoleResquest;
import com.shop.upms.api.model.dto.resquest.role.ListRoleRequest;
import com.shop.upms.api.model.dto.resquest.role.UpdateRoleRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.service.ISysRoleService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.Response;
import com.yqkj.exception.ParamException;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/api/sys/v1.0/sysRole")
@Api(value = "角色")
@Slf4j
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/{id}")
    @WriteLog(model = "角色管理" , tile = "详情")
    @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value = "id", required = true)
    @ApiOperation(value = "角色详情")
    public Response<RoleInfoReponse> info(@PathVariable Integer id) {
        return ResponseToole.success(this.sysRoleService.info(id));
    }

    /**
     * @return success/false
     */
    @PutMapping(value = "/switch")
    @ApiOperation(value = "状态")
    @WriteLog(model = "角色管理" , tile = "状态")
    public Response<Boolean> switchStatus(@RequestBody @Valid BathStatusUserRequest updateUserRequest , @ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysRoleService.bathStatus(updateUserRequest ,authUserDto));
    }
    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/list")
    @ApiOperation(value = "角色列表")
    @WriteLog(model = "角色管理" , tile = "列表")
    public Response<Page<ListRolePageResponse>> page(ListRoleRequest listRoleRequest) {
     return ResponseToole.success(this.sysRoleService.list(listRoleRequest));
    }
    /**
     * 添加
     * @return success/false
     */
    @PostMapping
    @ApiOperation(value = "新增角色")
    @WriteLog(model = "角色管理" , tile = "新增")
    public Response<Long> add(@RequestBody AddRoleResquest addRoleResquest) {
        return ResponseToole.success(sysRoleService.add(addRoleResquest));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @WriteLog(model = "角色管理" , tile = "删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
    public Response<Boolean> delete(@PathVariable String id) throws ParamException {
        return ResponseToole.success(sysRoleService.bathDel(id));
    }
    /**
     * 编辑
     * @return success/false
     */
    @PutMapping
    @ApiOperation(value = "角色编辑")
    @WriteLog(model = "角色管理" , tile = "删除")
    public Response<Boolean> edit(@Valid @RequestBody UpdateRoleRequest updateRoleRequest) {
        return ResponseToole.success(sysRoleService.update(updateRoleRequest));

    }
}
