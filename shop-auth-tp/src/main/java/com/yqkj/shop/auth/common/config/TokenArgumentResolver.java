package com.yqkj.shop.auth.common.config;

import com.yqkj.dto.AuthUserDto;
import com.yqkj.shop.auth.common.CurrentUserToole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



/**
 *
  * class_name: TokenArgumentResolver
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:44
  *
 **/
@Slf4j
@Configuration
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    String REDIS_LOGIN_USER_PRE = "com:yqkj:shop:user";

    /**
     * 1. 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(AuthUserDto.class);
    }

    /**
     *
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        try {
            return CurrentUserToole.getCurrentUserInfo();
        }catch (Exception e) {

            e.printStackTrace();

        }
        return  new AuthUserDto();
    }

}
