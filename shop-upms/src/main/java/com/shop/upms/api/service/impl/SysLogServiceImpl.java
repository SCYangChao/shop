package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.mapper.SysLogMapper;
import com.shop.upms.api.model.dto.response.log.ListLogResponse;
import com.shop.upms.api.model.dto.resquest.log.BathLogResquest;
import com.shop.upms.api.model.dto.resquest.log.ListLogRequest;
import com.shop.upms.api.model.entity.SysLog;
import com.shop.upms.api.service.ISysLogService;
import com.shop.upms.api.util.DateTypeParseTool;
import com.yqkj.dto.LogDto;
import org.springframework.beans.BeanUtils;
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
 * @since 2019-04-13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Boolean bath(BathLogResquest bathLogResquest) {

        if (null == bathLogResquest.getDatas() || bathLogResquest.getDatas().isEmpty()) {
            return Boolean.FALSE;
        }

        List<SysLog> logs = new ArrayList<>(bathLogResquest.getDatas().size());

        Date date = new Date();
        for (LogDto logDto : bathLogResquest.getDatas()) {
            SysLog sysLog = new SysLog();
            sysLog.setUpdateTime(date);
            sysLog.setCreateTime(date);
            BeanUtils.copyProperties(logDto ,sysLog);
            logs.add(sysLog);
        }

        return insertBatch(logs);
    }

    @Override
    public Page<ListLogResponse> page(ListLogRequest listLogRequest) {

        Page<ListLogResponse> page = new Page(listLogRequest.getPage() , listLogRequest.getLimit() );

        DateTypeParseTool dateTypeParseTool = new DateTypeParseTool(listLogRequest.getDateType() ,listLogRequest.getStartTime(),listLogRequest.getEndTime());
        listLogRequest.setStartTime(dateTypeParseTool.getStart());
        listLogRequest.setEndTime(dateTypeParseTool.getEnd());
        page.setRecords(sysLogMapper.pageList(page ,listLogRequest));

        return page;
    }
}
