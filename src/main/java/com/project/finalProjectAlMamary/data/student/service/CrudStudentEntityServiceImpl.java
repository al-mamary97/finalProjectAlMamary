package com.project.finalProjectAlMamary.data.student.service;

import java.util.List;

import com.project.finalProjectAlMamary.data.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;
import com.project.finalProjectAlMamary.data.student.service.CrudStudentEntityService;

@Service
public class CrudStudentEntityServiceImpl implements CrudStudentEntityService
{
    private final StudentRepository studentRepository;

    public CrudStudentEntityServiceImpl(
            StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentEntity> getByAge(Integer age)
    {
        return studentRepository.findAllByAge(age);
    }

    @Override
    public List<StudentEntity> getByCityTitle(String cityTitle)
    {
        return studentRepository.findAllByCityTitle(cityTitle);
    }

    @Override
    public StudentEntity save(StudentEntity entity)
    {
        return studentRepository.save(entity);
    }

    @Override
    public List<StudentEntity> getAll()
    {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentEntity> getByGroupTitle(String groupTitle)
    {
        return studentRepository.findAllByGroupTitle(groupTitle);
    }
}
