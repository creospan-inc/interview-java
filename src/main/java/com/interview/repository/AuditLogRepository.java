package com.interview.repository;

import com.interview.model.AuditLog;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("db")
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
