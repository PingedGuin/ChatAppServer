package com.app.user.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.member.service.MemberService;
import com.app.user.data.dto.UserPrincipal;
import com.app.user.data.dto.UserInfo;
import com.app.user.data.dto.UserDto;
import com.app.user.data.dto.mapper.UserMapper;
import com.app.user.data.entity.UserEntity;
import com.app.user.repository.UserInfoRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private final UserInfoRepository userInfoRepository;
    private final MemberService memberService;
    @Getter
    private final UserMapper mapper;

    private final Cache<Long, UserDto> userCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<Long, UserEntity> entityCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public UserService(UserInfoRepository userInfoRepository, MemberService memberService, UserMapper mapper) {
        this.userInfoRepository = userInfoRepository;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    public UserInfo getUserInfo(String email) {
        UserEntity entity = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return entityToDto(entity);
    }

    private UserInfo entityToDto(UserEntity entity) {
        return new UserInfo(entity.getUsername(), entity.getEmail());
    }

    private UserDto entityToPrincipalDto(UserEntity userEntity) {
        return new UserDto(userEntity);
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

    public UserEntity getUserEntityById(Long userId) {
        UserEntity cachedUser = entityCache.getIfPresent(userId);
        if (cachedUser != null) {
            return cachedUser;
        }

        UserEntity user = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        entityCache.put(userId, user);

        return user;
    }
}
