package com.ssm.dao;

import com.ssm.model.Account;
import com.ssm.model.GeoCoordMap;
import com.ssm.model.migrate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao {
    Boolean addUser(Account account);

    Account selectAccount(Integer id);

    Account selectAccountByName(String username);

    List<GeoCoordMap> selectMapInfo();

    Boolean updateMapInfo(@Param("cityName") String cityName, @Param("cityLocation") String cityLocation);

    List<migrate> selectBjInfo();

    Boolean updateState(@Param("randomcode") String randomcode);

    String findpassWords(String username);
}
