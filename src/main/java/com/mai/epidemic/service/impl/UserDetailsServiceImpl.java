package com.mai.epidemic.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mai.epidemic.commons.exception.ServiceException;
import com.mai.epidemic.mapper.MenuMapper;
import com.mai.epidemic.mapper.UserMapper;
import com.mai.epidemic.pojo.User;
import com.mai.epidemic.pojo.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);

        if (ObjectUtil.isNull(user)) {
            throw new ServiceException("用户名或密码错误！");
        }
        // 查询对应的权限信息

        List<String> permsList = menuMapper.selectPermsByUserId(user.getId());

        // 把数据封装成UserDetails返回
        return new LoginUser(user, permsList);

    }
}
