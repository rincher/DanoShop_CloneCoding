package com.example.demo.controller;

import com.example.demo.Dto.AuthenticationRequestDto;
import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.domain.AuthenticationResponse;
import com.example.demo.domain.User;
import com.example.demo.filter.JwtRequestFilter;
import com.example.demo.service.RegisterService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public Optional<User> findOne(@RequestBody Long id){
        return userService.findOne(id);
    }

    @PutMapping("/api/users/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody RegisterRequestDto requestDto){
        userService.update(id, requestDto);
        return id;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/users")
    public List<User> getUsersforAdmin(){
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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
        System.out.println(requestDto.getPassword());
        System.out.println(requestDto.getUsername());
        AuthenticationResponse token = tokenService.createToken(requestDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping ("/api/getUser")
    public User user (@RequestBody String token) throws Exception {
        return jwtRequestFilter.doFilter(token);
    }
}