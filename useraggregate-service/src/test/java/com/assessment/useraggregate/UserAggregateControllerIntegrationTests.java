package com.assessment.useraggregate;

import com.assessment.useraggregate.adapter.AccountModel;
import com.assessment.useraggregate.adapter.UserAccountClient;
import com.assessment.useraggregate.adapter.UserAndAccountModel;
import com.assessment.useraggregate.adapter.UserModel;
import com.assessment.useraggregate.dto.JSendDto;
import com.assessment.useraggregate.service.UserAggregateService;
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

@SpringBootTest(classes = UseraggregateServiceApplication.class,
                            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAggregateControllerIntegrationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAll(){
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.getForEntity("http://localhost:"+port+"/useraggregate", JSendDto.class);
       // Object object = jSendDtoResponseEntity.getBody().getData().get("User and Accounts");
       // UserAndAccountModel userAndAccountModel = objectMapper.convertValue(object, new TypeReference<UserAndAccountModel>() {});
        Assertions.assertTrue(jSendDtoResponseEntity.getStatusCode()== HttpStatus.OK);
    }

    @Test
    public void testSaveUserAndAccounts(){
        UserAndAccountModel userAndAccountModel = new UserAndAccountModel();
        UserModel userModel = new UserModel();
        userModel.setFirstName("fname");
        userModel.setLastName("lname");

        List<AccountModel> accountModels = new ArrayList<>();
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountNumber(9999);
        accountModel.setAccountType("checking");
        accountModel.setBalance(new BigDecimal(1111.34));
        accountModels.add(accountModel);

        userAndAccountModel.setUserModel(userModel);
        userAndAccountModel.setAccountModels(accountModels);
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.postForEntity("http://localhost:"+port+"/useraggregate",userAndAccountModel,JSendDto.class);

        Assertions.assertTrue(jSendDtoResponseEntity.getStatusCode()==HttpStatus.CREATED);
    }
}
