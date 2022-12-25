package com.example.springwebapp.repository;

import com.example.springwebapp.models.AuditedMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditedMethodRepository extends JpaRepository<AuditedMethod, Integer> {

}
