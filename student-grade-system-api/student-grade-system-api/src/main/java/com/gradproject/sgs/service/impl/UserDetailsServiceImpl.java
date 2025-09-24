package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.Role;
import com.gradproject.sgs.mapper.RoleMapper;
import com.gradproject.sgs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    // 1. 【新增】注入 RoleMapper
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.gradproject.sgs.entity.User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 2. 【新增】根据 user.getRoleId() 查询角色信息
        Role role = roleMapper.findById(user.getRoleId());
        String roleName = (role != null) ? role.getRoleName() : "";

        // 3. 【核心】创建一个包含用户角色的权限列表
        // SimpleGrantedAuthority 是 Spring Security 提供的一个权限类
        // 注意：角色名称在 Spring Security 内部通常需要一个 "ROLE_" 前缀，但我们的数据库里已经存了这个前缀，所以直接使用即可
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority) // 4. 【核心】将包含权限的列表传入
        );
    }
}
