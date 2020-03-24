package com.assessment.account;

import com.assessment.account.domain.Account;
import com.assessment.account.dto.JSendDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = AccountServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGet(){
       ResponseEntity<JSendDto> jSendDtoResponseEntity =
               testRestTemplate.getForEntity("http://localhost:"+port+"/accounts?userId=1", JSendDto.class);

       Object object = jSendDtoResponseEntity.getBody().getData().get("Accounts");
       List<Account> accountList = objectMapper.convertValue(object, new TypeReference<List<Account>>() {});

        Assertions.assertTrue(accountList.size()>0 && jSendDtoResponseEntity.getStatusCode()== HttpStatus.OK);
    }

    @Test
    public void testPost(){
        Account account = new Account(20,"Checking");
        account.setBalance(new BigDecimal(2222.45));
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.postForEntity("http://localhost:"+port+"/accounts/1",accountList,JSendDto.class);
        Assertions.assertTrue(jSendDtoResponseEntity.getStatusCode()==HttpStatus.CREATED);
    }


}
