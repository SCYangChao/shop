package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.mapper.SysRoleMapper;
import com.shop.upms.api.model.dto.response.role.ListRolePageResponse;
import com.shop.upms.api.model.dto.response.role.RoleInfoReponse;
import com.shop.upms.api.model.dto.resquest.role.AddRoleResquest;
import com.shop.upms.api.model.dto.resquest.role.ListRoleRequest;
import com.shop.upms.api.model.dto.resquest.role.UpdateRoleRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysRole;
import com.shop.upms.api.model.entity.SysRoleMenu;
import com.shop.upms.api.service.ISysRoleMenuService;
import com.shop.upms.api.service.ISysRoleService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.shop.auth.common.CurrentUserToole;
import com.yqkj.utile.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private  SysRoleMapper sysRoleMapper;

    @Override
    public Page<ListRolePageResponse> list(ListRoleRequest listRoleRequest) {

        Page<ListRolePageResponse> page = new Page(listRoleRequest.getPage() , listRoleRequest.getLimit() );

        page.setRecords(sysRoleMapper.pageList(page ,listRoleRequest));

        return page;
    }

    @Override
    public Long add(AddRoleResquest addRoleResquest) {

        SysRole sysRole = new SysRole();

        sysRole.setCreateBy(CurrentUserToole.getCurrentUserInfo().getUsername());
        sysRole.setUpdateBy(sysRole.getCreateBy());
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());
        sysRole.setResouceCodes(ListUtil.joinStr(addRoleResquest.getResouceCodes() , String::toString));
        sysRole.setDescription(addRoleResquest.getDescription());
        sysRole.setName(addRoleResquest.getName());


        this.insert(sysRole);

        this.initRoleMenu(sysRole.getId() ,addRoleResquest.getMIds());

        return sysRole.getId();
    }

    private  void initRoleMenu(Long roleID ,List<Long> mIds) {

        if (null== mIds || mIds.isEmpty()) {

            return;

        }

        try {

            List<SysRoleMenu> list = sysRoleMenuService.selectList(new EntityWrapper<SysRoleMenu>().eq("role_id", roleID));

            if (null != list && !list.isEmpty()) {

                sysRoleMenuService.deleteBatchIds(ListUtil.convertToList(list, SysRoleMenu::getId));

            }

            List<SysRoleMenu> sysRoleMenus = new ArrayList<>(mIds.size());
            for (Long mId : mIds) {

                sysRoleMenus.add(new SysRoleMenu(null, mId,roleID));


            }
            sysRoleMenuService.insertBatch(sysRoleMenus);
        }catch (Exception e) {

        }

    }


    @Override
    public Boolean update(UpdateRoleRequest updateRoleRequest) {
        SysRole sysRoleMenu = selectById(updateRoleRequest.getId());

        if (null == sysRoleMenu) {

            return Boolean.FALSE;

        }

        sysRoleMenu.setDescription(updateRoleRequest.getDescription());
        sysRoleMenu.setName(updateRoleRequest.getName());
        sysRoleMenu.setUpdateBy(CurrentUserToole.getCurrentUserInfo().getUsername());
        sysRoleMenu.setUpdateTime(new Date());
        sysRoleMenu.setResouceCodes(ListUtil.joinStr(updateRoleRequest.getResouceCodes() ,String::toString));
        updateById(sysRoleMenu);

        this.initRoleMenu(sysRoleMenu.getId() , updateRoleRequest.getMIds());

        return Boolean.TRUE;
    }

    @Override
    public Boolean bathDel(String ids) {
        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysRole> sysUserList =this.selectBatchIds(idLog);

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysRole::getId));

        return Boolean.TRUE;
    }

    @Override
    public RoleInfoReponse info(Integer id) {

        SysRole sysRole = this.selectById(id);

        if (null == sysRole) {
            return  null;
        }

        RoleInfoReponse reponse = new RoleInfoReponse();

        reponse.setDescription(sysRole.getDescription());
        reponse.setName(sysRole.getName());
        reponse.setId(sysRole.getId());
        List<SysRoleMenu> sysRoleMenuList = this.sysRoleMenuService.selectList(new EntityWrapper<SysRoleMenu>().eq("role_id" , id));
        reponse.setMids(ListUtil.convertToList(sysRoleMenuList ,SysRoleMenu::getMenuId));
        reponse.setResouceCodes(ListUtil.splits(sysRole.getResouceCodes() ,","));
        return reponse;
    }

    @Override
    public Boolean bathStatus(BathStatusUserRequest updateUserRequest, AuthUserDto authUserDto) {

        List<SysRole> sysRoleList =this.selectBatchIds(updateUserRequest.getIds());

        if (null == sysRoleList || sysRoleList.isEmpty()) {

            return  Boolean.FALSE;
        }

        Date date = new Date();
        for (SysRole sysRole : sysRoleList) {

            sysRole.setStatus(updateUserRequest.getStatus());
            sysRole.setUpdateTime(date );
            sysRole.setUpdateBy(authUserDto.getUsername());

        }

        updateBatchById(sysRoleList);

        return Boolean.TRUE;
    }
}
