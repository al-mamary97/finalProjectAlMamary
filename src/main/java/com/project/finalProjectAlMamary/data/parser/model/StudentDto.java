package com.project.finalProjectAlMamary.data.parser.model;

import java.util.List;

public class StudentDto
{
    private String fullName;

    private String groupTitle;

    private List<ModuleScoreDto> moduleScoreDtos;

    private Integer age;

    private String cityTitle;

    public String getFullName()
    {
        return fullName;
    }

    public StudentDto setFullName(String fullName)
    {
        this.fullName = fullName;
        return this;
    }

    public String getGroupTitle()
    {
        return groupTitle;
    }

    public StudentDto setGroupTitle(String groupTitle)
    {
        this.groupTitle = groupTitle;
        return this;
    }

    public List<ModuleScoreDto> getModuleScoreDtos()
    {
        return moduleScoreDtos;
    }

    public StudentDto setModuleScoreDtos(List<ModuleScoreDto> moduleScoreDtos)
    {
        this.moduleScoreDtos = moduleScoreDtos;
        return this;
    }

    public Integer getAge()
    {
        return age;
    }

    public StudentDto setAge(Integer age)
    {
        this.age = age;
        return this;
    }

    public String getCityTitle()
    {
        return cityTitle;
    }

    public StudentDto setCityTitle(String cityTitle)
    {
        this.cityTitle = cityTitle;
        return this;
    }

    @Override
    public String toString()
    {
        return "StudentDto{" +
                "fullName='" + fullName + '\'' +
                ", groupTitle='" + groupTitle + '\'' +
                ", moduleScoreDtos=" + moduleScoreDtos +
                '}';
    }
}
