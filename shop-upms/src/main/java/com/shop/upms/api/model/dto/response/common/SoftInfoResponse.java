package com.shop.upms.api.model.dto.response.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 *
  * class_name: UpdateLoadConfig
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午12:36
  *
 **/

@Data
public class SoftInfoResponse implements Serializable{
    @ApiModelProperty("名称")

    private String name;
    @ApiModelProperty("版本")

    private String version;
    @ApiModelProperty("描述")

    private String desc;
    @ApiModelProperty("团队")
    private String  team;

    @ApiModelProperty("初始的总内存")
    private String totalMemorySize;
    @ApiModelProperty("最大可用内存")
    private String maxMemorySize;

    @ApiModelProperty("已使用的内存")
    private String usedMemorySize;


}
