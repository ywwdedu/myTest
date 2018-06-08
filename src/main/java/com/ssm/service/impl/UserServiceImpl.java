package com.ssm.service.impl;
import com.ssm.dao.AccountDao;
import com.ssm.dao.IUserDao;
import com.ssm.model.Account;
import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")

public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;
    @Resource
    private AccountDao accountDao;

    public User selectUser(long userId){

        return this.userDao.selectUser(userId);
    }

    public User roleDisplay(String role){
        return this.userDao.roleDisplay(role);
    }


    public Boolean addUser(Account account) {
        return this.accountDao.addUser(account);
    }


}