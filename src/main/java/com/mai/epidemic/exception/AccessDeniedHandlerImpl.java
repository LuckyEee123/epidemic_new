package com.mai.epidemic.exception;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.HttpCode;
import com.mai.epidemic.commons.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String error = JSONUtil.toJsonStr(Result.error(HttpCode.FORBIDDEN, "权限不足"));
        WebUtils.renderString(response, error);
    }
}
