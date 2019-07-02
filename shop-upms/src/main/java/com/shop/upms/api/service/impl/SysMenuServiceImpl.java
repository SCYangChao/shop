package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.shop.upms.api.constant.CommonConstant;
import com.shop.upms.api.mapper.SysMenuMapper;
import com.shop.upms.api.model.dto.response.menu.MenunTreeResponse;
import com.shop.upms.api.model.dto.resquest.menu.AddMenuResquest;
import com.shop.upms.api.model.dto.resquest.menu.UpdateMenuResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysMenu;
import com.shop.upms.api.service.ISysMenuService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.shop.auth.common.CurrentUserToole;
import com.yqkj.utile.DateUtil;
import com.yqkj.utile.ListUtil;
import com.yqkj.utile.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<MenunTreeResponse> treeByUserRole(AuthUserDto authUserDto) {
        List<SysMenu> sysMenuList =null;

        Integer rootId = -1;
        sysMenuList= sysMenuMapper.userMenu(authUserDto.getId() ,null);

        if (Objects.isNull(sysMenuList)) {
            return CommonConstant.EMPTY_LSIT;
        }

        List<MenunTreeResponse> trees = bulidMenuTree(sysMenuList);
        return TreeUtil.bulid(trees, rootId);
    }
    private List<MenunTreeResponse> bulidMenuTree(List<SysMenu> sysMenuList) {
        List<MenunTreeResponse> trees = new ArrayList<>();

        MenunTreeResponse node;
        for (SysMenu sysMenu : sysMenuList) {

            if (sysMenu.getParentId().equals(sysMenu.getId())) {
                continue;
            }
            node = new MenunTreeResponse();

            BeanUtils.copyProperties(sysMenu, node);
            node.setId(sysMenu.getId().intValue());
            node.setType(sysMenu.getType());
            node.setSortOrder(sysMenu.getSortOrder());
            node.setCreateTime(DateUtil.getStringDate(sysMenu.getCreateTime() ,DateUtil.ymdhms));
            node.setStatus(sysMenu.getStatus());
            trees.add(node);
        }
        return  trees;
    }

    @Override
    public List<MenunTreeResponse> tree(AuthUserDto authUserDto) {

        List<SysMenu> sysMenuList =null;

        Integer rootId = -1;
        sysMenuList= selectList(new EntityWrapper<SysMenu>().orderBy("sort_order"));

        if (Objects.isNull(sysMenuList)) {
            return CommonConstant.EMPTY_LSIT;
        }

        List<MenunTreeResponse> trees = bulidMenuTree(sysMenuList);
        return TreeUtil.bulid(trees, rootId);

    }

    @Override
    public Long add(AddMenuResquest addMenuResquest) {
        SysMenu sysMenu = new SysMenu();

        BeanUtils.copyProperties(addMenuResquest ,sysMenu);

        sysMenu.setCreateBy(CurrentUserToole.getCurrentUserInfo().getUsername());
        sysMenu.setUpdateBy(sysMenu.getCreateBy());
        sysMenu.setCreateTime(new Date());
        sysMenu.setUpdateTime(sysMenu.getCreateTime());

        this.sysMenuMapper.insert(sysMenu);

        return sysMenu.getId();
    }

    @Override
    public Boolean update(UpdateMenuResquest updateMenuResquest) {
        SysMenu sysMenuer = selectById(updateMenuResquest.getId());

        if (null == sysMenuer) {

            return Boolean.FALSE;

        }

        initSysMenuUpdate(updateMenuResquest, sysMenuer);
        return this.sysMenuMapper.updateById(sysMenuer)>0;

    }
    private void initSysMenuUpdate(UpdateMenuResquest updateMenuResquest, SysMenu sysMenuer) {
        if (StringUtils.isNotBlank(updateMenuResquest.getComponent())) {

            sysMenuer.setComponent(updateMenuResquest.getComponent());

        }
        if (StringUtils.isNotBlank(updateMenuResquest.getIcon())) {

            sysMenuer.setIcon(updateMenuResquest.getIcon());

        }
        if (StringUtils.isNotBlank(updateMenuResquest.getName())) {

            sysMenuer.setName(updateMenuResquest.getName());

        }
        if (StringUtils.isNotBlank(updateMenuResquest.getUrl())) {

            sysMenuer.setUrl(updateMenuResquest.getUrl());

        }
        if (null != updateMenuResquest.getSortOrder()) {

            sysMenuer.setSortOrder(updateMenuResquest.getSortOrder());

        }
        if (null != updateMenuResquest.getParentId()) {

            sysMenuer.setParentId(updateMenuResquest.getParentId().intValue());

        }
    }

    @Override
    public Boolean bathDel(String ids) {
        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysMenu> sysUserList =this.selectBatchIds(idLog);

        if (ListUtil.isNull(sysUserList)) {
            return Boolean.FALSE;
        }

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysMenu::getId));
        return Boolean.TRUE;
    }

    @Override
    public Boolean bathStatus(BathStatusUserRequest updateUserRequest, AuthUserDto authUserDto) {
        List<SysMenu> sysMenuList =this.selectBatchIds(updateUserRequest.getIds());

        if (null == sysMenuList || sysMenuList.isEmpty()) {

            return  Boolean.FALSE;
        }

        Date date = new Date();
        for (SysMenu sysMenuRole : sysMenuList) {
            sysMenuRole.setStatus(updateUserRequest.getStatus());
            sysMenuRole.setUpdateTime(date );
            sysMenuRole.setUpdateBy(authUserDto.getUsername());

        }

        updateBatchById(sysMenuList);

        return Boolean.TRUE;
    }

    @Override
    public List<String> auth(AuthUserDto authUserDto) {

        List<SysMenu> sysMenuList = sysMenuMapper.userMenu(authUserDto.getId(), 0);

        return  ListUtil.convertToList(sysMenuList , SysMenu::getUrl);

    }
}
