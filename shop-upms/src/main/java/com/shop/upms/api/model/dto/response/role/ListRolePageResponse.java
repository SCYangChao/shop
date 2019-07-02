package com.shop.upms.api.model.dto.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
  * class_name: ListRolePageResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:29
  *
 **/
@ApiModel
@Data
public class ListRolePageResponse implements Serializable {

    private Long id;

    @ApiModelProperty("名稱")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("1 正常　0　禁用")
    private Integer status = Integer.valueOf(0);

    @ApiModelProperty("创建时间")
    private String createTime;

}
