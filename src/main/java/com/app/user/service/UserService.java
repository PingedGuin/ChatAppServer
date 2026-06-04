package com.app.user.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.user.data.dto.UserInfo;
import com.app.user.data.dto.UserPrincipal;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;

    private final Cache<Long, UserPrincipal> userCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

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
    private UserPrincipal entityToPrincipalDto(UserInfoEntity userInfoEntity){
        return new UserPrincipal(userInfoEntity);
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

    public UserPrincipal getUserById(Long userId) {
        UserPrincipal cachedUser = userCache.getIfPresent(userId);

        if (cachedUser != null) {
            return cachedUser;
        }

        var userEntity = userInfoRepository.findById(userId);

        if (userEntity.isEmpty()) {
            return null;
        }

        UserPrincipal user = entityToPrincipalDto(userEntity.get());
        userCache.put(userId, user);

        return user;
    }

}
