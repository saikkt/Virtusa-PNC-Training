package com.assessment.user.api;

import com.assessment.user.domain.User;
import com.assessment.user.dto.UserModel;

public class UserMapper {

    public static UserModel toModel(User entity){
        UserModel userModel = new UserModel();
        userModel.setId(entity.getId());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());
        userModel.setAddress(entity.getAddress());
        userModel.setPhoneNumber(entity.getPhoneNumber());
        userModel.setSsn(entity.getSsn());

        return userModel;
    }
}
