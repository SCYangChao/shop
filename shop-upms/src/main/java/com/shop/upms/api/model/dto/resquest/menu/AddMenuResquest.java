package com.shop.upms.api.model.dto.resquest.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
public class AddMenuResquest implements Serializable {
    @ApiModelProperty("名稱")
    @NotBlank(message = "name 不能爲空")
    private String name;
    @ApiModelProperty("上級ＩＤ　根節點　－１")
    @NotBlank(message = "parentId 不能爲空")
    private Integer parentId;
    @ApiModelProperty("0　菜單　1　按鈕")
    @NotNull(message = "type 不能爲空")
    private Integer type;
    @ApiModelProperty("前臺組件")
    private String component;
    @ApiModelProperty("ＩＣＯＮ")
    private String icon;
    @ApiModelProperty("排序")
    private Integer sortOrder = Integer.valueOf(255);
    @ApiModelProperty("地址")
    private String url;
}
