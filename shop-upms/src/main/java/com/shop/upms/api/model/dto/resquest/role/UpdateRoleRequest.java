package com.shop.upms.api.model.dto.resquest.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
  * class_name: UpdateRoleRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:42
  *
 **/
@Data
@ApiModel
public class UpdateRoleRequest extends AddRoleResquest {

    @ApiModelProperty(value = "ID" , required = true)
    @NotNull(message = "ID 不能为空")
    private Long id;

}
