package com.project.finalProjectAlMamary.data.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>
{
    List<StudentEntity> findAllByGroupTitle(String groupTitle);
    List<StudentEntity> findAllByAge(Integer age);
    List<StudentEntity> findAllByCityTitle(String cityTitle);
}
