package com.gradproject.sgs.controller;

import com.gradproject.sgs.entity.Course;
import com.gradproject.sgs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 允许任何已登录的用户查询课程列表
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 只允许管理员创建课程
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // 只允许管理员修改课程
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        return courseService.updateCourse(id, courseDetails);
    }

    // 只允许管理员删除课程
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return "Course with id " + id + " has been deleted successfully.";
    }
}