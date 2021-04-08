package com.example.demo.controller;

import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterService registerService;
    private final UserRepository userRepository;

    @Autowired
    public RegisterController(RegisterService registerService, UserRepository userRepository){
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    //회원가입
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody RegisterRequestDto requestDto) {
        registerService.registerUser(requestDto);
    }
}