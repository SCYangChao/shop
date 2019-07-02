package com.shop.upms.api.model.dto.response.area;

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
public class AreaTreeResponse extends TreeNode {

    @ApiModelProperty("名稱")
    private String name;
}
