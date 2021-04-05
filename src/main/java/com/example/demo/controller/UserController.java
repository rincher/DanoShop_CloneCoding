package com.example.demo.controller;

import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.domain.User;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final RegisterService registerService;

    @PostMapping("/api/user")
    public Optional<User> findOne(@RequestBody Long id) {
        return userService.findOne(id);
    }

    @PutMapping("/api/users/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody RegisterRequestDto requestDto) {
        userService.update(id, requestDto);
        return id;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/users")
    public List<User> getUsersforAdmin() {
        return userService.getUsers();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //render
    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    @PostMapping("/api/signup")
    public void registerUser(@RequestBody RegisterRequestDto requestDto) {
        registerService.registerUser(requestDto);
    }
}
