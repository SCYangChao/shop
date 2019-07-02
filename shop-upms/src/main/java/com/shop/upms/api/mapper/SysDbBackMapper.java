package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.db.ListDbBackResponse;
import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import com.shop.upms.api.model.entity.SysDbBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
public interface SysDbBackMapper extends BaseMapper<SysDbBack> {

    List<ListDbBackResponse> pageList(Page page , @Param("dto") BasePageResquest basePageResquest);


}
