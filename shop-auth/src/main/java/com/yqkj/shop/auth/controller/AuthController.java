package com.yqkj.shop.auth.controller;

import com.yqkj.dto.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
  * class_name: LoginController
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:00
  *
 **/
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices tokenServices;

    @ApiOperation(value = "退出登录", notes = "通过移除token来达到退出登录的效果", httpMethod = "GET")
    @ApiImplicitParam(name = "accessToken", value = "用来认证的access_token", required = true, dataType = "String")
    @GetMapping("/logout")
    public Response<String> logout(String accessToken) {
        tokenServices.revokeToken(accessToken);
        return new Response<String>();
    }

}
