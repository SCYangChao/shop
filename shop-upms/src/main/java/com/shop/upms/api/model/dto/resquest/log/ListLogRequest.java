package com.shop.upms.api.model.dto.resquest.log;


import com.shop.upms.api.constant.ShopEnum;
import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
  * class_name: ListRoleRequest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午9:36
  *
 **/
@Data
@ApiModel
public class ListLogRequest extends BasePageResquest {

    @ApiModelProperty("用户名称")
    private  String userName;

    @ApiModelProperty("200　成功　500　失败")
    private Integer resultStatus;

    @ApiModelProperty("DAY　1天,MONTH　30天,WEKK　7天　,THREE－3天,WISHES-任意;")
    private ShopEnum.DateType dateType;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;



}
