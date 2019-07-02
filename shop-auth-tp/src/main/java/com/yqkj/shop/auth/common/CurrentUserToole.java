package com.yqkj.shop.auth.common;

import com.yqkj.cache.RedisUtils;
import com.yqkj.dto.AuthUserDto;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.yqkj.shop.auth.common.constant.AuthConstant.REDIS_LOGIN_USER_PRE;

/**
 *
  * class_name: CurrentUserToole
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:53
  *
 **/
public class CurrentUserToole {
    
    public   static AuthUserDto getCurrentUserInfo() {
        try {
            return (AuthUserDto) RedisUtils.get(REDIS_LOGIN_USER_PRE + SecurityContextHolder.getContext().getAuthentication().getName());
        }catch (Exception e) {
          e.printStackTrace();
        }
        return  new AuthUserDto();
    }
}
