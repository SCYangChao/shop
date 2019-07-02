package com.shop.upms.api.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


/**
 *
  * class_name: UpdateLoadConfig
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:36
  *
 **/

@Configuration
public class UpLoadFileConfig {


    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L*2);
        return factory.createMultipartConfig();
    }
}
