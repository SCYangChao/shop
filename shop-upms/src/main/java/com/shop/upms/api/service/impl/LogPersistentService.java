package com.shop.upms.api.service.impl;

import com.shop.upms.api.mapper.SysLogMapper;
import com.shop.upms.api.model.entity.SysLog;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.LogDto;
import com.yqkj.log.ILogPersistentService;
import com.yqkj.shop.auth.common.CurrentUserToole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
  * class_name: LogPersistentService
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午9:58
  *
 **/
@Service
public class LogPersistentService implements ILogPersistentService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Boolean excute(LogDto logDto) {

        AuthUserDto authUserDto = CurrentUserToole.getCurrentUserInfo();

        if (null != authUserDto) {
            authUserDto.setUsername(authUserDto.getUsername());
        }

        SysLog sysLog = new SysLog();

        BeanUtils.copyProperties(logDto ,sysLog);

        sysLog.setResultStatus(logDto.getResultStatus().toString());
        sysLog.setRequesUri(logDto.getRequestUri());
        sysLog.setTitle(logDto.getTitle());
        sysLogMapper.insert(sysLog);

        return Boolean.TRUE;
    }
}
