package com.shop.upms.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *
  * class_name: UpdateLoadConfig
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:36
  *
 **/

@Data
@Configuration
@ConfigurationProperties(prefix = "ip.config")
public class IpConfig {

 String url="http://apis.juhe.cn/ip/ipNew?ip=%s&key=24ea371d4d2a8c851d939a975bc7b733";


}
