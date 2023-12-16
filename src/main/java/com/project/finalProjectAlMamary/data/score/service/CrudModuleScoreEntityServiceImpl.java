package com.project.finalProjectAlMamary.data.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.finalProjectAlMamary.data.score.entity.ModuleScoreEntity;
import com.project.finalProjectAlMamary.data.score.repository.ModuleScoreRepository;
import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

@Service
public class CrudModuleScoreEntityServiceImpl implements CrudModuleScoreEntityService
{
    private final ModuleScoreRepository moduleScoreRepository;

    @Autowired
    public CrudModuleScoreEntityServiceImpl(
            ModuleScoreRepository moduleScoreRepository)
    {
        this.moduleScoreRepository = moduleScoreRepository;
    }

    @Override
    public ModuleScoreEntity save(ModuleScoreEntity entity)
    {
        return moduleScoreRepository.save(entity);
    }

    @Override
    public ModuleScoreEntity findByStudentAndModuleTitle(StudentEntity studentEntity, String moduleTitle)
    {
        return moduleScoreRepository.findByStudentIdAndTitle(studentEntity.getId(), moduleTitle);
    }
}
