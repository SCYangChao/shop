package com.shop.upms.api.model.dto.resquest.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
  * class_name: AddMenuResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:40
  *
 **/
@Data
@ApiModel
public class UpdateMenuResquest implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("名稱")
    private String name;
    @ApiModelProperty("上級ＩＤ　根節點　－１")
    private Long parentId;
    @ApiModelProperty("0　菜單　1　按鈕")
    private Integer type;
    @ApiModelProperty("前臺組件")
    private String component;
    @ApiModelProperty("ＩＣＯＮ")
    private String icon;
    private Integer sortOrder = Integer.valueOf(255);
    @ApiModelProperty("地址")
    private String url;
}
