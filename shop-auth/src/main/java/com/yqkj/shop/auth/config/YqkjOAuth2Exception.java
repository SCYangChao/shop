package com.yqkj.shop.auth.config;

import lombok.Data;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 *
  * class_name: YqkjOAuth2Exception
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:09
  *
 **/
@Data
public class YqkjOAuth2Exception extends OAuth2Exception {

    /**
     * true 成功 false 失敗
     */
    private Boolean status = true;

    /**
     * 業務編碼
     */
    private Integer code = Integer.valueOf(200);
    /**
     * 異常信息
     */
    private String msg = "處理成功";



    public YqkjOAuth2Exception(Integer code,String msg) {
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public YqkjOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public YqkjOAuth2Exception(String msg) {
        super(msg);
    }
}
