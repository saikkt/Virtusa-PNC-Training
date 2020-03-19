package com.assessment.account.repository;

import com.assessment.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByUserId(long userId);
}
