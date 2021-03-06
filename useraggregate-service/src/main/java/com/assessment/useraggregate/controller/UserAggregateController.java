package com.assessment.useraggregate.controller;

import com.assessment.useraggregate.adapter.UserAccountClient;
import com.assessment.useraggregate.adapter.UserAndAccountModel;
import com.assessment.useraggregate.dto.JSendDto;
import com.assessment.useraggregate.dto.JSendStatus;
import com.assessment.useraggregate.service.UserAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseEntity<JSendDto> getByUserId(@PathVariable(name = "userId") long userId){
        JSendDto jSendDto = new JSendDto();
        jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
        jSendDto.getData().put("User and Accounts", userAccountClient.getUserAndAccountsById(userId));
        return ResponseEntity.ok(jSendDto);
    }

    @PostMapping
    public ResponseEntity<JSendDto> saveUserAndAccounts(@RequestBody UserAndAccountModel userAndAccountModel){
        UserAndAccountModel savedUserAndAccountModel = userAccountClient.saveUserAndAccount(userAndAccountModel);
        JSendDto jSendDto = new JSendDto();
        if(savedUserAndAccountModel!=null){
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
            jSendDto.getData().put("UserAndAccount",savedUserAndAccountModel);
            return new ResponseEntity<>(jSendDto, HttpStatus.CREATED);
        }
        else {
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("Unable to create UserAndAccount",jSendDto);
            return ResponseEntity.unprocessableEntity().body(jSendDto);
        }
    }
}
