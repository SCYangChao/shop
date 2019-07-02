package com.yqkj.shop.auth.model.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String address;
    private String avatar;
    private String description;
    private String email;
    private String mobile;
    @TableField("nick_name")
    private String nickName;
    private String password;
    private Integer sex;
    private Integer status;
    private Integer type;
    private String username;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("department_id")
    private Long departmentId;
    private String street;
    @TableField("pass_strength")
    private String passStrength;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
