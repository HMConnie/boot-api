package com.sgcai.boot.api.bootapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("foreground")
public class HomeController {

    @GetMapping("home.html")
    public String home() {
        return "home";
    }
}
