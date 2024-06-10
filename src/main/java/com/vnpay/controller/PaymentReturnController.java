package com.vnpay.controller;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.entity.UserAccount;
import com.vnpay.repository.TransactionRepository;
import com.vnpay.repository.UserAccountRepository;
import com.vnpay.service.VnPayService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@RestController
@AllArgsConstructor
public class PaymentReturnController {
    private TransactionRepository transactionRepository;
    private UserAccountRepository userAccountRepository;
    private VnPayService vnPayService;

    @RequestMapping("/payment-return")
    public String handlePaymentReturn(@RequestParam Map<String, String> allParams, HttpSession session) {
        try{
            // Get the money transited
            String amount = allParams.get("vnp_Amount");
            Double amountTransit = Double.parseDouble(amount) / 100;

            // Get the date of transaction
            String dateUrl = allParams.get("vnp_PayDate");
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date payDate = dateFormat.parse(dateUrl);
            // Convert into LocalDateTime
            Instant instant = payDate.toInstant();
            LocalDateTime payDateLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            String transactionStatus = allParams.get("vnp_TransactionStatus");
            String transactionID = allParams.get("vnp_TransactionID");

//            String username = session.getAttribute("username").toString();
//            System.out.println(username);

            // Create a transacion to save into the database
            TransactionHitory transactionHitory = new TransactionHitory();
            // Import the value for transaction
            transactionHitory.setDate(payDateLocalDateTime);
            transactionHitory.setAmount(amountTransit);
            if(transactionStatus.equals("00")){
                transactionHitory.setStatus("SUCCESS");
            } else {
                transactionHitory.setStatus("FAIL");
            }
            UserAccount userAccount = userAccountRepository.findByUsername("testuser");
            transactionHitory.setUserAccount(userAccount);
//            String username = allParams.get("vnp_Username");

            transactionRepository.save(transactionHitory);
            // Implement verification of return data here
            return "Payment return data: " + allParams.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
