package com.shop.upms.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.user.InfoUserResponse;
import com.shop.upms.api.model.dto.response.user.ListUserResponse;
import com.shop.upms.api.model.dto.resquest.user.AddUserRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.dto.resquest.user.ListUserRequest;
import com.shop.upms.api.model.dto.resquest.user.UpdateUserRequest;
import com.shop.upms.api.model.entity.SysUser;
import com.yqkj.dto.AuthUserDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface ISysUserService extends IService<SysUser> {

    Page<ListUserResponse> list(ListUserRequest listUserRequest);

    Long add(AddUserRequest addUserRequest);

    Boolean update(UpdateUserRequest updateUserRequest);

    Boolean bathStatus(BathStatusUserRequest updateUserRequest , AuthUserDto authUserDto);


    InfoUserResponse info(Long id);

    Boolean bathDel(String ids);


}
