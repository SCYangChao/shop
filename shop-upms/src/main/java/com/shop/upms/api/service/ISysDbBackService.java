package com.shop.upms.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.db.ListDbBackResponse;
import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import com.shop.upms.api.model.entity.SysDbBack;
import com.yqkj.dto.AuthUserDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
public interface ISysDbBackService extends IService<SysDbBack> {

     Boolean   back(AuthUserDto authUserDto ,String type);

     Page<ListDbBackResponse> list(BasePageResquest basePageResquest);


}
