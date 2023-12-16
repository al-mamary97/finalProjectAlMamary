package com.project.finalProjectAlMamary.data.service.vk;

import java.util.Optional;

public record VkMemberInfo(
        String firstName,
        String lastName,
        Optional<String> cityTitleOptional,
        Optional<Integer> ageOptional)
{
}
