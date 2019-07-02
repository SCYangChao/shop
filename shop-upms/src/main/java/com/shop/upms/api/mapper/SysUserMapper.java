package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.user.ListUserResponse;
import com.shop.upms.api.model.dto.resquest.user.ListUserRequest;
import com.shop.upms.api.model.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<ListUserResponse> pageList(Page page, @Param("dto") ListUserRequest param , @Param("code") String code);

}
