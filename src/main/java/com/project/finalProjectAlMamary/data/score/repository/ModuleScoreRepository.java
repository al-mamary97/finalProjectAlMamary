package com.project.finalProjectAlMamary.data.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.finalProjectAlMamary.data.score.entity.ModuleScoreEntity;

@Repository
public interface ModuleScoreRepository extends JpaRepository<ModuleScoreEntity, Long>
{
    ModuleScoreEntity findByStudentIdAndTitle(Long studentId, String title);
}
