package com.example.demo.service;

import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "jason272k";
    @Autowired
    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(RegisterRequestDto requestDto){
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        String rawPass = requestDto.getPassword();
        String email = requestDto.getEmail();
        String phone = requestDto.getPhone();
        String name = requestDto.getName();
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }
        User user = new User(username, password, rawPass, name, email, phone, role);
        userRepository.save(user);
    }
    public String encoding (String info){
        return passwordEncoder.encode(info);
    }
}