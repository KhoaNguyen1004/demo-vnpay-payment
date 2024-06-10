package com.vnpay.repository;

import com.vnpay.entity.TransactionHitory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionHitory, Long> {
}
