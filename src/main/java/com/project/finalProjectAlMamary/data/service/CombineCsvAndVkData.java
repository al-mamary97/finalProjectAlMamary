package com.project.finalProjectAlMamary.data.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import com.project.finalProjectAlMamary.data.parser.model.StudentDto;
import com.project.finalProjectAlMamary.data.service.vk.VkMemberInfo;

public class CombineCsvAndVkData
{
    public static void combine(List<StudentDto> studentsDto, List<VkMemberInfo> vkMembers)
    {
        Map<String, List<StudentDto>> studentsMapByFullName = studentsDto.stream()
                .collect(groupingBy(StudentDto::getFullName));

        vkMembers.forEach(vkMemberInfo ->
        {
            String firstNameWithLastNameKey = vkMemberInfo.firstName() + " " + vkMemberInfo.lastName();
            if (studentsMapByFullName.containsKey(firstNameWithLastNameKey))
            {
                studentsMapByFullName.get(firstNameWithLastNameKey)
                        .forEach(studentDto -> studentDto
                                .setAge(vkMemberInfo.ageOptional().orElse(-1))
                                .setCityTitle(vkMemberInfo.cityTitleOptional().orElse("")));
                return;
            }

            studentsMapByFullName.computeIfPresent(
                    vkMemberInfo.lastName() + " " + vkMemberInfo.firstName(),
                    (key, oldValue) ->
                    {
                        oldValue.forEach(studentDto -> studentDto.setAge(vkMemberInfo.ageOptional().orElse(-1))
                                .setCityTitle(vkMemberInfo.cityTitleOptional().orElse("")));

                        return oldValue;
                    }
            );
        });
    }
}
