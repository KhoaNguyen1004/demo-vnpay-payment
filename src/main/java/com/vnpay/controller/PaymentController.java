package com.vnpay.controller;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.entity.UserAccount;
import com.vnpay.service.AccountService;
import com.vnpay.service.TransactionService;
import com.vnpay.service.VnPayService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PaymentController {
    private VnPayService vnPayService;
    private AccountService accountService;
    private TransactionService transactionService;

    @GetMapping("/create-payment")
    public String createPayment(@RequestParam String orderId, @RequestParam long amount,
                                @RequestParam String orderInfo, @RequestParam String returnUrl,
                                @RequestParam String username, HttpSession httpSession) {
        try {
            // Create a transaction with UNDONE status
            TransactionHitory transactionHitory = new TransactionHitory();
            Double amountTransit = amount / 100.0;
            transactionHitory.setAmount(amountTransit);

            // Process the transaction
            UserAccount accountTransit = accountService.processTransaction(username, amount);
            return vnPayService.createPaymentUrl(orderId, amount, orderInfo, returnUrl);
        } catch (Exception e) {
            return "Error creating payment URL: " + e.getMessage();
        }
    }
}
