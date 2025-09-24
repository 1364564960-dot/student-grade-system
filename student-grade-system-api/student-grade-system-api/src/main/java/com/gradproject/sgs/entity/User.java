package com.gradproject.sgs.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer roleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}