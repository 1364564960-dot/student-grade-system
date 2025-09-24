package com.gradproject.sgs.controller;

import com.gradproject.sgs.dto.CreateStudentRequest;
import com.gradproject.sgs.entity.StudentInfo;
import com.gradproject.sgs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 只允许管理员创建学生
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentInfo createStudent(@RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request.studentInfo(), request.password());
    }

    // 只允许管理员查询所有学生
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentInfo> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 只允许管理员修改学生信息
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentInfo updateStudent(@PathVariable Long id, @RequestBody StudentInfo studentDetails) {
        return studentService.updateStudentInfo(id, studentDetails);
    }

    // 只允许管理员删除学生
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student with info id " + id + " has been deleted successfully.";
    }
}
