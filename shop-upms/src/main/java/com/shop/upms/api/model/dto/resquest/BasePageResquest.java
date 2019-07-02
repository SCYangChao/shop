package com.shop.upms.api.model.dto.resquest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 *
 * class_name: BasePageResquest
 * describe: do
 * @author: yangchao.cool@gmail.com
 * creat_date: 上午9:33
 *
 **/
@ApiModel
@Data
public class BasePageResquest implements Serializable {
    @ApiModelProperty("當前頁")
    private  Integer page = Integer.valueOf(1);
    @ApiModelProperty("條數")
    private Integer limit= Integer.valueOf(10);

}
