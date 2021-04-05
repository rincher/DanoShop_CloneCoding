package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/login")
    public String login(@RequestBody Map<String, String> user){
        User member = userRepository.findByUsername(user.get("username")).orElseThrow(()-> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRole());
    }
}