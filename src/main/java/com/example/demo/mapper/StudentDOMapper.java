package com.example.demo.mapper;

import com.example.demo.entity.localtest.StudentDO;

import java.util.List;

public interface StudentDOMapper {

    void insertStudentBatch(List<StudentDO> list);
}
