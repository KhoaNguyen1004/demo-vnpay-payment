package com.vnpay.service;

import com.vnpay.entity.TransactionHitory;
import com.vnpay.entity.UserAccount;
import com.vnpay.repository.TransactionRepository;
import com.vnpay.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private UserAccountRepository userAccountRepository;
    private TransactionRepository transactionRepository;

    @Transactional
    public UserAccount processTransaction(String username, long ammount){
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if(userAccount == null){
            throw new RuntimeException("User not found");
        }
        userAccount.setBalance(userAccount.getBalance() + ammount);
        return userAccountRepository.save(userAccount);
    }

    public UserAccount getAccount(String username){
        return userAccountRepository.findByUsername(username);
    }

    public List<UserAccount> getAllAccount(){
        List<UserAccount> accounts =userAccountRepository.findAll();
        return accounts;
    }

//    public TransactionHitory createTransaction(Long accountId, Long amount){
//        UserAccount userAccount = userAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Update account balance
//        userAccount.setBalance(userAccount.getBalance() + amount);
//        userAccountRepository.save(userAccount);
//
//        // Create transaction
//        TransactionHitory transactionHitory = new TransactionHitory();
//        transactionHitory.setUserAccount(userAccount);
//        transactionHitory.setAmount(amount);
//        transactionHitory.setDate(new Date());
//        transactionHitory.setStatus("SUCCESS");
//
//        return transactionRepository.save(transactionHitory);
//    }
}
