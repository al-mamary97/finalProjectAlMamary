package com.project.finalProjectAlMamary.data.score.entity;

import com.project.finalProjectAlMamary.data.student.entity.StudentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ModuleScoreEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer activityPoints;

    private Integer exercisePoints;

    private Integer homeworkPoints;

    private Integer seminarPoints;

    private Integer sumPoints;

    @ManyToOne
    private StudentEntity student;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Integer getActivityPoints()
    {
        return activityPoints;
    }

    public void setActivityPoints(Integer activityPoints)
    {
        this.activityPoints = activityPoints;
    }

    public Integer getExercisePoints()
    {
        return exercisePoints;
    }

    public void setExercisePoints(Integer exercisePoints)
    {
        this.exercisePoints = exercisePoints;
    }

    public Integer getHomeworkPoints()
    {
        return homeworkPoints;
    }

    public void setHomeworkPoints(Integer homeworkPoints)
    {
        this.homeworkPoints = homeworkPoints;
    }

    public Integer getSeminarPoints()
    {
        return seminarPoints;
    }

    public void setSeminarPoints(Integer seminarPoints)
    {
        this.seminarPoints = seminarPoints;
    }

    public Integer getSumPoints()
    {
        return sumPoints;
    }

    public void setSumPoints(Integer sumPoints)
    {
        this.sumPoints = sumPoints;
    }

    public StudentEntity getStudent()
    {
        return student;
    }

    public void setStudent(StudentEntity student)
    {
        this.student = student;
    }
}
