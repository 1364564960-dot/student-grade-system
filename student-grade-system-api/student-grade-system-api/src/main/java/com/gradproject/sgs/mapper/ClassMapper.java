package com.gradproject.sgs.mapper;

import com.gradproject.sgs.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    int insert(Class newClass);
    Class findById(Integer id);
    List<Class> findAll();
    int update(Class classToUpdate);
    int deleteById(Integer id);
}