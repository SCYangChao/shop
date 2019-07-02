package com.shop.upms.api.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shop.upms.api.constant.CommonConstant;
import com.shop.upms.api.mapper.SysDepartmentMapper;
import com.shop.upms.api.model.dto.response.org.OrgTreeResponse;
import com.shop.upms.api.model.dto.resquest.org.AddOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.ListOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.TreeOrgRequest;
import com.shop.upms.api.model.dto.resquest.org.UpdateOrgRequest;
import com.shop.upms.api.model.dto.resquest.user.BathStatusUserRequest;
import com.shop.upms.api.model.entity.SysDepartment;
import com.shop.upms.api.service.ISysDepartmentService;
import com.yqkj.constant.GloabEnum;
import com.yqkj.dto.AuthUserDto;
import com.yqkj.exception.ParamException;
import com.yqkj.utile.ListUtil;
import com.yqkj.utile.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@Service
@Slf4j
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public Boolean bathDel(String ids) {
        List<Long> idLog = ListUtil.ids(ids);


        if (ListUtil.isNull(idLog)) {

            return Boolean.FALSE;

        }

        List<SysDepartment> sysUserList =this.selectBatchIds(idLog);

        deleteBatchIds(ListUtil.convertToList(sysUserList , SysDepartment::getId));

        return Boolean.TRUE;
    }

    @Override
    public Integer add(AddOrgRequest sysDepartMentAddResquest , AuthUserDto authUserDto) {
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setCode(sysDepartMentAddResquest.getCode());
        sysDepartment.setDelFlag(Integer.valueOf(0));
        sysDepartment.setCreateBy(authUserDto.getUsername());
        sysDepartment.setCreateTime(new Date());
        sysDepartment.setParent(Boolean.TRUE);
        sysDepartment.setStatus(Integer.valueOf(1));
        sysDepartment.setCreateBy(authUserDto.getUsername());
        sysDepartment.setParentId(sysDepartMentAddResquest.getParentId());
        sysDepartment.setName(sysDepartMentAddResquest.getName());
        sysDepartment.setAttachCode(attachCode(sysDepartMentAddResquest.getParentId()));
        this.insert(sysDepartment);
        return sysDepartment.getId().intValue();
    }

    @Override
    public Boolean update(UpdateOrgRequest updateOrgRequest , AuthUserDto authUserDto) throws ParamException {
        SysDepartment sysDepartment = this.selectById(updateOrgRequest.getId());
        log.info("update:{}" ,authUserDto);
        if (null == sysDepartment) {

            throw  new ParamException("參數非法");

        }
        if (!Objects.isNull(updateOrgRequest.getParentId())) {

            sysDepartment.setParentId(updateOrgRequest.getParentId());

        }

        sysDepartment.setCode(updateOrgRequest.getCode());
        sysDepartment.setName(updateOrgRequest.getName());
        sysDepartment.setUpdateBy(authUserDto.getUsername());
        sysDepartment.setUpdateTime(new Date());
        return this.updateById(sysDepartment);
    }

    @Override
    public Page<OrgTreeResponse> list(ListOrgRequest listOrgRequest) throws ParamException {
        Page<OrgTreeResponse> page = new Page(listOrgRequest.getPage() , listOrgRequest.getLimit() );

        page.setRecords(sysDepartmentMapper.queryOrgList(page ,listOrgRequest));

        return page;
    }

    @Override
    public List<OrgTreeResponse> tree(TreeOrgRequest treeOrgRequest, AuthUserDto authUserDto) throws ParamException {

        log.info("tree:{}" ,authUserDto);


        List<OrgTreeResponse> sysDepartmentList =null;

        Integer rootId = authUserDto.getDepartmentId().intValue();

        if (GloabEnum.UserType.isAdmin(authUserDto.getType())) {
            sysDepartmentList = this.sysDepartmentMapper.queryOrgTree(treeOrgRequest);
            rootId = -1;
        } else  {
            SysDepartment sysDepartment = this.selectById(authUserDto.getDepartmentId());

            if (null == sysDepartment) {

                return CommonConstant.EMPTY_LSIT;

            }
      /*      sysDepartmentList = selectList(new EntityWrapper<SysDepartment>().like("attach_code",
                    sysDepartment.getAttachCode()));*/

        }

        if (Objects.isNull(sysDepartmentList)) {
            return CommonConstant.EMPTY_LSIT;
        }

        return TreeUtil.bulid(sysDepartmentList, rootId);
    }

    private String attachCode(Long parentId){
        StringBuffer stringBuffer = new StringBuffer();
        String random = RandomStringUtils.random(6, CommonConstant.RANDOM_CHAR);
        if (null != parentId && parentId != -1) {

            SysDepartment sysDepartment = selectById(parentId);
            stringBuffer.append(sysDepartment.getAttachCode());
            stringBuffer.append("|").append(random);

        } else  {
            stringBuffer.append(random);
        }
        return stringBuffer.toString();
    }

    @Override
    public Boolean bathStatus(BathStatusUserRequest updateUserRequest, AuthUserDto authUserDto) {
        List<SysDepartment> sysMenuList =this.selectBatchIds(updateUserRequest.getIds());

        if (null == sysMenuList || sysMenuList.isEmpty()) {

            return  Boolean.FALSE;
        }

        Date date = new Date();
        for (SysDepartment sysMenuRole : sysMenuList) {
            sysMenuRole.setStatus(updateUserRequest.getStatus());
            sysMenuRole.setUpdateTime(date );
            sysMenuRole.setUpdateBy(authUserDto.getUsername());
        }

        updateBatchById(sysMenuList);

        return Boolean.TRUE;
    }
}
