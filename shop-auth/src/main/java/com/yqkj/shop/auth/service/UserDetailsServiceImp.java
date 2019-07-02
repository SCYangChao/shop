package com.yqkj.shop.auth.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yqkj.shop.auth.model.entity.SysUser;
import com.yqkj.shop.auth.model.entity.SysUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
  * class_name: UserDetailsServiceImp
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:34
  *
 **/
@Service("userDetailsServiceImp")
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;
    /**
     * 加載用戶數據
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       SysUser sysUser=  sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username" , s));
        if(sysUser == null){
            log.error("登录用户【"+s + "】不存在.");
            throw new UsernameNotFoundException("登录用户【"+s + "】不存在.");
        }
        SysUserDetails userDetails = new SysUserDetails();
        BeanUtils.copyProperties(sysUser , userDetails);
        return userDetails;
    }
}
