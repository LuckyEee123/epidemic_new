package com.mai.epidemic.exception;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.HttpCode;
import com.mai.epidemic.commons.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String error = JSONUtil.toJsonStr(Result.error(HttpCode.UNAUTHORIZED,"认证失败，请重新登录"));
        WebUtils.renderString(response, error);
    }
}
