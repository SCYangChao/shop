package com.shop.upms.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.area.AreaTreeResponse;
import com.shop.upms.api.model.dto.resquest.area.AddAreaResquest;
import com.shop.upms.api.model.dto.resquest.area.UpdateAreaResquest;
import com.shop.upms.api.model.entity.SysArea;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.exception.ParamException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-24
 */
public interface ISysAreaService extends IService<SysArea> {

    Integer add(AddAreaResquest addAreaResquest , AuthUserDto authUserDto);

    List<AreaTreeResponse> tree(AuthUserDto authUserDto) throws ParamException;

    Boolean update(UpdateAreaResquest updateOrgRequest , AuthUserDto authUserDto) throws ParamException;

    Boolean bathDel(String ids);

}
