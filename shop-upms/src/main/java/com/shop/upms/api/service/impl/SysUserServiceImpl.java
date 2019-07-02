package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.mapper.SysDepartmentMapper;
import com.shop.upms.api.mapper.SysUserMapper;
import com.shop.upms.api.model.dto.response.user.InfoUserResponse;
import com.shop.upms.api.model.dto.response.user.ListUserResponse;
import com.shop.upms.api.model.dto.resquest.user.AddUserRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.dto.resquest.user.ListUserRequest;
import com.shop.upms.api.model.dto.resquest.user.UpdateUserRequest;
import com.shop.upms.api.model.entity.SysDepartment;
import com.shop.upms.api.model.entity.SysUser;
import com.shop.upms.api.model.entity.SysUserRole;
import com.shop.upms.api.service.ISysUserRoleService;
import com.shop.upms.api.service.ISysUserService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.exception.ParamException;
import com.yqkj.shop.auth.common.CurrentUserToole;
import com.yqkj.utile.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public InfoUserResponse info(Long id) {

        if (null == id) {

            return null;

        }

       SysUser sysUser =  selectById(id);

        if (null == sysUser) {

            return  null;
        }

        InfoUserResponse infoUserResponse = new InfoUserResponse();
        BeanUtils.copyProperties(sysUser ,infoUserResponse);

        List<SysUserRole> sysUserRoleList =  sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id",id));
        if (null != sysUserRoleList && !sysUserRoleList.isEmpty()) {
            infoUserResponse.setRoleId(sysUserRoleList.get(0).getRoleId().intValue());
        }
        infoUserResponse.setUserName(sysUser.getUsername());
        return infoUserResponse;
    }

    @Override
    public Boolean bathDel(String ids) {

        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysUser> sysUserList =this.selectBatchIds(idLog);

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysUser::getId));

        return Boolean.TRUE;
    }

    @Override
    public Page<ListUserResponse> list(ListUserRequest listUserRequest) {

        Page<ListUserResponse> page = new Page(listUserRequest.getPage() , listUserRequest.getLimit() );
        String code = "";
        if (null != listUserRequest.getDepartmentId()) {
            SysDepartment sysDepartment = sysDepartmentMapper.selectById(listUserRequest.getDepartmentId());
            if (!Objects.isNull(sysDepartment)) {
                code = sysDepartment.getAttachCode();
            }
        }

        page.setRecords(sysUserMapper.pageList(page ,listUserRequest ,code));

        return page;

    }

    @Override
    public Long add(AddUserRequest addUserRequest) {

        SysUser sysUser = new SysUser();

        Integer row = this.selectCount(new EntityWrapper<SysUser>().eq("mobile",addUserRequest.getMobile()).or().
                eq("username",addUserRequest.getUserName()));

        if (row > 0) {

            throw  new ParamException("手机号或用户名重复！");

        }

        BeanUtils.copyProperties(addUserRequest ,sysUser);

        sysUser.setUsername(addUserRequest.getUserName());
        sysUser.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));

        sysUser.setCreateBy(CurrentUserToole.getCurrentUserInfo().getUsername());
        sysUser.setUpdateBy(sysUser.getCreateBy());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(sysUser.getCreateTime());

        this.sysUserMapper.insert(sysUser);
        this.initRole(sysUser.getId() ,addUserRequest.getRoleId());

        return sysUser.getId();
    }

    @Override
    public Boolean update(UpdateUserRequest updateUserRequest) {
        SysUser sysUser = selectById(updateUserRequest.getId());

        if (null == sysUser) {

            return  Boolean.FALSE;

        }

        UpdateInitBean(updateUserRequest, sysUser);

        this.initRole(sysUser.getId() ,updateUserRequest.getRoleId());

        return this.updateById(sysUser);

    }

    @Override
    public Boolean bathStatus(BathStatusUserRequest updateUserRequest, AuthUserDto authUserDto) {

        List<SysUser> sysUserList =this.selectBatchIds(updateUserRequest.getIds());

        if (null == sysUserList || sysUserList.isEmpty()) {

            return  Boolean.FALSE;
        }

        Date date = new Date();
        for (SysUser sysUser : sysUserList) {

            sysUser.setStatus(updateUserRequest.getStatus());
            sysUser.setUpdateTime(date );
            sysUser.setUpdateBy(authUserDto.getUsername());

        }

        updateBatchById(sysUserList);

        return Boolean.TRUE;
    }

    private Boolean initRole(Long userId , Integer roleId) {
        if (null == roleId) {
            return Boolean.FALSE;
        }


        sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id" ,userId));
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId.longValue());
        sysUserRole.setUserId(userId);
        sysUserRoleService.insert(sysUserRole);

        return Boolean.TRUE;
    }

    private void UpdateInitBean(UpdateUserRequest updateUserRequest, SysUser sysUser) {
        if (StringUtils.isNotBlank(updateUserRequest.getAvatar())) {
            sysUser.setAvatar(updateUserRequest.getAvatar());
        }
        if (null != updateUserRequest.getDepartmentId()) {
            sysUser.setDepartmentId(updateUserRequest.getDepartmentId());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getEmail())) {
            sysUser.setEmail(updateUserRequest.getEmail());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getMobile())) {
            sysUser.setMobile(updateUserRequest.getMobile());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getNickName())) {
            sysUser.setNickName(updateUserRequest.getNickName());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getUserName())) {
            sysUser.setUsername(updateUserRequest.getUserName());
        }
        if (null != updateUserRequest.getSex()) {
            sysUser.setSex(updateUserRequest.getSex());
        }
        if (null != updateUserRequest.getStatus()) {
            sysUser.setStatus(updateUserRequest.getStatus());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getJob())) {
            sysUser.setJob(updateUserRequest.getJob());
        }
        if (StringUtils.isNotBlank(updateUserRequest.getRemark())) {
            sysUser.setRemark(updateUserRequest.getRemark());
        }
        if (null != updateUserRequest.getBirthDay()) {
            sysUser.setBirthDay(updateUserRequest.getBirthDay());
        }
    }
}
