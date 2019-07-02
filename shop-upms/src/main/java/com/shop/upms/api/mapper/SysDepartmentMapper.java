package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.org.OrgTreeResponse;
import com.shop.upms.api.model.dto.resquest.org.ListOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.TreeOrgRequest;
import com.shop.upms.api.model.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * @param treeOrgRequest
     * @return
     */
    List<OrgTreeResponse> queryOrgTree(@Param("dto") TreeOrgRequest treeOrgRequest);

    List<OrgTreeResponse> queryOrgList(Page page , @Param("dto") ListOrgRequest dto);



}
