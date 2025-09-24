package com.gradproject.sgs.entity;

import lombok.Data;

@Data
public class StudentInfo {
    private Long id;
    private Long userId; // 关联 t_user 表的 ID
    private String studentNumber;
    private String name;
    private String gender;
    private Integer classId; // 关联 t_class 表的 ID
}
