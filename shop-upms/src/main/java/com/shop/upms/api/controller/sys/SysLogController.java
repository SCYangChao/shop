package com.shop.upms.api.controller.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.log.ListLogResponse;
import com.shop.upms.api.model.dto.resquest.log.BathLogResquest;
import com.shop.upms.api.model.dto.resquest.log.ListLogRequest;
import com.shop.upms.api.model.entity.SysLog;
import com.shop.upms.api.service.ISysLogService;
import com.yqkj.dto.Response;
import com.yqkj.log.WriteLog;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/sys/v1.0/sysLog")
@Api(value = "日誌")
public class SysLogController  {

    @Autowired
    private ISysLogService sysLogService;

    /**
    * 通过ID查询
    *
    * @param id ID
    * @return SysLog
    */
    @GetMapping("/{id}")
    public SysLog get(@PathVariable Integer id) {
        return sysLogService.selectById(id);
    }
    /**
    * 分页查询信息
    *
    * @return 分页对象
    */
    @GetMapping("/list")
    @ApiOperation(value = "日誌列表")
    @WriteLog(model = "日志管理" , tile = "列表")
    public Response<Page<ListLogResponse>> list(ListLogRequest listLogRequest) {
        return ResponseToole.success(sysLogService.page(listLogRequest));
    }

    /**
     * 添加
     * @return success
     */
    @PostMapping("/bath")
    @ApiOperation(value = "批量日誌")
    @WriteLog(model = "日志管理" , tile = "批量")
    public Response<Boolean> bath(@RequestBody @Valid BathLogResquest bathLogResquest) {
        return ResponseToole.success(sysLogService.bath(bathLogResquest));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @WriteLog(model = "日志管理" , tile = "删除")
    public Boolean delete(@PathVariable Long id) {
        return Boolean.FALSE;
    }


}
