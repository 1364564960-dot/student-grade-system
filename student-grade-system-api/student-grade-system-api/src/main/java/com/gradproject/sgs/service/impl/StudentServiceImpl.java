package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.StudentInfo;
import com.gradproject.sgs.entity.User;
import com.gradproject.sgs.mapper.StudentMapper;
import com.gradproject.sgs.mapper.UserMapper;
import com.gradproject.sgs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 角色ID 3 代表学生
    private static final int STUDENT_ROLE_ID = 3;

    @Override
    @Transactional // 【关键】添加事务注解，确保两个数据库操作要么都成功，要么都失败
    public StudentInfo createStudent(StudentInfo studentInfo, String password) {
        // 1. 创建用户账户
        User newUser = new User();
        // 默认使用学号作为登录用户名
        newUser.setUsername(studentInfo.getStudentNumber());
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoleId(STUDENT_ROLE_ID);
        userMapper.insert(newUser); // Mybatis 会将自增的 user ID 回填到 newUser 对象中

        // 2. 创建学生信息，并关联用户ID
        studentInfo.setUserId(newUser.getId());
        studentMapper.insert(studentInfo);

        return studentInfo;
    }

    @Override
    public List<StudentInfo> getAllStudents() {
        return studentMapper.findAll();
    }

    @Override
    public StudentInfo updateStudentInfo(Long id, StudentInfo studentDetails) {
        studentDetails.setId(id);
        studentMapper.update(studentDetails);
        return studentMapper.findById(id);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        // 1. 先根据学生信息ID找到关联的用户ID
        StudentInfo studentInfo = studentMapper.findById(id);
        if (studentInfo != null) {
            // 2. 删除学生信息表中的记录
            studentMapper.deleteById(id);
            // 3. 删除用户表中的记录
            userMapper.deleteById(studentInfo.getUserId());
        }
    }
}