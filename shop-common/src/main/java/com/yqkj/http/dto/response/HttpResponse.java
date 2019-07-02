package com.yqkj.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 数据请求包装
  * class_name: HttpResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午3:16
  *
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 500;

    public static final int NO_PERMISSION = 2;

    private int errcode=SUCCESS;

    private String errmsg="success";

    private T data;

    public  boolean isSuccess() {
        return SUCCESS == errcode;
    }

    public HttpResponse(T data) {
        this.data = data;
    }
}
