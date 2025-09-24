// ClassServiceImpl.java
package com.gradproject.sgs.service.impl;

import com.gradproject.sgs.entity.Class;
import com.gradproject.sgs.mapper.ClassMapper;
import com.gradproject.sgs.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Class createClass(Class newClass) {
        classMapper.insert(newClass);
        return newClass;
    }

    @Override
    public List<Class> getAllClasses() {
        return classMapper.findAll();
    }

    @Override
    public Class updateClass(Integer id, Class classDetails) {
        classDetails.setId(id);
        classMapper.update(classDetails);
        return classMapper.findById(id);
    }

    @Override
    public void deleteClass(Integer id) {
        classMapper.deleteById(id);
    }
}