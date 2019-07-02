package com.shop.upms.api.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("menu_id")
    private Long menuId;
    @TableField("role_id")
    private Long roleId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
