package com.interview.service;

import com.interview.model.AuditLog;
import com.interview.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("db")
public class AuditService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional
    public void logTransaction(String fromAccount, String toAccount, BigDecimal amount, String status) {
        auditLogRepository.save(new AuditLog(fromAccount, toAccount, amount, status));
    }

    public List<AuditLog> getAuditLog() {
        return auditLogRepository.findAll();
    }

    public void clearAuditLog() {
        auditLogRepository.deleteAll();
    }
}
