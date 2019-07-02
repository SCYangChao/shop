package com.shop.upms.api.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-13
 */
@TableName("sys_log")
@Data
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 耗時
     */
    private Date costTime;
    /**
     * 日誌時間
     */
    @TableField("log_time")
    private Date logTime;
    /**
     * IP
     */
    private String ip;
    /**
     * 模塊編碼
     */
    private String model;
    /**
     * 誌操作詳情
     */
    private String title;
    @TableField("reques_uri")
    private String requesUri;
    private String params;
    /**
     * 返回結果
     */
    @TableField("result_status")
    private String resultStatus;
    /**
     * 異常信息
     */
    private String exception;
    /**
     * 請求方式　get post 
     */
    private String method;

    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime = new Date();
    @TableField("del_flag")
    private Integer delFlag = Integer.valueOf(0);
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime = new Date();

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
