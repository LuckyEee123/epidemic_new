package com.mai.epidemic.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.RedisConstants;
import com.mai.epidemic.commons.exception.ServiceException;
import com.mai.epidemic.commons.utils.JwtUtil;
import com.mai.epidemic.commons.utils.RedisCache;
import com.mai.epidemic.pojo.User;
import com.mai.epidemic.pojo.dto.LoginUser;
import com.mai.epidemic.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;


    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken
        = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // authenticationManager进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证未通过
        if (ObjectUtil.isNull(authenticate)) {
            throw new ServiceException("登录失败");
        }
        // 认证通过，生成JWT
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUid().toString();
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("loginUser", loginUser);
        // 把完整的用户信息存入redis
        redisCache.setCacheObject(RedisConstants.LOGIN_USER_KEY + userId, loginUser,RedisConstants.LOGIN_USER_TIMEOUT, TimeUnit.SECONDS);

        return Result.success("登录成功！", map);
    }

    @Override
    public Result logout() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser  loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getUid();
        String redisKey = RedisConstants.LOGIN_USER_KEY + userId;
        // 删除redis中的数据
        redisCache.deleteObject(redisKey);

        return Result.success("注销成功！");
    }
}
