package com.shop.upms.api.model.dto.response.org;

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
public class OrgTreeResponse extends TreeNode {
    @ApiModelProperty("名稱")
    private String name;
    @ApiModelProperty("上級名稱")
    private String parentName;
    @ApiModelProperty(value = "机构编码")
    private String code;
    @ApiModelProperty(value = "0 禁用　1　正常")
    private Integer status = Integer.valueOf(1);

}
