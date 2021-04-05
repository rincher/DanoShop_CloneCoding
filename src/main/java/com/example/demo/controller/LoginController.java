package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/login")
public class LoginController {

    @GetMapping
    public String login(HttpSession session, HttpServletRequest httpServletRequest){
        System.out.println(session.getId());

        return "login";
    }

}