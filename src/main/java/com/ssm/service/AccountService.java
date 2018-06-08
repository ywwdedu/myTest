package com.ssm.service;

import com.ssm.model.Account;
import com.ssm.model.GeoCoordMap;
import com.ssm.model.migrate;

import java.util.List;

public interface AccountService {
    Boolean addUser(Account account);

    Account selectAccount(Integer id);

    Account checkIn(String username);

    List<GeoCoordMap>getMapInfo();

    Boolean update(String cityName,String cityLocation);

    List<migrate> selectBjInfo();

    Boolean setState(String randomcode);

    String findpassWords(String username);
}