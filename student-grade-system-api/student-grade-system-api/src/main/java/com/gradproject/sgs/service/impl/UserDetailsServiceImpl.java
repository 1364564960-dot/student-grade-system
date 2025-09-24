package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.Role;
import com.gradproject.sgs.entity.User; // 确保 User 被导入
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

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库查询用户
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 根据 roleId 查询角色信息
        Role role = roleMapper.findById(user.getRoleId());
        String roleName = (role != null) ? role.getRoleName() : "";

        // 创建权限列表
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // 返回 Spring Security 的 UserDetails 对象
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}