package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDto {

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
