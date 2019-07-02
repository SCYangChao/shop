package com.shop.upms.api.controller.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.menu.MenunTreeResponse;
import com.shop.upms.api.model.dto.response.user.InfoUserResponse;
import com.shop.upms.api.model.dto.response.user.ListUserResponse;
import com.shop.upms.api.model.dto.resquest.user.AddUserRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.dto.resquest.user.ListUserRequest;
import com.shop.upms.api.model.dto.resquest.user.UpdateUserRequest;
import com.shop.upms.api.service.ISysMenuService;
import com.shop.upms.api.service.ISysUserService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.Response;
import com.yqkj.exception.ParamException;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/api/sys/v1.0/sysUser")
@Api(value ="用户管理")
public class SysUserController  {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 獲取當前用戶信息
     * @param authUserDto
     * @return
     */
    @ApiOperation("當前用戶信息獲取")
    @GetMapping("/getCurrentUserInfo")
    @WriteLog(model = "用户管理" , tile = "當前用戶信息獲取")
    public Response<AuthUserDto> getCurrentUser(@ApiIgnore  AuthUserDto authUserDto) {
        return ResponseToole.success(authUserDto);
    }
    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysUser
     */
    @ApiOperation("獲取用戶信息")
    @GetMapping("/{id}")
    @WriteLog(model = "用户管理" , tile = "獲取用戶信息")
    public Response<InfoUserResponse> get(@PathVariable Long id) {
        return ResponseToole.success(sysUserService.info(id));
    }

    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    @WriteLog(model = "用户管理" , tile = "列表")
    public Response<Page<ListUserResponse>> page(ListUserRequest listUserRequest) {
        return ResponseToole.success(sysUserService.list(listUserRequest));
    }

    /**
     * 用戶菜單
     */
    @GetMapping("/tree")
    @ApiOperation(value = "用戶菜單")
    @WriteLog(model = "用户管理" , tile = "用户菜单")
    public Response<List<MenunTreeResponse>> tree(@ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysMenuService.treeByUserRole(authUserDto));
    }

    /**
     * 添加
     * @return success/false
     */
    @PostMapping
    @ApiOperation(value = "用户新增")
    @WriteLog(model = "用户管理" , tile = "新增")
    public Response<Long> add(@RequestBody @Valid AddUserRequest addUserRequest) {
        return ResponseToole.success(sysUserService.add(addUserRequest));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
   @DeleteMapping("/{id}")
   @ApiOperation(value = "用户删除")
   @WriteLog(model = "用户管理" , tile = "删除")
   @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
   public Response<Boolean> delete(@PathVariable("id") String id) throws ParamException {
        if (StringUtils.isBlank(id)) {
            throw  new ParamException("ID不能爲空");
        }
        return ResponseToole.success(sysUserService.bathDel(id));
    }

    /**
     * 编辑
     * @return success/false
     */
    @PutMapping
    @ApiOperation(value = "用户编辑")
    @WriteLog(model = "用户管理" , tile = "编辑")
    public Response<Boolean> edit(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return ResponseToole.success(sysUserService.update(updateUserRequest));
    }
    /**
     * 用户权限
     * @return success/false
     */
    @GetMapping(value = "/auth")
    @ApiOperation(value = "权限")
    @WriteLog(model = "用户管理" , tile = "用户权限")
    public Response<List<String>> auth(@ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysMenuService.auth(authUserDto));
    }
    /**
     * 用户状态
     * @return success/false
     */
    @PutMapping(value = "/switch")
    @ApiOperation(value = "用户状态")
    @WriteLog(model = "用户管理" , tile = "用户状态")
    public Response<Boolean> switchStatus(@RequestBody @Valid BathStatusUserRequest updateUserRequest , @ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysUserService.bathStatus(updateUserRequest ,authUserDto));
    }
}
