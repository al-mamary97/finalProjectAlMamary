package com.project.finalProjectAlMamary.data.parser.model;

public class ModuleScoreDto
{
    private String title;

    private Integer activityPoints;

    private Integer exercisePoints;

    private Integer homeworkPoints;

    private Integer seminarPoints;

    private Integer sumPoints;

    public String getTitle()
    {
        return title;
    }

    public ModuleScoreDto setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public Integer getActivityPoints()
    {
        return activityPoints;
    }

    public ModuleScoreDto setActivityPoints(Integer activityPoints)
    {
        this.activityPoints = activityPoints;
        return this;
    }

    public Integer getExercisePoints()
    {
        return exercisePoints;
    }

    public ModuleScoreDto setExercisePoints(Integer exercisePoints)
    {
        this.exercisePoints = exercisePoints;
        return this;
    }

    public Integer getHomeworkPoints()
    {
        return homeworkPoints;
    }

    public ModuleScoreDto setHomeworkPoints(Integer homeworkPoints)
    {
        this.homeworkPoints = homeworkPoints;
        return this;
    }

    public Integer getSeminarPoints()
    {
        return seminarPoints;
    }

    public ModuleScoreDto setSeminarPoints(Integer seminarPoints)
    {
        this.seminarPoints = seminarPoints;
        return this;
    }

    public Integer getSumPoints()
    {
        return sumPoints;
    }

    public ModuleScoreDto setSumPoints(Integer sumPoints)
    {
        this.sumPoints = sumPoints;
        return this;
    }

    @Override
    public String toString()
    {
        return "ModuleScoreDto{" +
                "title='" + title + '\'' +
                ", activityPoints=" + activityPoints +
                ", exercisePoints=" + exercisePoints +
                ", homeworkPoints=" + homeworkPoints +
                ", seminarPoints=" + seminarPoints +
                ", sumPoints=" + sumPoints +
                '}';
    }
}
