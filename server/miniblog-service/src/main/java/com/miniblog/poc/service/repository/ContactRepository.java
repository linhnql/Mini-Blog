package com.miniblog.poc.service.repository;

import com.miniblog.poc.service.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, String> {
    List<ContactEntity> findAll();
}
