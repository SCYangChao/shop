package com.yqkj.http.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * class_name: HttpConfig
 * describe: do
 * @author: yangchao.cool@gmail.com
 * creat_date: 下午3:42
 *
 **/
@Configuration
public class HttpConfig {


    @Bean("restTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

}
