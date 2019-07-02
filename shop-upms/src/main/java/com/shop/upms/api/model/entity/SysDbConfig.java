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
 * @since 2019-05-05
 */
@TableName("sys_db_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDbConfig extends Model<SysDbConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 00 手动 01 自动
     */
    private String type;
    /**
     * 00 固定周期 01 自定定义周期
     */
    @TableField("cycle_tpe")
    private String cycleTpe;
    /**
     * cycle_type 00 时 00 每天 01 每周 02 每月
     */
    private String cycle;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("update_by")
    private String updateBy;
    @TableField(value = "update_time")
    private Date updateTime = new Date();
    @TableField("create_time")
    private Date createTime = new Date();
    @TableField("create_by")
    private String createBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
