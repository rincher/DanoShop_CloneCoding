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