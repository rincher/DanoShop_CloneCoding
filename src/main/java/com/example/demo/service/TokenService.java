package com.example.demo.service;

import com.example.demo.Dto.AuthenticationRequestDto;
import com.example.demo.domain.AuthenticationResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final JwtUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final RegisterService registerService;

    public TokenService(JwtUtil jwtTokenUtil, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,UserDetailsServiceImpl userDetailsService, RegisterService registerService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.registerService = registerService;
    }
    //토큰 생성하는 서비스 부분
    //로그인창에서 입력받은 사용자이름과 비밀번호를 User DB에서 찾아서 확인하고 만약에 맞지 않으면 BadCredentialException을 반환하고
    //일치하면 jwtTokenUtill을 통해서 토큰을 만들고, 그리고 AuthenticationResponse를 통해 토큰 전달.
    public AuthenticationResponse createToken(AuthenticationRequestDto requestDto) throws Exception {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        try{
            Optional<User> id_found = userRepository.findByUsername(username);
            if(id_found.isPresent()){
                if(password.equals(user.getRawPass())){
                    final String jwt = jwtTokenUtil.generateToken(username);
                    return new AuthenticationResponse(jwt);
                }
            }
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        throw new IllegalArgumentException("Invalid information");
    }
}