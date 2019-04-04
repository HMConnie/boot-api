package com.sgcai.boot.api.bootapi.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcai.boot.api.bootapi.anno.NeedLogin;
import com.sgcai.boot.service.UserService;
import com.sgcai.boot.to.UserTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/bankand")
public class BankAndController {


    @Reference(version = "1.0.0")
    UserService userService;

    @GetMapping(value = "500.html")
    public String default500() {
        return "500";
    }

    @GetMapping(value = "404.html")
    public String default404() {
        return "404";
    }

    @GetMapping(value = "login.html")
    public String login() {
        return "login";
    }

    @GetMapping(value = "index.html")
    @NeedLogin
    public String index(UserTO user, Model model) {
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping(value = "content.html")
    @NeedLogin
    public String content() {
        return "content";
    }

    @GetMapping(value = "content_add.html")
    @NeedLogin
    public String contentAdd() {
        return "content_add";
    }

    @GetMapping(value = "tag.html")
    @NeedLogin
    public String tag() {
        return "tag";
    }

    @GetMapping(value = "user.html")
    @NeedLogin
    public String user(Model model) {
        List<UserTO> list = userService.findAll();
        model.addAttribute("users", list);
        return "user";
    }

    @GetMapping(value = "user_search.html")
    @NeedLogin
    public String userSearch() {
        return "user_search";
    }


}
