package com.shop.upms.api.model.dto.resquest.dic;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
  * class_name: UpdateSysDictResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:05
  *
 **/
@Data
@ApiModel
public class UpdateSysDictResquest extends AddSysDictResquest {

    @ApiModelProperty("id")
    private  Long id;

}
