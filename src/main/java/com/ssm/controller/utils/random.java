package com.ssm.controller.utils;

import java.util.UUID;

/**
 *生成随机字符串
 *
 *
 */
public class random {
    public static String getUUIDUtils(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
