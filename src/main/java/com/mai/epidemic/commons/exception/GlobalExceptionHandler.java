package com.mai.epidemic.commons.exception;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mai.epidemic.commons.exception.ServiceException;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg;
        try {
            msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } catch (Exception be) {
            msg = "";
        }
        log.warn("参数校验错误", e);
        return Result.error(msg);
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e) {
        String code = e.getCode();
        if (StrUtil.isNotBlank(code)) {
            return Result.error(code, e.getMessage());
        }
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = SystemException.class)
    public Result sysExceptionError(SystemException e) {
        log.error("系统错误", e);
        return Result.error("系统错误");
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e) {
        log.error("未知错误", e);
        return Result.error("未知错误");
    }

    /**
     * 处理AccessDeniedHandler无权限异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, AccessDeniedException e){
        log.error("权限不足，无法访问", e.getMessage());
        return Result.error(HttpCode.FORBIDDEN,"权限不足");
    }

}
