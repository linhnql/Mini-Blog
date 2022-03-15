package com.miniblog.poc.service.repository;

import com.miniblog.poc.service.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, String> {
    List<BlogEntity> findAll();
}
