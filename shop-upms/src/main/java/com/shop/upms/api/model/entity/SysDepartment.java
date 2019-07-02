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
@TableName("sys_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDepartment extends Model<SysDepartment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String code;
    @TableField("parent_id")
    private Long parentId;
    @TableField("is_parent")
    private Boolean parent;
    private Integer status;
    private Integer sort;
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
    @TableField("attach_code")
    private String attachCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
