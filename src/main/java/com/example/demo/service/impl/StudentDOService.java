package com.example.demo.service.impl;

import com.example.demo.entity.localtest.StudentDO;
import com.example.demo.mapper.StudentDOMapper;
import com.example.demo.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentDOService implements com.example.demo.service.StudentDOService {

    @Autowired
    private StudentDOMapper studentDOMapper;

    @Override
    public void insertStudentBatch(String stuPrefix) {
        List<StudentDO> list = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            StudentDO studentDO = new StudentDO();
            studentDO.setName(GeneratorUtil.getName());
            studentDO.setGrade(GeneratorUtil.getGradeOrClazz());
            studentDO.setAddress(GeneratorUtil.getRoad());
            studentDO.setPhone(GeneratorUtil.getTel());
            studentDO.setSex(GeneratorUtil.getSex());
            studentDO.setEmail(GeneratorUtil.getEmail(10, 20));
            studentDO.setClazz(GeneratorUtil.getGradeOrClazz());
            studentDO.setStuNumber(GeneratorUtil.getStuNumber(stuPrefix));
            list.add(studentDO);
        }
        studentDOMapper.insertStudentBatch(list);
    }
}
