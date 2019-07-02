package com.shop.upms.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.menu.MenunTreeResponse;
import com.shop.upms.api.model.dto.resquest.menu.AddMenuResquest;
import com.shop.upms.api.model.dto.resquest.menu.UpdateMenuResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysMenu;
import com.yqkj.dto.AuthUserDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<MenunTreeResponse> treeByUserRole(AuthUserDto authUserDto);

    List<MenunTreeResponse> tree(AuthUserDto authUserDto);

    Long add(AddMenuResquest addMenuResquest);

    Boolean update(UpdateMenuResquest updateMenuResquest);

    Boolean bathDel(String ids);

    Boolean bathStatus(BathStatusUserRequest updateUserRequest , AuthUserDto authUserDto);

    List<String> auth(AuthUserDto authUserDto);

}
