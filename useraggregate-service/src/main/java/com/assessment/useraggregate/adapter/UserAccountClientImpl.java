package com.assessment.useraggregate.adapter;

import com.assessment.useraggregate.dto.JSendDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserAccountClientImpl implements UserAccountClient {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<UserModel> getUsers() {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9000/users",JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
        Object object = jSendDto.getData().get("Users");
        List<UserModel> userModels = objectMapper.convertValue(object, new TypeReference<List<UserModel>>() {
        });
        return userModels;
    }

    @Override
    public List<AccountModel> getAccountsById(long userId) {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9002/accounts?userId="+userId,JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
        Object object = jSendDto.getData().get("Accounts");
        List<AccountModel> accountModels = objectMapper.convertValue(object, new TypeReference<List<AccountModel>>() {
        });
        return accountModels;
    }

    @Override
    public List<UserAndAccountModel> getUserAndAccounts(){
        List<UserAndAccountModel> userAndAccountModels = new ArrayList<>();
        getUsers().forEach(userModel -> {
            List<AccountModel> accountModels = getAccountsById(userModel.getId());
            userAndAccountModels.add(UserAndAccountMapper.userAndAccountMapper(userModel,accountModels));
        });
        return userAndAccountModels;
    }

    @Override
    public UserAndAccountModel saveUserAndAccount(UserAndAccountModel userAndAccountModel) {

        UserModel userModel = UserAndAccountMapper.toUserModel(userAndAccountModel);
        List<AccountModel> accountModels = UserAndAccountMapper.toAccountModel(userAndAccountModel);

        //post user model
        ResponseEntity<JSendDto> userModelResponseEntity =
                restTemplate.postForEntity("http://localhost:9000/users",userModel,JSendDto.class);
        Object object = userModelResponseEntity.getBody().getData().get("Saved User");
        UserModel savedUserModel = objectMapper.convertValue(object, new TypeReference<UserModel>() {});
        HttpStatus httpStatus = userModelResponseEntity.getStatusCode();
        //post account model
        //------- to do

        return null;
    }
}
