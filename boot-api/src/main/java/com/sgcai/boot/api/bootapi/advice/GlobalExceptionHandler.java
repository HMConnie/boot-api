package com.sgcai.boot.api.bootapi.advice;

import com.sgcai.boot.api.bootapi.exception.AuthException;
import com.sgcai.boot.api.bootapi.exception.CustomException;
import com.sgcai.boot.api.bootapi.utils.ResponseUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception e) {
        LOG.error("internal_server_error", e);
        return ResponseUtils.toErrorResponse("internal_server_error", "服务器内部错误");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView noHandlerFoundException(NoHandlerFoundException e) {
        return new ModelAndView("redirect:/bankand/404.html");
    }

    @ExceptionHandler(AuthException.class)
    public ModelAndView authException(AuthException e) {
        return new ModelAndView("redirect:/bankand/login.html");
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleCustomException(CustomException e) {
        return ResponseUtils.toErrorResponse(e.getMessage(), e.getMsgText());
    }
}
