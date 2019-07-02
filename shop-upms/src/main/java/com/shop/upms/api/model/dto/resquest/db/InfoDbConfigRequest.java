package com.shop.upms.api.model.dto.resquest.db;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
public class InfoDbConfigRequest implements Serializable {
    /**
     * 00 手动 01 自动
     */
    @ApiModelProperty("00 手动 01 自动")
    @NotBlank(message = "Type 不能为空")
    private String type;
    /**
     * 00 固定周期 01 自定定义周期
     */
    @ApiModelProperty(hidden = true)
    private String cycleTpe = "00";
    /**
     * cycle_type 00 时 00 每天 01 每周 02 每月
     */
    @ApiModelProperty("00 每天 01 每周 02 每月")
    @NotBlank(message = "cycle 不能为空")
    private String cycle;

}
