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
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@TableName("sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("parent_id")
    private Integer parentId;
    private Integer type;
    private String component;
    private String path;
    private String title;
    private String icon;
    private Integer level;
    @TableField("sort_order")
    private Integer sortOrder;
    private Integer status;
    private String url;
    private String description;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
