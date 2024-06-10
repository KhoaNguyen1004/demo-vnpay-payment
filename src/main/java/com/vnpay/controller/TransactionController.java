package com.vnpay.controller;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.service.TransactionService;
import com.vnpay.service.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final VnPayService vnPayService;
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionHitory> getAllTransactions() {
        List<TransactionHitory> transactions = transactionService.getAllTransactions();
        return transactions;
    }

    @GetMapping("/{id}")
    public TransactionHitory getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }
}
