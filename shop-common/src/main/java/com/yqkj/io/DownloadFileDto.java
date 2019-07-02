package com.yqkj.io;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;


/**
 *
  * class_name: DownloadFileDto
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:38
  *
 **/
public class DownloadFileDto implements Serializable {

    private HttpServletRequest request ;
    private HttpServletResponse response;
    private String filePath;

    public DownloadFileDto(HttpServletRequest request, HttpServletResponse response, String filePath) {
        this.request = request;
        this.response = response;
        this.filePath = filePath;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
