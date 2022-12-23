package com.mai.epidemic.service.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.constants.RedisConstants;
import com.mai.epidemic.commons.utils.IDUtil;
import com.mai.epidemic.commons.utils.RedisCache;
import com.mai.epidemic.pojo.User;
import com.mai.epidemic.service.UserService;
import com.mai.epidemic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-12-20 21:50:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisCache redisCache;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<User> list() {
        List<User> userList = null;
        String list = redisCache.getCacheObject(RedisConstants.QUERY_USER_LIST);
        if (StrUtil.isBlank(list)) {
            List<User> dbUserList = userMapper.selectList(null);
            redisCache.setCacheObject(RedisConstants.QUERY_USER_LIST, JSONUtil.toJsonStr(dbUserList));
            return dbUserList;
        } else {
             userList = JSONUtil.toBean(list, new TypeReference<List<User>>() {
             }, true);
        }
        return userList;
    }

    @Override
    public User addUser(User user) {
        IDUtil idUtil = new IDUtil();
        Date date = new Date();
        user.setUid(idUtil.FastUid());
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.addUser(user);
        return user;
    }
}




