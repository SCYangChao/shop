package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shop.upms.api.model.entity.SysMenu;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> userMenu(@Param("userId") Long userId , @Param("mType") Integer mType);

}
