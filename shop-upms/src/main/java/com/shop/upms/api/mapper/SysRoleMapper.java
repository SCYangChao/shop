package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.role.ListRolePageResponse;
import com.shop.upms.api.model.dto.resquest.role.ListRoleRequest;
import com.shop.upms.api.model.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<ListRolePageResponse> pageList(Page  page , @Param("dto") ListRoleRequest listRoleRequest);


}
