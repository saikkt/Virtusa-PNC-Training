package com.assessment.user.service;

import com.assessment.user.dto.JSendStatus;
import com.assessment.user.dto.JSendDto;
import com.assessment.user.domain.User;
import com.assessment.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public JSendDto findAll(){

        List<User> userList = userRepository.findAll();

        JSendDto jSendDto = new JSendDto();
        jSendDto.getData().put("users",userList);
        jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());

        return jSendDto;
    }

    public JSendDto find(long id)  {

        Optional<User> optionalUser = userRepository.findById(id);
        JSendDto jSendDto = new JSendDto();
        if(!optionalUser.isPresent()){
            jSendDto.setStatus(JSendStatus.FAIL.toString().toLowerCase());
            jSendDto.getData().put("User ID Not Found",String.valueOf(id));
            return jSendDto;
        }
        else {
            jSendDto.getData().put("user",optionalUser.get());
            jSendDto.setStatus(JSendStatus.SUCCESS.toString().toLowerCase());
           return jSendDto;
        }

    }
}
