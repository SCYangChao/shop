package com.shop.upms.api.model.dto.response.db;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
  * class_name: ListDbBackResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:24
  *
 **/
@Data
@ApiModel
public class ListDbBackResponse implements Serializable {

    @ApiModelProperty("ID")
    private Integer id;
    /**
     * 备份路径
     */
    @ApiModelProperty("备份路径")
    private String path;
    /**
     */
    @ApiModelProperty("名称")
    private String fileName;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private String entTime;
    /**
     * 毫秒
     */
    @ApiModelProperty("毫秒")
    private Integer timeUp;

    @ApiModelProperty("账户")
    private String acc;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("手机")
    private String phone;
    /**
     * 0 失败 1 成功
     */
    @ApiModelProperty("0 失败 1 成功")
    private Integer status;

    @ApiModelProperty("00 手动 01 自动")
    private String backType;

    @ApiModelProperty("备份时间")
    private String createTime;

}
