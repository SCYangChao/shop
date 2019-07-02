package com.shop.upms.api.model.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
  * class_name: ListUserResponse
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:52
  *
 **/
@Data
@ApiModel
public class ListUserResponse implements Serializable {

    private Long id;
    @ApiModelProperty("頭像")
    private String avatar;
    @ApiModelProperty("郵件")
    private String email;
    @ApiModelProperty("手機號")
    private String mobile;
    @ApiModelProperty("暱稱")
    private String nickName;
    @ApiModelProperty("名別　0　男　1　女　2　未知")
    private Integer sex;
    @ApiModelProperty("用戶名稱")
    private String userName;
    @ApiModelProperty("部門名稱")
    private String departmentId;
    @ApiModelProperty("1 正常　0　禁用")
    private Integer status;
    @ApiModelProperty("创建时间")
    private String createTime;


}
