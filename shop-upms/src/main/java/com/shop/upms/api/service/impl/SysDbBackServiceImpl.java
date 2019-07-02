package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.config.DatasourcePropertiesConfig;
import com.shop.upms.api.mapper.SysDbBackMapper;
import com.shop.upms.api.model.dto.response.db.ListDbBackResponse;
import com.shop.upms.api.model.dto.resquest.BasePageResquest;
import com.shop.upms.api.model.entity.SysDbBack;
import com.shop.upms.api.service.ISysDbBackService;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.utile.BackDbToole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-05-05
 */
@Service
@Slf4j
public class SysDbBackServiceImpl extends ServiceImpl<SysDbBackMapper, SysDbBack> implements ISysDbBackService {

    @Autowired
    private SysDbBackMapper sysDbBackMapper;

    @Autowired
    private DatasourcePropertiesConfig datasourcePropertiesConfig;

    @Override
    public Boolean back(AuthUserDto authUserDto , String type) {

        String fileName = "back_"+System.nanoTime()+".sql";
        SysDbBack sysDbBack = new SysDbBack();

        if (null !=authUserDto) {

            sysDbBack.setAcc(authUserDto.getUsername());
            sysDbBack.setPhone(authUserDto.getMobile());
            sysDbBack.setUserName(authUserDto.getNickName());
            sysDbBack.setCreateBy(authUserDto.getUsername());

        }

        sysDbBack.setBackType(type);
        sysDbBack.setCreateTime(new Date());
        sysDbBack.setFileName(fileName);
        sysDbBack.setPath(datasourcePropertiesConfig.getSavePath());

        sysDbBack.setStartTime(new Date());
        sysDbBack.setStatus(1);
        try {
            BackDbToole.exportDatabase(datasourcePropertiesConfig.getHostIP(), datasourcePropertiesConfig.getUserName(), datasourcePropertiesConfig.getPassword(), datasourcePropertiesConfig.getSavePath(), fileName, "shop");
        }catch (Exception e) {
           sysDbBack.setStatus(0);
        }
        sysDbBack.setEntTime(new Date());

        sysDbBackMapper.insert(sysDbBack);

        return Boolean.TRUE;
    }

    @Override
    public Page<ListDbBackResponse> list(BasePageResquest basePageResquest) {

        Page<ListDbBackResponse> page = new Page(basePageResquest.getPage() , basePageResquest.getLimit() );

        page.setRecords(sysDbBackMapper.pageList(page ,basePageResquest));

        return page;
    }
}
