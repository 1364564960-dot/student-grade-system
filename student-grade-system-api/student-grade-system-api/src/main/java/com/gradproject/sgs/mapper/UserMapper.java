// 文件路径: src/main/java/com/gradproject/sgs/mapper/UserMapper.java
package com.gradproject.sgs.mapper;

import com.gradproject.sgs.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

    // 【关键修正】添加 insert 方法声明
    int insert(User user);

    // 【关键修正】添加 deleteById 方法声明
    int deleteById(Long id);
}