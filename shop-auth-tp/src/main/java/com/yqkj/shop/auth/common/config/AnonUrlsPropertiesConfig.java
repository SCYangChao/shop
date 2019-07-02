package com.yqkj.shop.auth.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 *
  * class_name: FilterUrlsPropertiesConfig
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午8:40
  *
 **/
@Configuration
@ConditionalOnExpression("!'${urls}'.isEmpty()")
@ConfigurationProperties(prefix = "urls")
public class AnonUrlsPropertiesConfig {

    private List<String> anon = new ArrayList<>();

    public List<String> getAnon() {
        return anon;
    }

    public void setAnon(List<String> anon) {
        this.anon = anon;
    }
}
