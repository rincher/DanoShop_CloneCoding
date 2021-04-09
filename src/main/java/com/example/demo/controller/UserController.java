package com.example.demo.controller;

import com.example.demo.Dto.AuthenticationRequestDto;
import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.domain.AuthenticationResponse;
import com.example.demo.domain.User;
import com.example.demo.filter.JwtRequestFilter;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final JwtRequestFilter jwtRequestFilter;

    @PostMapping("/api/user")
    public Optional<User> findOne(@RequestBody String username){
        return userService.findOne(username);
    }

    //사용자 정보 수정
    @PutMapping("/api/userEdit")
    public void updateUser(@RequestBody RegisterRequestDto requestDto) {
        userService.update(requestDto);
    }

    //사용자 삭제
    @DeleteMapping("/api/unregister/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    //Login을 통해서 사용자 정보와 JWT Token을 발급
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<Message> createToken(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
        // 토큰 생성하는 서비스
        AuthenticationResponse token = tokenService.createToken(requestDto);
        //입력받은 사용자를 찾는 부분
        Optional<User> getUser = userService.findOne(requestDto.getUsername());
        //메세지를 만들어서 여러 responsebody를 내려줄 수 있도록 한다.
        Message message = Message.builder()
                .message1(token)
                .message2(getUser)
                .build();
        //200ok 일 경우에만 responsebody에 넣어주는 부분
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    //로그인된 사용자 정보를 돌려주는 부분
    @PostMapping ("/api/getUser")
    public User user (@RequestBody String token) throws Exception {
        return jwtRequestFilter.doFilter(token);
    }

    // Admin 전용
    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/users")
    public List<User> getUsersforAdmin(){
        return userService.getUsers();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/admin/users/{username}")
    public void deleteUserAdmin(@PathVariable String username) {
        userService.deleteUser(username);
    }

}