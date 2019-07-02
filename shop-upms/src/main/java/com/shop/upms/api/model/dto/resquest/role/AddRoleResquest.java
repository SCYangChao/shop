package com.shop.upms.api.model.dto.resquest.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;


/**
 *
  * class_name: AddRoleResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:22
  *
 **/
@Data
@ApiModel
public class AddRoleResquest implements Serializable {

    @ApiModelProperty("名稱")
    @NotBlank(message = "name 不能爲空")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("資源ＩＤ")
    private List<Long> mIds;

    @ApiModelProperty("resouceCodes")
    private List<String> resouceCodes;

}
