package com.shop.upms.api.model.dto.resquest.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
public class UpdateOrgRequest implements Serializable {


    @ApiModelProperty(value = "ID" , required = true)
    @NotBlank(message = "ID 不能为空")
    private Long id;

    @ApiModelProperty(value = "机构编码" , required = true)
    @NotBlank(message = "code 不能为空")
    private String code;

    @ApiModelProperty(value = "机构名称" , required = true)
    @NotBlank(message = "name 不能为空")
    private String name;

    @ApiModelProperty(value = "0 禁用　1　正常")
    private Integer status = Integer.valueOf(1);

    @ApiModelProperty(value = "上级ＩＤ")
    private Long parentId;

}
