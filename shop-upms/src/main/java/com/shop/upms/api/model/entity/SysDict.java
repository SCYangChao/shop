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
@TableName("sys_dict")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String description;
    @TableField("parent_id")
    private Long parentId;
    @TableField("sort_order")
    private Integer sortOrder;
    private Integer status;
    private String title;
    private String value;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_by")
    private String createBy;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
