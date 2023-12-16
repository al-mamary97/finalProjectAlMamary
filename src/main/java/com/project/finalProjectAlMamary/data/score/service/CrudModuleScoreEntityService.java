package com.project.finalProjectAlMamary.data.score.service;

import com.project.finalProjectAlMamary.data.score.entity.ModuleScoreEntity;
import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

public interface CrudModuleScoreEntityService
{
    ModuleScoreEntity save(ModuleScoreEntity entity);

    ModuleScoreEntity findByStudentAndModuleTitle(StudentEntity studentEntity, String moduleTitle);
}
