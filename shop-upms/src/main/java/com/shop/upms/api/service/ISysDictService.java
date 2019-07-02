package com.shop.upms.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.shop.upms.api.model.dto.response.dic.DictTreeResponse;
import com.shop.upms.api.model.dto.resquest.dic.AddSysDictResquest;
import com.shop.upms.api.model.dto.resquest.dic.UpdateSysDictResquest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysDict;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.dto.KeyValueCommonDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
public interface ISysDictService extends IService<SysDict> {

    List<DictTreeResponse> queryTree();

    Long add(AddSysDictResquest addSysDictResquest);

    Boolean edit(UpdateSysDictResquest updateSysDictResquest);

    List<KeyValueCommonDto> queryDisct(String parentKey);

    Boolean bathDel(String ids);

    Boolean bathStatus(BathStatusUserRequest updateUserRequest , AuthUserDto authUserDto);

}
