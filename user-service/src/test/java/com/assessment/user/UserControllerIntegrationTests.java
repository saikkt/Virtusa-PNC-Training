package com.assessment.user;

import com.assessment.user.domain.User;
import com.assessment.user.dto.JSendDto;
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

import java.util.List;

@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAll(){
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.getForEntity("http://localhost:"+port+"/users", JSendDto.class);

       Object object = jSendDtoResponseEntity.getBody().getData().get("Users");
       List<User> userList = objectMapper.convertValue(object, new TypeReference<List<User>>() {});

        Assertions.assertTrue(jSendDtoResponseEntity.getStatusCode()== HttpStatus.OK && userList.size()==3);

    }

    @Test
    public void testGet(){
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.getForEntity("http://localhost:"+port+"/users/"+1,JSendDto.class);

        Object object = jSendDtoResponseEntity.getBody().getData().get("User");
        User user = objectMapper.convertValue(object, new TypeReference<User>() {});
        Assertions.assertTrue(user!=null && jSendDtoResponseEntity.getStatusCode()==HttpStatus.OK);
    }

    @Test
    public void testPost(){
        User user = new User("TestFName","TestLName");
        ResponseEntity<JSendDto> jSendDtoResponseEntity =
                testRestTemplate.postForEntity("http://localhost:"+port+"/users",user,JSendDto.class);
        Object object = jSendDtoResponseEntity.getBody().getData().get("Saved User");
        User savedUser = objectMapper.convertValue(object, new TypeReference<User>() {});
        Assertions.assertTrue(savedUser!=null && jSendDtoResponseEntity.getStatusCode()==HttpStatus.CREATED);
    }
}
