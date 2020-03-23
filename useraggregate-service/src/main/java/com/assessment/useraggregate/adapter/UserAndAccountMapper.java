package com.assessment.useraggregate.adapter;

import java.util.List;

public class UserAndAccountMapper {
    public static UserAndAccountModel userAndAccountMapper(UserModel userModel, List<AccountModel> accountModels){
        UserAndAccountModel userAndAccountModel = new UserAndAccountModel();
        userAndAccountModel.setId(userModel.getId());
        userAndAccountModel.setFirstName(userModel.getFirstName());
        userAndAccountModel.setLastName(userModel.getLastName());
        userAndAccountModel.setAddress(userModel.getAddress());
        userAndAccountModel.setEmailAddress(userModel.getEmailAddress());
        userAndAccountModel.setPhoneNumber(userModel.getPhoneNumber());
        userAndAccountModel.setSsn(userModel.getSsn());
        userAndAccountModel.setAccountModels(accountModels);

        return userAndAccountModel;
    }
}
