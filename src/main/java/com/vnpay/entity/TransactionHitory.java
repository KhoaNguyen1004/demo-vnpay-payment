package com.vnpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private UserAccount userAccount;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status;
}
