package com.interview.challenge5;

import com.interview.model.Account;
import com.interview.repository.AccountRepository;
import com.interview.service.AccountService;
import com.interview.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Challenge 5: Transaction Management
 *
 * Audit logs should be saved, even when exceptions are thrown.
 */
@SpringBootTest
class TransactionManagementTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private AuditService auditService;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
        auditService.clearAuditLog();
        accountRepository.save(new Account("ACC123", new BigDecimal("1000"), "John Doe"));
        accountRepository.save(new Account("ACC456", new BigDecimal("2000"), "Jane Doe"));
    }

    @Test
    void testSuccessfulTransfer() {
        accountService.transferFunds("ACC123", "ACC456", new BigDecimal("500"));

        Account fromAccount = accountRepository.findByAccountNumber("ACC123").orElseThrow();
        Account toAccount = accountRepository.findByAccountNumber("ACC456").orElseThrow();

        assertEquals(new BigDecimal("500.00"), fromAccount.getBalance());
        assertEquals(new BigDecimal("2500.00"), toAccount.getBalance());
    }

    @Test
    void testTransferLimitExceeded() {
        assertThrows(RuntimeException.class, () ->
                accountService.transferFunds("ACC123", "ACC456", new BigDecimal("1500"))
        );

        Account fromAccount = accountRepository.findByAccountNumber("ACC123").orElseThrow();
        Account toAccount = accountRepository.findByAccountNumber("ACC456").orElseThrow();

        // Ensures rollback
        assertEquals(new BigDecimal("1000.00"), fromAccount.getBalance());
        assertEquals(new BigDecimal("2000.00"), toAccount.getBalance());
    }
    
    @Test
    void testAuditLogForSuccessfulTransaction() {
        accountService.transferFunds("ACC123", "ACC456", new BigDecimal("500"));
        
        // Should have audit log entry for successful transaction
        assertEquals(1, auditService.getAuditLog().size());
        assertTrue(auditService.getAuditLog().get(0).getStatus().contains("SUCCESS"));
    }
    
    @Test
    void testNestedTransactionRollbackBehavior() {
        assertThrows(RuntimeException.class, () -> {
            accountService.transferFunds("ACC123", "ACC456", new BigDecimal("1500"));
        });
        
        assertEquals(1, auditService.getAuditLog().size(),
            "Expected audit log to contain failed transaction entry");
        assertTrue(auditService.getAuditLog().get(0).getStatus().contains("FAILED"),
            "Expected audit entry to indicate FAILED status");
    }
}
