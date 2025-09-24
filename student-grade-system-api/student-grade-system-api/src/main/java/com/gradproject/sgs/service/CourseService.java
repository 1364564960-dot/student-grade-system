// CourseService.java
package com.gradproject.sgs.service;

import com.gradproject.sgs.entity.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> getAllCourses();
    Course updateCourse(Integer id, Course courseDetails);
    void deleteCourse(Integer id);
}