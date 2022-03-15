package com.miniblog.poc.service.repository;

import com.miniblog.poc.service.entity.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {
    Optional<ApiKeyEntity> findByKey(String key);
}
