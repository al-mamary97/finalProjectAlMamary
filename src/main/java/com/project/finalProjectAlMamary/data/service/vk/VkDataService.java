package com.project.finalProjectAlMamary.data.service.vk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GetMembersSort;
import com.vk.api.sdk.objects.users.Fields;

@Service
public class VkDataService
{
    private final VkApiClient vkApiClient;
    private final UserActor userActor;

    @Autowired
    public VkDataService(VkApiClient vkApiClient, UserActor userActor)
    {
        this.vkApiClient = vkApiClient;
        this.userActor = userActor;
    }

    public List<VkMemberInfo> getGroupMembers(String groupId)
    {
        List<VkMemberInfo> vkMembers = new ArrayList<>();
        int offset = 1;
        while (true)
        {
            String json = getGroupMembersJson(groupId, offset++);

            try
            {
                ArrayNode membersJsonArrayNode = (ArrayNode)new ObjectMapper()
                        .readTree(json)
                        .get("response")
                        .get("items");

                if (membersJsonArrayNode.size() == 0)
                {
                    break;
                }

                for (int i = 0; i < membersJsonArrayNode.size(); i++)
                {
                    vkMembers.add(getVkMemberInfo(membersJsonArrayNode.get(i)));
                }
            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
        }

        return vkMembers;
    }

    private String getGroupMembersJson(String groupId, Integer offset)
    {
        try
        {
            return vkApiClient.groups().getMembers(userActor)
                    .groupId(groupId)
                    .sort(GetMembersSort.ID_ASC)
                    .count(1000)
                    .offset(offset * 1000 - 1000)
                    .fields(Fields.BDATE, Fields.CITY)
                    .executeAsString();
        }
        catch (ClientException e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private VkMemberInfo getVkMemberInfo(JsonNode jsonNode)
    {
        return new VkMemberInfo(
                jsonNode.get("first_name").asText(),
                jsonNode.get("last_name").asText(),
                jsonNode.has("city")
                        ? Optional.of(jsonNode.get("city").get("title").asText())
                        : Optional.empty(),
                getAge(jsonNode)
        );
    }

    private Optional<Integer> getAge(JsonNode jsonNode)
    {
        if (!jsonNode.has("bdate"))
        {
            return Optional.empty();
        }

        String bDateString = jsonNode.get("bdate").asText();

        if (bDateString.length() != 10)
        {
            return Optional.empty();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date bDate = null;
        try
        {
            bDate = simpleDateFormat.parse(bDateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
        Calendar calendarBDate = Calendar.getInstance();
        calendarBDate.setTime(bDate);

        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < calendarBDate.get(Calendar.DAY_OF_YEAR)) {
            return Optional.of(Calendar.getInstance().get(Calendar.YEAR) - calendarBDate.get(Calendar.YEAR) - 1);
        }

        return Optional.of(Calendar.getInstance().get(Calendar.YEAR) - calendarBDate.get(Calendar.YEAR));
    }
}
