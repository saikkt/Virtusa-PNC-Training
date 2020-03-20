package com.assessment.useraggregate.service;

import com.assessment.useraggregate.dto.JSendDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAggregateService {

    private RestTemplate restTemplate = new RestTemplate();

    public JSendDto getUsers(){
        ResponseEntity<JSendDto> jSendDtoResponseEntity = restTemplate.getForEntity("http://localhost:9000/users",JSendDto.class);
        JSendDto jSendDto = jSendDtoResponseEntity.getBody();
        return jSendDto;
    }

//    public JSendDto getAccounts(){
//
//    }

}
