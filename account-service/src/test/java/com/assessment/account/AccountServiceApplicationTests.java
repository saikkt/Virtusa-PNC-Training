package com.assessment.account;

import com.assessment.account.domain.Account;
import com.assessment.account.repository.AccountRepository;
import com.assessment.account.service.AccountService;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountServiceApplicationTests {

	@Mock
	private AccountRepository accountRepository;
	@InjectMocks
	private AccountService accountService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindByUserId(){
		List<Account> accountList = new ArrayList<>();
		accountList.add(new Account(1L,1111,"Checking"));

		Mockito.when(accountRepository.findByUserId(0)).thenReturn(accountList);
		Assertions.assertEquals(accountList,accountService.findByUserId(0));
	}

	@Test
	public void testSaveAll(){
		List<Account> accountList = new ArrayList<>();
		accountList.add(new Account(1L,11111,"Checking"));
		accountList.add(new Account(2L,22222,"Savings"));

		Mockito.when(accountRepository.saveAll(accountList)).thenReturn(accountList);
		Assertions.assertEquals(accountList,accountService.saveAll(accountList));
		Mockito.verify(accountRepository);
	}
}
