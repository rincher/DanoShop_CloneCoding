package com.example.demo.controller;

import com.example.demo.domain.AuthenticationResponse;
import com.example.demo.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
//여기는 메세지를 만들기 위해서 만들어진 class
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    private AuthenticationResponse message1;
    private Optional message2;

    @Builder
    public Message(AuthenticationResponse message1, Optional message2){
        this.message1 = message1;
        this.message2 = message2;

    }
}
