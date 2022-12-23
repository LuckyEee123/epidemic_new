package com.mai.epidemic.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.mai.epidemic.commons.constants.RedisConstants;
import com.mai.epidemic.commons.exception.ServiceException;
import com.mai.epidemic.commons.utils.JwtUtil;
import com.mai.epidemic.commons.utils.RedisCache;
import com.mai.epidemic.pojo.dto.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // 获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            // 需要return是为了防止filterChain再往回走一遍验证
            return;
        }
        // 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
                   userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("token非法");
        }
        // 从redis中获取用户信息
        String redisKey = RedisConstants.LOGIN_USER_KEY + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (ObjectUtil.isNull(loginUser)) {
            throw new ServiceException("请重新登陆！");
        }
        // 存入SecurityContextHolder
        // TODO 获取权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
