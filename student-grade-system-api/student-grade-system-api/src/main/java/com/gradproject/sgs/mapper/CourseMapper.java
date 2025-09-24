package com.gradproject.sgs.mapper;

import com.gradproject.sgs.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    int insert(Course course);
    List<Course> findAll();
    int update(Course course);
    int deleteById(Integer id);
}