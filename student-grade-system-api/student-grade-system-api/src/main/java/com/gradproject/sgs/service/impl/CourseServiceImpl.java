// CourseServiceImpl.java
package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.Course;
import com.gradproject.sgs.mapper.CourseMapper;
import com.gradproject.sgs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course createCourse(Course course) {
        courseMapper.insert(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseMapper.findAll();
    }

    @Override
    public Course updateCourse(Integer id, Course courseDetails) {
        courseDetails.setId(id);
        courseMapper.update(courseDetails);
        return courseDetails;
    }

    @Override
    public void deleteCourse(Integer id) {
        courseMapper.deleteById(id);
    }
}