package com.mai.epidemic.service;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.User;

public interface LoginService {

    /**
     * 登录
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 注销
     * @return
     */
    Result logout();
}
