package com.shop.upms.api.model.dto.resquest.log;

import com.yqkj.dto.LogDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 *
  * class_name: BathLogResquest
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 上午10:12
  *
 **/
@ApiModel
@Data
public class BathLogResquest implements Serializable {

    @ApiModelProperty("日誌")
    private List<LogDto> datas;

}
