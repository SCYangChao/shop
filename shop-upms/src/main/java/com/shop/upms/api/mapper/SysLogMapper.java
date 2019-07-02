package com.shop.upms.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.model.dto.response.log.ListLogResponse;
import com.shop.upms.api.model.dto.resquest.log.ListLogRequest;
import com.shop.upms.api.model.entity.SysLog;
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
public interface SysLogMapper extends BaseMapper<SysLog> {

    List<ListLogResponse> pageList(Page page, @Param("dto") ListLogRequest listLogRequest);

}
