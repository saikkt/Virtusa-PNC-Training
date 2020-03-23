package com.assessment.useraggregate.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class UserAndAccountModel {

    @JsonProperty(value = "user")
    private UserModel userModel;
    @JsonProperty(value = "account")
    private List<AccountModel> accountModels;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<AccountModel> getAccountModels() {
        return accountModels;
    }

    public void setAccountModels(List<AccountModel> accountModels) {
        this.accountModels = accountModels;
    }
}
