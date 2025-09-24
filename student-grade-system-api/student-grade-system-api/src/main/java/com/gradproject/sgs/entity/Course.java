package com.gradproject.sgs.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Course {
    private Integer id;
    private String courseName;
    private BigDecimal credits; // 使用 BigDecimal 来精确表示学分
}