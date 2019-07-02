package com.yqkj.shop.auth.token;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yqkj.cache.RedisUtils;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.shop.auth.constant.CommonConstant;
import com.yqkj.shop.auth.model.entity.SysUser;
import com.yqkj.shop.auth.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 *
  * class_name: TokenFilter
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:25
  *
 **/
@Aspect
@Component
@Slf4j
public class TokenFilter {


    @Autowired
    private ISysUserService sysUserService;

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    private void TokenFilter() {

    }

    @AfterReturning(returning = "obj", pointcut = "TokenFilter()")
    public void doAfterReturning(ResponseEntity<OAuth2AccessToken> obj) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String object =request.getParameter("username");
        log.info("用户名:{}",object);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username" , object));

        AuthUserDto authUserDto = new AuthUserDto();
        if (!Objects.isNull(sysUser)) {

            BeanUtils.copyProperties(sysUser ,authUserDto);

        }
        if (StringUtils.isNotBlank(object)) {
            RedisUtils.set(CommonConstant.REDIS_LOGIN_USER_PRE+object , authUserDto);

        }
        log.info("token:{}",obj.getBody().getValue());
    }
}
