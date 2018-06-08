package com.ssm.service;

import com.ssm.model.Account;
import com.ssm.model.User;

public interface IUserService {
     User selectUser(long userId);

     User roleDisplay(String role);

     Boolean addUser(Account account);
}

