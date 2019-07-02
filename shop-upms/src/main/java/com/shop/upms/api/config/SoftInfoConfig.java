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
@ConfigurationProperties(prefix = "soft.config")
public class SoftInfoConfig {

    private String name="shop";

    private String version="1.0";

    private String desc="描述";

    private String  team="研发团队";


}
