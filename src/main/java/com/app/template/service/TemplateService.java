package com.app.template.service;

import com.app.channel.service.ChannelService;
import com.app.template.data.entity.GuildTemplateEntity;
import com.app.template.data.entity.TemplateChannelEntity;
import com.app.template.repository.GuildTemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private final GuildTemplateRepository templateRepository;
    private final ChannelService channelService;

    public TemplateService(
            GuildTemplateRepository templateRepository,
            ChannelService channelService
    ) {
        this.templateRepository = templateRepository;
        this.channelService = channelService;
    }

    public void applyTemplate(Long templateId, Long guildId) {

        GuildTemplateEntity template = templateRepository.findById(templateId)
                .orElseThrow();

        for (TemplateChannelEntity channel : template.getChannels()) {

            channelService.createChannel(
                    guildId,
                    channel.getName(),
                    channel.getType(),
                    channel.getPosition()
            );
        }
    }
}
