package com.yqkj.shop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


/**
 *
  * class_name: AuthServerApplication
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午7:48
  *
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.yqkj.shop.auth","com.yqkj.config","com.yqkj.exception"})
@EnableAuthorizationServer
@EnableCaching
public class AuthServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
