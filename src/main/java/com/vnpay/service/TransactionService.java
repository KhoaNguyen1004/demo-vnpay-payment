package com.vnpay.service;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public List<TransactionHitory> getAllTransactions() {
        List<TransactionHitory> transactionHitories = transactionRepository.findAll();
        return transactionHitories;
    }

    public TransactionHitory getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
}
