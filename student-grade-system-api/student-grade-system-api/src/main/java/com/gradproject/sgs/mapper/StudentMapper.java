package com.gradproject.sgs.mapper;

import com.gradproject.sgs.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insert(StudentInfo studentInfo);
    List<StudentInfo> findAll();
    StudentInfo findById(Long id);
    StudentInfo findByUserId(Long userId);
    int update(StudentInfo studentInfo);
    int deleteById(Long id);
}
