package com.yqkj.http.callback;


import com.yqkj.http.dto.response.HttpResponse;

/**
 *
  * class_name: CallResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午3:56
  *
 **/
public interface CallResponse<T> {
    /**
     * 回调
     * @param param
     * @return
     */
    HttpResponse<T> call(String param);
}
