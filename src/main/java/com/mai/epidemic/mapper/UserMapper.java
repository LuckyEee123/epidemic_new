package com.mai.epidemic.mapper;

import com.mai.epidemic.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2022-12-20 21:50:37
* @Entity com.mai.epidemic.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void addUser(User user);
}




