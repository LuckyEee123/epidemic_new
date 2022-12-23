package com.mai.epidemic.service;

import com.mai.epidemic.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-12-20 21:50:37
*/
public interface UserService extends IService<User> {

    User addUser(User user);
}
