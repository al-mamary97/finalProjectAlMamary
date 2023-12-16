package com.project.finalProjectAlMamary.data.student.service;

import java.util.List;

import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

public interface CrudStudentEntityService
{
    List<StudentEntity> getByAge(Integer age);

    List<StudentEntity> getByCityTitle(String cityTitle);

    StudentEntity save(StudentEntity entity);

    List<StudentEntity> getAll();

    List<StudentEntity> getByGroupTitle(String groupTitle);
}
