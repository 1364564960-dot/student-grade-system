package com.gradproject.sgs.mapper;

// 【关键修正】添加了对 Role 和 List 的 import
import com.gradproject.sgs.entity.Role;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {

    List<Role> findAll();

    Role findById(@Param("id") Integer id);
}