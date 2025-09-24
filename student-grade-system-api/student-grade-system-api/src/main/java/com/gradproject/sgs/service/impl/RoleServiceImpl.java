package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.Role;
import com.gradproject.sgs.mapper.RoleMapper;
import com.gradproject.sgs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listAllRoles() {
        return roleMapper.findAll();
    }
}