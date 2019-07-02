package com.shop.upms.api.service.impl;

import com.shop.upms.api.constant.ShopEnum;
import com.shop.upms.api.model.dto.response.db.InfoDbConfigResponse;
import com.shop.upms.api.service.ISysDbBackService;
import com.shop.upms.api.service.ISysDbConfigService;
import com.yqkj.utile.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@EnableScheduling
@Slf4j
public class DbCallBackTaskService implements SchedulingConfigurer {

    private String cron = null;

    @Autowired
    private ISysDbBackService sysDbBackService;

    @Autowired
    private ISysDbConfigService sysDbConfigService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        log.info("configureTasks::+++++++++++++++++++++++++++++++++++++++++++++++");
        InfoDbConfigResponse info = sysDbConfigService.info();


        scheduledTaskRegistrar.addTriggerTask(() -> {

            if (null != info && ShopEnum.DbBackType.AUTH.equals(info.getType())) {
                sysDbBackService.back(null, ShopEnum.DbBackType.AUTH.getValue());
            }
            // 任务逻辑
        }, triggerContext -> {

            PeriodicTrigger cronTrigger = new PeriodicTrigger(getNextTime(info));

            return cronTrigger.nextExecutionTime(triggerContext);
        });

    }

    private long getNextTime(InfoDbConfigResponse info) {

        if (null == info || ShopEnum.DbBackType.HAD.equals(info.getType())) {

            return  DateUtil.addDay(new Date(),1).getTime();

        }
        String type = info.getCycle();
        Date date = new Date();
        switch (type) {

            case "00":
                date = DateUtil.addDay(date,1);
                break;
            case "01" :
                date = DateUtil.getStartDayOfWeek(date);
            case "02":
                date = DateUtil.getStartDayOfNextMonth(date);

        }
        if (Objects.isNull(date)) {
            date=DateUtil.addDay(date,1);
        }
        return date.getTime();
    }


}
