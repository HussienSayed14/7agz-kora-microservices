package com.accountmicroservice.repositories;

import com.accountmicroservice.entities.LoginAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}
