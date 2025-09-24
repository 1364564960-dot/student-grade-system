package com.gradproject.sgs.controller;

import com.gradproject.sgs.entity.Class;
import com.gradproject.sgs.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    // 允许任何已登录的用户查询班级列表
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    // 只允许拥有 'ROLE_ADMIN' 权限的用户创建班级
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Class createClass(@RequestBody Class newClass) {
        return classService.createClass(newClass);
    }

    // 只允许拥有 'ROLE_ADMIN' 权限的用户修改班级
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Class updateClass(@PathVariable Integer id, @RequestBody Class classDetails) {
        return classService.updateClass(id, classDetails);
    }

    // 只允许拥有 'ROLE_ADMIN' 权限的用户删除班级
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return "Class with id " + id + " has been deleted successfully.";
    }
}