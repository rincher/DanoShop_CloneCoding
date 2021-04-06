package com.example.demo.controller;

import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {

    private final RegisterService registerService;
    private final UserRepository userRepository;

    @Autowired
    public RegisterController(RegisterService registerService, UserRepository userRepository){
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    //Render
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //회원가입
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody RegisterRequestDto requestDto) {
        System.out.println(requestDto.getPassword());
        System.out.println(requestDto.getUsername());
        registerService.registerUser(requestDto);
        return "redirect:/";
    }
}