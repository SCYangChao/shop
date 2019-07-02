package com.shop.upms.api.util;

import com.shop.upms.api.constant.ShopEnum;
import com.yqkj.exception.BizException;
import com.yqkj.utile.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
  * class_name: DateTypeParseTool
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午7:10
  *
 **/
@Data
public class DateTypeParseTool implements Serializable {


    private Date end;

    private Date start;

    public DateTypeParseTool(ShopEnum.DateType statisticsType , Date start , Date end) {
        if (null == statisticsType) {
            return;
        }
        Integer day = 0;
        switch (statisticsType) {
            case WEKK:
                day=6;
                initDateType(day);
                break;
            case MONTH:
                day=29;
                initDateType(day);
                break;
            case THREE:
                day=2;
                initDateType(day);
                break;
            case WISHES:
                if (null == start || null == end) {
                    throw  new BizException("开始时间与结束时间不能为空");
                }
                this.end=end;
                this.start = start;
        }

    }
    private void initDateType(Integer day) {
        end = new Date();
        this.start = DateUtil.addDay(end , -day);
        this.start=DateUtil.getDay_(this.start);
    }
}
