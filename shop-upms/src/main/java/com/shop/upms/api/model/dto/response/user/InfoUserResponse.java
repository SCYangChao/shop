package com.shop.upms.api.model.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
public class InfoUserResponse implements Serializable {
    private Long id;
    @ApiModelProperty("頭像")
    private String avatar;
    @ApiModelProperty("郵件")
    @NotBlank(message = "email 不能爲空")
    private String email;
    @ApiModelProperty("手機號")
    @NotBlank(message = "mobile 不能爲空")
    private String mobile;
    @ApiModelProperty("姓名")
    @NotBlank(message = "nickName 不能爲空")
    private String nickName;
    @ApiModelProperty("密碼")
    @NotBlank(message = "password 不能爲空")
    private String password;
    @ApiModelProperty("名別　0　男　1　女　2　未知")
    @NotNull(message = "sex 不能爲空")
    private Integer sex;
    @ApiModelProperty("用戶名稱")
    @NotBlank(message = "userName 不能爲空")
    private String userName;
    @ApiModelProperty("部門名稱")
    @NotNull(message = "departmentId 不能爲空")
    private Integer departmentId;
    @ApiModelProperty("1 正常　0　禁用")
    private Integer status = Integer.valueOf(1);
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
