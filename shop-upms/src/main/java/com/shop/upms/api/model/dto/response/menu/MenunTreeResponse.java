package com.shop.upms.api.model.dto.response.menu;

import com.yqkj.utile.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
  * class_name: DictTreeResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午8:57
  *
 **/
@Data
@ApiModel
public class MenunTreeResponse extends TreeNode {
    @ApiModelProperty("圖標")
    private String icon;
    @ApiModelProperty("名稱")
    private String name;
    @ApiModelProperty("ＵＲＬ")
    private String url;
    @ApiModelProperty("是否展開")
    private boolean spread = false;
    @ApiModelProperty("前臺Compent")
    private String component;
    @ApiModelProperty("0　菜單　1　按鈕")
    private Integer type;
    @ApiModelProperty("排序")
    private Integer sortOrder = Integer.valueOf(255);
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("1 正常　0　禁用")
    private Integer status;
}
