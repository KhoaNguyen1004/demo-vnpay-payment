package com.vnpay.controller;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.entity.UserAccount;
import com.vnpay.service.AccountService;
import com.vnpay.service.VnPayService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final VnPayService vnPayService;
    private AccountService accountService;

    @GetMapping("/{username}")
    public UserAccount getAccount(@PathVariable String username){
        return accountService.getAccount(username);
    }

    @GetMapping
    public List<UserAccount> getAllAccount(){
        List<UserAccount> accounts = accountService.getAllAccount();
        return accounts;
    }

    @PostMapping("/transaction")
    public UserAccount processTransaction(@RequestParam String username, @RequestParam long amount){
        return accountService.processTransaction(username, amount);
    }

}
