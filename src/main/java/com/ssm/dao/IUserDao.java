package com.ssm.dao;

import com.ssm.model.Account;
import com.ssm.model.User;


public interface IUserDao {
    User selectUser(long id);

    User roleDisplay(String role);

    Boolean addUser(Account account);
}
