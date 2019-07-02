package com.shop.upms.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.log.ListLogResponse;
import com.shop.upms.api.model.dto.resquest.log.BathLogResquest;
import com.shop.upms.api.model.dto.resquest.log.ListLogRequest;
import com.shop.upms.api.model.entity.SysLog;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-13
 */
public interface ISysLogService extends IService<SysLog> {

    Boolean bath(@RequestBody BathLogResquest bathLogResquest);

    Page<ListLogResponse> page(ListLogRequest listLogRequest);

}
