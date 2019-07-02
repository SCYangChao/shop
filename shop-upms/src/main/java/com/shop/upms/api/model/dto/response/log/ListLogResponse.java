package com.shop.upms.api.model.dto.response.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
  * class_name: ListLogResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午9:53
  *
 **/
@Data
@ApiModel
public class ListLogResponse implements Serializable {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("日誌時間")
    private Date logTime;

    @ApiModelProperty("接口耗時")
    private Integer costTime;

    @ApiModelProperty("IP")
    private String ip;
    /**
     * 日志类型
     */
    @ApiModelProperty("日誌模塊編碼")
    private String model;
    /**
     * 日誌信息
     */
    @ApiModelProperty("日誌操作詳情")
    private String title;
    /**
     * 请求URI
     */
    @ApiModelProperty("请求URI")
    private String requestUri;
    /**
     * 操作方式
     */
    @ApiModelProperty("操作方式")
    private String method;
    /**
     * 操作提交的数据
     */
    @ApiModelProperty("操作提交的数据")
    private String params;
    /**
     *
     */
    @ApiModelProperty("狀態　200　500")
    private Integer resultStatus;
    /**
     * 异常信息
     */
    @ApiModelProperty("异常信息")
    private String exception;

    @ApiModelProperty("操作人")
    private String updateBy;


}
