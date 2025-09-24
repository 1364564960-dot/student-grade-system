package com.gradproject.sgs.entity;

import lombok.Data;

/**
 * 对应数据库中的 t_role 表
 */
@Data
public class Role {

    private Integer id;

    private String roleName;

    private String description;
}