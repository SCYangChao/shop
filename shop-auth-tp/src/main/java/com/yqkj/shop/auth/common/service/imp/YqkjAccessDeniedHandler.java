package com.yqkj.shop.auth.common.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.yqkj.shop.auth.common.constant.AuthConstant;
import com.yqkj.utile.ResponseToole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
  * class_name: YqkjAccessDeniedHandler
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:31
  *
 **/
@Component
public class YqkjAccessDeniedHandler extends OAuth2AccessDeniedHandler  {

    private static Logger logger = LoggerFactory.getLogger(YqkjAccessDeniedHandler.class);
    /**
     * 授权拒绝处理
     *
     * @param request       request
     * @param response      response
     * @param authException authException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        logger.info("授权失败，禁止访问");
        response.setCharacterEncoding(AuthConstant.UTF8);
        response.setContentType(AuthConstant.CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter printWriter = response.getWriter();
        try {
            printWriter.append(JSONObject.toJSONString(ResponseToole.error(403 ,"授权失败，禁止访问")));
        }finally {
            printWriter.flush();
            printWriter.close();
        }

    }
}
