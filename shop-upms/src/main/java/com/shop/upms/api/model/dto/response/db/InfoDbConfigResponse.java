package com.shop.upms.api.model.dto.response.db;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
  * class_name: InfoDbConfigRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午7:10
  *
 **/
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class InfoDbConfigResponse implements Serializable {
    /**
     * 00 手动 01 自动
     */
    @ApiModelProperty("00 手动 01 自动")
    private String type = "01";
    /**
     * 00 固定周期 01 自定定义周期
     */
    @ApiModelProperty(hidden = true)
    private String cycleTpe = "00";
    /**
     * cycle_type 00 时 00 每天 01 每周 02 每月
     */
    @ApiModelProperty("00 每天 01 每周 02 每月")
    private String cycle = "00";

}
