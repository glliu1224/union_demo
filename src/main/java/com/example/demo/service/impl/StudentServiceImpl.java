package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.abstracts.Observer;
import com.example.demo.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class StudentServiceImpl extends Observer<Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    Executor executor = Executors.newFixedThreadPool(4);

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
    public void insertStudentBatch(String stuNumberPrefix) {
        for (int j = 0; j < 4; j++) {
            List<Student> list = new LinkedList<>();
            for (int i = 0; i < 10000; i++) {
                Student student = new Student();
                student.setName(GeneratorUtil.getName());
                student.setHeight(GeneratorUtil.getHeight());
                student.setWeight(GeneratorUtil.getHeight());
                student.setAge(GeneratorUtil.getAge());
                student.setAddress(GeneratorUtil.getRoad());
                student.setSchoolId(GeneratorUtil.getNum(0, 10));
                student.setPhone(GeneratorUtil.getTel());
                student.setStuNumber(GeneratorUtil.getStuNumber(stuNumberPrefix));
                student.setSex(GeneratorUtil.getSex());
                list.add(student);
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    studentMapper.insertBatch(list);
                }
            });
        }
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
