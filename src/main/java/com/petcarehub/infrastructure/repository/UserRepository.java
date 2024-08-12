package com.petcarehub.infrastructure.repository;

import com.petcarehub.core.domain.User;
import com.petcarehub.core.domain.ArchivedUser;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
  boolean existsByEmail(@Param("email") String email);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findByEmail(@Param("email") String email);

  @Modifying
  @Transactional
  default void archiveUser(UUID userId, ArchivedUserRepository archivedUserRepository) {
    findById(userId).ifPresent(user -> {
      ArchivedUser archivedUser = ArchivedUser.builder()
          .id(user.getUserId())
          .email(user.getEmail())
          .password(user.getPassword())
          .role(user.getRole())
          .firstName(user.getFirstName())
          .lastName(user.getLastName())
          .organizationName(user.getOrganizationName())
          .address(user.getAddress())
          .phoneNumber(user.getPhoneNumber())
          .createdAt(user.getCreatedAt())
          .updatedAt(user.getUpdatedAt())
          .deletedAt(LocalDateTime.now())
          .build();

      archivedUserRepository.save(archivedUser);
      delete(user);
    });
  }
}
