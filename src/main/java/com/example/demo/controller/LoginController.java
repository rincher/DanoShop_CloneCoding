package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> user, @AuthenticationPrincipal UserDetails userDetails){
        User member = userRepository.findByUsername(user.get("username")).orElseThrow(()-> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        System.out.println(userDetails);
        return jwtTokenProvider.createToken(member.getUsername(), member.getPassword());
    }
}