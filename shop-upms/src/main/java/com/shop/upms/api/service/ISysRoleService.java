package com.shop.upms.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.role.ListRolePageResponse;
import com.shop.upms.api.model.dto.response.role.RoleInfoReponse;
import com.shop.upms.api.model.dto.resquest.role.AddRoleResquest;
import com.shop.upms.api.model.dto.resquest.role.ListRoleRequest;
import com.shop.upms.api.model.dto.resquest.role.UpdateRoleRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysRole;
import com.yqkj.dto.AuthUserDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface ISysRoleService extends IService<SysRole> {

    Page<ListRolePageResponse> list(ListRoleRequest listRoleRequest);

    Long add(AddRoleResquest addRoleResquest);

    Boolean update(UpdateRoleRequest updateRoleRequest);

    Boolean bathDel(String ids);

    RoleInfoReponse info(Integer id);

    Boolean bathStatus(BathStatusUserRequest updateUserRequest , AuthUserDto authUserDto);


}
