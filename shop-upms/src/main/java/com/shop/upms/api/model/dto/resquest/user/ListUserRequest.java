package com.shop.upms.api.model.dto.resquest.user;

import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
  * class_name: ListUserRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:51
  *
 **/
@Data
@ApiModel
public class ListUserRequest extends BasePageResquest {
    @ApiModelProperty("用戶名稱")
    private String userName;
    @ApiModelProperty("部門名稱")
    private Integer departmentId;
    @ApiModelProperty("1 正常　0　禁用")
    private Integer status;
}
