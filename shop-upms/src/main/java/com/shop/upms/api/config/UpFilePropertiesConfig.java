package com.shop.upms.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
  * class_name: DatasourcePropertiesConfig
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午10:28
  *
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "file.local")
public class UpFilePropertiesConfig {

    String savePah;

    String url;

}
