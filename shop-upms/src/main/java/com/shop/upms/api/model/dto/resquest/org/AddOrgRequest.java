package com.shop.upms.api.model.dto.resquest.org;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
  * class_name: AddOrgRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:26
  *
 **/
@ApiModel
@Data
public class AddOrgRequest implements Serializable {

    @ApiModelProperty(value = "上级机构ID" , required = true)
    @NotNull(message = "parentId 不能为空")
    private Long parentId = Long.valueOf(-1);

    @ApiModelProperty(value = "机构编码" , required = true)
    @NotBlank(message = "code 不能为空")
    private String code;

    @ApiModelProperty(value = "机构名称" , required = true)
    @NotBlank(message = "name 不能为空")
    private String name;

    @ApiModelProperty(value = "排序",hidden = true)
    private Integer sort = Integer.valueOf(255);

    @ApiModelProperty(hidden = true)
    private Integer status = Integer.valueOf(1);

}
