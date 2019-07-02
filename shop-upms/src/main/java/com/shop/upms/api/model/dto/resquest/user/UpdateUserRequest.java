package com.shop.upms.api.model.dto.resquest.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
  * class_name: UpdateUserRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:51
  *
 **/
@Data
@ApiModel
public class UpdateUserRequest implements Serializable {
    @ApiModelProperty("id")
    @NotNull(message = "ＩＤ　不能爲空")
    private Long id;
    @ApiModelProperty("頭像")
    private String avatar;
    @ApiModelProperty("郵件")
    private String email;
    @ApiModelProperty("手機號")
    private String mobile;
    @ApiModelProperty("暱稱")
    private String nickName;
    @ApiModelProperty("密碼")
    private String password;
    @ApiModelProperty("名別　0　男　1　女　2　未知")
    private Integer sex;
    @ApiModelProperty("用戶名稱")
    private String userName;
    @ApiModelProperty("部門名稱")
    private Integer departmentId;
    @ApiModelProperty("1 正常　0　禁用")
    private Integer status;
    @ApiModelProperty("角色ＩＤ")
    private Integer roleId;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @ApiModelProperty("職務")
    private String job;
}
