package com.petcarehub.infrastructure.repository;

import com.petcarehub.core.domain.ArchivedUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedUserRepository extends JpaRepository<ArchivedUser, UUID> {
}