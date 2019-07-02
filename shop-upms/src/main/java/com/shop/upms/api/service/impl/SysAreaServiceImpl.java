package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.mapper.SysAreaMapper;
import com.shop.upms.api.model.dto.response.area.AreaTreeResponse;
import com.shop.upms.api.model.dto.resquest.area.AddAreaResquest;
import com.shop.upms.api.model.dto.resquest.area.UpdateAreaResquest;
import com.shop.upms.api.model.entity.SysArea;
import com.shop.upms.api.service.ISysAreaService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.exception.ParamException;
import com.yqkj.utile.ListUtil;
import com.yqkj.utile.TreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-24
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements ISysAreaService {

    @Override
    public Integer add(AddAreaResquest addAreaResquest, AuthUserDto authUserDto) {
        SysArea sysArea = new SysArea();
        BeanUtils.copyProperties(addAreaResquest , sysArea);
        sysArea.setSort(Integer.valueOf(255));
        insert(sysArea);
        return sysArea.getId();
    }

    @Override
    public List<AreaTreeResponse> tree(AuthUserDto authUserDto) throws ParamException {
        List<SysArea> sysAreaList = this.selectList(new EntityWrapper<SysArea>());
        List<AreaTreeResponse> trees = new ArrayList<>();

        AreaTreeResponse node;
        for (SysArea sysMenu : sysAreaList) {

            if (sysMenu.getPid().equals(sysMenu.getId())) {
                continue;
            }
            node = new AreaTreeResponse();

            node.setId(sysMenu.getId().intValue());
            node.setParentId(sysMenu.getPid());
            node.setName(sysMenu.getName());

            trees.add(node);
        }
       return TreeUtil.bulid(trees, -1);
    }

    @Override
    public Boolean update(UpdateAreaResquest updateOrgRequest, AuthUserDto authUserDto) throws ParamException {

        SysArea sysArea = selectById(updateOrgRequest.getId());

        if (null == sysArea) {

            return Boolean.FALSE;

        }
        sysArea.setPid(updateOrgRequest.getPid());
        sysArea.setName(updateOrgRequest.getName());

        updateById(sysArea);

        return Boolean.TRUE;
    }

    @Override
    public Boolean bathDel(String ids) {
        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysArea> sysUserList =this.selectBatchIds(idLog);

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysArea::getId));

        return Boolean.TRUE;
    }
}
