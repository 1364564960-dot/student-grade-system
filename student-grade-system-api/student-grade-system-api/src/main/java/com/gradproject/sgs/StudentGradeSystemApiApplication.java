package com.gradproject.sgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 【关键】添加这个注解，告诉 Spring Boot 去哪里扫描你的 Mapper 接口
@MapperScan("com.gradproject.sgs.mapper")
public class StudentGradeSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentGradeSystemApiApplication.class, args);
	}

}
