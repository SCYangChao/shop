package com.yqkj.shop.auth.common.service.imp;

import com.yqkj.shop.auth.common.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *
  * class_name: PermissionServiceImpl
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:34
  *
 **/
@Service("permissionService")
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    private String NOT_LOGIN_USER = "anonymousUser";


    @Override
    public boolean excute(HttpServletRequest request, Authentication authentication) {

        if (NOT_LOGIN_USER.equals(authentication.getPrincipal())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }


 }
