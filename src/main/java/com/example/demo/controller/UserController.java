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

    @PutMapping("/api/userEdit")
    public void updateUser(@RequestBody RegisterRequestDto requestDto) {
        userService.update(requestDto);
    }

    @DeleteMapping("/api/unregister/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

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

    //render
    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<Message> createToken(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
        AuthenticationResponse token = tokenService.createToken(requestDto);
        Optional<User> getUser = userService.findOne(requestDto.getUsername());
        Message message = Message.builder()
                .message1(token)
                .message2(getUser)
                .build();
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @PostMapping ("/api/getUser")
    public User user (@RequestBody String token) throws Exception {
        return jwtRequestFilter.doFilter(token);
    }
}