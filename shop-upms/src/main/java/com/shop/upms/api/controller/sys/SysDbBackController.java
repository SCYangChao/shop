package com.shop.upms.api.controller.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.constant.ShopEnum;
import com.shop.upms.api.model.dto.response.db.ListDbBackResponse;
import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import com.shop.upms.api.model.entity.SysDbBack;
import com.shop.upms.api.service.ISysDbBackService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.Response;
import com.yqkj.io.DownloadFileDto;
import com.yqkj.io.DownloadFileUtile;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
@RestController
@RequestMapping("/api/sys/v1.0/sysDbBack/")
@Api(value = "系统备份")
public class SysDbBackController {

    @Autowired
    private ISysDbBackService sysDbBackService;

    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/list")
    @ApiOperation("列表备份")
    @WriteLog(model = "系统备份" , tile = "列表")
    public Response<Page<ListDbBackResponse>> list(BasePageResquest basePageResquest) {
        return ResponseToole.success(sysDbBackService.list(basePageResquest));
    }

    @GetMapping("back")
    @ApiOperation("手动备份")
    @WriteLog(model = "系统备份" , tile = "手动备份\"")
    public Response<Boolean> back(@ApiIgnore  AuthUserDto authUserDto) {
        return ResponseToole.success(this.sysDbBackService.back(authUserDto , ShopEnum.DbBackType.HAD.getValue()));
    }
    /**
     * @param id
     */
    @GetMapping(value = "down/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiImplicitParam(paramType = "path", dataType = "Integer",  name = "id", value = "ID", required = true)
    @ApiOperation("下载")
    @WriteLog(model = "系统备份" , tile = "下载")
    public void down(@PathVariable("id")Integer id , HttpServletRequest request , HttpServletResponse response) {
        SysDbBack sysDbBack = sysDbBackService.selectById(id);

        if (null == sysDbBack) {
            return;
        }
        DownloadFileUtile.dounloadLocalFile(new DownloadFileDto(request,response ,sysDbBack.getPath()+ File.separator+sysDbBack.getFileName()));
        return;
    }
}
