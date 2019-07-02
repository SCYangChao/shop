package com.yqkj.shop.auth.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
  * class_name: SysUserDetails
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:36
  *
 **/
@Data
public class SysUserDetails implements UserDetails {
    private Long id;
    private String address;
    private String avatar;
    private String description;
    private String email;
    private String mobile;
    @TableField("nick_name")
    private String nickName;
    private String password;
    private Integer sex;
    private Integer status;
    private Integer type;
    private String username;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("department_id")
    private String departmentId;
    private String street;

    private List<SimpleGrantedAuthority> GrantedAuthority = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority.add(new SimpleGrantedAuthority("testtset"));
        return GrantedAuthority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == 1;
    }
}
