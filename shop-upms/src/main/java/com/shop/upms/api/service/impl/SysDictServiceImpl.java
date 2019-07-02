package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.constant.CommonConstant;
import com.shop.upms.api.mapper.SysDictMapper;
import com.shop.upms.api.model.dto.response.dic.DictTreeResponse;
import com.shop.upms.api.model.dto.resquest.dic.AddSysDictResquest;
import com.shop.upms.api.model.dto.resquest.dic.UpdateSysDictResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysDict;
import com.shop.upms.api.service.ISysDictService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.KeyValueCommonDto;
import com.yqkj.shop.auth.common.CurrentUserToole;
import com.yqkj.utile.ListUtil;
import com.yqkj.utile.TreeUtil;
import org.apache.commons.lang3.StringUtils;
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
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {


    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<DictTreeResponse> queryTree() {


        List<DictTreeResponse> sysDepartmentList =null;

        List<SysDict> sysDicts = selectList(new EntityWrapper<>());

        if (null == sysDicts || sysDicts.isEmpty()) {
            return CommonConstant.EMPTY_LSIT;
        }

        sysDepartmentList = new ArrayList<>(sysDicts.size());
        for (SysDict sysDict : sysDicts) {

            DictTreeResponse dictTreeResponse = new DictTreeResponse();


            dictTreeResponse.setDescription(sysDict.getDescription());
            dictTreeResponse.setSortOrder(sysDict.getSortOrder());
            dictTreeResponse.setStatus(sysDict.getStatus());
            dictTreeResponse.setTitle(sysDict.getTitle());
            dictTreeResponse.setValue(sysDict.getValue());
            dictTreeResponse.setId(sysDict.getId().intValue());
            dictTreeResponse.setParentId(sysDict.getParentId().intValue());

            sysDepartmentList.add(dictTreeResponse);
        }



        return TreeUtil.bulid(sysDepartmentList, -1);

    }

    @Override
    public Long add(AddSysDictResquest addSysDictResquest) {
        SysDict sysDict = new SysDict();
        sysDict.setCreateBy(CurrentUserToole.getCurrentUserInfo().getUsername());
        sysDict.setUpdateBy(sysDict.getCreateBy());
        sysDict.setCreateTime(new Date());
        sysDict.setUpdateTime(sysDict.getCreateTime());
        sysDict.setStatus(addSysDictResquest.getStatus());
        sysDict.setSortOrder(addSysDictResquest.getSortOrder());
        sysDict.setTitle(addSysDictResquest.getTitle());
        sysDict.setValue(addSysDictResquest.getValue());
        sysDict.setParentId(addSysDictResquest.getParentId());
        sysDict.setDescription(addSysDictResquest.getDescription());

        this.insert(sysDict);

        return sysDict.getId();
    }

    @Override
    public Boolean edit(UpdateSysDictResquest updateSysDictResquest) {

        SysDict sysDict = this.selectById(updateSysDictResquest.getId());

        if (null == sysDict) {

            return  Boolean.FALSE;

        }

        initUpdate(updateSysDictResquest, sysDict);
        return this.updateById(sysDict);
    }

    private void initUpdate(UpdateSysDictResquest updateSysDictResquest, SysDict sysDict) {
        if (StringUtils.isNotBlank(updateSysDictResquest.getDescription())) {
            sysDict.setDescription(updateSysDictResquest.getDescription());
        }
        if (StringUtils.isNotBlank(updateSysDictResquest.getValue())) {
            sysDict.setValue(updateSysDictResquest.getValue());
        }

        if (StringUtils.isNotBlank(updateSysDictResquest.getTitle())) {
            sysDict.setTitle(updateSysDictResquest.getTitle());
        }

        if (null != updateSysDictResquest.getStatus()) {

            sysDict.setStatus(updateSysDictResquest.getStatus());

        }
    }

    @Override
    public Boolean bathDel(String ids) {
        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysDict> sysUserList =this.selectBatchIds(idLog);

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysDict::getId));

        return Boolean.TRUE;
    }

    @Override
    public Boolean bathStatus(BathStatusUserRequest updateUserRequest, AuthUserDto authUserDto) {
        List<SysDict> sysMenuList =this.selectBatchIds(updateUserRequest.getIds());

        if (null == sysMenuList || sysMenuList.isEmpty()) {

            return  Boolean.FALSE;
        }

        Date date = new Date();
        for (SysDict sysMenuRole : sysMenuList) {
            sysMenuRole.setStatus(updateUserRequest.getStatus());
            sysMenuRole.setUpdateTime(date );
            sysMenuRole.setUpdateBy(authUserDto.getUsername());

        }

        updateBatchById(sysMenuList);

        return Boolean.TRUE;
    }

    @Override
    public List<KeyValueCommonDto> queryDisct(String parentKey) {

        if (StringUtils.isBlank(parentKey)) {

            return CommonConstant.EMPTY_LSIT;

        }
        return this.sysDictMapper.queryDisct(parentKey);
    }
}
