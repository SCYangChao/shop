package com.shop.admin.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
  * describe: do
 * class_name: ShopApplication
 * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:28
  *
 **/
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.yqkj.log","com.shop.upms","com.yqkj.http","com.yqkj.config","com.yqkj.exception","com.yqkj.shop.auth.common.config","com.yqkj.shop.auth.common.service"})
@EnableTransactionManagement
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}