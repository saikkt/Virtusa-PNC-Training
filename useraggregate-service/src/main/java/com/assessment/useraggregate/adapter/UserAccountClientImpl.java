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
import java.util.Objects;


@Component
public class UserAccountClientImpl implements UserAccountClient {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserModel getUserById(long userId) {
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                restTemplate.getForEntity("http://localhost:9000/users/"+userId,JSendDto.class);
        Object object = null;
        if(jSendDtoResponseEntity.getBody()!=null){
            object = jSendDtoResponseEntity.getBody().getData().get("User");
        }

        return objectMapper.convertValue(object, new TypeReference<UserModel>() {});
    }
    @Override
    public List<UserModel> getUsers() {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9000/users",JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
        Object object = null;
        if (jSendDto != null) {
            object = jSendDto.getData().get("Users");
        }
        return objectMapper.convertValue(object, new TypeReference<List<UserModel>>() {
        });
    }

    @Override
    public List<AccountModel> getAccountsById(long userId) {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9002/accounts?userId="+userId,JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
        Object object = null;
        if (jSendDto != null) {
            object = jSendDto.getData().get("Accounts");
        }
        return objectMapper.convertValue(object, new TypeReference<List<AccountModel>>() {
        });
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
    public UserAndAccountModel getUserAndAccountsById(long userId) {
        UserAndAccountModel userAndAccountModel = new UserAndAccountModel();
        UserModel userModel = getUserById(userId);
        List<AccountModel> accountModels = getAccountsById(userId);
        userAndAccountModel.setUserModel(userModel);
        userAndAccountModel.setAccountModels(accountModels);
        return userAndAccountModel;
    }

    @Override
    public UserAndAccountModel saveUserAndAccount(UserAndAccountModel userAndAccountModel) {

        UserModel userModel = UserAndAccountMapper.toUserModel(userAndAccountModel);
        List<AccountModel> accountModels = UserAndAccountMapper.toAccountModel(userAndAccountModel);

        //Save user model
        ResponseEntity<JSendDto> userModelResponseEntity =
                restTemplate.postForEntity("http://localhost:9000/users",userModel,JSendDto.class);
        Object userObject = Objects.requireNonNull(userModelResponseEntity.getBody()).getData().get("Saved User");
        UserModel savedUserModel = objectMapper.convertValue(userObject, new TypeReference<UserModel>() {});
        HttpStatus userModelResponseEntityStatusCode = userModelResponseEntity.getStatusCode();

        //Save account model
        ResponseEntity<JSendDto> accountModelResponseEntity =
                restTemplate.postForEntity("http://localhost:9002/accounts/"+savedUserModel.getId(),accountModels,JSendDto.class);
        Object accountObject = Objects.requireNonNull(accountModelResponseEntity.getBody()).getData().get("Saved Accounts");
        List<AccountModel> savedAccountModels = objectMapper.convertValue(accountObject, new TypeReference<List<AccountModel>>() {});
        HttpStatus accountModelResponseEntityStatusCode = accountModelResponseEntity.getStatusCode();

        if(userModelResponseEntityStatusCode==HttpStatus.CREATED &&
            accountModelResponseEntityStatusCode==HttpStatus.CREATED){
            UserAndAccountModel savedUserAndAccountModel = new UserAndAccountModel();
            savedUserAndAccountModel.setUserModel(savedUserModel);
            savedUserAndAccountModel.setAccountModels(savedAccountModels);
            return savedUserAndAccountModel;
        }

        return null;
    }


}
