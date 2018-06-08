package com.ssm.service.impl;

import com.ssm.dao.AccountDao;
import com.ssm.model.Account;
import com.ssm.model.GeoCoordMap;
import com.ssm.model.migrate;
import com.ssm.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Override
    public Boolean addUser(Account account) {
        return this.accountDao.addUser(account);
    }

    @Override
    public Account selectAccount(Integer id) {
        return this.accountDao.selectAccount(id);
    }

    @Override
    public Account checkIn(String username) {return  this.accountDao.selectAccountByName(username); }

    @Override
    public List<GeoCoordMap> getMapInfo() {
        return this.accountDao.selectMapInfo();
    }

    @Override
    public Boolean update(String cityName, String cityLocation) {
        return this.accountDao.updateMapInfo(cityName,cityLocation);
    }

    @Override
    public List<migrate> selectBjInfo() {
        return this.accountDao.selectBjInfo();
    }

    @Override
    public Boolean setState(String randomcode) {
        return this.accountDao.updateState(randomcode);
    }

    @Override
    public String findpassWords(String username) {
        return this.accountDao.findpassWords(username);
    }


}