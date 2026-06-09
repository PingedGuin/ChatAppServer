package com.app.user.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.user.data.dto.UserPrincipal;
import com.app.user.data.dto.UserInfo;
import com.app.user.data.dto.UserDto;
import com.app.user.data.dto.mapper.UserMapper;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;
    @Getter
    private final UserMapper mapper;

    private final Cache<Long, UserDto> userCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<Long, UserInfoEntity> entityCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public UserService(UserInfoRepository userInfoRepository, UserMapper mapper) {
        this.userInfoRepository = userInfoRepository;
        this.mapper = mapper;
    }

    public UserInfo getUserInfo(String email) {
        UserInfoEntity entity = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return entityToDto(entity);
    }

    private UserInfo entityToDto(UserInfoEntity entity) {
        return new UserInfo(entity.getUsername(), entity.getEmail());
    }

    private UserDto entityToPrincipalDto(UserInfoEntity userInfoEntity) {
        return new UserDto(userInfoEntity);
    }

    public List<GuildInfoDto> getUserGuilds(Long userId) {

        List<GuildEntity> guilds = userInfoRepository.findUserGuilds(userId);
        return guilds.stream()
                .map(g -> new GuildInfoDto(
                        g.getId(),
                        g.getName(),
                        g.getDescription(),
                        g.getGuildIcon(),
                        g.getGuildBanner(),
                        g.getOwner().getId(),
                        g.getMemberCount(),
                        g.getChannelCount()
                ))
                .toList();
    }

    public UserDto getUserById(Long userId) {
        UserDto cachedUser = userCache.getIfPresent(userId);

        if (cachedUser != null) {
            return cachedUser;
        }

        var userEntity = userInfoRepository.findById(userId);

        if (userEntity.isEmpty()) {
            return null;
        }

        UserDto user = entityToPrincipalDto(userEntity.get());
        userCache.put(userId, user);

        return user;
    }
    public UserPrincipal getUserPrincipalById(Long userId) {
        var userInfo = entityCache.getIfPresent(userId);

        if (userInfo == null) {
            userInfo = userInfoRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            entityCache.put(userId, userInfo);
        }

        return new UserPrincipal(
                userInfo.getId(),
                userInfo.getUsername(),
                userInfo.getEmail(),
                userInfo.isVerified(),
                userInfo.getRole()
        );
    }

    public UserInfoEntity getUserEntityById(Long userId) {
        UserInfoEntity cachedUser = entityCache.getIfPresent(userId);
        if (cachedUser != null) {
            return cachedUser;
        }

        UserInfoEntity user = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        entityCache.put(userId, user);

        return user;
    }
}
