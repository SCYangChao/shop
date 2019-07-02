package com.shop.upms.api.controller.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.org.OrgTreeResponse;
import com.shop.upms.api.model.dto.resquest.org.AddOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.ListOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.TreeOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.UpdateOrgRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.service.ISysDepartmentService;
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
@RequestMapping("/api/sys/v1.0/org")
@Api(value = "机构管理")
public class SysDepartmentController {

    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/tree")
    @WriteLog(model = "机构管理" , tile = "机构管理查询信息")
    @ApiOperation(value = "机构管理查询信息")
    public Response<List<OrgTreeResponse>> tree(TreeOrgRequest treeOrgRequest, @ApiIgnore AuthUserDto authUserDto)  throws ParamException {
        return ResponseToole.success(sysDepartmentService.tree(treeOrgRequest , authUserDto));
    }
    /**
     *
     * @param listOrgRequest
     * @return
     * @throws ParamException
     */
    @GetMapping("/list")
    @ApiOperation(value = "下级机构")
    @WriteLog(model = "机构管理" , tile = "下级机构")
    public Response<Page<OrgTreeResponse>> list(ListOrgRequest listOrgRequest)  throws ParamException {
        return ResponseToole.success(sysDepartmentService.list(listOrgRequest));
    }
    /**
     * 添加
     * @param  sysDepartment  实体
     * @return success/false
     */
    @PostMapping
    @ApiOperation(value = "机构管理添加")
    @WriteLog(model = "机构管理" , tile = "新增")
    public Response<Integer> add(@RequestBody  @Valid AddOrgRequest sysDepartment , @ApiIgnore  AuthUserDto authUserDto) {
        return ResponseToole.success(sysDepartmentService.add(sysDepartment ,authUserDto));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "机构管理删除")
    @WriteLog(model = "机构管理" , tile = "删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "多個ＩＤ用逗號隔開", required = true)
    public Response<Boolean> delete(@PathVariable String id) throws ParamException {
        return ResponseToole.success(sysDepartmentService.bathDel(id));
    }

  /**
   *
    * class_name: SysDepartmentController
    * describe: do
    * @author: yangchao.cool@gmail.com
    * creat_date: 上午9:15
    *
   **/
    @PutMapping
    @ApiOperation(value = "机构管理编辑")
    @WriteLog(model = "机构管理" , tile = "编辑")
    public Response<Boolean> update(@RequestBody UpdateOrgRequest updateOrgRequest, @ApiIgnore  AuthUserDto authUserDto) throws  ParamException {
        return ResponseToole.success(sysDepartmentService.update(updateOrgRequest,authUserDto));
    }

    /**
     * @return success/false
     */
    @PutMapping(value = "/switch")
    @ApiOperation(value = "状态")
    @WriteLog(model = "机构管理" , tile = "状态")
    public Response<Boolean> switchStatus(@RequestBody @Valid BathStatusUserRequest updateUserRequest , @ApiIgnore AuthUserDto authUserDto) {
        return ResponseToole.success(sysDepartmentService.bathStatus(updateUserRequest ,authUserDto));
    }
}
