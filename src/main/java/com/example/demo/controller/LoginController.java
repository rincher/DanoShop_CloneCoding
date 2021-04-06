package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

    //Render
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }
}