package com.app.role.repository;

import com.app.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  //  Optional<RoleEntity> findByGuildIdAndRoleId(Long guild_id, Long roleId);

   // Optional<RoleEntity> getRoleEntitesByUserId(String userId);
}
