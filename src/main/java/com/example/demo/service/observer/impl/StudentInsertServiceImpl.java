package com.example.demo.service.observer.impl;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.observer.ProjectService;
import com.example.demo.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("StudentInsertServiceImpl")
public class StudentInsertServiceImpl implements ProjectService<Student>{

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public Class<?> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student getEntity(Class clazz) throws IllegalAccessException, InstantiationException {
        Student student = (Student)clazz.newInstance();
        student.setAddress(GeneratorUtil.getRoad() + "glliu测试观察者模式数据");
        student.setAge(GeneratorUtil.getAge());
        student.setHeight(GeneratorUtil.getHeight());
        student.setWeight(GeneratorUtil.getHeight());
        student.setName(GeneratorUtil.getName());
        student.setPhone(GeneratorUtil.getTel());
        student.setSchoolId(GeneratorUtil.getSchStu());
        student.setStuNumber(GeneratorUtil.getStuNum());
        student.setSex(GeneratorUtil.getSex());
        student.setCreateTime(new Date());

        return student;
    }
}
