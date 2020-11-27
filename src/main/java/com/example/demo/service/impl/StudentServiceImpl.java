package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.abstracts.Observer;
import com.example.demo.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentServiceImpl extends Observer<Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void insertBatch() {
        Student student = new Student();
        student.setAddress(GeneratorUtil.getRoad());
        student.setAge(GeneratorUtil.getAge());
        student.setHeight(GeneratorUtil.getHeight());
        student.setWeight(GeneratorUtil.getHeight());
        student.setName(GeneratorUtil.getName());
        student.setPhone(GeneratorUtil.getTel());
        student.setSchoolId(GeneratorUtil.getSchStu());
        student.setStuNumber(GeneratorUtil.getStuNum());
        student.setSex(GeneratorUtil.getSex());
        student.setCreateTime(new Date());
        studentMapper.insert(student);
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
