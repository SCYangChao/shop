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
@TableName("sys_db_recover")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDbRecover extends Model<SysDbRecover> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 备份路径
     */
    private String path;
    /**
     * 00 固定周期 01 自定定义周期
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField("ent_time")
    private Date entTime;
    /**
     * 毫秒
     */
    @TableField("time_up")
    private Integer timeUp;
    private String acc;
    @TableField("user_name")
    private String userName;
    private String phone;
    /**
     * 0 失败 1 成功
     */
    private Integer status;
    @TableField("back_type")
    private String backType;
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
