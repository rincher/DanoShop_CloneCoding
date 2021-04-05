package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    //Render
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

}