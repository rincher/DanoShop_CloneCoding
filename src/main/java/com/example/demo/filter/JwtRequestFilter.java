package com.example.demo.filter;

import com.example.demo.domain.User;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public User doFilter(String token) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        String[] payloadChunks = payload.split(",");
        int length = payloadChunks[0].length()-1;
        String username = payloadChunks[0].substring(8, length);
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return new User(user.getUsername(), user.getPassword(), user.getRawPass(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }
}