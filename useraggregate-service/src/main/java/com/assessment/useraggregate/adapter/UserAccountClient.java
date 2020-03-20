package com.assessment.useraggregate.adapter;

import java.util.HashMap;
import java.util.List;

public interface UserAccountClient {
    List<UserModel> getUsers();
    List<AccountModel> getUserAccounts(long userId);
    HashMap<Object,Object> getUserAndAccounts();
}
