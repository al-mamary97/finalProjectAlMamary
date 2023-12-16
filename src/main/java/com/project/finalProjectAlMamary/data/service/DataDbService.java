package com.project.finalProjectAlMamary.data.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.finalProjectAlMamary.data.parser.model.ModuleScoreDto;
import com.project.finalProjectAlMamary.data.parser.model.StudentDto;
import com.project.finalProjectAlMamary.data.score.entity.ModuleScoreEntity;
import com.project.finalProjectAlMamary.data.score.service.CrudModuleScoreEntityService;
import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;
import com.project.finalProjectAlMamary.data.student.service.CrudStudentEntityService;

@Service
public class DataDbService
{
    private final CrudStudentEntityService crudStudentEntityService;
    private final CrudModuleScoreEntityService crudModuleScoreEntityService;

    @Autowired
    public DataDbService(
            CrudStudentEntityService crudStudentEntityService,
            CrudModuleScoreEntityService crudModuleScoreEntityService)
    {
        this.crudStudentEntityService = crudStudentEntityService;
        this.crudModuleScoreEntityService = crudModuleScoreEntityService;
    }

    public Set<Integer> getAges()
    {
        return crudStudentEntityService.getAll().stream()
                .map(StudentEntity::getAge)
                .filter(age -> age != -1).collect(Collectors.toSet());
    }

    public Set<String> getCities()
    {
        return crudStudentEntityService.getAll().stream()
                .map(StudentEntity::getCityTitle)
                .filter(cityTitle -> !cityTitle.equals("")).collect(Collectors.toSet());
    }

    public Set<String> getGroups()
    {
        return crudStudentEntityService.getAll().stream().map(StudentEntity::getGroupTitle).collect(Collectors.toSet());
    }

    public List<StudentEntity> getStudentsByGroup(String groupTitle)
    {
        return crudStudentEntityService.getByGroupTitle(groupTitle);
    }

    public List<StudentEntity> getStudentsByAge(Integer age)
    {
        return crudStudentEntityService.getByAge(age);
    }

    public List<StudentEntity> getStudentsByCity(String cityTitle)
    {
        return crudStudentEntityService.getByCityTitle(cityTitle);
    }

    public ModuleScoreEntity findByStudentAndModuleTitle(StudentEntity studentEntity, String moduleTitle)
    {
        return crudModuleScoreEntityService.findByStudentAndModuleTitle(studentEntity, moduleTitle);

    }

    public void save(StudentDto studentDto)
    {
        StudentEntity studentEntity = crudStudentEntityService.save(createStudentEntityObject(studentDto));
        studentDto.getModuleScoreDtos().stream()
                .map(moduleScoreDto -> createModuleScoreEntity(moduleScoreDto, studentEntity))
                .forEach(crudModuleScoreEntityService::save);
    }

    private StudentEntity createStudentEntityObject(StudentDto dto)
    {
        StudentEntity entity = new StudentEntity();
        entity.setFullName(dto.getFullName());
        entity.setGroupTitle(dto.getGroupTitle());
        entity.setCityTitle(dto.getCityTitle());
        entity.setAge(dto.getAge());

        return entity;
    }

    private ModuleScoreEntity createModuleScoreEntity(ModuleScoreDto dto, StudentEntity studentEntity)
    {
        ModuleScoreEntity entity = new ModuleScoreEntity();
        entity.setTitle(dto.getTitle());
        entity.setActivityPoints(dto.getActivityPoints());
        entity.setExercisePoints(dto.getExercisePoints());
        entity.setHomeworkPoints(dto.getHomeworkPoints());
        entity.setSeminarPoints(dto.getSeminarPoints());
        entity.setSumPoints(dto.getSumPoints());
        entity.setStudent(studentEntity);

        return entity;
    }
}
