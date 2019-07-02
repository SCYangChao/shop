package com.shop.upms.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.org.OrgTreeResponse;
import com.shop.upms.api.model.dto.resquest.org.AddOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.ListOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.TreeOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.UpdateOrgRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysDepartment;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.exception.ParamException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface ISysDepartmentService extends IService<SysDepartment> {

    Integer add(AddOrgRequest sysDepartMentAddResquest , AuthUserDto authUserDto);

    List<OrgTreeResponse> tree(TreeOrgRequest treeOrgRequest, AuthUserDto authUserDto) throws  ParamException;

    Page<OrgTreeResponse> list(ListOrgRequest listOrgRequest) throws  ParamException;

    Boolean update(UpdateOrgRequest updateOrgRequest , AuthUserDto authUserDto) throws ParamException;

    Boolean bathDel(String ids);

    Boolean bathStatus(BathStatusUserRequest updateUserRequest , AuthUserDto authUserDto);

}
