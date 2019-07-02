package com.yqkj.shop.auth.common.config;

import com.alibaba.fastjson.JSONObject;
import com.yqkj.shop.auth.common.service.imp.YqkjAccessDeniedHandler;
import com.yqkj.utile.ResponseToole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

import java.util.Objects;


/**
 *
  * class_name: ResourceServerConfiguration
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:06
  *
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;


    @Autowired
    private YqkjAccessDeniedHandler yqkjAccessDeniedHandler;

    @Autowired(required = false)
    private  AnonUrlsPropertiesConfig anonUrlsPropertiesConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.exceptionHandling().accessDeniedHandler(yqkjAccessDeniedHandler);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        if (!Objects.isNull(anonUrlsPropertiesConfig) && !anonUrlsPropertiesConfig.getAnon().isEmpty()) {

            anonUrlsPropertiesConfig.getAnon().stream().forEach(url ->{

                registry.antMatchers(url).permitAll();

            });

        }
        registry.anyRequest()
                .access("@permissionService.excute(request,authentication)");
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }
    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity translate(Exception e) throws Exception {
                ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                // do something with header or response
                if(401==responseEntity.getStatusCode().value()){
                    //自定义返回数据格式
                    ;
                    return new ResponseEntity(JSONObject.toJSONString(ResponseToole.error(402,"TOKEN 失效"))
                            , headers, HttpStatus.OK);
                }else{
                    return new ResponseEntity(body, headers, responseEntity.getStatusCode());
                }
            }
        };
    }
    /**
     * 加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}