package com.sgcai.boot.api.bootapi.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcai.boot.api.bootapi.exception.CustomException;
import com.sgcai.boot.api.bootapi.utils.Constants;
import com.sgcai.boot.api.bootapi.utils.CookieUtil;
import com.sgcai.boot.service.UserService;
import com.sgcai.boot.to.TokenTO;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class ApiController {


    @Reference(version = "1.0.0")
    UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam("mobile") String mobile, @RequestParam("pwd") String pwd, HttpServletResponse resp) throws CustomException {
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(pwd)) {
            throw new CustomException("mobile or pwd is not null", "用户名或密码不能为空");
        }
        TokenTO tokenTO = userService.findUserByMobile(mobile, pwd);
        if (tokenTO == null) {
            throw new CustomException("mobile or pwd is wrong", "用户名或密码不正确");
        }
        CookieUtil.addCookie(resp, Constants.SESSION_LOGIN_USER, tokenTO.getAccessToken(), Constants.COOKIE_LOGIN_EXPIRE);
        return "redirect:/bankand/index.html";
    }
}
