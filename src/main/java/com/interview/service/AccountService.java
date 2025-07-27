package com.interview.service;

import com.interview.model.Account;
import com.interview.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("db")
public class AccountService {

@Autowired
private AccountRepository accountRepository;

@Autowired
private AuditService auditService;

    @Transactional
    public void transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + fromAccountNumber));
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + toAccountNumber));

        if (amount.compareTo(new BigDecimal("1000")) > 0) {
            auditService.logTransaction(fromAccountNumber, toAccountNumber, amount, "FAILED");
            throw new RuntimeException("Transfer limit exceeded");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        try {
            auditService.logTransaction(fromAccountNumber, toAccountNumber, amount, "SUCCESS");
        } catch (Exception e) {
            // Ignore audit log failures for successful transactions
        }
    }
}

