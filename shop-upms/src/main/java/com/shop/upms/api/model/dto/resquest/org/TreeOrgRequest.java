package com.shop.upms.api.model.dto.resquest.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
  * class_name: UpdateOrgRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:08
  *
 **/
@ApiModel
@Data
public class TreeOrgRequest implements Serializable {

    @ApiModelProperty(value = "0 禁用　1　正常")
    private Integer status;

}
