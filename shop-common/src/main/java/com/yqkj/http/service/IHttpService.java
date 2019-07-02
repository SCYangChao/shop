package com.yqkj.http.service;



import com.yqkj.http.callback.CallResponse;
import com.yqkj.http.dto.header.Header;
import com.yqkj.http.dto.request.BaseRequest;
import com.yqkj.http.dto.response.HttpResponse;

/**
 *  Http工具类
  * class_name: IHttpService
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午2:47
  *OO
 **/
public interface IHttpService {
    /**
     * POST请求
     * @param url
     * @param baseRequest
     * @param header
     * @return
     */
    HttpResponse post(String url, BaseRequest baseRequest, Header header, CallResponse callResponse);

    /**
     * ＧＥＴ
     * @param url
     * @param header
     * @return
     */
    HttpResponse get(String url, Header header, CallResponse callResponse);
}
