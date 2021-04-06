package com.example.demo.service;

import com.example.demo.Dto.RegisterRequestDto;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(RegisterRequestDto requestDto){
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        user.update(requestDto);
    }

    public Optional<User> findOne(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getUsers(){
        return userRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public void deleteUser(String username){
        userRepository.deleteByUsername(username);
    }

}