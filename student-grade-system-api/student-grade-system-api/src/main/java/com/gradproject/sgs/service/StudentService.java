package com.gradproject.sgs.service;

import com.gradproject.sgs.entity.StudentInfo;
import java.util.List;

public interface StudentService {
    // 创建学生时需要同时提供登录信息和个人信息
    StudentInfo createStudent(StudentInfo studentInfo, String password);
    List<StudentInfo> getAllStudents();
    StudentInfo updateStudentInfo(Long id, StudentInfo studentDetails);
    void deleteStudent(Long id);
}
