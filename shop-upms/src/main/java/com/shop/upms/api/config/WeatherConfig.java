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
@ConfigurationProperties(prefix = "weather.config")
public class WeatherConfig {

    String url="http://apis.juhe.cn/simpleWeather/query?city=%s&key=key";

    Long time = 1*60L;


}
