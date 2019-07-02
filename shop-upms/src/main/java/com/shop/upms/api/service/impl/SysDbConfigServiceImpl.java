package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.mapper.SysDbConfigMapper;
import com.shop.upms.api.model.dto.response.db.InfoDbConfigResponse;
import com.shop.upms.api.model.dto.resquest.db.InfoDbConfigRequest;
import com.shop.upms.api.model.entity.SysDbConfig;
import com.shop.upms.api.service.ISysDbConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
@Service
public class SysDbConfigServiceImpl extends ServiceImpl<SysDbConfigMapper, SysDbConfig> implements ISysDbConfigService {

    @Autowired
    private SysDbConfigMapper sysDbConfigMapper;

    @Override
    public InfoDbConfigResponse info() {

        List<SysDbConfig> objects = this.sysDbConfigMapper.selectList(new EntityWrapper<SysDbConfig>());


        InfoDbConfigResponse infoDbConfigResponse= new InfoDbConfigResponse();

        if (Objects.isNull(objects) || objects.isEmpty()) {

            return  infoDbConfigResponse;

        }

        SysDbConfig sysDbConfig = objects.get(0);

        infoDbConfigResponse.setType(sysDbConfig.getType());
        infoDbConfigResponse.setCycle(sysDbConfig.getCycle());

        return infoDbConfigResponse;
    }

    @Override
    public Boolean edit(InfoDbConfigRequest infoDbConfigRequest) {

        List<SysDbConfig> objects = this.sysDbConfigMapper.selectList(new EntityWrapper<SysDbConfig>());

        if (Objects.isNull(objects) || objects.isEmpty()) {

            SysDbConfig sysDbConfig = new SysDbConfig();
            sysDbConfig.setCycle(infoDbConfigRequest.getCycle());
            sysDbConfig.setType(infoDbConfigRequest.getType());

            this.insert(sysDbConfig);

        } else {
            SysDbConfig sysDbConfig = objects.get(0);
            sysDbConfig.setCycle(infoDbConfigRequest.getCycle());
            sysDbConfig.setType(infoDbConfigRequest.getType());

            this.updateById(sysDbConfig);

        }
        return Boolean.TRUE;
    }
}
