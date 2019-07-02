package com.shop.upms.api.model.dto.response.dic;

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
public class DictTreeResponse extends TreeNode {
    @ApiModelProperty("描述")
    private String description;
    private Integer sortOrder = 100;
    @ApiModelProperty("0　禁用　1　正常")
    private Integer status;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("標題／名稱")
    private String title;

}
