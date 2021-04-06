package com.example.demo.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement
public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }
}