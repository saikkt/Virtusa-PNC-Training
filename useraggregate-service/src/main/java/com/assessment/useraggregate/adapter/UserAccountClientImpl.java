package com.assessment.useraggregate.adapter;

import com.assessment.useraggregate.dto.JSendDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserAccountClientImpl implements UserAccountClient {

    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<UserModel> getUsers() {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9000/users",JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
//        List<Object> objectList = jSendDto.getData().values().stream().forEach(o -> {
//        });
        Object object = jSendDto.getData().get("Users");
        List<UserModel> userModels = (List<UserModel>) (List<?>) object;

        return userModels;
    }

    @Override
    public List<AccountModel> getUserAccounts(long userId) {
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9002/accounts?userId="+userId,JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
//        List<Object> objectList = jSendDto.getData().values().stream().
//                collect(Collectors.toList());
        Object object = jSendDto.getData().get("Accounts");
        List<AccountModel> accountModels = (List<AccountModel>) (List<?>) object;


        return accountModels;
    }

    @Override
    public HashMap<Object,Object> getUserAndAccounts(){
        HashMap<Object,Object> userModelAccountModelHashMap = new HashMap<>();
        List<UserModel> userModels = getUsers();
//        for(int i = 0; i<userModels.size();i++){
//           // UserModel userModel = userModels.get(i);
//           // Long id = userModels.get(i).getId();
//          //  List<AccountModel> accountModels = getUserAccounts(id);
//            //userModelAccountModelHashMap.put(userModels.get(i),getUserAccounts(userModels.get(i).getId()));
//        }
        userModelAccountModelHashMap.put(getUsers(),getUserAccounts(1));
//        userModels.stream().forEach(
//                userModel -> {
//                    List<AccountModel> accountModels = getUserAccounts(userModel.getId());
//                    userModelAccountModelHashMap.put(userModel,accountModels);
//                }
//        );
        return userModelAccountModelHashMap;
    }
}
