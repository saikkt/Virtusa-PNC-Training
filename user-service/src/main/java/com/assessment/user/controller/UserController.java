package com.assessment.user.controller;

import com.assessment.user.domain.User;
import com.assessment.user.dto.JSendDto;
import com.assessment.user.dto.JSendStatus;
import com.assessment.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<JSendDto> getAll(){
        JSendDto jSendDto = new JSendDto();

        List<User> userList = userService.findAll();
//                .map(user -> UserMapper.toModel(user))
//                .collect(Collectors.toList());

        if(userList.isEmpty()){
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("Users","Not Found");
            return new ResponseEntity<>(jSendDto,HttpStatus.NO_CONTENT);
        }
        else {
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
            jSendDto.getData().put("Users",userList);
            return ResponseEntity.ok(jSendDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSendDto> get(@PathVariable(name = "id") long id) {
        Optional<User> user = userService.find(id);
        JSendDto jSendDto = new JSendDto();
        if(user.isPresent()){
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
            jSendDto.getData().put("User",user.get());
            return ResponseEntity.ok(jSendDto);
        }
        else {
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("User Not Found",String.valueOf(id));
            return new ResponseEntity<>(jSendDto, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JSendDto> post(@RequestBody User user){
       JSendDto jSendDto = new JSendDto();
       User savedUser = userService.save(user);
       if(savedUser!=null){
           jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
           jSendDto.getData().put("Saved User",savedUser);
           return ResponseEntity.created(URI.create("/users/"+savedUser.getId())).body(jSendDto);
       }
       else {
           jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
           jSendDto.getData().put("Unable to Save User",user);
           return ResponseEntity.unprocessableEntity().body(jSendDto);
       }
    }
}
