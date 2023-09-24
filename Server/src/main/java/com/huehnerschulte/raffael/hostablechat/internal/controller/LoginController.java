package com.huehnerschulte.raffael.hostablechat.internal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String showLoginForm(){
        return "login";
    }
}
