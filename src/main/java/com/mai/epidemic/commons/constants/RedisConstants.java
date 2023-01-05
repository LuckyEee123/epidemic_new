package com.mai.epidemic.commons.constants;

import java.util.Random;

public interface RedisConstants {

    String QUERY_USER_LIST = "userList:";
    String LOGIN_USER_KEY = "loginUser:";

    String EMPTY_CACHE = "{}";

    // 过期时间添加随机值 解决缓存击穿
    Integer LOGIN_USER_TIMEOUT = 60 * 60 * 24 * 7 + (new Random().nextInt(20) * 30);
    Integer CACHE_TIMEOUT = 60 * 60 * 24 + (new Random().nextInt(50) * 50);
    Integer EMPTY_CACHE_TIMEOUT = 60 + new Random().nextInt(15);



}
