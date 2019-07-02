package com.shop.upms.api.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shop.upms.api.constant.CommonConstant;
import com.shop.upms.api.model.entity.SysUserRole;
import com.shop.upms.api.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangchao.cool@gmail.com
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/api/v1.0/sysUserRole")
public class SysUserRoleController  {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
    * 通过ID查询
    *
    * @param id ID
    * @return SysUserRole
    */
    @GetMapping("/{id}")
    public SysUserRole get(@PathVariable Integer id) {
        return sysUserRoleService.selectById(id);
    }


    /**
    * 分页查询信息
    *
    * @param params 分页对象
    * @return 分页对象
    */
    @RequestMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysUserRoleService.selectPage(new Page<>(), new EntityWrapper<>());
    }

    /**
     * 添加
     * @param  sysUserRole  实体
     * @return success/false
     */
    @PostMapping
    public Boolean add(@RequestBody SysUserRole sysUserRole) {
        return sysUserRoleService.insert(sysUserRole);
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(id);
        return sysUserRoleService.updateById(sysUserRole);
    }

    /**
     * 编辑
     * @param  sysUserRole  实体
     * @return success/false
     */
    @PutMapping
    public Boolean edit(@RequestBody SysUserRole sysUserRole) {
        return sysUserRoleService.updateById(sysUserRole);
    }
}
