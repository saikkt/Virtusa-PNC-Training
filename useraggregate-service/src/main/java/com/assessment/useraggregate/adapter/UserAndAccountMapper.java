package com.assessment.useraggregate.adapter;

import java.util.List;

public class UserAndAccountMapper {
    public static UserAndAccountModel userAndAccountMapper(UserModel userModel, List<AccountModel> accountModels){
        UserAndAccountModel userAndAccountModel = new UserAndAccountModel();
//        userAndAccountModel.setId(userModel.getId());
//        userAndAccountModel.setFirstName(userModel.getFirstName());
//        userAndAccountModel.setLastName(userModel.getLastName());
//        userAndAccountModel.setAddress(userModel.getAddress());
//        userAndAccountModel.setEmailAddress(userModel.getEmailAddress());
//        userAndAccountModel.setPhoneNumber(userModel.getPhoneNumber());
//        userAndAccountModel.setSsn(userModel.getSsn());
//        userAndAccountModel.setAccountModels(accountModels);
        userAndAccountModel.setUserModel(userModel);
        userAndAccountModel.setAccountModels(accountModels);
        return userAndAccountModel;
    }

    public static UserModel toUserModel(UserAndAccountModel userAndAccountModel){
        UserModel userModel = new UserModel();
//        userModel.setId(userAndAccountModel.getId());
//        userModel.setFirstName(userAndAccountModel.getFirstName());
//        userModel.setLastName(userAndAccountModel.getLastName());
//        userModel.setEmailAddress(userAndAccountModel.getEmailAddress());
//        userModel.setAddress(userAndAccountModel.getAddress());
//        userModel.setPhoneNumber(userAndAccountModel.getPhoneNumber());
//        userModel.setSsn(userAndAccountModel.getSsn());

        return userAndAccountModel.getUserModel();
    }

    public static List<AccountModel> toAccountModel (UserAndAccountModel userAndAccountModel){
       return userAndAccountModel.getAccountModels();
    }
}
