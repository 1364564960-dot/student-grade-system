package com.gradproject.sgs.dto;

import com.gradproject.sgs.entity.StudentInfo;

// 使用 record 来简化数据传输对象
public record CreateStudentRequest(StudentInfo studentInfo, String password) {
}