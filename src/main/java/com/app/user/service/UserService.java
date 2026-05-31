package com.app.user.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.user.data.dto.UserInfo;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;

    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    //todo add cheche tho

    public UserInfo getUserInfo(String email) {
        UserInfoEntity entity = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return entityToDto(entity);
    }

    private UserInfo entityToDto(UserInfoEntity entity) {
        return new UserInfo(entity.getUsername(), entity.getEmail());
    }

    public List<GuildInfoDto> getUserGuilds(Long userId) {

        List<GuildEntity> guilds = userInfoRepository.findUserGuilds(userId);

        return guilds.stream()
                .map(g -> new GuildInfoDto(
                        g.getId(),
                        g.getGuildName(),
                        g.getDescription(),
                        g.getGuildIcon(),
                        g.getGuildBanner(),
                        g.getOwnerId(),
                        g.getMemberCount(),
                        g.getChannelCount()
                ))
                .toList();
    }

}
