package com.assessment.user.service;

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


    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public Optional<User> find(long id)  {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }

    public User save(User user){
        User savedUser =  userRepository.save(user);
        return savedUser;
    }
}
