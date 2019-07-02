package com.shop.upms.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.db.InfoDbConfigResponse;
import com.shop.upms.api.model.dto.resquest.db.InfoDbConfigRequest;
import com.shop.upms.api.model.entity.SysDbConfig;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
public interface ISysDbConfigService extends IService<SysDbConfig> {

    InfoDbConfigResponse info();

    Boolean edit(InfoDbConfigRequest infoDbConfigRequest);

}
