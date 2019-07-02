package com.shop.upms.api.model.dto.resquest.role;


import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
  * class_name: ListRoleRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:36
  *
 **/
@Data
@ApiModel
public class ListRoleRequest extends BasePageResquest {

    @ApiModelProperty("名稱")
    private  String name;

    @ApiModelProperty("1 正常　0　禁用")
    private Integer status;

}
