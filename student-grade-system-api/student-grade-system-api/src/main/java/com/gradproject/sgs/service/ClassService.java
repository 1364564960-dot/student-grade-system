// ClassService.java
package com.gradproject.sgs.service;

import com.gradproject.sgs.entity.Class;
import java.util.List;

public interface ClassService {
    Class createClass(Class newClass);
    List<Class> getAllClasses();
    Class updateClass(Integer id, Class classDetails);
    void deleteClass(Integer id);
}