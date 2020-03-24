package com.assessment.account.service;

import com.assessment.account.domain.Account;
import com.assessment.account.dto.JSendDto;
import com.assessment.account.dto.JSendStatus;
import com.assessment.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findByUserId(long userId){
        List<Account> accounts = accountRepository.findByUserId(userId);
        return accounts;
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public List<Account> saveAll(List<Account> accounts){
       return accountRepository.saveAll(accounts);
    }
}
