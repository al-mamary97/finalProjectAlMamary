package com.project.finalProjectAlMamary.data.configuration.vk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

@Configuration
public class VkConfiguration
{
    private static final String VK_API_CLIENT_VERSION= "5.81";

    private final String vkCode;
    private final Long appId;

    public VkConfiguration(
            @Value("${vk.config.data.code}") String vkCode,
            @Value("${vk.config.data.app.id}") Long appId)
    {
        this.vkCode = vkCode;
        this.appId = appId;
    }

    @Bean
    public VkApiClient getVkApiClient()
    {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vkApiClient = new VkApiClient(transportClient);
        vkApiClient.setVersion(VK_API_CLIENT_VERSION);

        return vkApiClient;
    }

    @Bean
    public UserActor getVkUserActor()
    {
        return new UserActor(appId, vkCode);
    }
}
