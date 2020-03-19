package com.assessment.account.api;

import com.assessment.account.domain.Account;
import com.assessment.account.dto.JSendDto;
import com.assessment.account.dto.JSendStatus;
import com.assessment.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @GetMapping
//    public ResponseEntity<JSendDto> getAll(){
//        return ResponseEntity.ok(accountService.findAll());
//    }

    @GetMapping
    public ResponseEntity<JSendDto> get(@RequestParam long userId){
        List<Account> accounts = accountService.findByUserId(userId);
        JSendDto jSendDto = new JSendDto();

        if(accounts.isEmpty()||accounts==null){
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("Accounts Not Found For User Id",String.valueOf(userId));
            return new ResponseEntity<>(jSendDto, HttpStatus.NOT_FOUND);
        }
        else {
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
            jSendDto.getData().put("Accounts",accounts);
            return ResponseEntity.ok(jSendDto);
        }
    }

    @PostMapping
    public ResponseEntity<JSendDto> save(@RequestBody Account account){
        Account savedAccount = accountService.save(account);
        JSendDto jSendDto = new JSendDto();
        if(savedAccount!=null){
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
            jSendDto.getData().put("Saved Account",savedAccount);
            return ResponseEntity.created(URI.create("/accounts/"+savedAccount.getId())).body(jSendDto);
        }
        else {
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("Unable to save account",account);
            return new ResponseEntity<>(jSendDto,HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
