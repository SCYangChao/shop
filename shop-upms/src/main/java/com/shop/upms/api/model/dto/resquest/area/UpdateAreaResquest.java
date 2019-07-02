package com.shop.upms.api.model.dto.resquest.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
  * class_name: AddAreaResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午5:46
  *
 **/

@Data
@ApiModel
public class UpdateAreaResquest implements Serializable {

    @ApiModelProperty("id")
    @NotNull(message = "id 不能为空")
    private Integer id;

    @ApiModelProperty("区域名称")
    @NotBlank(message = "name 不能为空")
    private String name;

    @ApiModelProperty("上级区域编码")
    @NotNull(message = "pid 不能为空")
    private Integer pid;


}
