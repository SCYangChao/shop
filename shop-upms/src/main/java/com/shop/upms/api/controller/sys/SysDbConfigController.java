package com.shop.upms.api.controller.sys;

import com.shop.upms.api.model.dto.response.db.InfoDbConfigResponse;
import com.shop.upms.api.model.dto.resquest.db.InfoDbConfigRequest;
import com.shop.upms.api.service.ISysDbConfigService;
import com.yqkj.dto.Response;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
@RestController
@RequestMapping("/api/sys/v1.0/sysDbConfig")
@Api(value = "备份配置")
public class SysDbConfigController {

    @Autowired
    private ISysDbConfigService sysDbConfigService;
    /**
    *
    * 通过ID查询
    *
    * @return SysDbConfig
    **/
    @GetMapping("/info")
    @ApiOperation("配置详情")
    @WriteLog(model = "备份配置" , tile = "配置详情")
    public Response<InfoDbConfigResponse> info() {
        return ResponseToole.success(sysDbConfigService.info());
    }

    /**
     *
     * 添加
     * @return success/false
     **/
    @PutMapping
    @ApiOperation("配置修改")
    @WriteLog(model = "备份配置" , tile = "配置修改")
    public Response<Boolean> edit(@RequestBody InfoDbConfigRequest infoDbConfigRequest) {
        return ResponseToole.success(sysDbConfigService.edit(infoDbConfigRequest));
    }

}
