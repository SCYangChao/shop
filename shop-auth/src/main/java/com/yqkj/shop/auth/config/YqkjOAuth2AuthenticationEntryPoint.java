package com.yqkj.shop.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 *
  * class_name: YqkjOAuth2AuthenticationEntryPoint
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:48
  *
 **/
@Component
@Slf4j
public class YqkjOAuth2AuthenticationEntryPoint extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        return handleOAuth2Exception(new YqkjOAuth2Exception(HttpStatus.OK.value(), "用戶名或密碼錯誤"));
    }
    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(YqkjOAuth2Exception e) throws IOException {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");

        ResponseEntity<OAuth2Exception> response = new ResponseEntity<OAuth2Exception>(e, headers,
                HttpStatus.valueOf(status));

        return response;

    }

}
