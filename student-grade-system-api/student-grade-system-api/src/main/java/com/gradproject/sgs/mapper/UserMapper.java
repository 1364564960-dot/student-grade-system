package com.gradproject.sgs.mapper;

import com.gradproject.sgs.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
}