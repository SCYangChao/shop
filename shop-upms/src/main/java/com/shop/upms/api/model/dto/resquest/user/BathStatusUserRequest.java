package com.shop.upms.api.model.dto.resquest.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *
  * class_name: UpdateUserRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:51
  *
 **/
@Data
@ApiModel
public class BathStatusUserRequest implements Serializable {

    @ApiModelProperty("ids")
    @NotEmpty(message = "ids　不能爲空")
    private List<Long> ids;


    @ApiModelProperty("1 正常　0　禁用")
    @NotNull(message = "status 不能为空")
    private Integer status = Integer.valueOf(0);
}
