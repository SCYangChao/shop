package com.shop.upms.api.model.dto.resquest.dic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 *
  * class_name: AddSysDictResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:00
  *
 **/
@Data
@ApiModel
public class AddSysDictResquest implements Serializable {

    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("上級")
    @NotNull(message = "parentId not null")
    private Long parentId;
    private Integer sortOrder = 100;
    @ApiModelProperty("0　禁用　1　正常")
    private Integer status =0;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("標題／名稱")
    @NotBlank(message = "title not null")
    private String title;

}
