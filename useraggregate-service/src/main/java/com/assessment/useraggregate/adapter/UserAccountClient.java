package com.assessment.useraggregate.adapter;

import java.util.List;

public interface UserAccountClient {
    UserModel getUserById(long userId);
    List<UserModel> getUsers();
    List<AccountModel> getAccountsById(long userId);
    List<UserAndAccountModel> getUserAndAccounts();
    UserAndAccountModel getUserAndAccountsById(long userId);
    UserAndAccountModel saveUserAndAccount(UserAndAccountModel userAndAccountModel);

}
