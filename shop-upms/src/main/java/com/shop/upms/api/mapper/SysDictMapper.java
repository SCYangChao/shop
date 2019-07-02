package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shop.upms.api.model.entity.SysDict;
import com.yqkj.dto.KeyValueCommonDto;
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
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<KeyValueCommonDto> queryDisct(@Param("parentKey") String parentKey);

}
