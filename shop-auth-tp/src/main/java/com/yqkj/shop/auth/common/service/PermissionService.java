package com.yqkj.shop.auth.common.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;


/**
 *
  * class_name: PermissionService
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:34
  *
 **/
public interface PermissionService {

    boolean excute(HttpServletRequest request, Authentication authentication);
}
