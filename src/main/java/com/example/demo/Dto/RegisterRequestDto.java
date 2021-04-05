package com.example.demo.Dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private boolean admin = false;
    private String adminToken = "";
}