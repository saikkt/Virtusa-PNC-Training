package com.assessment.useraggregate.api;

import com.assessment.useraggregate.adapter.UserAccountClient;
import com.assessment.useraggregate.dto.JSendDto;
import com.assessment.useraggregate.dto.JSendStatus;
import com.assessment.useraggregate.service.UserAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useraggregate")
public class UserAggregateController {

    @Autowired
    private UserAggregateService userAggregateService;
    @Autowired
    private UserAccountClient userAccountClient;

    @GetMapping
    public ResponseEntity<JSendDto> getAll(){
        JSendDto jSendDto = new JSendDto();
        jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
        jSendDto.getData().put("User and Accounts", userAccountClient.getUserAndAccounts());
        return ResponseEntity.ok(jSendDto);
    }
}
