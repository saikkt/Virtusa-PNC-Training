package com.assessment.useraggregate.adapter;

import java.util.List;

public interface UserAccountClient {
    List<UserModel> getUsers();
    List<AccountModel> getAccountsById(long userId);
    List<UserAndAccountModel> getUserAndAccounts();
    UserAndAccountModel saveUserAndAccount(UserAndAccountModel userAndAccountModel);
}
