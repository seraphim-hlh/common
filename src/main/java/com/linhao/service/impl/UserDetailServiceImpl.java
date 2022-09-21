package com.linhao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linhao.mapper.SysUserMapper;
import com.linhao.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = this.userMapper.selectOne(queryWrapper);
        if (sysUser==null){
            return null;
    }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysUser.getUserRole());
        grantedAuthorities.add(grantedAuthority);
        return new User(username, sysUser.getPassword(),grantedAuthorities );
    }
}
