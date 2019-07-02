package com.shop.upms.api.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("role_id")
    private Long roleId;
    @TableField("user_id")
    private Long userId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
